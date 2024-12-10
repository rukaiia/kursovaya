package kg.attractor.movie_review.service;

import kg.attractor.movie_review.model.MovieCastMember;
import kg.attractor.movie_review.repository.MovieCastMemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MovieCastMemberService {

    MovieCastMemberRepository repository;

    public long save(MovieCastMember movieCastMember) {
        return repository.save(movieCastMember).getId();
    }

    public String findRoleByMovieIdAndCastMemberId(Long movieId, Long castMemberId) {
        return repository.findRoleByMovieIdAndCastMemberId(
                movieId,
                castMemberId);
    }

}
