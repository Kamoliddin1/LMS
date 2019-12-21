package com.controller;

import com.main.SingletonCon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddBookPopupController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField title;

    @FXML
    private TextField isbn;

    @FXML
    private TextField author;

    @FXML
    private TextField publishDate;
    @FXML
    private TextField subject;
    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    void updateBook(){
        // jdbc Connection
        Statement stmt = null;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "SELECT SESSION.ISUPDATED FROM ADMIN.SESSION order by ID desc";
            ResultSet result = stmt.executeQuery(query);
            result.next();
            query = "update ADMIN.BOOKS set TITLE =  \'" + title.getText() + "\'," +
                    "ISBN = \'" + isbn.getText() + "\'," +
                    "SUBJECTTITLE = \'" + subject.getText() + "\'," +
                    "AUTHORNAME = \'" + author.getText() + "\'," +
                    "PUBLISH_DATE = \'" + publishDate.getText() + "\' " +
                    "where id = " + result.getInt(1);
            int results = stmt.executeUpdate(query);
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
    @FXML
    void addBook(ActionEvent event) {
        // jdbc Connection
        Statement stmt = null;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "insert into ADMIN.BOOKS(title, isbn, PUBLISH_DATE,AUTHORNAME,SUBJECTTITLE)\n" +
                    "values(" +
                    "\'" + title.getText() + "\'," +
                    "\'" + isbn.getText() + "\',"  +
                    "\'" + publishDate.getText() + "\'," +
                    "\'" + author.getText() + "\',"  +
                    "\'" + subject.getText() + "\')";
            int results = stmt.executeUpdate(query);
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // jdbc Connection
        Statement stmt = null;
        int curid;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "SELECT SESSION.ISUPDATED FROM ADMIN.SESSION order by ID desc";
            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                curid = results.getInt(1);
                query = "select SUBJECTTITLE,AUTHORNAME,TITLE,ISBN,PUBLISH_DATE from ADMIN.books where id = " + curid;
                results = stmt.executeQuery(query);
                results.next();
                author.setText(results.getString(2));
                subject.setText(results.getString(1));
                title.setText(results.getString(3));
                isbn.setText(results.getString(4));
                publishDate.setText(results.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
    }

