package com.controller;

import com.main.SingletonCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class ReserveController implements Initializable {
    private static ObservableList<Reserve> reservedata = FXCollections.observableArrayList();
    @FXML
    private TableView<Reserve> TableReserve;

    @FXML
    private TableColumn<Reserve, String> cBook;

   @FXML
    private TableColumn<Reserve, Integer> cID;

    @FXML
    private TableColumn<Reserve, String> cBorrowedDate;

    @FXML
    private TableColumn<Reserve, CheckBox> cReserve;

//    init
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;
        try {
            stmt = SingletonCon.getConnection().createStatement();
            String query = "SELECT b.id, b.TITLE,b.BORROWED_DATE FROM ADMIN.BOOKS b where b.RESERVED_STATUS = 0 and b.BORROWED_STATUS = 1";
            ResultSet results = stmt.executeQuery(query);
            reservedata.clear();
            while (results.next()){
                reservedata.add(new Reserve(results.getInt(1),
                        results.getString(2),results.getString(3), new CheckBox()));
            };
            cID.setCellValueFactory(new PropertyValueFactory<>("id"));
            cBook.setCellValueFactory(new PropertyValueFactory<>("book_title"));
            cBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowed_date"));
            cReserve.setCellValueFactory(new PropertyValueFactory<>("isReserved"));
            TableReserve.setItems(reservedata);



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }


//    Reserve books by updating a column RESERVED_STATUS = 1
    public void handleSelectedCheckbox(ActionEvent event) {
        ObservableList<Reserve> selectAsReserved = FXCollections.observableArrayList();
        Statement stmt = null;
        for (Reserve res: reservedata){
            if(res.getIsReserved().isSelected())
            {
                selectAsReserved.add(res);
            }
        }
        for(Reserve r: selectAsReserved) {
            int bookid = r.getId();
            try {
                //Get a connection
                stmt = SingletonCon.getConnection().createStatement();
                String query = "UPDATE BOOKS SET ADMIN.BOOKS.RESERVED_STATUS = 1 " +
                        "WHERE ADMIN.BOOKS.ID = " + bookid;
                int results = stmt.executeUpdate(query);
                System.out.println(results);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
        }
        reservedata.removeAll(selectAsReserved);


    }
}
