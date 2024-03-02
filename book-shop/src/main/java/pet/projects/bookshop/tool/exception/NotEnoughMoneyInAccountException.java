package pet.projects.bookshop.tool.exception;

public class NotEnoughMoneyInAccountException extends RuntimeException {

    public NotEnoughMoneyInAccountException() {
    }

    public NotEnoughMoneyInAccountException(String message) {
        super(message);
    }

    public NotEnoughMoneyInAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyInAccountException(Throwable cause) {
        super(cause);
    }

}
