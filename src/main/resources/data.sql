-- Sample Data for Library Management System
-- This file populates the database with test data for demonstration

-- ============================================================================
-- LIBRARIES (Parent Records)
-- ============================================================================

INSERT INTO library (name, address, phone, email, established_date) VALUES
('Central City Library', '123 Main Street, Dublin, Ireland', '+353-1-123-4567', 'info@centrallibrary.ie', '1995-06-15'),
('Riverside Public Library', '456 River Road, Cork, Ireland', '+353-21-987-6543', 'contact@riverside.ie', '2003-03-20'),
('University Library', '789 College Green, Galway, Ireland', '+353-91-555-0123', 'library@university.ie', '1988-09-01'),
('Children''s Reading Center', '321 Park Avenue, Limerick, Ireland', '+353-61-444-5555', 'kids@readingcenter.ie', '2010-11-12'),
('Waterford City Library', '42 Market Square, Waterford, Ireland', '+353-51-222-3333', 'info@waterfordlibrary.ie', '2000-01-10'),
('Drogheda Community Library', '88 West Street, Drogheda, Ireland', '+353-41-333-4444', 'hello@droghedalibrary.ie', '2015-08-05');

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
('9780439023481', 'The Giving Tree', 'Shel Silverstein', 'Children''s Picture Book', '1964-10-07', '2021-07-18', 64, 4),
('9780064430197', 'Charlotte''s Web', 'E.B. White', 'Children''s Literature', '1952-10-15', '2020-03-12', 192, 4),
('9780061124952', 'The Cat in the Hat', 'Dr. Seuss', 'Children''s Picture Book', '1957-03-12', '2021-04-22', 61, 4),
('9780394800011', 'Green Eggs and Ham', 'Dr. Seuss', 'Children''s Picture Book', '1960-08-12', '2021-04-22', 62, 4);

-- Books for Waterford City Library (library_id = 5)
INSERT INTO book (isbn, title, author, genre, publication_date, acquisition_date, pages, library_id) VALUES
('9780141439556', 'Sense and Sensibility', 'Jane Austen', 'Romance', '1811-10-30', '2021-02-14', 409, 5),
('9780141439662', 'Emma', 'Jane Austen', 'Romance', '1815-12-23', '2021-02-14', 474, 5),
('9780743477123', 'The Sun Also Rises', 'Ernest Hemingway', 'Classic Fiction', '1926-10-22', '2019-07-08', 251, 5),
('9780684801223', 'The Old Man and the Sea', 'Ernest Hemingway', 'Classic Fiction', '1952-09-01', '2019-07-08', 127, 5),
('9780060850524', 'A Farewell to Arms', 'Ernest Hemingway', 'War Fiction', '1929-09-27', '2019-07-08', 332, 5),
('9780679410034', 'Ulysses', 'James Joyce', 'Modernist Literature', '1922-02-02', '2018-06-16', 730, 5),
('9780141182452', 'Dubliners', 'James Joyce', 'Short Stories', '1914-06-15', '2018-06-16', 207, 5),
('9780141182803', 'Finnegans Wake', 'James Joyce', 'Experimental Fiction', '1939-05-04', '2018-06-16', 628, 5);

-- Books for Drogheda Community Library (library_id = 6)
INSERT INTO book (isbn, title, author, genre, publication_date, acquisition_date, pages, library_id) VALUES
('9780345391803', 'The Hitchhiker''s Guide to the Galaxy', 'Douglas Adams', 'Science Fiction', '1979-10-12', '2022-05-20', 224, 6),
('9780553803709', 'I, Robot', 'Isaac Asimov', 'Science Fiction', '1950-12-02', '2021-09-15', 224, 6),
('9780441172719', 'Dune', 'Frank Herbert', 'Science Fiction', '1965-08-01', '2021-10-22', 688, 6),
('9780812550702', 'Ender''s Game', 'Orson Scott Card', 'Science Fiction', '1985-01-15', '2022-01-10', 324, 6),
('9780345342966', 'Foundation', 'Isaac Asimov', 'Science Fiction', '1951-05-01', '2021-09-15', 255, 6),
('9780553418026', 'Fahrenheit 451', 'Ray Bradbury', 'Dystopian Fiction', '1953-10-19', '2020-11-30', 249, 6),
('9780451526342', 'Animal Farm', 'George Orwell', 'Political Satire', '1945-08-17', '2021-03-10', 112, 6),
('9780141182575', 'The War of the Worlds', 'H.G. Wells', 'Science Fiction', '1898-01-01', '2021-12-05', 192, 6),
('9780141182582', 'The Time Machine', 'H.G. Wells', 'Science Fiction', '1895-01-01', '2021-12-05', 128, 6);
