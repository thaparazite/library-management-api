package com.library.api.mapper;

import com.library.api.dto.request.CreateBookRequest;
import com.library.api.dto.request.UpdateBookRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.model.Book;
import com.library.api.model.Library;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Book entity and DTOs
 * Handles the separation between internal model and API representation
 */
@Component
public class BookMapper {

    /**
     * Convert CreateBookRequest DTO to Book entity
     * Note: Library must be set separately by the service layer
     */
    public Book toEntity(CreateBookRequest request) {
        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setPublicationDate(request.getPublicationDate());
        book.setAcquisitionDate(request.getAcquisitionDate());
        book.setPages(request.getPages());
        // Library is set by the service layer
        return book;
    }

    /**
     * Update existing Book entity from UpdateBookRequest DTO
     * Note: Library must be set separately by the service layer
     */
    public void updateEntity(Book book, UpdateBookRequest request) {
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setPublicationDate(request.getPublicationDate());
        book.setAcquisitionDate(request.getAcquisitionDate());
        book.setPages(request.getPages());
        // Library is updated by the service layer
    }

    /**
     * Convert Book entity to BookResponse DTO
     * Only exposes safe, public fields
     */
    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .publicationDate(book.getPublicationDate())
                .acquisitionDate(book.getAcquisitionDate())
                .pages(book.getPages())
                .libraryId(book.getLibrary() != null ? book.getLibrary().getId() : null)
                .libraryName(book.getLibrary() != null ? book.getLibrary().getName() : null)
                .build();
    }
}
