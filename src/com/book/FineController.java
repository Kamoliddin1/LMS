package com.book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FineController implements Initializable {
    private ObservableList<Fine> finedata= FXCollections.observableArrayList();
    @FXML
    private TableView<Fine> TableFine;

    @FXML
    private TableColumn<Fine, String> cBook;

    @FXML
    private TableColumn<Fine, String> cBorrowedDate;

    @FXML
    private TableColumn<Fine, Integer> cDay;

    @FXML
    private TableColumn<Fine, Integer> cFineDay;

    @FXML
    private TableColumn<Fine, Double> cFine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Statement stmt = null;

        String university_id = "\'u1810197\'";
        try {
            stmt = com.main.Demo.getConnection().createStatement();
            String query = "SELECT b.TITLE,b.BORROWED_DATE,b.DUE_DAY,b.FINE_PER_DAY FROM ADMIN.BOOKS b join users u " +
                    "on b.STUDENT_BORROWED_ID = u.ID where u.UNIVERSITY_ID = " + university_id;
            ResultSet results = stmt.executeQuery(query);
            while (results.next()){
                finedata.add(new Fine(results.getString(1), results.getString(2),results.getInt(3),
                        results.getInt(3),results.getDouble(4)));
            };
            cBook.setCellValueFactory(new PropertyValueFactory<>("book_title"));
            cBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowed_date"));
            cDay.setCellValueFactory(new PropertyValueFactory<>("day"));
            cFineDay.setCellValueFactory(new PropertyValueFactory<>("fine_day"));
            cFine.setCellValueFactory(new PropertyValueFactory<>("fine"));
            TableFine.setItems(finedata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
