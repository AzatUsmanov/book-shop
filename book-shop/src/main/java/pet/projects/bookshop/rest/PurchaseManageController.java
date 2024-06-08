package pet.projects.bookshop.rest;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.service.inter.PurchaseService;
import pet.projects.bookshop.tool.exception.PurchaseNotFoundException;

import java.util.List;

@RestController
@AllArgsConstructor
public class PurchaseManageController {

    private final PurchaseService purchaseService;

    @GetMapping("/management/purchase")
    List<Purchase> findAll() {
        return purchaseService.findAll();
    }

    @GetMapping("/management/purchase/{id}")
    Purchase findById(@PathVariable Integer id) throws PurchaseNotFoundException {
        return purchaseService.findById(id);
    }

}
