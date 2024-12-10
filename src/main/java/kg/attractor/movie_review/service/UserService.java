package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void createUser(UserDto userDto) {
        repository.save(User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(encoder.encode(userDto.getPassword()))
                .enabled(Boolean.TRUE)
                .build());

    }
}
