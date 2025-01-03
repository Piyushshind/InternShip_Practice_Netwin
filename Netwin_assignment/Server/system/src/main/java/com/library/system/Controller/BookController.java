package com.library.system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.system.DTO.BookDTO;
import com.library.system.Entities.Book;
import com.library.system.Services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/filter")
    public List<Book> getBooksByFilters(
        @RequestParam(required = false) String genreName,
        @RequestParam(required = false) String authorName,
        @RequestParam(required = false) Integer publicationYear
    ) {
        if (genreName != null) {
            return bookService.getBooksByGenre(genreName);
        } else if (authorName != null) {
            return bookService.getBooksByAuthor(authorName);
        } else if (publicationYear != null) {
            return bookService.getBooksByPublicationYear(publicationYear);
        } else {
            return bookService.getAllBooks();
        }
    }

    @PostMapping
    public Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody BookDTO book) {
        return bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}

