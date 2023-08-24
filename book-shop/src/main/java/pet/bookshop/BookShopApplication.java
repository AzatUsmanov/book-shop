package pet.bookshop;

import org.hibernate.sql.model.PreparableMutationOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import pet.bookshop.models.User;
import pet.bookshop.repositories.UserRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class BookShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

}
