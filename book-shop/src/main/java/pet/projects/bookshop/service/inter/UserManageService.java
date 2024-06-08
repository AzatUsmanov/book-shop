package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.tool.exception.UserAlreadyExistException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.util.List;

public interface UserManageService {
    User findById(Integer id) throws UserNotFoundException;
    List<User> findAll();
    void add(User user) throws UserAlreadyExistException;
    void deleteById(Integer id) throws UserNotFoundException;
    void update(User user) throws UserNotFoundException;
}
