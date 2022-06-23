package com.example.demo.controller.student;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.OgloszeniaGUI;
import com.example.demo.repository.OgloszeniaRepository;
import com.example.demo.entity.OgloszeniaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class OgloszeniaPodgladController implements Initializable {
    private final Nawigator nawigator = new Nawigator();
    private final OgloszeniaRepository or = new OgloszeniaRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));

        ArrayList<OgloszeniaEntity> ogloszenia = or.getOgloszeniaByStudentId(Integer.parseInt(userInfo.get("id")));
        OgloszeniaGUI.generateOgloszeniaGUIfromArray(ogloszenia_main, ogloszenia);
    }

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_profil;

    @FXML
    private Button b_wyloguj;

    @FXML
    private Button b_zaliczenia;

    @FXML
    private Label email1;

    @FXML
    private VBox ogloszenia_main;


    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exit (ActionEvent event){
        System.exit(0);
    }

    @FXML
    void ogloszenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_OGLOSZENIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profil(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_PROFIL, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void wyloguj(ActionEvent event) {
        try {
            UserInfo.remove();
            Nawigator.changeScene(Nawigator.Resource.LOGOWANIE, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void zaliczenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ZALICZENIA, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
