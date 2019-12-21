package com.fxml;

import com.main.SingletonCon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    void handleLoginAction(ActionEvent event){}
    @FXML
    void handleLoginBtnClicked(MouseEvent event) {
        Statement stmt = null;

        String uname = username.getText();
        String pword = password.getText();
        try {
            stmt = SingletonCon.getConnection().createStatement();

            String query = "SELECT ID,UNIVERSITY_ID,PASSWORD,role FROM ADMIN.USERS where UNIVERSITY_ID = " + "\'"+ uname +"\'"+
                    " and " + "PASSWORD = " + "\'"+pword +"\'";
            ResultSet resultSet = stmt.executeQuery(query);

            if(resultSet.next()) {
                int userid = resultSet.getInt(1);
                int role = resultSet.getInt(4);
                System.out.println(resultSet.getString(2));
                query = "delete from SESSION where 1=1";
                stmt.executeUpdate(query);
                query = "INSERT INTO SESSION(USER_ID) VALUES (" + userid + ")";
                stmt.executeUpdate(query);
                closeStage();
                openMain(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }
    private void openMain(int role) {
        if(role==1) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/com/main/mainUIStudent.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("LMS");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (role==2) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/com/main/mainUILibrarian.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("LMS");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(role==3) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/com/main/mainUIAdmin.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("LMS");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


