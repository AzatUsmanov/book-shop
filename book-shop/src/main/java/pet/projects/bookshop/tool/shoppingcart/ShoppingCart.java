package pet.projects.bookshop.tool.shoppingcart;

import pet.projects.bookshop.model.Book;

import java.util.List;

public interface ShoppingCart {
     void add(Book book);
    void delete(Book book);
    void clear();
    List<Book> getBooks();
}
