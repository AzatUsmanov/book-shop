package pet.projects.bookshop.rest.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pet.projects.bookshop.model.ErrorDetails;

import pet.projects.bookshop.tool.exception.UserAlreadyExistException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;
import pet.projects.bookshop.tool.exception.BookAlreadyExistException;
import pet.projects.bookshop.tool.exception.PurchaseNotFoundException;


@Slf4j
@RestControllerAdvice
public class ManagementControllerAdvice {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> userAlreadyExistExceptionHandle(
            UserAlreadyExistException exception
    ) {
        log.error("user already exist", exception);
        final var errorDetails = new ErrorDetails("user already exist");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundExceptionHandle(
            UserNotFoundException exception
    ) {
        log.error("user not found", exception);
        final var errorDetails = new ErrorDetails("user not found");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> bookAlreadyExistExceptionHandle(
            BookAlreadyExistException exception
    ) {
        log.error("book already exist", exception);
        final var errorDetails = new ErrorDetails("book already exist");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<ErrorDetails> purchaseNotFoundExceptionHandle(
            PurchaseNotFoundException exception
    ) {
        log.error("purchase not found", exception);
        final var errorDetails = new ErrorDetails("purchase not found");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

}
