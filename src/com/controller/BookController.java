package com.controller;

import com.main.SingletonCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TableColumn<Book, String> cISBN;

    @FXML
    private TableColumn<Book, String> cPublish_Date;
    @FXML
    private TableColumn<Book, String> cAuthor;
    @FXML
    private TableColumn<Book, String> cSubject;
    @FXML
    private TableColumn<Book, CheckBox> cBookCRUD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;

        try {
            stmt = SingletonCon.getConnection().createStatement();

            String query = "SELECT BOOKS.ID, TITLE, ISBN, PUBLISH_DATE, AUTHORNAME, SUBJECTTITLE FROM ADMIN.BOOKS";
            ResultSet results = stmt.executeQuery(query);
            bookdata.clear();
            while (results.next()){
                bookdata.add(new Book(results.getInt(1), results.getString(2),results.getString(3),
                        results.getString(4),results.getString(5),results.getString(6), new CheckBox()));
            };
            cId.setCellValueFactory(new PropertyValueFactory<>("id"));
            cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            cISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            cPublish_Date.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
            cAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            cSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            cBookCRUD.setCellValueFactory(new PropertyValueFactory<>("bookCheckbox"));
            TableBook.setItems(bookdata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

    }
    @FXML
    void handleBookCreateCheckbox(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/fxml/addBookPopup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Add Student");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleBookDeleteCheckbox(ActionEvent event) {
        ObservableList<Book> selectBookCRUD = FXCollections.observableArrayList();
        // jdbc Connection
        Statement stmt = null;
        for (Book bCRUD: bookdata){
            if(bCRUD.getBookCheckbox().isSelected())
            {
                selectBookCRUD.add(bCRUD);
            }
        }
        for(Book b: selectBookCRUD) {
            int bookid = b.getId();
            try {
                //Get a connection
                stmt = SingletonCon.getConnection().createStatement();
                String query = "DELETE FROM ADMIN.BOOKS WHERE ID = " + bookid;
                int results = stmt.executeUpdate(query);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
        }
        bookdata.removeAll(selectBookCRUD);
    }

    @FXML
    void handleBookUpdateCheckbox(ActionEvent event) throws IOException {
        ObservableList<Book> selectBookCRUD = FXCollections.observableArrayList();
        // jdbc Connection
        Statement stmt = null;
        for (Book sCRUD: bookdata){
            if(sCRUD.getBookCheckbox().isSelected())
            {
                try {
                    //Get a connection
                    stmt = SingletonCon.getConnection().createStatement();
                    String query = "update ADMIN.SESSION set ISUPDATED = " + sCRUD.getId();
                    int results = stmt.executeUpdate(query);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERROR");
                }
            }
        }
        Parent root = FXMLLoader.load(getClass().getResource("/com/fxml/updateBookPopup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Update Book");
        stage.setScene(scene);
        stage.show();
    }
    public void refreshTable(){
        Statement stmt = null;

        try {
            stmt = SingletonCon.getConnection().createStatement();

            String query = "SELECT BOOKS.id, title, isbn, publish_date,AUTHORNAME,SUBJECTTITLE FROM ADMIN.BOOKS";
            ResultSet results = stmt.executeQuery(query);
            bookdata.clear();
            while (results.next()){
                bookdata.add(new Book(results.getInt(1), results.getString(2),results.getString(3),
                        results.getString(4),results.getString(5),results.getString(6), new CheckBox()));
            };
            cId.setCellValueFactory(new PropertyValueFactory<>("id"));
            cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            cISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            cPublish_Date.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
            cAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            cSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
            cBookCRUD.setCellValueFactory(new PropertyValueFactory<>("bookCheckbox"));
            TableBook.setItems(bookdata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
