package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Book;
import pet.projects.bookshop.tool.exception.BookAlreadyExistException;
import pet.projects.bookshop.tool.exception.BookNotFoundException;

public interface BookManageService {
    Book findById(Integer id) throws BookNotFoundException;
    void add(Book book) throws BookAlreadyExistException;
    void deleteById(Integer id) throws BookNotFoundException;
    void update(Book book) throws BookNotFoundException;
}
