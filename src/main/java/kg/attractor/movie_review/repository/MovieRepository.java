package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>, CrudRepository<Movie, Long> {
    @Query("select m from Movie as m where m.name = :name")
    Optional<Movie> findMovieByName(String name);

    @Query("""
            select m.id,
                   m.name,
                   m.releaseYear,
                   m.description,
                   m.director.id
            from Movie m,
                 MovieCastMember mcm
            where mcm.castMember.id = :castMemberId
              and m.id = mcm.movie.id
            """)
    List<Movie> findByMovieCastMembersCastMemberId(Long castMemberId);


    //    @Query("""
//            select
//                m.id,
//                m.name,
//                m.releaseYear,
//                m.description,
//                m.director
//            from Movie m
//                inner join MovieCastMember as mcm on mcm.movie.id = m.id
//                inner join CastMember  as cm on mcm.castMember.id = cm.id
//            where m.releaseYear >= :releaseYear
//            and cm.fullName like '%:castMemberName0%'
//            """)
    @Query(nativeQuery = true,
            value = """
                    select
                        m.ID,
                        m.NAME,
                        m.RELEASE_YEAR,
                        m.DESCRIPTION,
                        m.DIRECTOR_ID
                    from MOVIE m
                        inner join MOVIE_CAST_MEMBER MCM on m.ID = MCM.MOVIE_ID
                        inner join CAST_MEMBER CM on CM.ID = MCM.CAST_MEMBER_ID
                    where m.RELEASE_YEAR >= :releaseYear
                    and cm.FULLNAME like '%:castMemberName0%'
                    """)
    List<Movie> selectMoviesByActorAndReleaseYear(int releaseYear, String castMemberName);
}
