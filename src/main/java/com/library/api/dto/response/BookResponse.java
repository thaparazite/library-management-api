package com.library.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Book response
 * Used in GET /api/books endpoints
 * Exposes only essential book information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String genre;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate acquisitionDate;

    private Integer pages;

    // Reference to parent library (ID and name only, not full object)
    private Long libraryId;
    private String libraryName;
}
