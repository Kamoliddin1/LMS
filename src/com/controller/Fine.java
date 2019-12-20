package com.book;

public class Fine {
    private String book_title;
    private String borrowed_date;
    private Integer day;
    private Integer fine_day;
    private Double fine;

    public Fine(String book_title, String borrowed_date, Integer day, Integer fine_day, Double fine) {
        this.book_title = book_title;
        this.borrowed_date = borrowed_date;
        this.day = day;
        this.fine_day = fine_day;
        this.fine = fine;
    }

    public Fine() {
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getFine_day() {
        return fine_day;
    }

    public void setFine_day(Integer fine_day) {
        this.fine_day = fine_day;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}
