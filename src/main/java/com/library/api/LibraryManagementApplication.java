package com.library.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * Entry point for the Library Management REST API
 * 
 * @SpringBootApplication includes:
 * - @Configuration: Marks this as a source of bean definitions
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration
 * - @ComponentScan: Scans for components in this package and sub-packages
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
        
        System.out.println("\n" +
                "===========================================================\n" +
                "   Library Management REST API - Successfully Started\n" +
                "===========================================================\n" +
                "   Application is running on: http://localhost:8080\n" +
                "   H2 Console available at: http://localhost:8080/h2-console\n" +
                "   \n" +
                "   API Endpoints:\n" +
                "   - GET    /api/libraries\n" +
                "   - GET    /api/libraries/{id}\n" +
                "   - POST   /api/libraries\n" +
                "   - PUT    /api/libraries/{id}\n" +
                "   - DELETE /api/libraries/{id}\n" +
                "   - GET    /api/libraries/{id}/books\n" +
                "   \n" +
                "   - GET    /api/books\n" +
                "   - GET    /api/books/{id}\n" +
                "   - POST   /api/books\n" +
                "   - PUT    /api/books/{id}\n" +
                "   - DELETE /api/books/{id}\n" +
                "   - GET    /api/books/by-publication-date\n" +
                "   - GET    /api/books/by-acquisition-date\n" +
                "===========================================================\n");
    }
}
