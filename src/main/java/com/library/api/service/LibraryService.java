package com.library.api.service;

import com.library.api.dto.request.CreateLibraryRequest;
import com.library.api.dto.request.UpdateLibraryRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.dto.response.LibraryResponse;
import com.library.api.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for Library business logic
 * Defines the contract for library operations
 */
public interface LibraryService {

    /**
     * Get all libraries (paginated)
     */
    PageResponse<LibraryResponse> getAllLibraries(Pageable pageable);

    /**
     * Get library by ID
     */
    LibraryResponse getLibraryById(Long id);

    /**
     * Create new library
     */
    LibraryResponse createLibrary(CreateLibraryRequest request);

    /**
     * Update existing library
     */
    LibraryResponse updateLibrary(Long id, UpdateLibraryRequest request);

    /**
     * Delete library (cascade deletes all books)
     */
    void deleteLibrary(Long id);

    /**
     * Get all books for a specific library (paginated)
     * Demonstrates one-to-many relationship navigation
     */
    PageResponse<BookResponse> getBooksByLibraryId(Long libraryId, Pageable pageable);
}
