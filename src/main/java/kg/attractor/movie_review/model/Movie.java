package kg.attractor.movie_review.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    private String name;
    private Integer releaseYear; // Y -> _y
    private String description;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToOne(mappedBy = "movie")
    @PrimaryKeyJoinColumn
    private MovieImage movieImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<MovieCastMember> movieCastMembers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    List<Review> reviews;
}
