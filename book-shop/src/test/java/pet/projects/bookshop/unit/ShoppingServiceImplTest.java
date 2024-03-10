package pet.projects.bookshop.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.dto.Role;
import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.repositories.BookRepository;
import pet.projects.bookshop.repositories.PurchaseRepository;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.impl.ShoppingServiceImpl;
import pet.projects.bookshop.tool.exception.BookNotFoundException;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;
import pet.projects.bookshop.model.ShoppingCart;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ShoppingServiceImplTest  {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private ShoppingCart shoppingCart;
    @InjectMocks
    private ShoppingServiceImpl service;

    @Test
    public void putBookInCartByNameAndAuthor() {
        final var name = "name";
        final var author = "author";
        final var book = buildBook();
        when(bookRepository.findByNameAndAuthor(name, author))
                .thenReturn(Optional.of(book));

        service.putBookInCartByNameAndAuthor(name, author);

        verify(shoppingCart, times(1)).add(book);
    }

    @Test
    public void putNonExistentBookInCartByNameAndAuthor() {
        final var name = "name";
        final var author = "author";
        when(bookRepository.findByNameAndAuthor(name, author))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putBookInCartByNameAndAuthor(name, author))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    public void deleteBookInCartByNameAndAuthor() {
        final var name = "name";
        final var author = "author";
        final var book = buildBook();
        when(bookRepository.findByNameAndAuthor(name, author))
                .thenReturn(Optional.of(book));

        service.deleteBookFromCartByNameAndAuthor(name, author);

        verify(shoppingCart, times(1)).delete(book);
    }
    @Test
    public void deleteNonExistentBookInCartByNameAndAuthor() {
        final var name = "name";
        final var author = "author";
        when(bookRepository.findByNameAndAuthor(name, author))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putBookInCartByNameAndAuthor(name, author))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    public void buyBooksFromCartWithNonExistentUser() {
        final var username = "username";
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buyBooksFromCart(username))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void buyBooksFromCartWithNotEnoughMoney() {
        final var notEnoughMoney = BigDecimal.ZERO;
        final var user = buildUser();
        final var username = user.getUsername();
        final var books = buildBooks();
        user.setMoneyInAccount(notEnoughMoney);
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        when(shoppingCart.getBooks())
                .thenReturn(books);


        assertThatThrownBy(() -> service.buyBooksFromCart(username))
                .isInstanceOf(NotEnoughMoneyInAccountException.class);
    }

    @Test
    public void buyBooksFromCartWithCorrectData() {
        final var user = buildUser();
        final var username = user.getUsername();
        final var books = buildBooks();
        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        when(shoppingCart.getBooks())
                .thenReturn(new ArrayList<>());
        user.setMoneyInAccount(BigDecimal.ONE);

        service.buyBooksFromCart(username);

        verify(userRepository, times(1)).deleteByUsername(username);
        verify(userRepository, times(1)).save(user);
    }

    private User buildUser() {
        return User.builder()
                .id(1)
                .username("username")
                .role(Role.USER)
                .password("password")
                .moneyInAccount(BigDecimal.TEN)
                .purchases(new ArrayList<>())
                .build();
    }

    private List<Book> buildBooks() {
        final var books = new ArrayList<Book>();

        for (int i = 0; i < 9; i++) {
            final var book = Book.builder()
                    .id(i)
                    .name("name" + String.valueOf(i))
                    .author("author" + String.valueOf(i))
                    .purchases(new ArrayList<>())
                    .description("description")
                    .price(BigDecimal.ONE)
                    .build();
            books.add(book);
        }
        return books;
    }

    private Book buildBook() {
        return Book.builder()
                .id(1)
                .name("name")
                .author("author")
                .description("description")
                .purchases(new ArrayList<>())
                .price(BigDecimal.ZERO)
                .build();
    }

    private List<Purchase> buildPurchases(List<Book> books, User user) {
        final var purchases = new ArrayList<Purchase>(books.size());

        for (var book : books) {
            final var time = new Timestamp(System.currentTimeMillis());
            final var purchase = Purchase.builder()
                    .id(Integer.MAX_VALUE)
                    .user(user)
                    .book(book)
                    .time(time)
                    .build();

            purchases.add(purchase);
        }
        return purchases;
    }
}
