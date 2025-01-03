INSERT INTO authors (author_name) VALUES ('J.K. Rowling'), ('George Orwell');
INSERT INTO genres (genre_name) VALUES ('Fantasy'), ('Romantic');
INSERT INTO books (title, author_id, genre_id, publication_year, quantity, is_available)
VALUES 
('Harry Potter', 1, 1, 1997, 10, true),
('1984', 2, 2, 1949, 5, true);
INSERT INTO users (username, password, user_role) VALUES ('admin', 'password', 'Admin'), ('visitor', 'password', 'Visitor');
