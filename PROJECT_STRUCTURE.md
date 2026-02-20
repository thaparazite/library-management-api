# Library Management REST API - Project Structure

## 🎯 Domain Model

**Library Management System** with one-to-many relationship:
- **Library** (Parent Entity) <- One-to-Many -> **Book** (Child Entity)
- Each library can have multiple books
- Each book belongs to one library
- Includes date handling for publication dates and acquisition dates

---

## 📁 Directory Structure

```
library-management-api/
|
├── pom.xml                                    # Maven build configuration
├── README.md                                  # Project overview and setup guide
├── PROJECT_STRUCTURE.md                       # This file
├── .gitignore                                 # Git ignore rules
|
├── src/
|   ├── main/
|   |   ├── java/com/library/api/
|   |   |   |
|   |   |   ├── LibraryManagementApplication.java    # Spring Boot main class
|   |   |   |
|   |   |   ├── controller/                   # REST API Layer
|   |   |   |   ├── WelcomeController.java
|   |   |   |   ├── LibraryController.java
|   |   |   |   └── BookController.java
|   |   |   |
|   |   |   ├── service/                      # Business Logic Layer
|   |   |   |   ├── BaseService.java
|   |   |   |   ├── LibraryService.java
|   |   |   |   ├── LibraryServiceImpl.java
|   |   |   |   ├── BookService.java
|   |   |   |   └── BookServiceImpl.java
|   |   |   |
|   |   |   ├── repository/                   # Data Access Layer
|   |   |   |   ├── LibraryRepository.java
|   |   |   |   └── BookRepository.java
|   |   |   |
|   |   |   ├── model/                        # JPA Entities
|   |   |   |   ├── Library.java
|   |   |   |   └── Book.java
|   |   |   |
|   |   |   ├── dto/                          # Data Transfer Objects
|   |   |   |   ├── request/
|   |   |   |   |   ├── CreateLibraryRequest.java
|   |   |   |   |   ├── UpdateLibraryRequest.java
|   |   |   |   |   ├── CreateBookRequest.java
|   |   |   |   |   └── UpdateBookRequest.java
|   |   |   |   |
|   |   |   |   └── response/
|   |   |   |       ├── LibraryResponse.java
|   |   |   |       ├── BookResponse.java
|   |   |   |       ├── PageResponse.java
|   |   |   |       └── ErrorResponse.java
|   |   |   |
|   |   |   ├── mapper/                       # Entity <-> DTO Conversion
|   |   |   |   ├── LibraryMapper.java
|   |   |   |   └── BookMapper.java
|   |   |   |
|   |   |   ├── exception/                    # Exception Handling
|   |   |   |   ├── ResourceNotFoundException.java
|   |   |   |   ├── BadRequestException.java
|   |   |   |   └── GlobalExceptionHandler.java
|   |   |   |
|   |   |   └── util/                         # Utility Classes
|   |   |       └── PaginationUtils.java
|   |   |
|   |   └── resources/
|   |       ├── application.properties        # Configuration
|   |       ├── schema.sql                    # Database schema
|   |       └── data.sql                      # Sample data
|   |
|   └── test/java/com/library/api/           # Test classes
|
├── docs/                                     # Documentation
|   ├── architecture-diagram.drawio
|   ├── architecture-diagram.drawio.png
|   ├── erd-diagram.drawio
|   ├── erd-diagram.drawio.png
|   └── LOMBOK_SETUP.md
|
└── postman/                                  # API Testing
    └── Library_API_Collection.json
```

---

## 📊 File Count Summary

| Layer | Files | Purpose |
|-------|-------|---------|
| **Controllers** | 3 | HTTP request handling |
| **Services** | 5 | Business logic (1 base + 2 interfaces + 2 implementations) |
| **Repositories** | 2 | Data access |
| **Models** | 2 | Database entities |
| **DTOs** | 8 | Data transfer (4 request + 4 response) |
| **Mappers** | 2 | Entity-DTO conversion |
| **Exceptions** | 3 | Error handling |
| **Utils** | 1 | Helper utilities |
| **Main** | 1 | Application entry point |
| **Total Java Files** | 27 | |

---

## 🏗️ Layer Responsibilities

