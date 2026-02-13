package com.library.api.controller;

import com.library.api.dto.request.CreateLibraryRequest;
import com.library.api.dto.request.UpdateLibraryRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.dto.response.LibraryResponse;
import com.library.api.dto.response.PageResponse;
import com.library.api.service.LibraryService;
import com.library.api.util.PaginationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Library endpoints
 * Handles HTTP requests for library operations
 */
@RestController
@RequestMapping("/api/libraries")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

    private final LibraryService libraryService;

    /**
     * GET /api/libraries
     * Get all libraries (paginated)
     */
    @GetMapping
    public ResponseEntity<PageResponse<LibraryResponse>> getAllLibraries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {
        
        log.info("GET /api/libraries - page: {}, size: {}, sort: {}", page, size, sort);
        
        Pageable pageable = PaginationUtils.createPageable(page, size, sort);
        PageResponse<LibraryResponse> response = libraryService.getAllLibraries(pageable);
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/libraries/{id}
     * Get library by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<LibraryResponse> getLibraryById(@PathVariable Long id) {
        log.info("GET /api/libraries/{}", id);
        
        LibraryResponse response = libraryService.getLibraryById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/libraries
     * Create new library
     */
    @PostMapping
    public ResponseEntity<LibraryResponse> createLibrary(
            @Valid @RequestBody CreateLibraryRequest request) {
        
        log.info("POST /api/libraries - name: {}", request.getName());
        
        LibraryResponse response = libraryService.createLibrary(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT /api/libraries/{id}
     * Update existing library
     */
    @PutMapping("/{id}")
    public ResponseEntity<LibraryResponse> updateLibrary(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLibraryRequest request) {
        
        log.info("PUT /api/libraries/{} - name: {}", id, request.getName());
        
        LibraryResponse response = libraryService.updateLibrary(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/libraries/{id}
     * Delete library (cascade deletes all books)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        log.info("DELETE /api/libraries/{}", id);
        
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/libraries/{id}/books
     * Get all books for a specific library (paginated)
     */
    @GetMapping("/{id}/books")
    public ResponseEntity<PageResponse<BookResponse>> getBooksByLibraryId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String sort) {
        
        log.info("GET /api/libraries/{}/books - page: {}, size: {}", id, page, size);
        
        Pageable pageable = PaginationUtils.createPageable(page, size, sort);
        PageResponse<BookResponse> response = libraryService.getBooksByLibraryId(id, pageable);
        
        return ResponseEntity.ok(response);
    }
}
