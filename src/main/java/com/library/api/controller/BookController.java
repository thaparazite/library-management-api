package com.library.api.controller;

import com.library.api.dto.request.CreateBookRequest;
import com.library.api.dto.request.UpdateBookRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.dto.response.PageResponse;
import com.library.api.service.BookService;
import com.library.api.util.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * REST Controller for Book endpoints
 * Handles HTTP requests for book operations
 */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    /**
     * GET /api/books
     * Get all books with optional filtering (paginated)
     */
    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre) {
        
        log.info("GET /api/books - page: {}, size: {}, startDate: {}, endDate: {}", page, size, startDate, endDate);
        
        Pageable pageable = PaginationUtils.createPageable(page, size, sort);
        
        // Apply filters using a cleaner approach
        PageResponse<BookResponse> response = getFilteredBooks(pageable, startDate, endDate, author, genre);
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/books/{id}
     * Get book by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        log.info("GET /api/books/{}", id);
        
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/books
     * Create new book
     */
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        log.info("POST /api/books - title: {}, libraryId: {}", request.getTitle(), request.getLibraryId());
        
        BookResponse response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT /api/books/{id}
     * Update existing book
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookRequest request) {
        
        log.info("PUT /api/books/{} - title: {}", id, request.getTitle());
        
        BookResponse response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/books/{id}
     * Delete book
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("DELETE /api/books/{}", id);
        
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/books/by-publication-date
     * Get books by publication date range (paginated)
     */
    @GetMapping("/by-publication-date")
    public ResponseEntity<PageResponse<BookResponse>> getBooksByPublicationDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "publicationDate,asc") String sort) {
        
        log.info("GET /api/books/by-publication-date - startDate: {}, endDate: {}", startDate, endDate);
        
        Pageable pageable = PaginationUtils.createPageable(page, size, sort);
        PageResponse<BookResponse> response = bookService.getBooksByPublicationDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/books/by-acquisition-date
     * Get books by acquisition date range (paginated)
     */
    @GetMapping("/by-acquisition-date")
    public ResponseEntity<PageResponse<BookResponse>> getBooksByAcquisitionDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "acquisitionDate,asc") String sort) {
        
        log.info("GET /api/books/by-acquisition-date - startDate: {}, endDate: {}", startDate, endDate);
        
        Pageable pageable = PaginationUtils.createPageable(page, size, sort);
        PageResponse<BookResponse> response = bookService.getBooksByAcquisitionDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Private helper method to apply filters (DRY principle)
     */
    private PageResponse<BookResponse> getFilteredBooks(
            Pageable pageable, 
            LocalDate startDate, 
            LocalDate endDate, 
            String author, 
            String genre) {
        
        if (startDate != null && endDate != null) {
            return bookService.getBooksByPublicationDateRange(startDate, endDate, pageable);
        } else if (author != null && !author.trim().isEmpty()) {
            return bookService.searchBooksByAuthor(author, pageable);
        } else if (genre != null && !genre.trim().isEmpty()) {
            return bookService.getBooksByGenre(genre, pageable);
        } else {
            return bookService.getAllBooks(pageable);
        }
    }
}
