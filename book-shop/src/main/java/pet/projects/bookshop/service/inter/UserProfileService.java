package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Purchase;

import java.math.BigDecimal;
import java.util.List;

public interface UserProfileService {
    BigDecimal getAmountMoneyOnAccount(String username);
    void putMoneyIntoAccount(String username, BigDecimal amountOfMoney);
    void withdrawMoneyFromAccount(String username, BigDecimal amountOfMoney);
    List<Purchase> getPurchases(String username);
}
