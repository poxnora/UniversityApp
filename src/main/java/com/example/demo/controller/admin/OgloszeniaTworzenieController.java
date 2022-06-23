package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.OgloszeniaComboBox;
import com.example.demo.repository.KierunkiRepository;
import com.example.demo.repository.OgloszeniaRepository;
import com.example.demo.entity.AdministratorzyEntity;
import com.example.demo.entity.KierunkiEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class OgloszeniaTworzenieController implements Initializable {
    private final KierunkiRepository kierunkiRepository = new KierunkiRepository();
    private final OgloszeniaRepository or = new OgloszeniaRepository();
    private AdministratorzyEntity admin;
    private final Nawigator nawigator = new Nawigator();
    private final HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_dodaj_ogloszenie;

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
    private Label komunikat;

    @FXML
    private ComboBox<String> select1;

    @FXML
    private ComboBox<KierunkiEntity> select2;

    @FXML
    private TextArea t_ogloszenie;

    @FXML
    private TextField t_tytul;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email1.setText(currentUserInfo.get("email"));

       OgloszeniaComboBox.initializeComboBoxForStudent(select1);
        KierunkiComboBox.initializeComboBox(select2, null,false);
        select2.setDisable(true);


    }

    @FXML
    public void selectFor(ActionEvent event) {
        String value = select1.getValue();
        if(value.equals("Ogólnouczelniane")) {
            select2.setDisable(true);
        }
        else {
            select2.setDisable(false);
        }
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
    void dodaj_ogloszenie(ActionEvent event) {
        String tytul = t_tytul.getText();
        String ogloszenie = t_ogloszenie.getText();
        Boolean dlaWszystkich = select1.getValue().equals("Ogólnouczelniane");
        if(tytul.length() < 3 || ogloszenie.length() < 10) {
            komunikat("Tytuł musi mieć co najmniej 3, a ogłoszenie co najmniej 10 znaków");
        } else {
            if(dlaWszystkich) {
                or.insertOgloszenie(tytul, ogloszenie, Integer.valueOf(currentUserInfo.get("id")), (byte) 1);
            }
            else {
                Integer idKierunku = select2.getSelectionModel().getSelectedItem().getIdKierunku();
                Integer idOgloszenia = or.insertOgloszenie(tytul, ogloszenie, Integer.valueOf(currentUserInfo.get("id")), (byte) 0);
                or.insertOgloszeniaKierunki(idOgloszenia, idKierunku);
            }
            komunikat("Dodano ogłoszenie!");
        }
        resetTextFields();
    }

    public void komunikat(String s) {
        komunikat.setText(s);
        System.out.println(s);
    }

    public void resetTextFields() {
        t_ogloszenie.setText("");
        t_tytul.setText("");
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
    void selectKierunki(ActionEvent event) {

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
