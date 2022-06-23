package com.example.demo.controller.student;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.AnkietyPodgladGUI;
import com.example.demo.repository.UzytkownicyRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AnkietyPodgladController implements Initializable {

    private final Nawigator nawigator = new Nawigator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));
        int semestr = Integer.parseInt(userInfo.get("semestr"));
        int id = Integer.parseInt(userInfo.get("id"));


        AnkietyPodgladGUI.setStudentData(semestr, id);
        AnkietyPodgladGUI.initializeGUI(null, main, UzytkownicyRepository.UserRole.STUDENT);

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
    private VBox main;

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
