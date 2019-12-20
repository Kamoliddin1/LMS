package com.book;

import com.main.SingletonCon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class AddBookPopupController {
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
//            query = "update ADMIN.USERS set UNIVERSITY_ID =  \'" + university_id.getText() + "\'," +
//                    "LAST_NAME = \'" + last_name.getText() + "\'," +
//                    "FIRST_NAME = \'" + first_name.getText() + "\'," +
//                    "PASSWORD = \'" + password.getText() + "\'," +
//                    "role = " + 1 + "where id = " + result.getInt(1);
//            int results = stmt.executeUpdate(query);
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
}
