package com.example.demo.controller;

import com.example.demo.auth.Login;
import com.example.demo.auth.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LogowanieController {

    @FXML
    private PasswordField Haslo;

    @FXML
    private TextField login;

    @FXML
    private Label labelChangePassword;

    @FXML
    private void exit (ActionEvent event){
        System.exit(0);
    }

    public void Login(ActionEvent event) {
        String loginInput = login.getText();
        String passwordInput = Haslo.getText();
        if(Login.login(loginInput, passwordInput)) {
            try {
                switch(UserInfo.getCurrentUserRole()) {
                    case ADMIN -> changeScene("/fxml/admin/profil.fxml", event);
                    case STUDENT -> changeScene("/fxml/student/profil.fxml", event);
                    case PROWADZACY -> changeScene("/fxml/prowadzacy/profil.fxml", event);

                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("Błędne logowanie");
            labelChangePassword.setText("Błąd logowania: Niepoprawny LOGIN lub HASŁO");
            labelChangePassword.setTextFill(Color.web("#FF0000"));
            labelChangePassword.setVisible(true);
        }

    }

    public void changeScene(String resourcePath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(resourcePath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
