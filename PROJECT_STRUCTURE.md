# Library Management REST API - Project Structure

## Domain Model
**Library Management System** with one-to-many relationship:
- **Library** (Parent Entity) ← One-to-Many → **Book** (Child Entity)
- Each library can have multiple books
- Each book belongs to one library
- Includes date handling for publication dates and acquisition dates

---

## Directory Structure

```
library-management-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── library/
│   │   │           └── api/
│   │   │               ├── LibraryManagementApplication.java
│   │   │               │
│   │   │               ├── controller/           # REST Controllers (API Layer)
│   │   │               │   ├── WelcomeController.java
│   │   │               │   ├── LibraryController.java
│   │   │               │   └── BookController.java
│   │   │               │
│   │   │               ├── service/              # Business Logic Layer
│   │   │               │   ├── LibraryService.java
│   │   │               │   ├── LibraryServiceImpl.java
│   │   │               │   ├── BookService.java
│   │   │               │   └── BookServiceImpl.java
│   │   │               │
│   │   │               ├── repository/           # Data Access Layer
│   │   │               │   ├── LibraryRepository.java
│   │   │               │   └── BookRepository.java
│   │   │               │
│   │   │               ├── model/                # Domain Entities
│   │   │               │   ├── Library.java
│   │   │               │   └── Book.java
│   │   │               │
│   │   │               ├── dto/                  # Data Transfer Objects
│   │   │               │   ├── request/
│   │   │               │   │   ├── CreateLibraryRequest.java
│   │   │               │   │   ├── UpdateLibraryRequest.java
│   │   │               │   │   ├── CreateBookRequest.java
│   │   │               │   │   └── UpdateBookRequest.java
│   │   │               │   │
│   │   │               │   └── response/
│   │   │               │       ├── LibraryResponse.java
│   │   │               │       ├── LibraryDetailResponse.java
│   │   │               │       ├── BookResponse.java
│   │   │               │       └── PageResponse.java
│   │   │               │
│   │   │               ├── mapper/               # Entity ↔ DTO Mapping
│   │   │               │   ├── LibraryMapper.java
│   │   │               │   └── BookMapper.java
│   │   │               │
│   │   │               ├── exception/            # Custom Exceptions
│   │   │               │   ├── ResourceNotFoundException.java
│   │   │               │   ├── ValidationException.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               │
│   │   │               ├── config/               # Configuration Classes
│   │   │               │   └── AppConfig.java
│   │   │               │
│   │   │               └── util/                 # Utility Classes
│   │   │                   └── DateUtil.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties            # Configuration
│   │       ├── application.yml                   # Alternative config
│   │       └── schema.sql                        # Database schema
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── library/
│                   └── api/
│                       ├── controller/
│                       ├── service/
│                       └── repository/
│
├── database/
│   ├── schema.sql                                # Database creation script
│   └── ERD.png                                   # Entity Relationship Diagram
│
├── postman/
│   └── Library_API_Collection.json               # Postman collection for testing
│
├── docs/
│   ├── API_Documentation.md                      # API endpoint documentation
│   └── Report.docx                               # Assignment report
│
├── pom.xml                                       # Maven dependencies
├── .gitignore
└── README.md
```

---

## Layer Responsibilities

### 1. **Controller Layer** (`controller/`)
- **Purpose**: Handle HTTP requests and responses
- **Responsibilities**:
  - Receive HTTP requests
  - Validate request parameters
  - Call appropriate service methods
  - Return HTTP responses with proper status codes
- **Files**:
  - `LibraryController.java`: Endpoints for library operations
  - `BookController.java`: Endpoints for book operations

### 2. **Service Layer** (`service/`)
- **Purpose**: Business logic and orchestration
- **Responsibilities**:
  - Implement business rules
  - Coordinate between controllers and repositories
  - Handle transactions
  - Perform data validation
- **Files**:
  - `LibraryService.java`: Interface defining library operations
  - `LibraryServiceImpl.java`: Implementation of library business logic
  - `BookService.java`: Interface defining book operations
  - `BookServiceImpl.java`: Implementation of book business logic

### 3. **Repository Layer** (`repository/`)
- **Purpose**: Data persistence and retrieval
- **Responsibilities**:
  - Database CRUD operations
  - Custom queries
  - Data access abstraction
- **Files**:
  - `LibraryRepository.java`: JPA repository for Library entity
  - `BookRepository.java`: JPA repository for Book entity

### 4. **Model Layer** (`model/`)
- **Purpose**: Database entities
- **Responsibilities**:
  - Represent database tables
  - Define relationships
  - Entity validation rules
- **Files**:
  - `Library.java`: Library entity with JPA annotations
  - `Book.java`: Book entity with foreign key to Library

