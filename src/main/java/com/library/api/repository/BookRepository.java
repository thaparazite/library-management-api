package com.library.api.repository;

import com.library.api.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository interface for Book entity
 * Includes custom query methods for date filtering and library relationships
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find all books belonging to a specific library (paginated)
     */
    Page<Book> findByLibraryId(Long libraryId, Pageable pageable);

    /**
     * Find book by ISBN
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Check if book exists by ISBN
     */
    boolean existsByIsbn(String isbn);

    /**
     * Find books published within a date range (paginated)
     * Demonstrates date handling requirement
     */
    @Query("SELECT b FROM Book b WHERE b.publicationDate BETWEEN :startDate AND :endDate")
    Page<Book> findByPublicationDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );

    /**
     * Find books acquired within a date range (paginated)
     */
    @Query("SELECT b FROM Book b WHERE b.acquisitionDate BETWEEN :startDate AND :endDate")
    Page<Book> findByAcquisitionDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );

    /**
     * Find books by author (paginated)
     */
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    /**
     * Find books by genre (paginated)
     */
    Page<Book> findByGenreIgnoreCase(String genre, Pageable pageable);

    /**
     * Count books in a library
     */
    long countByLibraryId(Long libraryId);
}
