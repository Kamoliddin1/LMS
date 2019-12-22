package com.controller;

import com.main.SingletonCon;
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

//        String university_id = "\'u1810197\'";
        try {
            stmt = SingletonCon.getConnection().createStatement();
            int userid = 1;
            String query = "SELECT USER_ID FROM ADMIN.SESSION";
            ResultSet resultSet = stmt.executeQuery(query);
            if(resultSet.next()) {
                userid = resultSet.getInt(1);
            }
            query = "SELECT TITLE, BORROWED_DATE, DUE_DAY, FINE_PER_DAY FROM ADMIN.BOOKS where ID = " +userid;
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                finedata.add(new Fine(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3),
                        resultSet.getInt(3),resultSet.getDouble(4)));
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
