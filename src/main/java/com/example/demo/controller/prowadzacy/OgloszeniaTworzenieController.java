package com.example.demo.controller.prowadzacy;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.OgloszeniaComboBox;
import com.example.demo.gui.ZajeciaComboBox;
import com.example.demo.repository.KierunkiRepository;
import com.example.demo.repository.OgloszeniaRepository;
import com.example.demo.repository.ZajeciaRepository;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.ZajeciaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class OgloszeniaTworzenieController implements Initializable {
    private final HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
    private final Nawigator nawigator = new Nawigator();
    private final ZajeciaRepository zr = new ZajeciaRepository();
    private final KierunkiRepository kr = new KierunkiRepository();
    private final OgloszeniaRepository or = new OgloszeniaRepository();
    private final ArrayList<ZajeciaEntity> zajecia = zr.getZajeciaByProwadzacyId(Integer.valueOf(currentUserInfo.get("id")));
    private final ArrayList<KierunkiEntity> kierunki = kr.getKierunkiProwadzacego(Integer.valueOf(currentUserInfo.get("id")));

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
    private Label komunikat;

    @FXML
    private ComboBox<String> select1;

    @FXML
    private ComboBox<ZajeciaEntity> select2;

    @FXML
    private TextArea t_ogloszenie;

    @FXML
    private TextField t_tytul;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email1.setText(currentUserInfo.get("email"));

        OgloszeniaComboBox.initializaComboBoxForProwadzacyTworzenie(select1);
        ZajeciaComboBox.initializeComboBox(select2,zajecia);
        select2.setDisable(true);
    }


    @FXML
    void ankiety(ActionEvent event) {

    }

    @FXML
    void dodaj_ogloszenie(ActionEvent event) {
        String tytul = t_tytul.getText();
        String ogloszenie = t_ogloszenie.getText();
        Boolean dlaWszystkich = select1.getValue().equals("Wszyscy studenci");
        if(tytul.length() < 3 || ogloszenie.length() < 10) {
            komunikat("Tytuł musi mieć co najmniej 3, a ogłoszenie co najmniej 10 znaków");
        } else {
            if(dlaWszystkich) {
                Integer idOgloszenia = or.insertOgloszenie(tytul, ogloszenie, Integer.valueOf(currentUserInfo.get("id")), (byte) 0);
                for(KierunkiEntity k: kierunki) {
                    or.insertOgloszeniaKierunki(idOgloszenia, k.getIdKierunku());
                }

            }
            else {
                Integer idZajec = select2.getValue().getIdZajec();
                Integer idOgloszenia = or.insertOgloszenie(tytul, ogloszenie, Integer.valueOf(currentUserInfo.get("id")), (byte) 0);
                or.insertOgloszeniaZajecia(idOgloszenia, idZajec);
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
        String value = select1.getValue();
        if(value.equals("Zajęcia")) {
            select2.setDisable(false);
        }
        else {
            select2.setDisable(true);
        }
    }

    @FXML
    void selectZajecia(ActionEvent event) {

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
