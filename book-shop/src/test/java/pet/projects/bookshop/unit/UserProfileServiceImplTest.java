package pet.projects.bookshop.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.projects.bookshop.dto.Role;
import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.impl.UserProfileServiceImpl;
import pet.projects.bookshop.tool.exception.NotEnoughMoneyInAccountException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceImplTest {
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserProfileServiceImpl service;

    @Test
    public void putMoneyIntoAccountWithNonExistentUser() {
        final var username = "username";
        final var validAmountOfMoney = BigDecimal.ONE;
        when(repository.findByUsername(username))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.putMoneyIntoAccount(username, validAmountOfMoney))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void putValidAmountOfMoneyIntoAccount() {
        final var user = buildUser();
        final var username = user.getUsername();
        final var validAmountOfMoney = BigDecimal.ONE;
        final var moneyInAccount = user.getMoneyInAccount().add(validAmountOfMoney);
        user.setMoneyInAccount(moneyInAccount);
        when(repository.findByUsername(username))
                .thenReturn(Optional.of(user));

        service.putMoneyIntoAccount(username, validAmountOfMoney);

        verify(repository, times(1)).deleteByUsername(username);
        verify(repository, times(1)).save(user);
    }

    @Test
    public void putInvalidAmountOfMoneyIntoAccount() {
        final var user = buildUser();
        final var username = user.getUsername();
        final var invalidAmountOfMoney = BigDecimal.ONE.negate();
        when(repository.findByUsername(username))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() -> service.putMoneyIntoAccount(username, invalidAmountOfMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void withdrawMoneyFromAccountWithNonExistentUser() {
        final var username = "username";
        final var validAmountOfMoney = BigDecimal.ONE;
        when(repository.findByUsername(username))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.withdrawMoneyFromAccount(username, validAmountOfMoney))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void withdrawAmountOfMoneyFromAccount() {
        final var user = buildUser();
        final var username = user.getUsername();
        final var validAmountOfMoney = BigDecimal.ONE;
        final var moneyInAccount = user.getMoneyInAccount().add(validAmountOfMoney.negate());
        user.setMoneyInAccount(moneyInAccount);
        when(repository.findByUsername(username))
                .thenReturn(Optional.of(user));

        service.withdrawMoneyFromAccount(username, validAmountOfMoney);

        verify(repository, times(1)).deleteByUsername(username);
        verify(repository, times(1)).save(user);
    }

    @Test
    public void withdrawInvalidAmountOfMoneyFromAccount() {
        final var user = buildUser();
        final var username = user.getUsername();
        final var invalidAmountOfMoney = BigDecimal.ONE.negate();
        when(repository.findByUsername(username))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() -> service.withdrawMoneyFromAccount(username, invalidAmountOfMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void withdrawMoneyFromAccountWithNotEnoughMoney() {
        final var user = buildUser();
        final var username = user.getUsername();
        final var amountOfMoney = BigDecimal.TEN;
        final var moneyForWithdraw = user.getMoneyInAccount().add(amountOfMoney);
        when(repository.findByUsername(username))
                .thenReturn(Optional.of(user));

        assertThatThrownBy(() -> service.withdrawMoneyFromAccount(username, moneyForWithdraw))
                .isInstanceOf(NotEnoughMoneyInAccountException.class);
    }

    private User buildUser() {
        return User.builder()
                .id(1)
                .username("username")
                .role(Role.USER)
                .moneyInAccount(BigDecimal.TEN)
                .purchases(new ArrayList<>())
                .build();
    }
}
