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
import org.springframework.web.bind.annotation.RestController;

import com.library.system.DTO.AuthorDTO;
import com.library.system.Entities.Author;
import com.library.system.Repository.AuthorRepository;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAuthorName(authorDTO.getAuthorName());
        return authorRepository.save(author);
    }

    @PutMapping("/{authorId}")
    public Author updateAuthor(@PathVariable Long authorId, @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        author.setAuthorName(authorDetails.getAuthorName());
        return authorRepository.save(author);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
