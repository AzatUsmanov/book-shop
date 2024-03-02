package pet.projects.bookshop.rest.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pet.projects.bookshop.model.ErrorDetails;
import pet.projects.bookshop.tool.exception.BookNotFoundException;

@Slf4j
@RestControllerAdvice
public class BookSearchControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> bookNotFoundExceptionHandle(
            BookNotFoundException exception
    ) {
        log.error("book not found", exception);
        final var errorDetails = new ErrorDetails("book not found");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

}
