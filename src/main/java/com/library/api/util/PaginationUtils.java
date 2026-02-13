package com.library.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Utility class for common operations
 * No need for inheritance - just reusable static methods
 */
public class PaginationUtils {

    private PaginationUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Create Pageable object from pagination parameters
     * 
     * @param page Page number (0-indexed)
     * @param size Page size
     * @param sort Sort parameter (format: "field,direction")
     * @return Pageable object for Spring Data
     */
    public static Pageable createPageable(int page, int size, String sort) {
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        
        String sortField = sortParams[0];
        return PageRequest.of(page, size, Sort.by(direction, sortField));
    }
}
