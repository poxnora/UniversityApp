package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.OgloszeniaComboBox;
import com.example.demo.gui.OgloszeniaGUI;
import com.example.demo.repository.KierunkiRepository;
import com.example.demo.entity.AdministratorzyEntity;
import com.example.demo.entity.KierunkiEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class OgloszeniaPodgladController implements Initializable {
    private final KierunkiRepository kierunkiRepository = new KierunkiRepository();
    private AdministratorzyEntity admin;
    private final Nawigator nawigator = new Nawigator();


    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_dodaj_ogloszenie;

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
    private VBox ogloszenia_main;

    @FXML
    private ComboBox<String> select1;

    @FXML
    private ComboBox<KierunkiEntity> select2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));

        OgloszeniaComboBox.initializeComboBoxForStudent(select1);

        KierunkiComboBox.initializeComboBox(select2, null,false);

        OgloszeniaGUI.generateOgloszeniaGUI(ogloszenia_main, null);
        select2.setDisable(true);
    }

    @FXML
    public void selectFor(ActionEvent event) {
        String selected = select1.getSelectionModel().getSelectedItem();
        if(Objects.equals(selected, "Ogólnouczelniane")) {
            select2.setDisable(true);
            OgloszeniaGUI.generateOgloszeniaGUI(ogloszenia_main, null);
        }
        else {
            select2.setDisable(false);
        }
    }

    @FXML
    public void selectKierunki(ActionEvent event) {
        KierunkiEntity kierunek = select2.getSelectionModel().getSelectedItem();
        OgloszeniaGUI.generateOgloszeniaGUI(ogloszenia_main, kierunek);
    }

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void dodaj_ogloszenie(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_OGLOSZENIA_TWORZENIE, event);
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
