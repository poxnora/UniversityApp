package com.example.demo.controller.prowadzacy;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.PrzedmiotyComboBox;
import com.example.demo.gui.ZajeciaPodgladGUI;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.PrzedmiotyEntity;
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
import java.util.ResourceBundle;

public class ZajeciaPodgladController implements Initializable {
    private final UzytkownicyRepository ur = new UzytkownicyRepository();
    private final HashMap<String,String> userInfo = UserInfo.getCurrentUserInfo();
    private int id = Integer.parseInt(userInfo.get("id"));

    @FXML
    private Button b_ankiety;

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
    private VBox zajecia_main;

    @FXML
    private ComboBox<PrzedmiotyEntity> select1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email1.setText(userInfo.get("email"));
        PrzedmiotyComboBox.initializeComboBoxForProwadzacy(select1, id, true);
        select1.getSelectionModel().select(0);
        populate();

    }

    private void populate() {
        ZajeciaPodgladGUI.initializeZajeciaGUIforProwadzacyPrzedmiot(id, select1.getValue(), zajecia_main, UzytkownicyRepository.UserRole.PROWADZACY);
    }


    @FXML
    void select1() {
        populate();
    }

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_ANKIETY_PODGLAD, event);
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
    void selectFor(ActionEvent event) {

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
}
