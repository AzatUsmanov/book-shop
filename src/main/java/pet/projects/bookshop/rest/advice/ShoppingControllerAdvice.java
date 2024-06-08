package pet.projects.bookshop.rest.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pet.projects.bookshop.dto.ErrorDetails;
import pet.projects.bookshop.tool.exception.BookAlreadyInCartException;
import pet.projects.bookshop.tool.exception.BookNotFoundInCartException;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;

@Slf4j
@RestControllerAdvice
public class ShoppingControllerAdvice {

    @ExceptionHandler(BookAlreadyInCartException.class)
    public ResponseEntity<ErrorDetails> bookAlreadyInCartExceptionHandle(
            BookAlreadyInCartException exception
    ) {
        final var errorDetails = new ErrorDetails("Book already in cart");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(BookNotFoundInCartException.class)
    public ResponseEntity<ErrorDetails> bookNotFoundInCartExceptionHandle(
            BookNotFoundInCartException exception
    ) {
        log.error("Book not found in cart", exception);
        final var errorDetails = new ErrorDetails("Book not found in cart");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(NotEnoughMoneyInAccountException.class)
    public ResponseEntity<ErrorDetails> notEnoughMoneyInAccountExceptionHandle(
            NotEnoughMoneyInAccountException exception
    ) {
        log.error("not enough money in account exception", exception);
        final var errorDetails = new ErrorDetails("not enough money in account exception");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

}
