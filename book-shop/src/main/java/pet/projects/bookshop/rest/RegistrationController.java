package pet.projects.bookshop.rest;


import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.service.inter.RegistrationService;
import pet.projects.bookshop.tool.exception.UserAlreadyRegisteredException;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registerService;

    @PostMapping("/registration")
    public void register(@RequestBody User user) throws UserAlreadyRegisteredException {
        registerService.register(user);
    }

}
