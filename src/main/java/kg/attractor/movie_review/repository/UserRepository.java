package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByEmail(String email);
}
