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

import com.library.system.DTO.GenreDTO;
import com.library.system.Entities.Genre;
import com.library.system.Repository.GenreRepository;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @PostMapping
    public Genre addGenre(@RequestBody GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreName(genreDTO.getGenreName());
        return genreRepository.save(genre);
    }

    @PutMapping("/{genreId}")
    public Genre updateGenre(@PathVariable Long genreId, @RequestBody Genre genreDetails) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
        genre.setGenreName(genreDetails.getGenreName());
        return genreRepository.save(genre);
    }

    @DeleteMapping("/{genreId}")
    public void deleteGenre(@PathVariable Long genreId) {
        genreRepository.deleteById(genreId);
    }
}
