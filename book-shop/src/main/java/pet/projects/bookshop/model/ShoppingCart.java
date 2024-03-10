package pet.projects.bookshop.model;

import pet.projects.bookshop.dto.Book;

import java.util.List;

public interface ShoppingCart {
     void add(Book book);
    void delete(Book book);
    void clear();
    List<Book> getBooks();
}
