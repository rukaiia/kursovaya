package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.service.MovieImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class MovieImageController {
    private final MovieImageService movieImageService;

    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable long imageId) {
        return movieImageService.downloadImage(imageId);
    }

    @PostMapping("/upload")
    public HttpStatus uploadImage(MovieImageDto movieImageDto) {
        movieImageService.uploadImage(movieImageDto);
        return HttpStatus.OK;
    }

    @GetMapping("{movieId}")
    public ResponseEntity<?> getImageByMovie(@PathVariable Long movieId) {
        return movieImageService.getImageByMovieId(movieId);
    }
}
