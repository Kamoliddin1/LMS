package com.main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
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
    private void loadUI(String ui){



        Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/com/login/"+ui + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(pane);
    }

}
