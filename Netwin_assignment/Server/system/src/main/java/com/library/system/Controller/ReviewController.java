package com.library.system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.system.DTO.ReviewDTO;
import com.library.system.Services.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @GetMapping("/book/{bookId}")
    public List<ReviewDTO> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBook(bookId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
