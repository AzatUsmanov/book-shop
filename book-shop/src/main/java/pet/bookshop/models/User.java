package pet.bookshop.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "users")
public class User {
    private static final int NAME_MAX_LENGTH = 50;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = NAME_MAX_LENGTH, unique = true, nullable = false)
    private String username;
    @Column(name = "money_in_account", nullable = false)
    private BigDecimal moneyInAccount;
}
