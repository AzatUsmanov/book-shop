package pet.projects.bookshop.rest.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pet.projects.bookshop.model.ErrorDetails;
import pet.projects.bookshop.tool.exception.UserAlreadyRegisteredException;

@Slf4j
@RestControllerAdvice
public class RegistrationControllerAdvice {

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorDetails> userAlreadyRegisteredExceptionHandle(
            UserAlreadyRegisteredException exception
    ) {
        log.error("user already registered", exception);
        final var errorDetails = new ErrorDetails("user already registered");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

}
