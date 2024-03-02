package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.model.Book;

import java.util.List;

public interface BookSearchService {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findAll();
}
