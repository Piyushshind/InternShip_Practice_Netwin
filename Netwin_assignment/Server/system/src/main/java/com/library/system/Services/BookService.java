package com.library.system.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.DTO.BookDTO;
import com.library.system.Entities.Author;
import com.library.system.Entities.Book;
import com.library.system.Entities.Genre;
import com.library.system.Repository.AuthorRepository;
import com.library.system.Repository.BookRepository;
import com.library.system.Repository.GenreRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByGenre(String genreName) {
        return bookRepository.findByGenre_GenreName(genreName);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findByAuthor_AuthorName(authorName);
    }

    public List<Book> getBooksByPublicationYear(int publicationYear) {
        return bookRepository.findByPublicationYear(publicationYear);
    }

    public Book addBook(BookDTO bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        // book.setGenre(bookDto.getGenreIds());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setQuantity(bookDto.getQuantityAvailable());

        Author author;
        try {
            author = authorRepository.findByAuthorName(bookDto.getAuthorName());
            if (author.equals(null)) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            Author new_author = new Author();
            new_author.setAuthorName(bookDto.getAuthorName());
            author = authorRepository.save(new_author);
        }
        book.setAuthor(author);

        Genre genre;
        try {
            genre = genreRepository.findByGenreName(bookDto.getGenreName());
            if (genre.equals(null)) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            Genre new_Genre = new Genre();
            new_Genre.setGenreName(bookDto.getGenreName());
            genre = genreRepository.save(new_Genre);
        }
        book.setGenre(genre);

        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, BookDTO bookDto) {

        Book old_book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (bookDto.getTitle().isEmpty()) {
            old_book.setTitle(old_book.getTitle());
        } else {
            old_book.setTitle(bookDto.getTitle());
        }

        if (bookDto.getQuantityAvailable() < 0) {
            old_book.setQuantity(old_book.getQuantity());
        } else if (bookDto.getQuantityAvailable() == 0) {
            old_book.setQuantity(0);
        } else {
            old_book.setQuantity(bookDto.getQuantityAvailable());
        }

        if (bookDto.getPublicationYear() <= 0 || bookDto.getPublicationYear() > 9999) {
            old_book.setPublicationYear(old_book.getPublicationYear());
        } else {
            old_book.setPublicationYear(bookDto.getPublicationYear());
        }

        // old_book.setQuantity(bookDto.getQuantityAvailable());
        if (bookDto.getAuthorName().isEmpty()) {
            old_book.setAuthor(old_book.getAuthor());
        } else {
            Author author;
            try {
                author = authorRepository.findByAuthorName(bookDto.getAuthorName());
                if (author.equals(null)) {
                    throw new NullPointerException();
                }
            } catch (NullPointerException e) {
             
                Author new_author = new Author();
                new_author.setAuthorName(bookDto.getAuthorName());
                author = authorRepository.save(new_author);
            }
            old_book.setAuthor(author);
        }

        if (bookDto.getGenreName().isEmpty()) {
            old_book.setGenre(old_book.getGenre());
        } else {
            Genre genre;
            try {
                genre = genreRepository.findByGenreName(bookDto.getGenreName());
                if (genre.equals(null)) {
                    throw new NullPointerException();
                }
            } catch (NullPointerException e) {
                Genre new_Genre = new Genre();
                new_Genre.setGenreName(bookDto.getGenreName());
                genre = genreRepository.save(new_Genre);
            }
            old_book.setGenre(genre);
        }

        return bookRepository.save(old_book);
    }

    public Book getBook(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        return book;
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
