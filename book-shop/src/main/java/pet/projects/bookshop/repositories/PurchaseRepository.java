package pet.projects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.projects.bookshop.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
