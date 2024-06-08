package pet.projects.bookshop.rest;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.service.inter.BookManageService;
import pet.projects.bookshop.tool.exception.BookAlreadyExistException;
import pet.projects.bookshop.tool.exception.BookNotFoundException;

@RestController
@AllArgsConstructor
public class BookManageController {

    private final BookManageService bookManageService;

    @GetMapping("/management/book/{id}")
    Book findById(@PathVariable Integer id) throws BookNotFoundException {
        return bookManageService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/management/book")
    void add(@RequestBody Book book) throws BookAlreadyExistException {
        bookManageService.add(book);
    }

    @DeleteMapping("/management/book/{id}")
    void deleteById(@PathVariable Integer id) throws BookNotFoundException {
        bookManageService.deleteById(id);
    }

    @PatchMapping("/management/book")
    void updateById(@RequestParam Book book) throws BookNotFoundException {
        bookManageService.update(book);
    }

}
