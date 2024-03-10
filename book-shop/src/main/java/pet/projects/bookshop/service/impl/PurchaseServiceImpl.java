package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.repositories.PurchaseRepository;
import pet.projects.bookshop.service.inter.PurchaseService;
import pet.projects.bookshop.tool.exception.PurchaseNotFoundException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findById(Integer id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException());
    }

}
