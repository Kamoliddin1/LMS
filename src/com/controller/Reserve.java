package com.controller;

import javafx.scene.control.CheckBox;

public class Reserve {
    private Integer id;
    private String book_title;
    private String borrowed_date;

    private CheckBox isReserved;


    public Reserve(Integer id, String book_title, String borrowed_date, CheckBox isReserved) {
        this.id = id;
        this.book_title = book_title;
        this.borrowed_date = borrowed_date;
        this.isReserved = isReserved;
    }


    public Reserve() {
    }

    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public CheckBox getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(CheckBox isReserved) {
        this.isReserved = isReserved;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBorrowed_date() {
        return borrowed_date;
    }

    public void setBorrowed_date(String borrowed_date) {
        this.borrowed_date = borrowed_date;
    }
}
