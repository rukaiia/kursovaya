package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.CastMemberDto;
import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.dto.MovieMvcDto;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.service.MovieService;
import kg.attractor.movie_review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieMvcController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping
    public String getMovies(Model model) {
        var movies = movieService.getMovies(0, 9, "id");
        model.addAttribute("movies", movies);
        return "movies/movies";
    }

    @GetMapping("/{movieId}")
    public String getMovie(
            @PathVariable Long movieId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            Model model
    ) {
        model.addAttribute("movie", movieService.getMovieDtoById(movieId));
        model.addAttribute("reviews", reviewService.getReviewsByMovieId(movieId, "createTime", page, size));
        return "movies/movie_info";
    }

    @GetMapping("add")
    public String addMovie() {
        return "movies/add_movie";
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addMovie(
            @RequestParam(name = "cast_member_name") String castMemberName,
            @RequestParam(name = "cast_member_role") String castMemberRole,
            MovieMvcDto movieDto,
            Authentication auth
    ) {
        movieService.saveMovie(
                MovieDto.builder()
                        .name(movieDto.getName())
                        .releaseYear(movieDto.getReleaseYear())
                        .description(movieDto.getDescription())
                        .director(DirectorDto.builder()
                                .fullName(movieDto.getDirector())
                                .build())
                        .castMembers(List.of(
                                CastMemberDto.builder()
                                        .fullName(castMemberName)
                                        .role(castMemberRole)
                                        .build()
                        ))
                        .build(),
                auth
        );

        return "redirect:/";
    }
    @PostMapping("/{movieId}/add-rating")
    public String addRating(@PathVariable Long movieId, @ModelAttribute RatingDto ratingDto, Authentication auth, Model model) {
        Long userId = ((UserDetailsImpl) auth.getPrincipal()).getId();

        ratingService.addRating(movieId, userId, ratingDto.getRating(), ratingDto.getComment());

        double averageRating = ratingService.getAverageRating(movieId);

        model.addAttribute("movie", movieRepository.findById(movieId).orElseThrow());
        model.addAttribute("averageRating", averageRating);

        return "redirect:/";
    }



    @GetMapping("/{movieId}/average")
    public String getAverageRating(@PathVariable Long movieId, Model model) {
        double averageRating = ratingService.getAverageRating(movieId);
        model.addAttribute("averageRating", averageRating);

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        model.addAttribute("movie", movie);
        return "rating/movie-rating";
    }
}
}
