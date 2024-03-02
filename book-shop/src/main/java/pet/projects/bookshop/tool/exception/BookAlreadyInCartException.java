package pet.projects.bookshop.tool.exception;

public class BookAlreadyInCartException extends RuntimeException {

    public BookAlreadyInCartException() {
    }

    public BookAlreadyInCartException(String message) {
        super(message);
    }

    public BookAlreadyInCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyInCartException(Throwable cause) {
        super(cause);
    }

}
