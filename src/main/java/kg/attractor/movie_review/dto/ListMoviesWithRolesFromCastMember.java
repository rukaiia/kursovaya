package kg.attractor.movie_review.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListMoviesWithRolesFromCastMember {
    private CastMemberDto castMember;
    private List<MovieDto> movies;
    private String role;

}
