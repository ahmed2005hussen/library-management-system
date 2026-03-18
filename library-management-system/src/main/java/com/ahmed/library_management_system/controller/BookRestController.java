package com.ahmed.library_management_system.controller;

import com.ahmed.library_management_system.entity.Book;
import com.ahmed.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    BookService bookService;

    @Autowired
    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);

    }

    // code -> DAO -> service -> rest

// ADMIN only
// - create book
// - update book
// - delete book

// USER + ADMIN
// - read books
// - search & filter


// CRUD

// Create book
// Read book by id
// update book
// delete book

// read all books

// get books(category , author , title ,
// sort by price , sort by author name , sort by borrow price , sort by year )

// check if book can be borrowed (copies > 0 && can_borrow)
// check if book can be bought   (copies > 0 && can_buy)
// reduce copies when borrowed/bought
// increase copies when returned

// get books by price range
//   (minPrice=10&maxPrice=50)

// get low stock books
//   (copies < threshold) → for admin

// get available books only
//   (copies > 0)


}
