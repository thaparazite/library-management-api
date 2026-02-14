# Entity Relationship Diagram (ERD)
## Library Management System Database

```
┌─────────────────────────────────────────────────┐
│                    LIBRARY                      │
│                 (Parent Entity)                 │
├─────────────────────────────────────────────────┤
│ PK  id                  BIGINT                  │
│     name                VARCHAR(255)  NOT NULL  │
│     address             VARCHAR(500)            │
│     phone               VARCHAR(20)             │
│     email               VARCHAR(255)            │
│     established_date    DATE                    │
│     created_at          TIMESTAMP               │
│     updated_at          TIMESTAMP               │
└─────────────────────────────────────────────────┘
                        │
                        │ 1
                        │
                        │ Has Many
                        │
                        │ *
                        ▼
┌─────────────────────────────────────────────────┐
│                     BOOK                        │
│                 (Child Entity)                  │
├─────────────────────────────────────────────────┤
│ PK  id                  BIGINT                  │
│ UK  isbn                VARCHAR(13)   NOT NULL  │
│     title               VARCHAR(255)  NOT NULL  │
│     author              VARCHAR(255)  NOT NULL  │
│     genre               VARCHAR(100)            │
│     publication_date    DATE                    │
│     acquisition_date    DATE                    │
│     pages               INTEGER                 │
│ FK  library_id          BIGINT        NOT NULL  │
│     created_at          TIMESTAMP               │
│     updated_at          TIMESTAMP               │
└─────────────────────────────────────────────────┘


RELATIONSHIP TYPE: One-to-Many
─────────────────────────────────────────────────

CARDINALITY:
  • One Library can have ZERO or MANY Books (1:*)
  • One Book belongs to EXACTLY ONE Library (mandatory)

FOREIGN KEY CONSTRAINT:
  • Column: book.library_id
  • References: library.id
  • On Delete: CASCADE
    (Deleting a library automatically deletes all its books)

INDEXES:
  Library:
    • PRIMARY KEY (id)
    • INDEX (name)

  Book:
    • PRIMARY KEY (id)
    • UNIQUE KEY (isbn)
    • INDEX (title)
    • INDEX (author)
    • INDEX (publication_date)
    • INDEX (library_id) - for JOIN optimization

DATE FIELDS:
  Library:
    • established_date: When the library was founded

  Book:
    • publication_date: Original publication date of the book
    • acquisition_date: When the library acquired the book

AUDIT FIELDS (both tables):
    • created_at: Record creation timestamp
    • updated_at: Last modification timestamp


API NAVIGATION:
─────────────────────────────────────────────────

From Parent to Children:
  GET /api/libraries/{libraryId}/books
  → Returns all books belonging to the specified library

From Child to Parent:
  GET /api/books/{bookId}
  → BookResponse includes libraryId reference

Create Child for Parent:
  POST /api/books
  Body: { "libraryId": 1, "title": "...", ... }
  → Creates a book linked to library with ID 1

Cascade Delete:
  DELETE /api/libraries/{libraryId}
  → Deletes the library AND all its books automatically


SAMPLE DATA FLOW:
─────────────────────────────────────────────────

Library (ID: 1)
  └── Books:
      ├── Book 1 (ID: 1, library_id: 1)
      ├── Book 2 (ID: 2, library_id: 1)
      └── Book 3 (ID: 3, library_id: 1)

Library (ID: 2)
  └── Books:
      ├── Book 4 (ID: 4, library_id: 2)
      └── Book 5 (ID: 5, library_id: 2)


VALIDATION RULES:
─────────────────────────────────────────────────

Library:
  • name: Required, max 255 characters
  • email: Valid email format (if provided)
  • established_date: Valid date, not in the future

Book:
  • isbn: Required, unique, 10 or 13 digits
  • title: Required, max 255 characters
  • author: Required, max 255 characters
  • library_id: Required, must reference existing library
  • publication_date: Valid date
  • acquisition_date: Valid date, not before publication_date
  • pages: Positive integer (if provided)
```

## Database Normalization

This schema follows **Third Normal Form (3NF)**:

1. **First Normal Form (1NF)**: All columns contain atomic values
2. **Second Normal Form (2NF)**: No partial dependencies (all non-key attributes depend on the entire primary key)
3. **Third Normal Form (3NF)**: No transitive dependencies (non-key attributes don't depend on other non-key attributes)

## Referential Integrity

The database enforces referential integrity through:
- **Primary Keys**: Unique identifier for each record
- **Foreign Keys**: Ensures book.library_id always references a valid library
- **Cascade Delete**: Maintains data consistency when parent records are deleted
- **Unique Constraints**: Prevents duplicate ISBNs

## Performance Considerations

- **Indexes** on frequently queried columns (name, title, author, dates)
- **Foreign key index** on library_id for efficient JOINs
- **Auto-incrementing IDs** for optimal insertion performance
- **Appropriate data types** to minimize storage and improve query speed
