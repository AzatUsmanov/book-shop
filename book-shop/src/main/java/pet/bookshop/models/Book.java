package pet.bookshop.models;


import jakarta.persistence.*;

import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class Book {
    private static final int NAME_MAX_LENGTH = 50;
    private static final int DESCRIPTION_MAX_LENGTH = 250;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = NAME_MAX_LENGTH, nullable = false)
    private String name;
    @Column(length = NAME_MAX_LENGTH)
    private String author;
    @Column(length = DESCRIPTION_MAX_LENGTH)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
}
