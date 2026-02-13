# Library Management REST API

A robust RESTful API for managing libraries and their book collections, demonstrating modern API design principles including entity relationships, DTOs, pagination, error handling, and date management.

## 📋 Project Overview

This API provides a complete library management system with:
- **One-to-Many Relationship**: Libraries and their Books
- **Full CRUD Operations**: Create, Read, Update, Delete for both entities
- **Advanced Features**: Pagination, date filtering, sorting, cascading deletes
- **Clean Architecture**: Layered design with clear separation of concerns
- **Comprehensive DTOs**: Controlled data exposure through Data Transfer Objects
- **Robust Error Handling**: Structured exceptions with meaningful HTTP status codes

## 🎯 Assignment Requirements Met

| Requirement | Implementation |
|-------------|----------------|
| ✅ Entity Relationships | One-to-Many: Library → Books with cascade delete |
| ✅ Date/Time Handling | Publication dates, acquisition dates, filtering & sorting |
| ✅ DTOs | Separate Request/Response DTOs for all entities |
| ✅ Error Handling | GlobalExceptionHandler with 400, 404, and custom errors |
| ✅ Pagination | Spring Data pagination with page metadata |

## 🏗️ Architecture

### Layer Structure
```
├── Controller Layer    → HTTP Request/Response handling
├── Service Layer       → Business logic and orchestration
├── Repository Layer    → Data access and persistence
├── Model Layer         → JPA entities (database tables)
├── DTO Layer          → Data Transfer Objects
├── Mapper Layer       → Entity ↔ DTO conversion
└── Exception Layer    → Error handling and validation
```

### Technology Stack
- **Framework**: Spring Boot 3.5.7
- **Language**: Java 25
- **Database**: H2 (in-memory)
- **Build Tool**: Maven
- **ORM**: Spring Data JPA / Hibernate
- **Validation**: Jakarta Bean Validation

## 📁 Project Structure

