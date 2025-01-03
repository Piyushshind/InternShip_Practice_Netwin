package com.library.system.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.system.DTO.ReviewDTO;
import com.library.system.Entities.Book;
import com.library.system.Entities.Review;
import com.library.system.Entities.User;
import com.library.system.Repository.BookRepository;
import com.library.system.Repository.ReviewRepository;
import com.library.system.Repository.UserRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    public List<ReviewDTO> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBook_BookId(bookId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewId(review.getReviewId());
        reviewDTO.setBookId(review.getBook().getBookId());
        reviewDTO.setUserId(review.getUser().getUserId());
        reviewDTO.setReviewContent(review.getReviewContent());
        reviewDTO.setRating(review.getRating());
        return reviewDTO;
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setReviewId(reviewDTO.getReviewId());

        Book book = bookRepository.findById(reviewDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        review.setBook(book);

        User user = userRepository.findById(reviewDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        review.setUser(user);

        review.setReviewContent(reviewDTO.getReviewContent());
        review.setRating(reviewDTO.getRating());
        return review;
    }
}
