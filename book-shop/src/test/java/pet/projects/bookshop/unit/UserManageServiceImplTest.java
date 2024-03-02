package pet.projects.bookshop.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pet.projects.bookshop.model.Role;
import pet.projects.bookshop.model.User;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.impl.UserManageServiceImpl;

import pet.projects.bookshop.tool.exception.UserAlreadyExistException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
@ExtendWith(MockitoExtension.class)
public class UserManageServiceImplTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserManageServiceImpl service;

    @Test
    public void addNonExistentUser() {
        final var user = buildUser();
        final var userForCheck = buildUser();
        final var password = user.getPassword();
        final var encryptedPassword = passwordEncoder.encode(password);
        userForCheck.setPassword(encryptedPassword);
        when(repository.existsByUsername(user.getUsername()))
                .thenReturn(false);

        service.add(user);

        verify(repository, times(1)).save(userForCheck);
    }

    @Test
    public void addExistentUser() {
        final var user = buildUser();
        final var userForCheck = buildUser();
        final var password = user.getPassword();
        final var encryptedPassword = passwordEncoder.encode(password);
        userForCheck.setPassword(encryptedPassword);
        when(repository.existsByUsername(user.getUsername()))
                .thenReturn(true);

        assertThatThrownBy(() -> service.add(user))
                .isInstanceOf(UserAlreadyExistException.class);
    }

    @Test
    public void deleteExistentUser() {
        final var id = Integer.MAX_VALUE;
        when(repository.existsById(id))
                .thenReturn(true);

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void deleteNonExistentUser() {
        final var id = Integer.MAX_VALUE;
        when(repository.existsById(id))
                .thenReturn(false);

        assertThatThrownBy(() -> service.deleteById(id))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void updateExistentUser() {
        final var user = buildUser();
        final var id = user.getId();
        when(repository.existsById(id))
                .thenReturn(true);

        service.update(user);

        verify(repository, times(1)).deleteById(id);
        verify(repository, times(1)).save(user);
    }

    @Test
    public void updateNonExistentUser() {
        final var user = buildUser();
        final var id = user.getId();
        when(repository.existsById(id))
                .thenReturn(false);

        assertThatThrownBy(() -> service.update(user))
                .isInstanceOf(UserNotFoundException.class);
    }

    private User buildUser() {
        return User.builder()
                .id(1)
                .username("username")
                .role(Role.USER)
                .moneyInAccount(BigDecimal.ZERO)
                .purchases(new ArrayList<>())
                .build();
    }
}
