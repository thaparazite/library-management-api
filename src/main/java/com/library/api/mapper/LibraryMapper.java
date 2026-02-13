package com.library.api.mapper;

import com.library.api.dto.request.CreateLibraryRequest;
import com.library.api.dto.request.UpdateLibraryRequest;
import com.library.api.dto.response.LibraryResponse;
import com.library.api.model.Library;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Library entity and DTOs
 * Handles the separation between internal model and API representation
 */
@Component
public class LibraryMapper {

    /**
     * Convert CreateLibraryRequest DTO to Library entity
     */
    public Library toEntity(CreateLibraryRequest request) {
        Library library = new Library();
        library.setName(request.getName());
        library.setAddress(request.getAddress());
        library.setPhone(request.getPhone());
        library.setEmail(request.getEmail());
        library.setEstablishedDate(request.getEstablishedDate());
        return library;
    }

    /**
     * Update existing Library entity from UpdateLibraryRequest DTO
     */
    public void updateEntity(Library library, UpdateLibraryRequest request) {
        library.setName(request.getName());
        library.setAddress(request.getAddress());
        library.setPhone(request.getPhone());
        library.setEmail(request.getEmail());
        library.setEstablishedDate(request.getEstablishedDate());
    }

    /**
     * Convert Library entity to LibraryResponse DTO
     */
    public LibraryResponse toResponse(Library library) {
        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .phone(library.getPhone())
                .email(library.getEmail())
                .establishedDate(library.getEstablishedDate())
                .totalBooks(library.getBooks() != null ? library.getBooks().size() : 0)
                .build();
    }
}
