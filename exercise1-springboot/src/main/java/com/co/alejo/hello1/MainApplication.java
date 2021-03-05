package com.co.alejo.hello1;

import com.co.alejo.hello1.controller.IBookRepository;
import com.co.alejo.hello1.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	// init bean to insert 3 books into h2 database.
	@Bean
	CommandLineRunner initDatabase(IBookRepository repository) {
		return args -> {
			repository.save(new Book("War art", "Sun Tzu", new BigDecimal("15.41")));
			repository.save(new Book("One Hundred Years of Solitude", "Gabriel García Márquez", new BigDecimal("9.69")));
			repository.save(new Book("The leader who had no title", "Robin S. Sharma", new BigDecimal("47.99")));
		};
	}

}
