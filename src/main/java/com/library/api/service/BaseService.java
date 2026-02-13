package com.library.api.service;

import com.library.api.exception.BadRequestException;
import com.library.api.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

/**
 * Base Service with reusable utility methods
 * Implements DRY (Don't Repeat Yourself) principle
 */
public abstract class BaseService {

    /**
     * Get entity by ID or throw ResourceNotFoundException
     * Reusable method to avoid code duplication
     * 
     * @param <T> Entity type
     * @param <ID> ID type
     */
    protected <T, ID> T findByIdOrThrow(JpaRepository<T, ID> repository, ID id, String entityName) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName, (Long) id));
    }

    /**
     * Validate that end date is not before start date
     */
    protected void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("Start date must be before or equal to end date");
        }
    }

    /**
     * Validate that acquisition date is not before publication date
     */
    protected void validateAcquisitionDate(LocalDate publicationDate, LocalDate acquisitionDate) {
        if (acquisitionDate != null && publicationDate != null) {
            if (acquisitionDate.isBefore(publicationDate)) {
                throw new BadRequestException("Acquisition date cannot be before publication date");
            }
        }
    }

    /**
     * Check if entity exists by ID
     * 
     * @param <T> Entity type
     * @param <ID> ID type
     */
    protected <T, ID> void ensureEntityExists(JpaRepository<T, ID> repository, ID id, String entityName) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(entityName, (Long) id);
        }
    }
}
