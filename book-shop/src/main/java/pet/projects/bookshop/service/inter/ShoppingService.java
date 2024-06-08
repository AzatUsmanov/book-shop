package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.tool.exception.BookAlreadyInCartException;
import pet.projects.bookshop.tool.exception.BookNotFoundException;
import pet.projects.bookshop.tool.exception.BookNotFoundInCartException;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.util.List;

public interface ShoppingService {
    void putBookInCartByNameAndAuthor(String name, String author) throws BookNotFoundException, BookAlreadyInCartException;
    void deleteBookFromCartByNameAndAuthor(String name, String author) throws BookNotFoundException, BookNotFoundInCartException;
    void clearCart();
    void buyBooksFromCart(String username) throws UserNotFoundException, NotEnoughMoneyInAccountException;
    List<Book> getBooksFromCart();
}
