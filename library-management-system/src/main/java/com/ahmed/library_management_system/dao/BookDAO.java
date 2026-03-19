package com.ahmed.library_management_system.dao;

import com.ahmed.library_management_system.entity.Book;

public interface BookDAO {

    Book addBook(Book book);

    Book findByIsbn(String isbn);

}
