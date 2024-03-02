package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.model.User;

import java.util.List;

public interface UserManageService {
    User findById(Integer id);
    List<User> findAll();
    void add(User user);
    void deleteById(Integer id);
    void update(User user);
}
