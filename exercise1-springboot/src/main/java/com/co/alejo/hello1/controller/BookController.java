package com.co.alejo.hello1.controller;

import com.co.alejo.hello1.exceptions.BookUnSupportedFieldPatchException;
import org.springframework.util.StringUtils;
import com.co.alejo.hello1.exceptions.BookNotFoundException;
import com.co.alejo.hello1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private IBookRepository repository;

    // Method for find all book
    @GetMapping("/books")
    List<Book> findAll(){
        return repository.findAll();
    }

    //Method for save a book
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook){
        return repository.save(newBook);
    }

    //Find a book
    @GetMapping("/books/{id}")
    Book findOne (@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or Update
    @PutMapping("/books/{id}")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id){
        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    return repository.save(x);
                }).orElseGet(()-> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    // Update author only
    @PatchMapping("/books/{id}")
    Book path(@RequestBody Map<String, String> update,@PathVariable Long id){
        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });
    }

    //Delete book
    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id){
        repository.deleteById(id);
    }
}
