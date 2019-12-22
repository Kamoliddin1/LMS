package com.controller;

import com.main.SingletonCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class IssueBooksController implements Initializable {

    private ObservableList<IssueBooks> issuebookdata= FXCollections.observableArrayList();
    @FXML
    private TableView<IssueBooks> TableIssueBook;
    @FXML
    private TableColumn<IssueBooks, Integer> cIBId;
    @FXML
    private TableColumn<IssueBooks, String> cIBPublish_Date;
    @FXML
    private TableColumn<IssueBooks, String> cIBTitle;
    @FXML
    private TableColumn<IssueBooks, TextField> cIBBookIn;
    @FXML
    private TableColumn<IssueBooks, TextField> cIBStudentID;
//    init
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;

        try {
            stmt = SingletonCon.getConnection().createStatement();

            String query = "SELECT BOOKS.ID, TITLE, PUBLISH_DATE FROM ADMIN.BOOKS where BORROWED_STATUS =  0";
            ResultSet results = stmt.executeQuery(query);
            issuebookdata.clear();
            while (results.next()){
                issuebookdata.add(new IssueBooks(results.getInt(1), results.getString(2),results.getString(3), new TextField(),
                        new TextField()));
            };
            cIBId.setCellValueFactory(new PropertyValueFactory<>("id"));
            cIBTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            cIBPublish_Date.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
            cIBBookIn.setCellValueFactory(new PropertyValueFactory<>("IssueBookTxt"));
            cIBStudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
            TableIssueBook.setItems(issuebookdata);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

    }

    @FXML
    void handleIssueBookSave(ActionEvent event) throws SQLException {
        ObservableList<IssueBooks> selectIssueBook = FXCollections.observableArrayList();
        // jdbc Connection
        Statement stmt = null;
        String universityid = "";
        String loanday = "";
        String query;
        int id = 1;
        for (IssueBooks bookissue: issuebookdata){
            universityid = bookissue.getStudentID().getText().trim();
            loanday = bookissue.getIssueBookTxt().getText().trim();
            System.out.println("id:"+universityid);
            System.out.println("DAY:"+loanday);
                query = "SELECT ID FROM ADMIN.USERS WHERE UNIVERSITY_ID = " + "\'" +universityid +"\'";
                ResultSet resultSet = stmt.executeQuery(query);
                if(loanday != "0" && (resultSet.next())) {
                    id = resultSet.getInt(1);
                    query = "UPDATE ADMIN.BOOKS set DUE_DAY = " + loanday +
                            ", BORROWED_STATUS = 1," +
                            " STUDENT_BORROWED_ID = " + id;
                    int results = stmt.executeUpdate(query);
                    selectIssueBook.add(bookissue);
                }
        }
        issuebookdata.removeAll(selectIssueBook);
    }
}
