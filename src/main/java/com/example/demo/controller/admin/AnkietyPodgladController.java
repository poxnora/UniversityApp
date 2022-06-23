package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.AnkietyPodgladGUI;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.ZajeciaComboBox;
import com.example.demo.repository.AnkietyRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.repository.ZajeciaRepository;
import com.example.demo.entity.KierunkiEntity;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AnkietyPodgladController implements Initializable {

    private final Nawigator nawigator = new Nawigator();
    ZajeciaRepository zr;
    AnkietyRepository ar;

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
    private Label email1;

    @FXML
    private VBox main;

    @FXML
    private Button nowa_ankieta;

    @FXML
    private ComboBox<KierunkiEntity> select1;

    @FXML
    private ComboBox<ZajeciaEntity> select2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));
        zr = new ZajeciaRepository();
        ar = new AnkietyRepository();
        KierunkiComboBox.initializeComboBox(select1,null,false);
        select1.getSelectionModel().select(0);
        adjustZajecia();
    }

   private void adjustZajecia() {
        KierunkiEntity kierunek = select1.getValue();
       ArrayList<ZajeciaEntity> zajecia = zr.getZajeciaByKierunek(kierunek);
       ZajeciaComboBox.initializeComboBox(select2, zajecia);
       select2.getSelectionModel().select(0);
   }

   private void fetchAnkiety() {
        ZajeciaEntity zajecia = select2.getValue();
       if (zajecia != null) AnkietyPodgladGUI.initializeGUI(zajecia, main, UzytkownicyRepository.UserRole.ADMIN);
       else main.getChildren().clear();
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
    void exit (ActionEvent event){
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
    void nowa_ankieta(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ANKIETY_TWORZENIE, event);
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
    void select1(ActionEvent event) {
       adjustZajecia();
       fetchAnkiety();
    }

    @FXML
    void select2(ActionEvent event) {
        fetchAnkiety();
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
