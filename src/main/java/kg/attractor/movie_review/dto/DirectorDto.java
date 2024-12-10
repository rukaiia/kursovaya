package kg.attractor.movie_review.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DirectorDto {
    private Long id;
    private String fullName;
}
