package com.library.system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.system.Entities.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre_GenreName(String genreName);
    List<Book> findByAuthor_AuthorName(String authorName);
    List<Book> findByPublicationYear(int publicationYear);
    
}
