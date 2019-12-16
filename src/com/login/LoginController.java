package com.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    void handleLoginAction(ActionEvent event){}
    @FXML
    void handleLoginBtnClicked(MouseEvent event) {

        String uname = username.getText();
        String pword = password.getText();
        if (uname.equals("admin") && pword.equals("admin")){
            System.out.println(uname);
            System.out.println(pword);
            closeStage();
            openMain();
        }
    }
    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }
    private void openMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/main/mainUI.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("LMS");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
