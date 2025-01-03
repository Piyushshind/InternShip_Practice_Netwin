package com.library.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.system.Entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreName(String genreName);
}
