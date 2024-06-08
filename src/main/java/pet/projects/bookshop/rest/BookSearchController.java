package pet.projects.bookshop.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.service.inter.BookSearchService;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @GetMapping("/search/book/name")
    public List<Book> findByName(@RequestParam String name) {
        return bookSearchService.findByName(name);
    }

    @GetMapping("/search/book/author")
    public List<Book> findByAuthor(@RequestParam String author) {
        return bookSearchService.findByAuthor(author);
    }

    @GetMapping("/search/book/all")
    public List<Book> findAll() {
        return bookSearchService.findAll();
    }

}
