package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface UserProfileService {
    BigDecimal getAmountMoneyOnAccount(String username) throws UserNotFoundException;
    void putMoneyIntoAccount(String username, BigDecimal amountOfMoney) throws UserNotFoundException;
    void withdrawMoneyFromAccount(String username, BigDecimal amountOfMoney) throws UserNotFoundException, NotEnoughMoneyInAccountException;
    List<Purchase> getPurchases(String username) throws UserNotFoundException;
}
