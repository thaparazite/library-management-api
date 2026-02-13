package com.library.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Library response (without book details)
 * Used in GET /api/libraries endpoints
 * Exposes only essential library information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryResponse {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishedDate;

    // Transient field - not from entity directly
    private Integer totalBooks;
}
