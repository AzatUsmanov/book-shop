package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.repositories.BookRepository;
import pet.projects.bookshop.service.inter.BookSearchService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
