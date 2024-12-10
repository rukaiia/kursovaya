package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies") // http://localhost:8089/movies
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping // http://localhost:8089/movies
    public Page<MovieDto> getMovies(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sort
    ) {
        return movieService.getMovies(page, size, sort);
    }

    @GetMapping("/sort/{sortedCriteria}") // http://localhost:8089/movies/sort/by_name
    public ResponseEntity<?> sortMovies(@PathVariable String sortedCriteria) {
        return movieService.sortedListMovies(sortedCriteria);
    }

    @GetMapping(value = "/search") // http://localhost:8089/movies/search?cast_member_name=Cast
    public ResponseEntity<?> findMoviesByCastMemberName(
            @RequestParam(name = "movie_id", required = false) String movieId,
            @RequestParam(name = "movie_name", required = false) String movieName,
            @RequestParam(name = "cast_member_name", required = false) String castMemberName
    ) {
        return movieService.search(movieId, movieName, castMemberName);
    }

    @PostMapping("/add")
    public HttpStatus createMovie(@RequestBody MovieDto movieDto, Authentication auth) {
        movieService.saveMovie(movieDto, auth);
        return HttpStatus.OK;
    }

    @DeleteMapping("{movieId}")
    public HttpStatus delete(@PathVariable Long movieId) {
        movieService.delete(movieId);
        return HttpStatus.OK;
    }

}
