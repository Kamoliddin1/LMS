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

public class LibrarianCRUDController implements Initializable {
    private static ObservableList<LibrarianCRUD> librariandata = FXCollections.observableArrayList();

    @FXML
    private TableView<LibrarianCRUD> TableLibrarian;

    @FXML
    private TableColumn<LibrarianCRUD, Integer> cLibID;

    @FXML
    private TableColumn<LibrarianCRUD, String> cLibUniID;

    @FXML
    private TableColumn<LibrarianCRUD, String> cLibFirstName;

    @FXML
    private TableColumn<LibrarianCRUD, String> cLibLastName;

    @FXML
    private TableColumn<LibrarianCRUD, CheckBox> cLibCRUD;

//    init
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // jdbc Connection
        Statement stmt = null;
        try {
            stmt = SingletonCon.getConnection().createStatement();


            String query = "select * from USERS where role = 2";
            ResultSet results = stmt.executeQuery(query);
            librariandata.clear();
            while (results.next()){
                librariandata.add(new LibrarianCRUD(results.getInt(1),
                        results.getString(2),results.getString(3),results.getString(4), new CheckBox()));
            };
            cLibID.setCellValueFactory(new PropertyValueFactory<>("id"));
            cLibUniID.setCellValueFactory(new PropertyValueFactory<>("university_id"));
            cLibFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            cLibLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            cLibCRUD.setCellValueFactory(new PropertyValueFactory<>("lib_crud"));
            TableLibrarian.setItems(librariandata);



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
//    refresh by reinitializing
    public void refreshTable(){
        // jdbc Connection
        Statement stmt = null;
        try {
            stmt = SingletonCon.getConnection().createStatement();

            String query = "select * from USERS where role = 2";
            ResultSet results = stmt.executeQuery(query);
            librariandata.clear();
            while (results.next()){
                librariandata.add(new LibrarianCRUD(results.getInt(1),
                        results.getString(2),results.getString(3),results.getString(4), new CheckBox()));
            };
            cLibID.setCellValueFactory(new PropertyValueFactory<>("id"));
            cLibUniID.setCellValueFactory(new PropertyValueFactory<>("university_id"));
            cLibFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            cLibLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            cLibCRUD.setCellValueFactory(new PropertyValueFactory<>("lib_crud"));
            TableLibrarian.setItems(librariandata);



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
    // Update Librarian
    public void handleLibrarianUpdateCheckbox(ActionEvent event) throws IOException {
        ObservableList<LibrarianCRUD> selectlCRUD = FXCollections.observableArrayList();

        // jdbc Connection
        Statement stmt = null;
        for (LibrarianCRUD lCRUD: librariandata){
            if(lCRUD.getLib_crud().isSelected())
            {
                try {
                    //Get a connection
                    stmt = SingletonCon.getConnection().createStatement();
                    String query = "update ADMIN.SESSION set ISUPDATED = " + lCRUD.getId();
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

        stage.setTitle("Update Librarian");
        stage.setScene(scene);
        stage.show();
    }
//    Create Librarian by calling addLibrarianPopup.fxml
    public void handleLibrarianCreateCheckbox(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/fxml/addLibrarianPopup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Add Librarian");
        stage.setScene(scene);
        stage.show();
    }
//  Delete Librarian
    public void handleLibrarianDeleteCheckbox(ActionEvent event) {
        ObservableList<LibrarianCRUD> selectlCRUD = FXCollections.observableArrayList();

        // jdbc Connection
        Statement stmt = null;
        for (LibrarianCRUD lCRUD: librariandata){
            if(lCRUD.getLib_crud().isSelected())
            {
                selectlCRUD.add(lCRUD);
            }
        }
        for(LibrarianCRUD r: selectlCRUD) {
            int librarianid = r.getId();
            try {
                stmt = SingletonCon.getConnection().createStatement();

                String query = "DELETE FROM ADMIN.USERS WHERE ID = "  + librarianid;
                int results = stmt.executeUpdate(query);
                System.out.println(results);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
        }
        librariandata.removeAll(selectlCRUD);
    }
}