package pet.projects.bookshop.service.inter;

import pet.projects.bookshop.dto.User;
import pet.projects.bookshop.tool.exception.UserAlreadyRegisteredException;

public interface RegistrationService {
    void register(User user) throws UserAlreadyRegisteredException;
}
