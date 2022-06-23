package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.RocznikComboBox;
import com.example.demo.repository.KierunkiRepository;
import com.example.demo.entity.KierunkiEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class KierunkiEdycjaController implements Initializable {

    private final Nawigator nawigator = new Nawigator();
    private KierunkiEntity kierunekUpdate;
    private KierunkiRepository kr = new KierunkiRepository();

    @FXML
    private Button b_ankiety;

    @FXML
    private Button dodaj;

    @FXML
    private CheckBox checkAdd;

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
    private ComboBox<KierunkiEntity> kierunek;

    @FXML
    private Label komunikat2;

    @FXML
    private TextArea nazwa;

    @FXML
    private ComboBox<Integer> rocznik;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));

        komunikat2.setVisible(true);
        KierunkiComboBox.initializeComboBox(kierunek, null,false);
        RocznikComboBox.initializeComboBox(rocznik);
        kierunekUpdate = kierunek.getValue();
        populate();


    }

    private void populate() {
        nazwa.setText(kierunekUpdate.getNazwa());
        rocznik.getSelectionModel().select(kierunekUpdate.getRocznik());
    }

    @FXML
    void checkAdd(ActionEvent event) {
        if(!checkAdd.isSelected()) {
            kierunek.setDisable(false);
            kierunekUpdate = kierunek.getValue();
           populate();
        }
        else {
            kierunek.setDisable(true);
            kierunekUpdate = null;
        }
    }

    @FXML
    void dodaj(ActionEvent event) {
        String nazwaVal = nazwa.getText();
        int rocznikVal = rocznik.getValue();
        if(nazwaVal.length() < 3) {
            komunikat2.setText("Nazwa musi miec minimum 3 znaki.");
            return;
        }
        if(kierunekUpdate == null) {
            if(kr.kierunekExists(nazwaVal, rocznikVal)) {
                komunikat2.setText("Taki kierunek juz istnieje");
                return;
            }
            kr.insertKierunek(nazwaVal, rocznikVal);
            komunikat2.setText("Dodano nowy kierunek");
            reset();
        }
        else {
            kr.updateKierunek(nazwaVal, rocznikVal, kierunekUpdate.getIdKierunku());
            komunikat2.setText("Edytowano kierunek.");
            reset();
        }
    }

    void reset() {
        KierunkiComboBox.initializeComboBox(kierunek, null,false);
        nazwa.setText("");
        checkAdd.setSelected(false);
        kierunek.setDisable(false);
        kierunekUpdate = kierunek.getValue();
       populate();
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
    void kierunek(ActionEvent event) {
        kierunekUpdate = kierunek.getValue();
       populate();
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
    void rocznik(ActionEvent event) {

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
