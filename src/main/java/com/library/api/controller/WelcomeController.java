package com.library.api.controller;

import com.library.api.repository.BookRepository;
import com.library.api.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Welcome Controller
 * Provides information about the API at the root endpoint
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WelcomeController {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> welcome() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Library Management REST API");
        response.put("version", "1.0.0");
        response.put("status", "running");
        
        // Show database status
        Map<String, Object> database = new HashMap<>();
        database.put("type", "H2 In-Memory");
        database.put("librariesCount", libraryRepository.count());
        database.put("booksCount", bookRepository.count());
        database.put("status", "✓ Data loaded successfully");
        response.put("database", database);
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Get all libraries", "GET /api/libraries");
        endpoints.put("Get all books", "GET /api/books");
        endpoints.put("Get library by ID", "GET /api/libraries/{id}");
        endpoints.put("Get book by ID", "GET /api/books/{id}");
        endpoints.put("Get books by library", "GET /api/libraries/{id}/books");
        
        response.put("endpoints", endpoints);
        response.put("documentation", "See README.md for complete API documentation");
        
        return ResponseEntity.ok(response);
    }
}
