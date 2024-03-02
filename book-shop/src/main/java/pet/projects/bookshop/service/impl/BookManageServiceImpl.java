package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.projects.bookshop.model.Book;
import pet.projects.bookshop.repositories.BookRepository;
import pet.projects.bookshop.service.inter.BookManageService;
import pet.projects.bookshop.tool.exception.BookAlreadyExistException;
import pet.projects.bookshop.tool.exception.BookNotFoundException;

@Slf4j
@Service
@AllArgsConstructor
public class BookManageServiceImpl implements BookManageService {

    private final BookRepository bookRepository;


    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException());
    }

    @Override
    public void add(Book book) {
        if (isPresent(book)) {
            throw new BookAlreadyExistException();
        }
        bookRepository.save(book);

        log.info("book " + book.toString() + " saved successfully");
    }

    @Override
    public void deleteById(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(id);

        log.info("book with id = " + id.toString() + " deleted successfully");
    }

    @Override
    @Transactional
    public void update(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new BookNotFoundException();
        }
        bookRepository.save(book);

        log.info("book with id = " + book.getId().toString() + " updated for " + book.toString() );
    }

    private boolean isPresent(Book book) {
        return bookRepository.existsByNameAndAuthor(
                book.getName(),
                book.getAuthor()
        );
    };

}
