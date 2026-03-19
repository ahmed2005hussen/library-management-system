package com.ahmed.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Year;


@Entity(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isbn", unique = true)
    @NotBlank(message = "ISBN can not be empty ")
    @Size(min = 13, max = 17, message = "ISBN must be exactly  13 characters ")
    String isbn;

    @Column(name = "title")
    @NotBlank(message = "Title can not be empty")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Author can not be empty")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "buy_price")
    @Min(value = 0, message = "Price can not be negative")
    private double buyPrice;

    @Column(name = "borrow_price_per_day")
    @Min(value = 0, message = "Price can not be negative")
    private double borrowPricePerDay;

    @Column(name = "can_buy")
    private boolean canBuy;

    @Column(name = "can_borrow")
    private boolean canBorrow;

    @Column(name = "copies")
    @Min(value = 0, message = "Copies can not be negative")
    private int copies;

    @Column(name = "year")
    @Convert(converter = YearConverter.class)
    private Year year;

    // for data binding
    public Book() {
    }


    public Book(String isbn, String title, String author, String category, double buyPrice,
                double borrowPricePerDay, boolean canBuy, boolean canBorrow,
                int copies, Year year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.buyPrice = buyPrice;
        this.borrowPricePerDay = borrowPricePerDay;
        this.canBuy = canBuy;
        this.canBorrow = canBorrow;
        this.copies = copies;
        this.year = year;
    }

    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getBorrowPricePerDay() {
        return borrowPricePerDay;
    }

    public void setBorrowPricePerDay(double borrowPricePerDay) {
        this.borrowPricePerDay = borrowPricePerDay;
    }

    public boolean isCanBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public boolean isCanBorrow() {
        return canBorrow;
    }

    public void setCanBorrow(boolean canBorrow) {
        this.canBorrow = canBorrow;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // for testing
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", buyPrice=" + buyPrice +
                ", borrowPricePerDay=" + borrowPricePerDay +
                ", canBuy=" + canBuy +
                ", canBorrow=" + canBorrow +
                ", copies=" + copies +
                ", year=" + year +
                '}';
    }
}
