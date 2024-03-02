package pet.projects.bookshop.rest;

import lombok.AllArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pet.projects.bookshop.model.Book;
import pet.projects.bookshop.service.inter.ShoppingService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ShoppingController {

    private final ShoppingService shoppingService;

    @PutMapping("/shopping")
    void putBookInCartByNameAndAuthor(@RequestParam String name,
                                      @RequestParam String author) {
        shoppingService.putBookInCartByNameAndAuthor(name, author);
    }

    @DeleteMapping("/shopping")
    void deleteBookFromCartByNameAndAuthor(@RequestParam String name,
                                           @RequestParam String author) {
        shoppingService.deleteBookFromCartByNameAndAuthor(name, author);
    }

    @DeleteMapping("/shopping/all")
    void clearCart() {
        shoppingService.clearCart();
    }

    @PostMapping("/shopping")
    void buyBooksFromCart(@AuthenticationPrincipal UserDetails userDetails) {
        shoppingService.buyBooksFromCart(userDetails.getUsername());
    }

    @GetMapping("/shopping")
    List<Book> getBooksFromCart() {
        return shoppingService.getBooksFromCart();
    }

}
