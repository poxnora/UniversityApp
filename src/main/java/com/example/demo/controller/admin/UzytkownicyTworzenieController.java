package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.HashPassword;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.RolaComboBox;
import com.example.demo.gui.SemestrComboBox;
import com.example.demo.repository.AdministratorzyRepository;
import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.util.RandomPassword;
import com.example.demo.entity.KierunkiEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UzytkownicyTworzenieController implements Initializable {
    private final Nawigator nawigator = new Nawigator();
    private UzytkownicyRepository ur;
    private ProwadzacyRepository pr;
    private StudentRepository sr;
    private AdministratorzyRepository ar;

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
    private Button dodaj;

    @FXML
    private Label email1;

    @FXML
    private TextArea imie;

    @FXML
    private ComboBox<KierunkiEntity> kierunek;

    @FXML
    private Label komunikat2;

    @FXML
    private TextArea nazwisko;

    @FXML
    private ComboBox<String> rola;

    @FXML
    private ComboBox<String> semestr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));
        ur = new UzytkownicyRepository();
        pr = new ProwadzacyRepository();
        sr = new StudentRepository();
        ar = new AdministratorzyRepository();

        RolaComboBox.initializeComboBox(rola);
        SemestrComboBox.initializeComboBox(semestr, 10);
        KierunkiComboBox.initializeComboBox(kierunek, null, false);

        semestr.setDisable(true);
        kierunek.setDisable(true);


    }
    @FXML
    void rola(ActionEvent event) {
        if(rola.getValue().equals("Student")) {
            kierunek.setDisable(false);
            semestr.setDisable(false);
        }
        else {
            kierunek.setDisable(true);
            semestr.setDisable(true);
        }
    }

    @FXML
    void semestr(ActionEvent event) {

    }

    @FXML
    void kierunek(ActionEvent event) {

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
    void dodaj(ActionEvent event) {
        String imieVal = imie.getText();
        String nazwiskoVal = nazwisko.getText();
        if(imieVal.length() < 2 && nazwiskoVal.length() < 2) {
            komunikat("Wprowadź prawidłowe imie i nazwisko");
            return;
        }
        String haslo = RandomPassword.generateRandomPassword(8);
        int id = ur.getNextId();
        String emailPrefix = imieVal.toLowerCase().substring(0,1) + nazwiskoVal.toLowerCase().substring(0,1)+ String.format("%06d", id);
        String emailMiddle;
        String emailSuffix = ".studia.edu.pl";
        String email = "";
        if(rola.getValue().equals("Student")) {
            int kierunekId = kierunek.getValue().getIdKierunku();
            int semestrVal = SemestrComboBox.valueToInt(semestr.getValue());
            emailMiddle = "@student";
            email = emailPrefix + emailMiddle + emailSuffix;
            ur.insertUzytkownikGetId(email, HashPassword.hash(haslo));
            sr.insertStudent(id, kierunekId, imieVal, nazwiskoVal, semestrVal);
        }
        else if(rola.getValue().equals("Prowadzący")) {
            emailMiddle = "@prac";
            email = emailPrefix + emailMiddle + emailSuffix;
            ur.insertUzytkownikGetId(email, HashPassword.hash(haslo));
            pr.insertProwadzacy(id, imieVal, nazwiskoVal);
        }
        else if(rola.getValue().equals("Admin")) {
            emailMiddle = "@admin";
            email = emailPrefix + emailMiddle + emailSuffix;
            ur.insertUzytkownikGetId(email, HashPassword.hash(haslo));
            ar.insertAdministrator(id, imieVal, nazwiskoVal);
        }
        komunikat("Dodano " + email + ".\nWygenerowane haslo: " + haslo);
        resetInput();
    }

    void resetInput() {
        imie.setText("");
        nazwisko.setText("");
        rola.getSelectionModel().select(0);
        semestr.setDisable(true);
        kierunek.setDisable(true);
    }

    @FXML
    private void exit (ActionEvent event){
        System.exit(0);
    }

    private void komunikat(String s) {
        komunikat2.setVisible(true);
        komunikat2.setText(s);
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
