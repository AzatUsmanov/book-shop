package pet.projects.bookshop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.repositories.UserRepository;
import pet.projects.bookshop.service.inter.UserManageService;
import pet.projects.bookshop.tool.exception.UserAlreadyExistException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserManageServiceImpl implements UserManageService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) throws UserAlreadyExistException {
        final var password = user.getPassword();
        final var encryptedPassword = passwordEncoder.encode(password);
        final var username = user.getUsername();
        user.setPassword(encryptedPassword);

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(user);

        log.info("user " + user.toString() + " added successfully");
    }

    @Override
    public void deleteById(Integer id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);

        log.info("user with id = " + id.toString() + " deleted successfully");
    }

    @Override
    @Transactional
    public void update(User user) throws UserNotFoundException {
        if (!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException();
        }
        userRepository.save(user);

        log.info("user with id = " + user.getId().toString() + " updated for " + user.toString());
    }

}
