package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.ReviewDto;
import kg.attractor.movie_review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getReviews() {
        return reviewService.getReviews();
    }

    @GetMapping("{movieId}")
    public List<ReviewDto> getReviewsByMovie(@PathVariable Long movieId) {
        return reviewService.getReviewsByMovieId(movieId, "createTime", 0, 200);
    }

    @PostMapping
    public HttpStatus addReview(@RequestBody ReviewDto reviewDto, Authentication auth) {
        reviewService.addReview(reviewDto, auth);
        return HttpStatus.OK;
    }
}
