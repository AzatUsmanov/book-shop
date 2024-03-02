package pet.projects.bookshop.rest;

import lombok.AllArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import pet.projects.bookshop.model.Purchase;
import pet.projects.bookshop.service.inter.UserProfileService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/profile/money-on-account")
    BigDecimal getAmountMoneyOnAccount(@AuthenticationPrincipal UserDetails userDetails) {
        return userProfileService.getAmountMoneyOnAccount(userDetails.getUsername());
    }

    @PutMapping("/profile/money-transfer")
    void putMoneyIntoAccount(@RequestParam BigDecimal amountOfMoney,
                             @AuthenticationPrincipal UserDetails userDetails) {
        userProfileService.putMoneyIntoAccount(userDetails.getUsername(), amountOfMoney);
    }

    @PutMapping("/profile/money-withdrawal")
    void withdrawMoneyFromAccount(@RequestParam BigDecimal amountOfMoney,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        userProfileService.withdrawMoneyFromAccount(userDetails.getUsername(), amountOfMoney);
    }

    @GetMapping("/profile/purchases")
    List<Purchase> getPurchases(@AuthenticationPrincipal UserDetails userDetails) {
        return userProfileService.getPurchases(userDetails.getUsername());
    }

}
