package com.example.demo.controller.prowadzacy;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.OgloszeniaComboBox;
import com.example.demo.gui.OgloszeniaGUI;
import com.example.demo.repository.OgloszeniaRepository;
import com.example.demo.entity.OgloszeniaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class OgloszeniaPodgladController implements Initializable {

    HashMap<String,String> userInfo = UserInfo.getCurrentUserInfo();
    private final OgloszeniaRepository or = new OgloszeniaRepository();
    private final Nawigator nawigator = new Nawigator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email1.setText(userInfo.get("email"));
        OgloszeniaComboBox.initializeComboBoxForProwadzacy(select1);
        OgloszeniaGUI.generateOgloszeniaGUI(ogloszenia_main, null);

    }


    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_dodaj_ogloszenie;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_profil;

    @FXML
    private Button b_wyloguj;

    @FXML
    private Button b_zajecia;

    @FXML
    private Label email1;

    @FXML
    private VBox ogloszenia_main;

    @FXML
    private ComboBox<String> select1;

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectOgloszenia(ActionEvent event) {
        String selected = select1.getSelectionModel().getSelectedItem();
        if(Objects.equals(selected, "Ogólnouczelniane")) {
            OgloszeniaGUI.generateOgloszeniaGUI(ogloszenia_main, null);
        }
        else {
            ArrayList<OgloszeniaEntity> ogloszenia = or.getOgloszeniaByUzytkownikId(Integer.parseInt(userInfo.get("id")));
            OgloszeniaGUI.generateOgloszeniaGUIfromArray(ogloszenia_main, ogloszenia);
        }
    }

    @FXML
    void dodaj_ogloszenie(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_OGLOSZENIA_TWORZENIE, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ogloszenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_OGLOSZENIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profil(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_PROFIL, event);
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
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_ZAJECIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void exit (ActionEvent event){
        System.exit(0);
    }



}
