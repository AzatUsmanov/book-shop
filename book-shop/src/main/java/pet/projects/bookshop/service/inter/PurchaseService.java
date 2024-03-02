package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.model.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();
    Purchase findById(Integer id);
}
