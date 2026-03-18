package com.ahmed.library_management_system.dao;

import com.ahmed.library_management_system.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImp implements BookDAO {

    EntityManager em;

    @Autowired
    public BookDAOImp(EntityManager entityManager){
        this.em = entityManager ;
    }

    @Override
    public Book addBook(Book book) {
        em.persist(book);
        return book;
    }
}
