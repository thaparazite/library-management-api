-- Library Management System Database Schema
-- This file defines the database structure for the Library Management REST API

-- Drop existing tables if they exist (for clean recreation)
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS library;

-- ============================================================================
-- LIBRARY TABLE (Parent Entity)
-- ============================================================================
-- Represents a library that can contain multiple books
CREATE TABLE library (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(500),
    phone VARCHAR(20),
    email VARCHAR(255),
    established_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index for faster searches by name
CREATE INDEX idx_library_name ON library(name);

-- ============================================================================
-- BOOK TABLE (Child Entity)
-- ============================================================================
-- Represents books belonging to a library
-- One-to-Many Relationship: One Library → Many Books
CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    publication_date DATE,
    acquisition_date DATE,
    pages INTEGER,
    library_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign Key Constraint with CASCADE DELETE
    -- When a library is deleted, all its books are also deleted
    CONSTRAINT fk_book_library 
        FOREIGN KEY (library_id) 
        REFERENCES library(id) 
        ON DELETE CASCADE
);

-- Indexes for performance optimization
CREATE INDEX idx_book_title ON book(title);
CREATE INDEX idx_book_author ON book(author);
CREATE INDEX idx_book_isbn ON book(isbn);
CREATE INDEX idx_book_publication_date ON book(publication_date);
CREATE INDEX idx_book_library_id ON book(library_id);

-- ============================================================================
-- COMMENTS AND NOTES
-- ============================================================================

-- RELATIONSHIP EXPLANATION:
-- - Each Library can have zero or more Books (One-to-Many)
-- - Each Book must belong to exactly one Library (mandatory relationship)
-- - The library_id column in the book table creates this relationship
-- - ON DELETE CASCADE ensures referential integrity:
--   When a library is deleted, all associated books are automatically deleted

-- DATE FIELDS:
-- - library.established_date: When the library was founded
-- - book.publication_date: When the book was originally published
-- - book.acquisition_date: When the library acquired this book
-- All dates use the DATE type and should follow yyyy-MM-dd format

-- AUDIT FIELDS:
-- - created_at: Timestamp when the record was created
-- - updated_at: Timestamp when the record was last modified
-- These support tracking and auditing requirements
