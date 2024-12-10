package kg.attractor.movie_review.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieMvcDto {
    private Long id;
    private String name;
    private Integer releaseYear;
    private String description;
    private String director;
    private List<CastMemberDto> castMembers;

}
