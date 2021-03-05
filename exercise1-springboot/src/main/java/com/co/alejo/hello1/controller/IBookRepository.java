package com.co.alejo.hello1.controller;

import com.co.alejo.hello1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
