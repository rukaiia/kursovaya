package kg.attractor.movie_review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private long movieId;
    private String reviewer;
    private int rating;
    private String comment;
    private String createTime;
}
