package com.co.alejo.hello1.exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id){
        super("Book Id not found: " + id);
    }

}
