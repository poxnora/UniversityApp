package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.AnkietyTworzenieGUI;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.repository.AnkietyRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AnkietyTworzenieController implements Initializable {

    private final Nawigator nawigator = new Nawigator();
    private AnkietyRepository ar;

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
    private Button dodaj_pytanie;

    @FXML
    private Label email1;

    @FXML
    private VBox main;

    @FXML
    private ComboBox<KierunkiEntity> select;

    @FXML
    private Button usun_pytanie;

    @FXML
    private Button zatwierdzenie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));
        ar = new AnkietyRepository();
        AnkietyTworzenieGUI.initializeGUI(main);
        KierunkiComboBox.initializeComboBox(select, null,true);
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
    void dodaj_pytanie(ActionEvent event) {
        System.out.println("dodaj");
        AnkietyTworzenieGUI.addPytanie(main);
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
    void select(ActionEvent event) {
        System.out.println(select.getValue());
        System.out.println(select.getValue().getNazwa());
    }

    @FXML
    void usun_pytanie(ActionEvent event) {
        System.out.println("usun");
        AnkietyTworzenieGUI.deletePytanie(main);
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

    @FXML
    void zatwierdzenie(ActionEvent event) {
        ArrayList<String> pytania = AnkietyTworzenieGUI.getPytania(main);
        if(pytania.size() < 1) return;
        int idZestawu = ar.insertZestawPytan();
        for(String s: pytania) {
            ar.insertPytanie(idZestawu, s);
        }
        KierunkiEntity kierunek = select.getValue();
        if(kierunek == null) {
            ar.insertAnkietaForAll(idZestawu);
        }
        else {
            ar.insertAnkietaForKierunek(idZestawu, kierunek.getIdKierunku());
        }
        AnkietyTworzenieGUI.deleteAll(main);
    }
}
