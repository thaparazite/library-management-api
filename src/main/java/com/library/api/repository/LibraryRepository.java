package com.library.api.repository;

import com.library.api.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Library entity
 * Extends JpaRepository to get CRUD operations automatically
 * Spring Data JPA generates the implementation at runtime
 */
@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    /**
     * Find library by name
     * Method name follows Spring Data JPA naming convention
     */
    Optional<Library> findByName(String name);

    /**
     * Check if library exists by name
     */
    boolean existsByName(String name);
}
