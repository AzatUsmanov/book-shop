package pet.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.bookshop.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
