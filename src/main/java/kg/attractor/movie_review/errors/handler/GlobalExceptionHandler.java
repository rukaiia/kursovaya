package kg.attractor.movie_review.errors.handler;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.service.ErrorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
//@RestControllerAdvice
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

//    @ExceptionHandler(NoSuchElementException.class)
//    private ResponseEntity<?> noSuchElementHandler(NoSuchElementException exception) {
//        return new ResponseEntity<>(errorService.makeBody(exception), HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    private ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
//        return new ResponseEntity<>(errorService.makeBody(exception.getBindingResult()), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    private String notFound(HttpServletRequest request, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "/errors/error";
    }
}
