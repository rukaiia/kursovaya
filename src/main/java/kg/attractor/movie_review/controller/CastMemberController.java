package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.service.CastMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cast_members")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CastMemberController {
    CastMemberService castMemberService;

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Long id) {
        castMemberService.delete(id);
        return HttpStatus.OK;
    }
}
