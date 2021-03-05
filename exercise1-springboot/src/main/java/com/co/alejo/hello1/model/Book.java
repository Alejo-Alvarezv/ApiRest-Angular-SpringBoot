package com.co.alejo.hello1.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String author;
    private BigDecimal price;

    public Book(String name, String author, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }
}