### 5. **DTO Layer** (`dto/`)
- **Purpose**: Data Transfer Objects for API communication
- **Responsibilities**:
  - Control what data is exposed to API consumers
  - Separate internal model from external API
  - Request validation
  - Response formatting
- **Subdirectories**:
  - `request/`: DTOs for incoming data (POST, PUT)
  - `response/`: DTOs for outgoing data (GET responses)

### 6. **Mapper Layer** (`mapper/`)
- **Purpose**: Convert between entities and DTOs
- **Responsibilities**:
  - Entity → DTO conversion
  - DTO → Entity conversion
  - Handle field mapping and transformation

### 7. **Exception Layer** (`exception/`)
- **Purpose**: Error handling
- **Responsibilities**:
  - Define custom exceptions
  - Global exception handling
  - Standardized error responses
- **Files**:
  - `ResourceNotFoundException.java`: For 404 errors
  - `ValidationException.java`: For 400 errors
  - `GlobalExceptionHandler.java`: Central exception handling with @ControllerAdvice

---

## Technology Stack

### Framework & Core
- **Spring Boot** 3.x (Latest stable)
- **Spring Web** (REST API)
- **Spring Data JPA** (Database access)

### Database
- **H2 Database** (In-memory, for development/demo)
- Or **PostgreSQL/MySQL** (Production-ready alternative)

### Build Tool
- **Maven** (Dependency management)

### Validation
- **Jakarta Validation API** (Bean Validation)
- **Hibernate Validator**

### Utilities
- **Lombok** (Reduce boilerplate code)
- **MapStruct** or Manual Mappers (DTO mapping)

### Testing
- **JUnit 5**
- **Mockito**
- **Spring Boot Test**

---

## API Endpoints Overview

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
GET    /api/books                  - Get all books (paginated, with date filtering)
GET    /api/books/{id}             - Get book by ID
POST   /api/books                  - Create new book
PUT    /api/books/{id}             - Update book
DELETE /api/books/{id}             - Delete book

GET    /api/books?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd  - Filter by publication date
GET    /api/books?sort=publicationDate,desc                 - Sort by date
```

---

## Database Schema (Preview)

### Library Table
```sql
CREATE TABLE library (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(500),
    established_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Book Table
```sql
CREATE TABLE book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_date DATE,
    acquisition_date DATE,
    library_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (library_id) REFERENCES library(id) ON DELETE CASCADE
);
```

**Relationship**: One Library → Many Books (ON DELETE CASCADE)

---

## Key Features Implementation

### ✅ 1. Entity Relationships
- One-to-Many: Library → Books
- Enforced via foreign key (`library_id` in Book table)
- Cascade delete: Deleting a library removes all its books
- Navigable: `/api/libraries/{id}/books` endpoint

### ✅ 2. Date Handling
- `publication_date` and `acquisition_date` in Book entity
- Format: `yyyy-MM-dd` (ISO 8601)
- Endpoints support date range filtering
- Sorting by date supported

### ✅ 3. DTOs
- Clear separation: Entity vs Request vs Response DTOs
- `LibraryResponse`: Exposes only public fields
- `BookResponse`: Excludes internal metadata
- `CreateBookRequest`: Validates incoming data

### ✅ 4. Error Handling
- `@ControllerAdvice` for global exception handling
- Custom exceptions with proper HTTP status codes
- Structured error response format:
  ```json
  {
    "timestamp": "2025-02-13T10:30:00",
    "status": 404,
    "error": "Not Found",
    "message": "Library with ID 999 not found",
    "path": "/api/libraries/999"
  }
  ```

### ✅ 5. Pagination
- Page number and size parameters
- Response includes:
  - `content`: Array of items
  - `pageNumber`: Current page
  - `pageSize`: Items per page
  - `totalElements`: Total count
  - `totalPages`: Total pages
- Example: `/api/books?page=0&size=10`

---

## Next Steps

1. **Create Maven `pom.xml`** with all dependencies
2. **Create database schema** (schema.sql)
3. **Implement entities** (Library, Book)
4. **Create DTOs** (Request/Response objects)
5. **Implement repositories** (JPA interfaces)
6. **Implement services** (Business logic)
7. **Implement controllers** (REST endpoints)
8. **Implement exception handling**
9. **Create mappers** (Entity ↔ DTO)
10. **Test with Postman**

---

## Assignment Alignment

| Requirement | Implementation |
|-------------|----------------|
| Entity Relationships | Library (1) ↔ Book (Many) |
| Date/Time Objects | `publication_date`, `acquisition_date` with filtering |
| DTOs | Separate Request/Response DTOs for all entities |
| Error Handling | GlobalExceptionHandler + custom exceptions |
| Pagination | PageResponse DTO with Spring Data pagination |

This structure ensures clean separation of concerns, maintainability, and meets all technical requirements of the assignment.
