package pet.projects.bookshop.model;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.tool.exception.BookAlreadyInCartException;
import pet.projects.bookshop.tool.exception.BookNotFoundInCartException;

import java.util.List;

public interface ShoppingCart {
     void add(Book book) throws BookAlreadyInCartException;
    void delete(Book book) throws BookNotFoundInCartException;
    void clear();
    List<Book> getBooks();
}
