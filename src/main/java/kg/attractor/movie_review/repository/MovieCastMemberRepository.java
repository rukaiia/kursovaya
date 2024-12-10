package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.MovieCastMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieCastMemberRepository extends CrudRepository<MovieCastMember, Long> {
    @Query("""
            select mcm.role
                            from MovieCastMember mcm
                            where mcm.movie.id = :movieId
                            and mcm.castMember.id = :castMemberId
            """)
    String findRoleByMovieIdAndCastMemberId(Long movieId, Long castMemberId);
}
