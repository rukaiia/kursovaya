package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.ReviewDto;
import kg.attractor.movie_review.model.Review;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.repository.MovieRepository;
import kg.attractor.movie_review.repository.ReviewRepository;
import kg.attractor.movie_review.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");

    public void addReview(ReviewDto reviewDto, Authentication auth) {
        User user = (User) auth.getPrincipal();
        log.info("User: {}", user);
        reviewRepository.save(Review.builder()
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .movie(movieRepository.findById(reviewDto.getMovieId()).orElseThrow(() -> new NoSuchElementException("Movie not found")))
                .reviewer(userRepository.findById(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found")))
                .createTime(LocalDateTime.now())
                .build());
    }

    public List<ReviewDto> getReviews() {
        return reviewRepository.findAll().stream()
                .map(e -> ReviewDto.builder()
                        .comment(e.getComment())
                        .reviewer(e.getReviewer().getEmail())
                        .rating(e.getRating())
                        .movieId(e.getMovie().getId())
                        .createTime(e.getCreateTime().format(FORMATTER))
                        .build())
                .toList();
    }

    @Transactional
    public List<ReviewDto> getReviewsByMovieId(Long movieId, String sortCriteria, int page, int size) {
//        Sort s1 = Sort.by(Sort.Order.asc("reviewer"), Sort.Order.asc("rating"));
//        Sort s2 = Sort.by(Sort.Order.asc("reviewer"), Sort.Order.desc("rating"));

//        Sort sort = Sort.by(sortCriteria);

//        int page = 1;
//        int count = 3;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortCriteria));
//        Page<Review> pageRequest = reviewRepository.findAll(pageable);


        List<Review> reviews = reviewRepository.findByMovieId(movieId, pageable);
        return reviews.stream()
                .map(e -> ReviewDto.builder()
                        .rating(e.getRating())
                        .comment(e.getComment())
                        .reviewer(e.getReviewer().getEmail())
                        .createTime(e.getCreateTime().format(FORMATTER))
                        .build())
                .toList();
    }
}
