package pet.bookshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pet.bookshop.models.User;
import pet.bookshop.repositories.UserRepository;

import java.math.BigDecimal;

@SpringBootTest
class BookShopApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
		var user = new User();
		user.setUsername("John Travolta");
		userRepository.save(user);
	}

}
