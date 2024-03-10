package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();
    Purchase findById(Integer id);
}
