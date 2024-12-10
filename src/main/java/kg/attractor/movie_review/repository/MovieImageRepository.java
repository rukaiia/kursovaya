package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.MovieImage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieImageRepository extends CrudRepository<MovieImage, Long> {
    Optional<MovieImage> findByMovieId(Long movieId);
}
