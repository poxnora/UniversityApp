package com.example.demo.controller.prowadzacy;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.controller.ILoadData;
import com.example.demo.gui.AnkietaPodgladOdpowiedziGUI;
import com.example.demo.entity.AnkietyEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.PrzedmiotyEntity;
import com.example.demo.entity.ZajeciaEntity;
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

public class AnkietyOdpowiedziPodgladController implements Initializable, ILoadData {

    private final Nawigator nawigator = new Nawigator();
    private AnkietyEntity ankieta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String,String> userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));

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
    private Button b_zajecia;

    @FXML
    private Label email1;

    @FXML
    private VBox main;

    @FXML
    private ComboBox<String> select1;

    @FXML
    private Label tekst;

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
    void select1(ActionEvent event) {

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

    @Override
    public void loadData(HashMap<String, Object> data) {
        ankieta = (AnkietyEntity) data.get("ankieta");
        AnkietaPodgladOdpowiedziGUI.initializeGUI(ankieta, main, select1);
        ZajeciaEntity zajecia = ankieta.getZajeciaByIdZajec();
        PrzedmiotyEntity przedmiot = zajecia.getPrzedmiotyByIdPrzedmiotu();
        ProwadzacyEntity prowadzacy = zajecia.getProwadzacyByIdProwadzacego();
        tekst.setText(przedmiot.getNazwa() + " - " + zajecia.getFormaZajec() + " - " + prowadzacy.getImie() + " " + prowadzacy.getNazwisko() );
    }
}

