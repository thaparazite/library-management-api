package com.library.api.service;

import com.library.api.dto.request.CreateBookRequest;
import com.library.api.dto.request.UpdateBookRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

/**
 * Service interface for Book business logic
 * Defines the contract for book operations
 */
public interface BookService {

    /**
     * Get all books (paginated)
     */
    PageResponse<BookResponse> getAllBooks(Pageable pageable);

    /**
     * Get book by ID
     */
    BookResponse getBookById(Long id);

    /**
     * Create new book
     */
    BookResponse createBook(CreateBookRequest request);

    /**
     * Update existing book
     */
    BookResponse updateBook(Long id, UpdateBookRequest request);

    /**
     * Delete book
     */
    void deleteBook(Long id);

    /**
     * Get books by publication date range (paginated)
     * Demonstrates date filtering requirement
     */
    PageResponse<BookResponse> getBooksByPublicationDateRange(
            LocalDate startDate, 
            LocalDate endDate, 
            Pageable pageable
    );

    /**
     * Get books by acquisition date range (paginated)
     */
    PageResponse<BookResponse> getBooksByAcquisitionDateRange(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    /**
     * Search books by author (paginated)
     */
    PageResponse<BookResponse> searchBooksByAuthor(String author, Pageable pageable);

    /**
     * Get books by genre (paginated)
     */
    PageResponse<BookResponse> getBooksByGenre(String genre, Pageable pageable);
}
