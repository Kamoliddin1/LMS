package com.controller;

import javafx.scene.control.TextField;

public class IssueBooks {
    private Integer id;
    private String title;
    private String publishDate;

    private TextField IssueBookTxt;
    private TextField StudentID;

    public IssueBooks() {
    }
//  Initialized Constructor

    public IssueBooks(Integer id, String title, String publishDate, TextField issueBookTxt, TextField studentID) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        IssueBookTxt = issueBookTxt;
        StudentID = studentID;
    }
//  simple Getters and Setters

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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public TextField getIssueBookTxt() {
        return IssueBookTxt;
    }

    public void setIssueBookTxt(TextField issueBookTxt) {
        IssueBookTxt = issueBookTxt;
    }

    public TextField getStudentID() {
        return StudentID;
    }

    public void setStudentID(TextField studentID) {
        StudentID = studentID;
    }
}
