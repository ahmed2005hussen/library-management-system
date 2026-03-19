package com.ahmed.library_management_system.dao;

import com.ahmed.library_management_system.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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

    @Override
    public Book findByIsbn(String isbn) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.isbn =:data", Book.class);

        query.setParameter("data" , isbn );

        Book book;
        try {
            book = query.getSingleResult();
        }catch (NoResultException e){
            book = null;
        }
        return book;
    }
}
