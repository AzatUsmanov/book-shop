package pet.projects.bookshop.rest.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pet.projects.bookshop.model.ErrorDetails;



@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> DataIntegrityViolationExceptionHandle(
            DataIntegrityViolationException exception
    ) {
        log.error("attempt to violate data integrity", exception);
        final var errorDetails = new ErrorDetails("user already exist");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

}
