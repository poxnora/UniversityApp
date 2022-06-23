package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.PrzedmiotyGUI;
import com.example.demo.entity.PrzedmiotyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PrzedmiotyPodgladController implements Initializable  {

    private final Nawigator nawigator = new Nawigator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));

        PrzedmiotyGUI.populatePrzedmiotyTable(table1);

    }

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_edytuj_przedmioty;

    @FXML
    private Button b_kierunki;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_profil;

    @FXML
    private Button b_przedmioty;

    @FXML
    private Button b_uzytkownicy;

    @FXML
    private Button b_wyloguj;

    @FXML
    private Button b_zajecia;

    @FXML
    private Button b_zaliczenia;

    @FXML
    private Label email1;

    @FXML
    private TableView<PrzedmiotyEntity> table1;

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void edytuj_przedmioty(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_PRZEDMIOTY_EDYCJA, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit (ActionEvent event){
        System.exit(0);
    }

    @FXML
    void kierunki(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_KIERUNKI_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ogloszenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_OGLOSZENIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profil(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_PROFIL, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void przedmioty(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_PRZEDMIOTY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void uzytkownicy(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_UZYTKOWNICY_PODGLAD, event);
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
    void zajecia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ZAJECIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
