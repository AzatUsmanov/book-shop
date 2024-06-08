package pet.projects.bookshop.tool.exception;

import java.util.NoSuchElementException;

public class PurchaseNotFoundException extends Exception {

    public PurchaseNotFoundException() {
    }

    public PurchaseNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }

    public PurchaseNotFoundException(Throwable cause) {
        super(cause);
    }

    public PurchaseNotFoundException(String s) {
        super(s);
    }

}
