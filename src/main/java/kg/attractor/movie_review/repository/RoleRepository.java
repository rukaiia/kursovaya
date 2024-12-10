package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
//    Role findByRole(String role);
}
