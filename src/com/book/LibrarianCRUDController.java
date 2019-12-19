package com.book;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LibrarianCRUDController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        // jdbc Connection
        Connection conn = null;
        Statement stmt = null;
        String URL = "jdbc:derby:fdb;create=true;";
        String USER = "admin";
        String PASSWORD = "admin";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
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



    public void handleStudentCRUDCheckbox(ActionEvent event) {
        ObservableList<StudentCRUD> selectsCRUD = FXCollections.observableArrayList();
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        // jdbc Connection
        Connection conn = null;
        Statement stmt = null;
        String URL = "jdbc:derby:fdb;create=true;";
        String USER = "admin";
        String PASSWORD = "admin";
        for (StudentCRUD sCRUD: studentdata){
            if(sCRUD.getCrud().isSelected())
            {
                selectsCRUD.add(sCRUD);
            }
        }
        for(StudentCRUD r: selectsCRUD) {
            int studentid = r.getId();
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
                //Get a connection
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                stmt = conn.createStatement();
                String query = "UPDATE BOOKS SET ADMIN.BOOKS.RESERVED_STATUS = 1 " +
                        "WHERE ADMIN.BOOKS.ID = " + studentid;
                int results = stmt.executeUpdate(query);
                System.out.println(results);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
        }
        studentdata.removeAll(selectsCRUD);
    }
}