```
library-management-api/
├── src/main/java/com/library/api/
│   ├── controller/           # REST endpoints
│   ├── service/              # Business logic
│   ├── repository/           # Data access
│   ├── model/                # JPA entities
│   ├── dto/                  # Data Transfer Objects
│   │   ├── request/          # Incoming DTOs
│   │   └── response/         # Outgoing DTOs
│   ├── mapper/               # Entity-DTO mapping
│   ├── exception/            # Exception handling
│   ├── config/               # Configuration
│   └── util/                 # Utilities
├── src/main/resources/
│   ├── application.properties
│   ├── data.sql              # Sample data
│   └── schema.sql            # Database schema (copied from /database)
├── database/
│   ├── schema.sql            # Master schema
│   └── ERD.md                # Entity Relationship Diagram
├── docs/
│   └── API_Documentation.md  # API endpoint guide
└── pom.xml                   # Maven dependencies
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Installation

1. **Clone or extract the project**
   ```bash
   cd library-management-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - Base URL: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:librarydb`
     - Username: `sa`
     - Password: (leave blank)

## 📊 Database Schema

### Library Table
```sql
CREATE TABLE library (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(500),
    phone VARCHAR(20),
    email VARCHAR(255),
    established_date DATE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

### Book Table
```sql
CREATE TABLE book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    publication_date DATE,
    acquisition_date DATE,
    pages INTEGER,
    library_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (library_id) REFERENCES library(id) ON DELETE CASCADE
);
```

**Relationship**: `Library (1) ←→ (Many) Book`

## 🔌 API Endpoints

### Library Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/libraries` | Get all libraries (paginated) |
| GET | `/api/libraries/{id}` | Get library by ID |
| POST | `/api/libraries` | Create new library |
| PUT | `/api/libraries/{id}` | Update library |
| DELETE | `/api/libraries/{id}` | Delete library (cascade) |
| GET | `/api/libraries/{id}/books` | Get all books for library |

### Book Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books (paginated) |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Create new book |
| PUT | `/api/books/{id}` | Update book |
| DELETE | `/api/books/{id}` | Delete book |

### Query Parameters

**Pagination**:
```
GET /api/books?page=0&size=10&sort=title,asc
```

**Date Filtering**:
```
GET /api/books?startDate=2000-01-01&endDate=2020-12-31
```

## 📝 Sample Requests

### Create a Library
```json
POST /api/libraries
{
  "name": "City Central Library",
  "address": "123 Main St",
  "phone": "+353-1-234-5678",
  "email": "info@citylibrary.ie",
  "establishedDate": "1995-06-15"
}
```

### Create a Book
```json
POST /api/books
{
  "isbn": "9780061120084",
  "title": "To Kill a Mockingbird",
  "author": "Harper Lee",
  "genre": "Classic Fiction",
  "publicationDate": "1960-07-11",
  "acquisitionDate": "2024-01-15",
  "pages": 336,
  "libraryId": 1
}
```

### Sample Response (GET /api/libraries/1)
```json
{
  "id": 1,
  "name": "Central City Library",
  "address": "123 Main Street, Dublin",
  "email": "info@centrallibrary.ie",
  "establishedDate": "1995-06-15",
  "totalBooks": 5
}
```

## 🎨 DTOs in Use

### Request DTOs
- `CreateLibraryRequest`: For creating libraries
- `UpdateLibraryRequest`: For updating libraries
- `CreateBookRequest`: For creating books
- `UpdateBookRequest`: For updating books

### Response DTOs
- `LibraryResponse`: Basic library info
- `LibraryDetailResponse`: Library with book count
- `BookResponse`: Book info with library reference
- `PageResponse<T>`: Paginated results wrapper

**Why DTOs?**
- Decouples internal data model from API
- Controls exactly what data is exposed
- Prevents over/under-fetching
- Enables different views of the same entity

## ⚠️ Error Handling

The API returns structured error responses:

**404 Not Found**:
```json
{
  "timestamp": "2024-02-13T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Library with ID 999 not found",
  "path": "/api/libraries/999"
}
```

**400 Bad Request** (Validation Error):
```json
{
  "timestamp": "2024-02-13T10:31:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": {
    "name": "Library name is required",
    "email": "Invalid email format"
  },
  "path": "/api/libraries"
}
```

## 🧪 Testing

### Using Postman
1. Import the collection from `postman/Library_API_Collection.json`
2. Run requests in sequence to test all features
3. Verify pagination, date filtering, and error scenarios

### Using H2 Console
1. Navigate to `http://localhost:8080/h2-console`
2. View database tables and relationships
3. Execute custom SQL queries

## 📚 Additional Resources

- **Full API Documentation**: See `docs/API_Documentation.md`
- **Database ERD**: See `database/ERD.md`
- **Project Structure**: See `PROJECT_STRUCTURE.md`

## 🔐 API Features Demonstrated

1. **Entity Relationships**
   - One-to-Many mapping via JPA
   - Cascade delete functionality
   - Bidirectional navigation

2. **Date Handling**
   - Consistent `yyyy-MM-dd` format
   - Date range filtering
   - Date-based sorting
   - Validation (no future dates)

3. **Pagination**
   - Page number and size control
   - Total elements/pages metadata
   - Sorting support

4. **Error Management**
   - Custom exceptions
   - Global exception handler
   - Meaningful HTTP status codes
   - Detailed error messages

5. **Clean Code**
   - Layered architecture
   - Separation of concerns
   - Lombok for reduced boilerplate
   - Comprehensive validation

## 👨‍💻 Development Notes

- Database resets on restart (H2 in-memory)
- Sample data loaded automatically
- Hot reload enabled with DevTools
- SQL queries logged in console
- Timestamps in UTC

## 📄 License

This project is created for educational purposes as part of my Microservices Architecture assignment.

---

**Author**: [@thaparazite](https://github.com/thaparazite) - Flaviu Vanca  
**Course**: Microservices Architecture  
**Date**: February 2026
