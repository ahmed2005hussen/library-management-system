package com.ahmed.library_management_system.exception;

public class BookExistsException extends RuntimeException {
    public BookExistsException(String isbn) {

        super("Book with isbn: " + isbn + " is already exist! ");

    }
}
