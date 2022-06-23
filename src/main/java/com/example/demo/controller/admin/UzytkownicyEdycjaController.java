package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.HashPassword;
import com.example.demo.auth.UserInfo;
import com.example.demo.repository.AdministratorzyRepository;
import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.validation.UzytkownikWalidacja;
import com.example.demo.entity.UzytkownicyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UzytkownicyEdycjaController implements Initializable {
    private final Nawigator nawigator = new Nawigator();
    private UzytkownicyRepository ur;
    private ProwadzacyRepository pr;
    private StudentRepository sr;
    private AdministratorzyRepository ar;
    private UzytkownicyEntity searched;

    @FXML
    private Button archiwizuj;

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
    private TextArea haslo;

    @FXML
    private TextArea imie;

    @FXML
    private Label komunikat;

    @FXML
    private Label komunikat2;

    @FXML
    private TextArea nazwisko;

    @FXML
    private Button szukaj;

    @FXML
    private TextField tekst1;

    @FXML
    private Button zatwierdz;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));
        ur = new UzytkownicyRepository();
        ar = new AdministratorzyRepository();
        sr = new StudentRepository();
        pr = new ProwadzacyRepository();
        komunikat.setVisible(true);
        komunikat2.setVisible(true);
        komunikat2.setText("");
        komunikat("Po nr indeksu, np. 000123", 1);

    }
    @FXML
    void szukaj(ActionEvent event) {
        String value = tekst1.getText();
        if(value.length() != 6) {
            komunikat("Nieprawidłowy nr indeksu", 1);
            return;
        }
        searched = ur.getUzytkownikById(Integer.valueOf(value));
        if(searched == null) {
            komunikat("Nie znaleziono.", 1);
        } else if(searched.getZarchiwizowany() == (byte) 1) {
            komunikat("Uzytkownik zarchiwizowany",1);
            searched = null;
        }
        else {
            imie.setText(ur.getImie(searched));
            nazwisko.setText(ur.getNazwisko(searched));
            haslo.setText(searched.getHaslo());
            komunikat(searched.getEmail(),1);
        }
    }

    @FXML
    void zatwierdz() {
        if(searched == null) return;
        String hasloVal = haslo.getText();
        String nazwiskoVal = nazwisko.getText();
        String imieVal = imie.getText();
        int id = searched.getIdUzytkownika();
        if(!UzytkownikWalidacja.isGoodPassword(hasloVal)){
            komunikat("Za słabe hasło.", 2);
            return;
        }
        else if (nazwiskoVal.length() < 2 || imieVal.length() < 2) {
            komunikat("Wprowadź prawidłowe imie i nazwisko", 2);
            return;
        }
        else {
            UzytkownicyRepository.UserRole role = ur.getRole(searched);
            String emailMiddle = role ==
                    UzytkownicyRepository.UserRole.ADMIN ? "@admin" :
                    role == UzytkownicyRepository.UserRole.STUDENT ? "@student":
                            "@prac";
            String email = imieVal.substring(0,1).toLowerCase() + nazwiskoVal.substring(0,1).toLowerCase() + String.format("%06d", id) + emailMiddle + ".studia.edu.pl";
            ur.updateUzytkownik(searched, HashPassword.hash(hasloVal),email);
            if(searched.getProwadzacyByIdUzytkownika() != null) {
                pr.updateProwadzacy(id, imieVal, nazwiskoVal);
            } else if (searched.getAdministratorzyByIdUzytkownika() != null) {
                ar.updateAdministrator(id, imieVal, nazwiskoVal);
            }
            else if(searched.getStudenciByIdUzytkownika() != null) {
                sr.updateStudent(id, imieVal, nazwiskoVal);
            }
            komunikat("Zmieniono dane!", 2);
            reset();
        }
    }

    void reset() {
        searched = null;
        imie.setText("");
        nazwisko.setText("");
        haslo.setText("");
        komunikat("", 1);
    }
    void komunikat(String s, int i) {
        if(i == 1) komunikat.setText(s);
        else komunikat2.setText(s);

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
    void archiwizuj(ActionEvent event) {
        if(searched != null) {
            ur.archiwizuj(searched.getIdUzytkownika());
            komunikat("Zarchiwizowano " + searched.getEmail(), 2);
            reset();
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
    void tekst1(ActionEvent event) {

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
