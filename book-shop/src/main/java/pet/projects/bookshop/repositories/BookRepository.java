package pet.projects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.projects.bookshop.dto.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    Optional<Book> findByNameAndAuthor(String name, String author);
    boolean existsByNameAndAuthor(String name, String author);
}