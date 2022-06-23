package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.*;
import com.example.demo.entity.AdministratorzyEntity;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.StudenciEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UzytkownicyPodgladController implements Initializable {
    private final Nawigator nawigator = new Nawigator();

    @FXML
    private Button awans;

    @FXML
    private Button b_ankiety;

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
    private Button edycja;

    @FXML
    private Label email1;

    @FXML
    private ComboBox<String> select1;

    @FXML
    private ComboBox<KierunkiEntity> select2;

    @FXML
    private TableView<?> table1;

    @FXML
    private Button tworzenie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));
        RolaComboBox.initializeComboBox(select1);
        select2.setDisable(true);
        KierunkiComboBox.initializeComboBox(select2,null,false);
        awans.setDisable(true);

        UzytkownicyGUI.initializeTableAdmin((TableView<AdministratorzyEntity>) table1);
    }
    @FXML
    void select1(ActionEvent event) {
        if(select1.getValue() == "Admin") {
            select2.setDisable(true);
            awans.setDisable(true);
            UzytkownicyGUI.initializeTableAdmin((TableView<AdministratorzyEntity>) table1);
        }
        else if(select1.getValue() == "Prowadzący") {
            select2.setDisable(true);
            awans.setDisable(true);
            UzytkownicyGUI.initializeTableProwadzacy((TableView<ProwadzacyEntity>) table1);
        }
        else {
            awans.setDisable(false);
            select2.setDisable(false);
            UzytkownicyGUI.initializeTableStudenci((TableView<StudenciEntity>) table1, select2.getValue());
        }
    }

    @FXML
    void select2(ActionEvent event) {
        UzytkownicyGUI.initializeTableStudenci((TableView<StudenciEntity>) table1, select2.getValue());
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
    void awans(ActionEvent event) {
        try {
            HashMap<String, Object> data = new HashMap<>();
            ObservableList<StudenciEntity> studenci = (ObservableList<StudenciEntity>) table1.getItems();
            data.put("studenci", table1.getItems());
            Nawigator.changeSceneLoadData(Nawigator.Resource.ADMIN_UZYTKOWNICY_AWANS, data, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void edycja(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_UZYTKOWNICY_EDYCJA, event);
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
    void tworzenie(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_UZYTKOWNICY_TWORZENIE, event);
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
