package com.library.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.system.Entities.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author  findByAuthorName(String authorName);
}

