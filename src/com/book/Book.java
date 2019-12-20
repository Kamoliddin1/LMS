package com.book;

import javafx.scene.control.CheckBox;

public class Book {
    private Integer id;
    private String title;
    private String isbn;
    private String subject;
    private String publishDate;
    private String author;

    private CheckBox bookCheckbox;

    Book() {
    }

    public Book(Integer id, String title, String isbn, String subject, String publishDate, String author, CheckBox bookCheckbox) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.subject = subject;
        this.publishDate = publishDate;
        this.author = author;
        this.bookCheckbox = bookCheckbox;
    }

    public CheckBox getBookCheckbox() {
        return bookCheckbox;
    }

    public void setBookCheckbox(CheckBox bookCheckbox) {
        this.bookCheckbox = bookCheckbox;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
