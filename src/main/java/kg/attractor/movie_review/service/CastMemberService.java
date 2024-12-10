package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.CastMemberDto;
import kg.attractor.movie_review.model.CastMember;
import kg.attractor.movie_review.repository.CastMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CastMemberService {
    private final CastMemberRepository repository;

    public List<CastMemberDto> getCastMembersByMovieId(Long movieId) {
        List<CastMember> members = repository.findByMovieId(movieId);
        return members.stream()
                .map(e -> CastMemberDto.builder()
                        .id(e.getId())
                        .fullName(e.getFullName())
                        .build()
                )
                .toList();
    }

    public Optional<CastMember> findCastMemberByName(String name) {
        return repository.findByFullName(name);
    }

    public void delete(Long id) {
        repository.delete(
                repository
                        .findById(id)
                        .orElseThrow(
                                () -> new NoSuchElementException("Cast member not found")
                        ));
    }

    public long save(CastMemberDto castMemberDto) {
        return repository.save(CastMember.builder().fullName(castMemberDto.getFullName()).build()).getId();
    }

    public CastMember getById(long castMemberId) {
        return repository.findById(castMemberId).orElseThrow(() -> new NoSuchElementException("Cast member not found"));
    }
}
