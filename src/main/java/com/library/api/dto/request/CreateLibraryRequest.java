package com.library.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for creating a new Library
 * Used in POST /api/libraries
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLibraryRequest {

    @NotBlank(message = "Library name is required")
    @Size(max = 255, message = "Library name must not exceed 255 characters")
    @Pattern(
    regexp = "^[\\p{L}0-9][\\p{L}0-9 \\-'.&,()]{0,254}$",
    message = "Library name contains invalid characters"
    )
    private String name;

    @Size(max = 500, message = "Address must not exceed 500 characters")
    @Pattern(
    regexp = "^[\\p{L}0-9][\\p{L}0-9 \\-'.&,()/#:]{0,499}$",
    message = "Address contains invalid characters"
    )
    private String address;

    @Pattern(regexp = "^\\+?[0-9\\-\\s()]+$", message = "Invalid phone number format")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @PastOrPresent(message = "Established date cannot be in the future")
    private LocalDate establishedDate;
}
