package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.tool.exception.PurchaseNotFoundException;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();
    Purchase findById(Integer id) throws PurchaseNotFoundException;
}
