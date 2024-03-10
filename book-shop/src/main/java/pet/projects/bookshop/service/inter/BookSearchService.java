package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Book;

import java.util.List;

public interface BookSearchService {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findAll();
}
