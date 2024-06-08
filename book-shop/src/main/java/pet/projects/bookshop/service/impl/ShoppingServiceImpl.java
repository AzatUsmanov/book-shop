package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.repositories.BookRepository;
import pet.projects.bookshop.repositories.PurchaseRepository;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.inter.ShoppingService;
import pet.projects.bookshop.tool.exception.BookAlreadyInCartException;
import pet.projects.bookshop.tool.exception.BookNotFoundException;
import pet.projects.bookshop.tool.exception.BookNotFoundInCartException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;
import pet.projects.bookshop.model.ShoppingCart;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {

    private final static int COMPARISON_LESS_THAN_ZERO = -1;

    private final static int TEMP_ID_VALUE = Integer.MAX_VALUE;

    private final ShoppingCart shoppingCart;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final PurchaseRepository purchaseRepository;

    @Override
    public void putBookInCartByNameAndAuthor(String name, String author) throws BookNotFoundException, BookAlreadyInCartException {
        final var book = bookRepository.findByNameAndAuthor(name, author)
                .orElseThrow(BookNotFoundException::new);

        shoppingCart.add(book);

        log.info("The book " + book.toString() + "has been successfully added to cart");
    }

    @Override
    public void deleteBookFromCartByNameAndAuthor(String name, String author) throws BookNotFoundException, BookNotFoundInCartException {
        final var book = bookRepository.findByNameAndAuthor(name, author)
                .orElseThrow(BookNotFoundException::new);

        shoppingCart.delete(book);

        log.info("The book " + book.toString() + "has been successfully deleted from cart");
    }

    @Override
    public void clearCart() {
        shoppingCart.clear();

        log.info("the recycle bin was successfully cleared");
    }

    @Override
    @Transactional
    public void buyBooksFromCart(String username) throws UserNotFoundException, NotEnoughMoneyInAccountException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        final var booksInCart = shoppingCart.getBooks();
        final var moneyInAccount = user.getMoneyInAccount();
        final var coastOfBooksInCart = calculateCostOfBooks(booksInCart);

        if (moneyInAccount.compareTo(coastOfBooksInCart)
                == COMPARISON_LESS_THAN_ZERO) {
            throw new NotEnoughMoneyInAccountException();
        }
        final var difference = moneyInAccount.add(coastOfBooksInCart.negate());
        user.setMoneyInAccount(difference);

        userRepository.save(user);

        final var purchases = buildPurchases(booksInCart, user);
        purchaseRepository.saveAll(purchases);

        log.info("books from the cart were successfully purchased");
    }

    @Override
    public List<Book> getBooksFromCart() {
        return shoppingCart.getBooks();
    }

    private BigDecimal calculateCostOfBooks(List<Book> books) {
        var cost = BigDecimal.ZERO;

        for (var book : books) {
            cost = cost.add(book.getPrice());
        }
        return cost;
    }

    private List<Purchase> buildPurchases(List<Book> books, User user) {
        final var purchases = new ArrayList<Purchase>(books.size());

        for (var book : books) {
            final var time = new Timestamp(System.currentTimeMillis());
            final var purchase = Purchase.builder()
                    .id(Integer.MAX_VALUE)
                    .time(time)
                    .user(user)
                    .book(book)
                    .build();

            purchases.add(purchase);
        }
        return purchases;
    }

}
