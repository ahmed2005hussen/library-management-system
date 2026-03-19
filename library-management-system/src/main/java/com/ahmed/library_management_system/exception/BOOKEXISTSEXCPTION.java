package com.ahmed.library_management_system.exception;

public class BOOKEXISTSEXCPTION extends RuntimeException {
    public BOOKEXISTSEXCPTION(String isbn) {
        super("Book with isbn: " + isbn + " is already exist! ");
    }
}
