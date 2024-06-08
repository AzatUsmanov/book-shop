package pet.projects.bookshop.tool.exception;

import java.util.NoSuchElementException;

public class BookNotFoundInCartException extends Exception {

    public BookNotFoundInCartException() {
    }

    public BookNotFoundInCartException(String message) {
        super(message);
    }

    public BookNotFoundInCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundInCartException(Throwable cause) {
        super(cause);
    }

}
