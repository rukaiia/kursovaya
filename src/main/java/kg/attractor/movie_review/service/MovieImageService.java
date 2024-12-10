package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.model.MovieImage;
import kg.attractor.movie_review.repository.MovieImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieImageService {
    private static final String SUB_DIR = "images";
    private final FileService fileService;
    private final MovieImageRepository repository;

    private final MovieService movieService;

    public void uploadImage(MovieImageDto movieImageDto) {
        String fileName = fileService.saveUploadedFile(movieImageDto.getFile(), SUB_DIR);
        MovieImage mi = MovieImage.builder()
                .movie(movieService.getMovieById(movieImageDto.getMovieId()))
                .fileName(fileName)
                .build();
        repository.save(mi);
    }

    public ResponseEntity<?> downloadImage(long imageId) {
        String fileName;
        try {
            MovieImage mi = repository.findById(imageId).orElseThrow(() -> new NoSuchElementException("Image not found"));
            fileName = mi.getFileName();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Image not found");
        }
        return fileService.getOutputFile(fileName, SUB_DIR, MediaType.IMAGE_JPEG);
    }

    public ResponseEntity<?> getImageByMovieId(Long movieId) {
        Optional<MovieImage> mayBeImage = repository.findByMovieId(movieId);
        if (mayBeImage.isEmpty()) {
            return fileService.getOutputFile("no_image.jpeg", SUB_DIR, MediaType.IMAGE_JPEG);
        }
        return fileService.getOutputFile(mayBeImage.get().getFileName(), SUB_DIR, MediaType.IMAGE_JPEG);
    }
}
