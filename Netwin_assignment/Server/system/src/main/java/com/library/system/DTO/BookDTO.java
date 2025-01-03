package com.library.system.DTO;

public class BookDTO {

    private String title;
    private int publicationYear;
    private Long authorId;
    private String authorName;
    private String genreName;
    private Long genreId;
    private int quantityAvailable;

    
    public String getGenreName() {
        return genreName;
    }
 
    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }
}
