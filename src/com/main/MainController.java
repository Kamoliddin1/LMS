package com.main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label first_name;

    @FXML
    private Label last_name;

    @FXML
    private BorderPane borderPane;
    @FXML
    void book(MouseEvent event) {
        loadUI("book");
    }
    @FXML
    void fine(MouseEvent event) {
        loadUI("fine");
    }
    @FXML
    void borrowedBooks(MouseEvent event){
            loadUI("borrowedBooks");
    }
    @FXML
    void reserve(MouseEvent event) {
        loadUI("reserve");
    }
    @FXML
    void studentCRUD(MouseEvent event){
        loadUI("studentCRUD");
    }
    @FXML
    void issueBooks(MouseEvent event){loadUI("issueBooks");}
    @FXML
    void librarianCRUD(MouseEvent event){
        loadUI("librarianCRUD");
    }
    private void loadUI(String ui){

        Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/com/fxml/" +ui + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(pane);
    }
    @FXML
    void logout(MouseEvent event) {
        Statement stmt = null;

        try {
            stmt = SingletonCon.getConnection().createStatement();
            String query = "DELETE FROM SESSION WHERE 1 = 1";
            int result = stmt.executeUpdate(query);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/loginUI.fxml"));
            System.out.println("LogoutButton was activated");
            Parent root2 =  fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setTitle("Login");
            stage.setScene(new Scene(root2));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;

        try {
            stmt = SingletonCon.getConnection().createStatement();
            int userid = 1;
            String query = "SELECT USER_ID FROM ADMIN.SESSION";
            ResultSet resultSet = stmt.executeQuery(query);
            if(resultSet.next()) {
                userid = resultSet.getInt(1);
            }
            String firstname,lastname;
            query = "SELECT FIRST_NAME,LAST_NAME FROM ADMIN.USERS where id = " + userid;
            resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                firstname = resultSet.getString(1);
                lastname = resultSet.getString(2);
                first_name.setText(firstname);
                last_name.setText(lastname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
