package pet.projects.bookshop.tool.exception;

import java.util.NoSuchElementException;

public class BookNotFoundException extends Exception {

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundException(Throwable cause) {
        super(cause);
    }

}
