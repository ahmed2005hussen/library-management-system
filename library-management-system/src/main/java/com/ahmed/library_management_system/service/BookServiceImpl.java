package com.ahmed.library_management_system.service;

import com.ahmed.library_management_system.dao.BookDAO;
import com.ahmed.library_management_system.entity.Book;
import com.ahmed.library_management_system.exception.BOOKEXISTSEXCPTION;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    BookDAO bookDao ;


    @Autowired
    public BookServiceImpl(BookDAO bookDAO ){
        this.bookDao = bookDAO;
    }


    @Override
    @Transactional
    public Book addBook(Book book) {

        String isbn = book.getIsbn();

       Book book1 = bookDao.findByIsbn(isbn);

        if(book1 != null){
            throw new BOOKEXISTSEXCPTION(isbn);
        }
        bookDao.addBook(book);
        System.out.println(book + " \n is added");

        return  book ;
    }
}
