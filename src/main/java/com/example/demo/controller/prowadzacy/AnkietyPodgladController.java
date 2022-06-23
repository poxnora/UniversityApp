package com.example.demo.controller.prowadzacy;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.AnkietyPodgladGUI;
import com.example.demo.gui.ZajeciaComboBox;
import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.repository.ZajeciaRepository;
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

public class AnkietyPodgladController implements Initializable {

    private final Nawigator nawigator = new Nawigator();
    private ProwadzacyRepository pr;
    private ZajeciaRepository zr;

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
    private ComboBox<ZajeciaEntity> select1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String,String> userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));
        pr = new ProwadzacyRepository();
        zr = new ZajeciaRepository();
        ZajeciaComboBox.initializeComboBox(select1, zr.getZajeciaByProwadzacyId(Integer.parseInt(userInfo.get("id"))));
        fetchAnkiety();

    }

    private void fetchAnkiety() {
        ZajeciaEntity zajecia = select1.getValue();
        if(zajecia != null) {
            AnkietyPodgladGUI.initializeGUI(zajecia, main, UzytkownicyRepository.UserRole.PROWADZACY);
        }
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
    void select1(ActionEvent event) {
        fetchAnkiety();
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
