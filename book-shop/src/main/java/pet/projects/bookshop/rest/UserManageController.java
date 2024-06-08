package pet.projects.bookshop.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.service.inter.UserManageService;
import pet.projects.bookshop.tool.exception.UserAlreadyExistException;
import pet.projects.bookshop.tool.exception.UserNotFoundException;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserManageController {

    private final UserManageService userManageService;

    @GetMapping("/management/user/{id}")
    User findById(@PathVariable Integer id) throws UserNotFoundException {
        return userManageService.findById(id);
    }

    @GetMapping("/management/user")
    List<User> findAll() {
        return userManageService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/management/user")
    void add(@RequestBody User user) throws UserAlreadyExistException {
        userManageService.add(user);
    }

    @DeleteMapping("/management/user/{id}")
    void deleteById(@PathVariable Integer id) throws UserNotFoundException {
        userManageService.deleteById(id);
    }

    @PatchMapping("/management/user")
    void updateById(@RequestBody User user) throws UserNotFoundException {
        userManageService.update(user);
    }

}
