package com.library.api.service;

import com.library.api.dto.request.CreateBookRequest;
import com.library.api.dto.request.UpdateBookRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.dto.response.PageResponse;
import com.library.api.exception.BadRequestException;
import com.library.api.mapper.BookMapper;
import com.library.api.model.Book;
import com.library.api.model.Library;
import com.library.api.repository.BookRepository;
import com.library.api.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Implementation of BookService
 * Extends BaseService for DRY validation logic
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookServiceImpl extends BaseService implements BookService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookResponse> getAllBooks(Pageable pageable) {
        log.debug("Fetching all books with pagination: {}", pageable);
        
        Page<BookResponse> bookPage = bookRepository.findAll(pageable)
                .map(bookMapper::toResponse);
        
        return PageResponse.of(bookPage);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        log.debug("Fetching book with ID: {}", id);
        
        Book book = findByIdOrThrow(bookRepository, id, "Book");
        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponse createBook(CreateBookRequest request) {
        log.debug("Creating new book: {}", request.getTitle());
        
        validateIsbnUniqueness(request.getIsbn(), null);
        validateAcquisitionDate(request.getPublicationDate(), request.getAcquisitionDate());
        
        Library library = findByIdOrThrow(libraryRepository, request.getLibraryId(), "Library");
        
        Book book = bookMapper.toEntity(request);
        book.setLibrary(library);
        
        Book savedBook = bookRepository.save(book);
        
        log.info("Created book with ID: {} for library ID: {}", savedBook.getId(), library.getId());
        return bookMapper.toResponse(savedBook);
    }

    @Override
    public BookResponse updateBook(Long id, UpdateBookRequest request) {
        log.debug("Updating book with ID: {}", id);
        
        Book book = findByIdOrThrow(bookRepository, id, "Book");
        
        validateIsbnUniqueness(request.getIsbn(), id);
        validateAcquisitionDate(request.getPublicationDate(), request.getAcquisitionDate());
        
        if (!book.getLibrary().getId().equals(request.getLibraryId())) {
            Library library = findByIdOrThrow(libraryRepository, request.getLibraryId(), "Library");
            book.setLibrary(library);
        }
        
        bookMapper.updateEntity(book, request);
        Book updatedBook = bookRepository.save(book);
        
        log.info("Updated book with ID: {}", id);
        return bookMapper.toResponse(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        log.debug("Deleting book with ID: {}", id);
        
        Book book = findByIdOrThrow(bookRepository, id, "Book");
        bookRepository.delete(book);
        
        log.info("Deleted book with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookResponse> getBooksByPublicationDateRange(
            LocalDate startDate, 
            LocalDate endDate, 
            Pageable pageable) {
        
        log.debug("Fetching books published between {} and {}", startDate, endDate);
        
        validateDateRange(startDate, endDate);
        
        Page<BookResponse> bookPage = bookRepository
                .findByPublicationDateBetween(startDate, endDate, pageable)
                .map(bookMapper::toResponse);
        
        return PageResponse.of(bookPage);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookResponse> getBooksByAcquisitionDateRange(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {
        
        log.debug("Fetching books acquired between {} and {}", startDate, endDate);
        
        validateDateRange(startDate, endDate);
        
        Page<BookResponse> bookPage = bookRepository
                .findByAcquisitionDateBetween(startDate, endDate, pageable)
                .map(bookMapper::toResponse);
        
        return PageResponse.of(bookPage);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookResponse> searchBooksByAuthor(String author, Pageable pageable) {
        log.debug("Searching books by author: {}", author);
        
        Page<BookResponse> bookPage = bookRepository
                .findByAuthorContainingIgnoreCase(author, pageable)
                .map(bookMapper::toResponse);
        
        return PageResponse.of(bookPage);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookResponse> getBooksByGenre(String genre, Pageable pageable) {
        log.debug("Fetching books by genre: {}", genre);
        
        Page<BookResponse> bookPage = bookRepository
                .findByGenreIgnoreCase(genre, pageable)
                .map(bookMapper::toResponse);
        
        return PageResponse.of(bookPage);
    }

    /**
     * Private helper method to validate ISBN uniqueness (DRY principle)
     */
    private void validateIsbnUniqueness(String isbn, Long excludeBookId) {
        if (bookRepository.existsByIsbn(isbn)) {
            // If updating, check if the ISBN belongs to the same book
            if (excludeBookId != null) {
                bookRepository.findByIsbn(isbn).ifPresent(existingBook -> {
                    if (!existingBook.getId().equals(excludeBookId)) {
                        throw new BadRequestException("Book with ISBN " + isbn + " already exists");
                    }
                });
            } else {
                throw new BadRequestException("Book with ISBN " + isbn + " already exists");
            }
        }
    }
}