### 1. Controller Layer (`controller/`)
- **Purpose**: Handle HTTP requests and responses
- **Responsibilities**:
  - Receive HTTP requests
  - Validate request parameters
  - Call appropriate service methods
  - Return HTTP responses with proper status codes

### 2. Service Layer (`service/`)
- **Purpose**: Business logic and orchestration
- **Responsibilities**:
  - Implement business rules
  - Coordinate between controllers and repositories
  - Handle transactions
  - Perform data validation

### 3. Repository Layer (`repository/`)
- **Purpose**: Data persistence and retrieval
- **Responsibilities**:
  - Database CRUD operations
  - Custom queries
  - Data access abstraction

### 4. Model Layer (`model/`)
- **Purpose**: Database entities
- **Responsibilities**:
  - Represent database tables
  - Define relationships
  - Entity validation rules

### 5. DTO Layer (`dto/`)
- **Purpose**: Data Transfer Objects for API communication
- **Responsibilities**:
  - Control what data is exposed to API consumers
  - Separate internal model from external API
  - Request validation
  - Response formatting

### 6. Mapper Layer (`mapper/`)
- **Purpose**: Convert between entities and DTOs
- **Responsibilities**:
  - Entity -> DTO conversion
  - DTO -> Entity conversion
  - Handle field mapping and transformation

### 7. Exception Layer (`exception/`)
- **Purpose**: Error handling
- **Responsibilities**:
  - Define custom exceptions
  - Global exception handling
  - Standardized error responses

---

## ⚙️ Technology Stack

### Framework & Core
- **Spring Boot** 3.x
- **Spring Web** (REST API)
- **Spring Data JPA** (Database access)

### Database
- **H2 Database** (In-memory, for development/demo)

### Build Tool
- **Maven**

### Validation
- **Jakarta Validation API**
- **Hibernate Validator**

### Utilities
- **Lombok** (Reduce boilerplate code)

### Testing
- **JUnit 5**
- **Mockito**
- **Spring Boot Test**

---

## 🔌 API Endpoints

### Library Endpoints
```
GET    /api/libraries              - Get all libraries (paginated)
GET    /api/libraries/{id}         - Get library by ID
POST   /api/libraries              - Create new library
PUT    /api/libraries/{id}         - Update library
DELETE /api/libraries/{id}         - Delete library (cascade delete books)

GET    /api/libraries/{id}/books   - Get all books for a library (paginated)
```

### Book Endpoints
```
GET    /api/books                              - Get all books (paginated)
GET    /api/books/{id}                         - Get book by ID
POST   /api/books                              - Create new book
PUT    /api/books/{id}                         - Update book
DELETE /api/books/{id}                         - Delete book

GET    /api/books/by-publication-date          - Filter by publication date
GET    /api/books/by-acquisition-date          - Filter by acquisition date
```

### Query Parameters
```
Pagination:    ?page=0&size=10&sort=title,asc
Date filter:   ?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd
Author filter: ?author=AuthorName
Genre filter:  ?genre=Fiction
```

---

## 🗄️ Database Schema

### Relationship
- **One Library -> Many Books** (ON DELETE CASCADE)
- Foreign key: `book.library_id` references `library.id`

### Library Table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(255) | NOT NULL |
| address | VARCHAR(500) | |
| phone | VARCHAR(20) | |
| email | VARCHAR(255) | |
| established_date | DATE | |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

### Book Table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| isbn | VARCHAR(13) | UNIQUE, NOT NULL |
| title | VARCHAR(255) | NOT NULL |
| author | VARCHAR(255) | NOT NULL |
| genre | VARCHAR(100) | |
| publication_date | DATE | |
| acquisition_date | DATE | |
| pages | INTEGER | |
| library_id | BIGINT | NOT NULL, FOREIGN KEY |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |
| updated_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

---

## 🚀 Quick Reference

### Key Files
- **Entry Point**: `LibraryManagementApplication.java`
- **Configuration**: `application.properties`
- **Database**: `schema.sql` + `data.sql`
- **Build**: `pom.xml`

### Build Commands
```bash
mvn clean install          # Build project
mvn spring-boot:run        # Run application
mvn test                   # Run tests
```

### H2 Console
```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:librarydb
Username: sa
Password: (blank)
```

### API Base URL
```
http://localhost:8080/api
```
