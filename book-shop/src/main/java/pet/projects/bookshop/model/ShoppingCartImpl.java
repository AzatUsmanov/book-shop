package pet.projects.bookshop.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.tool.exception.BookAlreadyInCartException;
import pet.projects.bookshop.tool.exception.BookNotFoundInCartException;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCartImpl implements ShoppingCart {

    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        if (books.contains(book)) {
            throw new BookAlreadyInCartException();
        }
        books.add(book);
    }

    public void delete(Book book) {
        if (!books.contains(book)) {
            throw new BookNotFoundInCartException();
        }
        books.remove(book);
    }

    public void clear(){
        books.clear();
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

}
