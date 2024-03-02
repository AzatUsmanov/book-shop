package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.projects.bookshop.model.Purchase;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.inter.UserProfileService;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final static BigDecimal MINIMUM_ACCEPTABLE_AMOUNT_OF_MONEY = BigDecimal.ZERO;

    private final static int COMPARISON_LESS_THAN_ZERO = -1;

    private final UserRepository userRepository;

    @Override
    public BigDecimal getAmountMoneyOnAccount(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException())
                .getMoneyInAccount();
    }

    @Override
    @Transactional
    public void putMoneyIntoAccount(String username, BigDecimal moneyToTransfer) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());;
        final var moneyInAccount = user.getMoneyInAccount();

        if (moneyToTransfer.compareTo(MINIMUM_ACCEPTABLE_AMOUNT_OF_MONEY)
                == COMPARISON_LESS_THAN_ZERO) {
            throw new IllegalArgumentException("");
        }
        final var sum =  moneyInAccount.add(moneyToTransfer);
        user.setMoneyInAccount(sum);

        userRepository.save(user);

        log.info("amount of money = " + moneyToTransfer.toString() + " were successfully deposited into the account");
    }

    @Override
    @Transactional
    public void withdrawMoneyFromAccount(String username, BigDecimal moneyToWithdraw) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
        final var moneyInAccount = user.getMoneyInAccount();

        if (moneyInAccount.compareTo(moneyToWithdraw)
                == COMPARISON_LESS_THAN_ZERO) {
            throw new NotEnoughMoneyInAccountException();
        } else if (moneyToWithdraw.compareTo(MINIMUM_ACCEPTABLE_AMOUNT_OF_MONEY)
                == COMPARISON_LESS_THAN_ZERO) {
            throw new IllegalArgumentException();
        }
        final var difference =  moneyInAccount.add(moneyToWithdraw.negate());
        user.setMoneyInAccount(difference);

        userRepository.save(user);

        log.info("amount of money = " + moneyToWithdraw.toString() + " were successfully withdrawn from the account");
    }

    @Override
    public List<Purchase> getPurchases(String username) {
        final var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
        return user.getPurchases();
    }

}
