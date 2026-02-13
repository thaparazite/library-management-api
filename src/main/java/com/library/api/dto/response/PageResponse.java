package com.library.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Generic DTO for paginated responses
 * Wraps a list of items with pagination metadata
 * 
 * @param <T> Type of items in the page (e.g., LibraryResponse, BookResponse)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;           // The actual data items
    private int pageNumber;             // Current page number (0-indexed)
    private int pageSize;               // Number of items per page
    private long totalElements;         // Total number of items across all pages
    private int totalPages;             // Total number of pages
    private boolean first;              // Is this the first page?
    private boolean last;               // Is this the last page?
    private boolean empty;              // Is the page empty?

    /**
     * Factory method to create PageResponse from Spring's Page object
     */
    public static <T> PageResponse<T> of(org.springframework.data.domain.Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .build();
    }
}
