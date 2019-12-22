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
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;

public class StudentCRUDController implements Initializable{
    private static ObservableList<StudentCRUD> studentdata = FXCollections.observableArrayList();

    @FXML
    private TableView<StudentCRUD> TableStudent;
    @FXML
    private TableColumn<StudentCRUD, Integer> cID;

    @FXML
    private TableColumn<StudentCRUD, String> cUniID;

    @FXML
    private TableColumn<StudentCRUD, String> cFirstName;

    @FXML
    private TableColumn<StudentCRUD, String> cLastName;

    @FXML
    private TableColumn<StudentCRUD, CheckBox> cCRUD;
    private Timer timer;
    @Override

    //    init
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // jdbc Connection
        Statement stmt = null;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "select * from USERS where role = 1";
            ResultSet results = stmt.executeQuery(query);
            studentdata.clear();
            while (results.next()){
                studentdata.add(new StudentCRUD(results.getInt(1),
                        results.getString(2),results.getString(3),results.getString(4), new CheckBox()));
            };
            cID.setCellValueFactory(new PropertyValueFactory<>("id"));
            cUniID.setCellValueFactory(new PropertyValueFactory<>("university_id"));
            cFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            cLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            cCRUD.setCellValueFactory(new PropertyValueFactory<>("crud"));
            TableStudent.setItems(studentdata);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

    //    refresh by reinitializing
    public void refreshTable(){
        Statement stmt = null;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "select * from USERS where role = 1";
            ResultSet results = stmt.executeQuery(query);
            studentdata.clear();
            while (results.next()){
                studentdata.add(new StudentCRUD(results.getInt(1),
                        results.getString(2),results.getString(3),results.getString(4), new CheckBox()));
            };
            cID.setCellValueFactory(new PropertyValueFactory<>("id"));
            cUniID.setCellValueFactory(new PropertyValueFactory<>("university_id"));
            cFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            cLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            cCRUD.setCellValueFactory(new PropertyValueFactory<>("crud"));
            TableStudent.setItems(studentdata);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

// Update Student
    public void handleStudentUpdateCheckbox(ActionEvent event) throws IOException {

        ObservableList<StudentCRUD> selectsCRUD = FXCollections.observableArrayList();
        // jdbc Connection
        Statement stmt = null;
        for (StudentCRUD sCRUD: studentdata){
            if(sCRUD.getCrud().isSelected())
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
        Parent root = FXMLLoader.load(getClass().getResource("/com/fxml/updateMemberPopup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Update Student");
        stage.setScene(scene);
        stage.show();
    }

    // Create Student by calling a addMemberPopup.fxml
    public void handleStudentCreateCheckbox(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/fxml/addMemberPopup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Add Student");
        stage.setScene(scene);
        stage.show();
    }
//    Delete Student
    public void handleStudentDeleteCheckbox(ActionEvent event) {
        ObservableList<StudentCRUD> selectsCRUD = FXCollections.observableArrayList();
        // jdbc Connection
        Statement stmt = null;
        for (StudentCRUD sCRUD: studentdata){
            if(sCRUD.getCrud().isSelected())
            {
                selectsCRUD.add(sCRUD);
            }
        }
        for(StudentCRUD r: selectsCRUD) {
            int userid = r.getId();
            try {
                //Get a connection
                stmt = SingletonCon.getConnection().createStatement();
                String query = "DELETE FROM ADMIN.USERS WHERE ID = " + userid;
                int results = stmt.executeUpdate(query);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
        }
        studentdata.removeAll(selectsCRUD);

    }
}