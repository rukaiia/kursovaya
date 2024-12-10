package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.model.Director;
import kg.attractor.movie_review.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public Optional<Director> findDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    public long save(DirectorDto director) {
        return directorRepository.save(Director.builder()
                .fullName(director.getFullName())
                .build()).getId();
    }

    public Optional<Director> findDirectorByName(String fullName) {
        return directorRepository.findByFullName(fullName);
    }
}
