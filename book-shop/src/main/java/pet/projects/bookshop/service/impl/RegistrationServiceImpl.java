package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.inter.RegistrationService;
import pet.projects.bookshop.tool.exception.UserAlreadyRegisteredException;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public void register(User user) {
        final var password = user.getPassword();
        final var encryptedPassword = passwordEncoder.encode(password);
        final var username = user.getUsername();
        user.setPassword(encryptedPassword);

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyRegisteredException();
        }
        userRepository.save(user);

        log.info("user " + user.toString() + " registered successfully");
    }

}
