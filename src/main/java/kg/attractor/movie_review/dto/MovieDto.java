package kg.attractor.movie_review.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieDto {
    private Long id;
    private String name;
    private Integer releaseYear;
    private String description;
    private DirectorDto director;
    private List<CastMemberDto> castMembers;
}
