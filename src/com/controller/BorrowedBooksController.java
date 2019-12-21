package com.controller;
import com.main.SingletonCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BorrowedBooksController implements Initializable {
    private ObservableList<Book> bookdata= FXCollections.observableArrayList();

    @FXML
    private TableView<Book> TableBBook;

    @FXML
    private TableColumn<Book, Integer> cBId;

    @FXML
    private TableColumn<Book, String> cBTitle;

    @FXML
    private TableColumn<Book, String> cBSubject;

    @FXML
    private TableColumn<Book, String> cBAuthor;

    @FXML
    private TableColumn<Book, String> cBISBN;

    @FXML
    private TableColumn<Book, String> cBPublish_Date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;

        try {
            stmt = SingletonCon.getConnection().createStatement();

            String query = "SELECT BOOKS.ID, TITLE, ISBN, PUBLISH_DATE, AUTHORNAME, SUBJECTTITLE FROM ADMIN.BOOKS where BORROWED_STATUS=1";
            ResultSet results = stmt.executeQuery(query);
            bookdata.clear();
            while (results.next()){
                bookdata.add(new Book(results.getInt(1), results.getString(2),results.getString(3),
                        results.getString(4),results.getString(5),results.getString(6), new CheckBox()));
            };
            cBId.setCellValueFactory(new PropertyValueFactory<>("id"));
            cBTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            cBISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            cBPublish_Date.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
            cBAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            cBSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            TableBBook.setItems(bookdata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
