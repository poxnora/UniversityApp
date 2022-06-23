package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.PrzedmiotyComboBox;
import com.example.demo.gui.ZajeciaPodgladGUI;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.KierunkiEntity;
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
    private final Nawigator nawigator = new Nawigator();

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_dodaj_zajecia;

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
    private VBox zajecia_main;

    @FXML
    private ComboBox<KierunkiEntity> select1;

    @FXML
    private ComboBox<PrzedmiotyEntity> select2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));
        KierunkiComboBox.initializeComboBox(select1, null,false);

        KierunkiEntity kierunek = select1.getSelectionModel().getSelectedItem();
        PrzedmiotyComboBox.initializeComboBoxForKierunek(select2, kierunek, true);
        getZajecia();
    }


    @FXML
    public void selectKierunki(ActionEvent event) {
        KierunkiEntity kierunek = select1.getSelectionModel().getSelectedItem();
        PrzedmiotyComboBox.initializeComboBoxForKierunek(select2, kierunek,true);
        getZajecia();
    }

    public void selectPrzedmioty(ActionEvent event) {
        getZajecia();
    }


    public void getZajecia() {
        ZajeciaPodgladGUI.initializeZajeciaGUI(select1.getSelectionModel().getSelectedItem(), select2.getSelectionModel().getSelectedItem(), zajecia_main, UzytkownicyRepository.UserRole.ADMIN);
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
    void b_dodaj_zajecia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ZAJECIA_TWORZENIE, event);
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
