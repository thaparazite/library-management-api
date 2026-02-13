package com.library.api.service;

import com.library.api.dto.request.CreateLibraryRequest;
import com.library.api.dto.request.UpdateLibraryRequest;
import com.library.api.dto.response.BookResponse;
import com.library.api.dto.response.LibraryResponse;
import com.library.api.dto.response.PageResponse;
import com.library.api.mapper.BookMapper;
import com.library.api.mapper.LibraryMapper;
import com.library.api.model.Library;
import com.library.api.repository.BookRepository;
import com.library.api.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of LibraryService
 * Extends BaseService for DRY validation logic
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LibraryServiceImpl extends BaseService implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final LibraryMapper libraryMapper;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public PageResponse<LibraryResponse> getAllLibraries(Pageable pageable) {
        log.debug("Fetching all libraries with pagination: {}", pageable);
        
        Page<LibraryResponse> responsePage = libraryRepository.findAll(pageable)
                .map(libraryMapper::toResponse);
        
        return PageResponse.of(responsePage);
    }

    @Override
    @Transactional(readOnly = true)
    public LibraryResponse getLibraryById(Long id) {
        log.debug("Fetching library with ID: {}", id);
        
        Library library = findByIdOrThrow(libraryRepository, id, "Library");
        return libraryMapper.toResponse(library);
    }

    @Override
    public LibraryResponse createLibrary(CreateLibraryRequest request) {
        log.debug("Creating new library: {}", request.getName());
        
        Library library = libraryMapper.toEntity(request);
        Library savedLibrary = libraryRepository.save(library);
        
        log.info("Created library with ID: {}", savedLibrary.getId());
        return libraryMapper.toResponse(savedLibrary);
    }

    @Override
    public LibraryResponse updateLibrary(Long id, UpdateLibraryRequest request) {
        log.debug("Updating library with ID: {}", id);
        
        Library library = findByIdOrThrow(libraryRepository, id, "Library");
        
        libraryMapper.updateEntity(library, request);
        Library updatedLibrary = libraryRepository.save(library);
        
        log.info("Updated library with ID: {}", id);
        return libraryMapper.toResponse(updatedLibrary);
    }

    @Override
    public void deleteLibrary(Long id) {
        log.debug("Deleting library with ID: {}", id);
        
        Library library = findByIdOrThrow(libraryRepository, id, "Library");
        
        libraryRepository.delete(library);
        
        log.info("Deleted library with ID: {} (cascade deleted {} books)", 
                id, library.getBooks().size());
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookResponse> getBooksByLibraryId(Long libraryId, Pageable pageable) {
        log.debug("Fetching books for library ID: {}", libraryId);
        
        ensureEntityExists(libraryRepository, libraryId, "Library");
        
        Page<BookResponse> bookPage = bookRepository.findByLibraryId(libraryId, pageable)
                .map(bookMapper::toResponse);
        
        return PageResponse.of(bookPage);
    }
}
