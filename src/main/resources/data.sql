-- Sample Data for Library Management System
-- This file populates the database with test data for demonstration

-- ============================================================================
-- LIBRARIES (Parent Records)
-- ============================================================================

INSERT INTO library (name, address, phone, email, established_date) VALUES
('Central City Library', '123 Main Street, Dublin, Ireland', '+353-1-123-4567', 'info@centrallibrary.ie', '1995-06-15'),
('Riverside Public Library', '456 River Road, Cork, Ireland', '+353-21-987-6543', 'contact@riverside.ie', '2003-03-20'),
('University Library', '789 College Green, Galway, Ireland', '+353-91-555-0123', 'library@university.ie', '1988-09-01'),
('Children''s Reading Center', '321 Park Avenue, Limerick, Ireland', '+353-61-444-5555', 'kids@readingcenter.ie', '2010-11-12');

-- ============================================================================
-- BOOKS (Child Records)
-- ============================================================================

-- Books for Central City Library (library_id = 1)
INSERT INTO book (isbn, title, author, genre, publication_date, acquisition_date, pages, library_id) VALUES
('9780061120084', 'To Kill a Mockingbird', 'Harper Lee', 'Classic Fiction', '1960-07-11', '2020-01-15', 336, 1),
('9780141439518', 'Pride and Prejudice', 'Jane Austen', 'Romance', '1813-01-28', '2019-05-20', 432, 1),
('9780451524935', '1984', 'George Orwell', 'Dystopian Fiction', '1949-06-08', '2021-03-10', 328, 1),
('9780743273565', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Classic Fiction', '1925-04-10', '2020-08-25', 180, 1),
('9780439708180', 'Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Fantasy', '1997-06-26', '2022-02-14', 309, 1);

-- Books for Riverside Public Library (library_id = 2)
INSERT INTO book (isbn, title, author, genre, publication_date, acquisition_date, pages, library_id) VALUES
('9780142437247', 'The Catcher in the Rye', 'J.D. Salinger', 'Coming of Age', '1951-07-16', '2021-06-01', 277, 2),
('9780316769488', 'Brave New World', 'Aldous Huxley', 'Dystopian Fiction', '1932-08-30', '2018-09-15', 234, 2),
('9780060935467', 'One Hundred Years of Solitude', 'Gabriel García Márquez', 'Magical Realism', '1967-05-30', '2020-11-20', 417, 2),
('9780141182605', 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', '1954-07-29', '2019-12-05', 1178, 2);

-- Books for University Library (library_id = 3)
INSERT INTO book (isbn, title, author, genre, publication_date, acquisition_date, pages, library_id) VALUES
('9780679783268', 'Crime and Punishment', 'Fyodor Dostoevsky', 'Psychological Fiction', '1866-01-01', '2018-04-10', 671, 3),
('9780140449136', 'The Odyssey', 'Homer', 'Epic Poetry', '0800-01-01', '2017-08-22', 541, 3),
('9780140447934', 'The Iliad', 'Homer', 'Epic Poetry', '0750-01-01', '2017-08-22', 704, 3),
('9780143039433', 'Moby-Dick', 'Herman Melville', 'Adventure', '1851-10-18', '2019-03-15', 704, 3),
('9780141439600', 'Jane Eyre', 'Charlotte Brontë', 'Gothic Romance', '1847-10-16', '2020-05-30', 532, 3);

-- Books for Children's Reading Center (library_id = 4)
INSERT INTO book (isbn, title, author, genre, publication_date, acquisition_date, pages, library_id) VALUES
('9780064401883', 'Where the Wild Things Are', 'Maurice Sendak', 'Children''s Literature', '1963-11-13', '2021-01-20', 48, 4),
('9780062382153', 'The Very Hungry Caterpillar', 'Eric Carle', 'Children''s Picture Book', '1969-06-03', '2020-09-10', 26, 4),
('9780590353427', 'Goodnight Moon', 'Margaret Wise Brown', 'Children''s Bedtime', '1947-09-03', '2019-11-15', 32, 4),
('9780545010221', 'The Hunger Games', 'Suzanne Collins', 'Young Adult Dystopian', '2008-09-14', '2022-03-25', 374, 4),
('9780439023481', 'The Giving Tree', 'Shel Silverstein', 'Children''s Picture Book', '1964-10-07', '2021-07-18', 64, 4);
