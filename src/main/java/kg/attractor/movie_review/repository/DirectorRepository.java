package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Director;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long> {
    Optional<Director> findByFullName(String fullName);
}
