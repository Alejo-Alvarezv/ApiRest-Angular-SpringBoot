package com.co.alejo.company;

import com.co.alejo.company.model.Employee;
import com.co.alejo.company.model.IEmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IEmployeeRepository repository) {

        return args -> {
            log.info("Pre-loading " + repository.save(new Employee("Alejo Alvarez", "Developer")));
            log.info("Pre-loading " + repository.save(new Employee("Lupita perez", "CEO")));
        };
    }
}