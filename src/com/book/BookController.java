package com.book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    private ObservableList<Book> bookdata= FXCollections.observableArrayList();
    @FXML
    private TableView<Book> TableBook;
    @FXML
    private TableColumn<Book, Integer> cId;

    @FXML
    private TableColumn<Book, String> cTitle;

    @FXML
    private TableColumn<Book, String> cSubject;

    @FXML
    private TableColumn<Book, String> cAuthor;

    @FXML
    private TableColumn<Book, String> cISBN;

//    @FXML
//    private TableColumn<?, ?> cBorDate;
//
//    @FXML
//    private TableColumn<?, ?> cDedDate;
//
//    @FXML
//    private TableColumn<?, ?> cStatus;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;

        try {
            stmt = com.main.Demo.getConnection().createStatement();

            String query = "SELECT * FROM ADMIN.BOOKS";
            ResultSet results = stmt.executeQuery(query);
            while (results.next()){
                bookdata.add(new Book(results.getInt(1), results.getString(2),results.getString(3),
                        results.getString(4),results.getString(5)));
            };
            cId.setCellValueFactory(new PropertyValueFactory<>("id"));
            cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            cSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            cAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            cISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            TableBook.setItems(bookdata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
