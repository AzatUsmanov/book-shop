package pet.projects.bookshop.unit;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pet.projects.bookshop.dto.Role;
import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.inter.RegistrationService;
import pet.projects.bookshop.tool.exception.UserAlreadyRegisteredException;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest  {
    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService service;

    @Test
    public void registerNoExistentUser() {
        final var user = buildUser();
        when(repository.existsByUsername(user.getUsername()))
                .thenReturn(false);
        when(passwordEncoder.encode(user.getPassword()))
                .thenReturn("password");

        service.register(user);

        verify(repository, times(1))
                .save(user);
    }

    @Test
    public void registerExistentUser() {
        final var user = buildUser();
        when(repository.existsByUsername(user.getUsername()))
                .thenReturn(true);
        when(passwordEncoder.encode(user.getPassword()))
                .thenReturn("password");

        assertThatThrownBy(() -> service.register(user))
                .isInstanceOf(UserAlreadyRegisteredException.class);
    }

    private User buildUser() {
        return User.builder()
                .id(Integer.MAX_VALUE)
                .username("username")
                .role(Role.USER)
                .moneyInAccount(BigDecimal.ONE)
                .purchases(new ArrayList<>())
                .build();
    }
}
