package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.model.Book;

public interface BookManageService {
    Book findById(Integer id);
    void add(Book book);
    void deleteById(Integer id);
    void update(Book book);
}
