package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Book;

import java.util.List;

public interface ShoppingService {
    void putBookInCartByNameAndAuthor(String name, String author);
    void deleteBookFromCartByNameAndAuthor(String name, String author);
    void clearCart();
    void buyBooksFromCart(String username);
    List<Book> getBooksFromCart();
}
