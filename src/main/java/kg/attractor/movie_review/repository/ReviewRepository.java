package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>, JpaRepository<Review, Long> {

    @Query("select r from Review r where r.movie.id = :movieId")
    List<Review> findByMovieId(Long movieId, Pageable pageable);

    List<Review> findByMovie_IdOrderByRatingDesc(Long movieId);

    Page<Review> findAll(Pageable pageable);
}
