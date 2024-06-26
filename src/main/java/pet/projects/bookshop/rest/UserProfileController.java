package pet.projects.bookshop.rest;

import lombok.AllArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import pet.projects.bookshop.dto.Purchase;
import pet.projects.bookshop.service.inter.UserProfileService;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile/money-on-account")
    BigDecimal getAmountMoneyOnAccount(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        return userProfileService.getAmountMoneyOnAccount(userDetails.getUsername());
    }

    @PutMapping("/profile/money-transfer")
    void putMoneyIntoAccount(@RequestParam BigDecimal amountOfMoney,
                             @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        userProfileService.putMoneyIntoAccount(userDetails.getUsername(), amountOfMoney);
    }

    @PutMapping("/profile/money-withdrawal")
    void withdrawMoneyFromAccount(@RequestParam BigDecimal amountOfMoney,
                                  @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException, NotEnoughMoneyInAccountException {
        userProfileService.withdrawMoneyFromAccount(userDetails.getUsername(), amountOfMoney);
    }

    @GetMapping("/profile/purchases")
    List<Purchase> getPurchases(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        return userProfileService.getPurchases(userDetails.getUsername());
    }

}
