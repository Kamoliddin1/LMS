package com.book;
import com.main.SingletonCon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddMemberPopupController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField university_id;

    @FXML
    private TextField password;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void updateMember(){
        // jdbc Connection
        Statement stmt = null;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "SELECT SESSION.ISUPDATED FROM ADMIN.SESSION order by ID desc";
            ResultSet result = stmt.executeQuery(query);
            result.next();
            query = "update ADMIN.USERS set UNIVERSITY_ID =  \'" + university_id.getText() + "\'," +
                    "LAST_NAME = \'" + last_name.getText() + "\'," +
                    "FIRST_NAME = \'" + first_name.getText() + "\'," +
                    "PASSWORD = \'" + password.getText() + "\'," +
                     "where id = " + result.getInt(1);
            int results = stmt.executeUpdate(query);
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

    }
    @FXML
    void addMember(ActionEvent event) {

        // jdbc Connection
        Statement stmt = null;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "insert into ADMIN.users(university_id, first_name, last_name, password, role)\n" +
                    "values(" +
                    "\'" + university_id.getText() + "\'," +
                    "\'" + first_name.getText() + "\'," +
                    "\'" + last_name.getText() + "\'," +
                    "\'" + password.getText() + "\'," + 1 + ")";
            int results = stmt.executeUpdate(query);
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

    @FXML
    void cancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // jdbc Connection
        Statement stmt = null;
        int curid;
        try {
            //Get a connection
            stmt = SingletonCon.getConnection().createStatement();
            String query = "SELECT SESSION.ISUPDATED FROM ADMIN.SESSION order by ID desc";
            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                curid = results.getInt(1);
                query = "select UNIVERSITY_ID,FIRST_NAME,LAST_NAME,PASSWORD from ADMIN.USERS where id = " + curid;
                results = stmt.executeQuery(query);
                results.next();
                first_name.setText(results.getString(2));
                university_id.setText(results.getString(1));
                last_name.setText(results.getString(3));
                password.setText(results.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
