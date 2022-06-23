package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.EctsComboBox;
import com.example.demo.gui.PrzedmiotyComboBox;
import com.example.demo.repository.PrzedmiotRepository;
import com.example.demo.entity.PrzedmiotyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PrzedmiotyEdycjaController implements Initializable {

    private final Nawigator nawigator = new Nawigator();
    private PrzedmiotyEntity przedmiotUpdate;
    private PrzedmiotRepository pr = new PrzedmiotRepository();

    @FXML
    private Button b_ankiety;

    @FXML
    private Button dodaj;

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
    private Label komunikat2;

    @FXML
    private TextArea nazwa;

    @FXML
    private ComboBox<PrzedmiotyEntity> przedmiot;

    @FXML
    private ComboBox<Integer> ects;

    @FXML
    private CheckBox checkAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));


        komunikat2.setVisible(true);
        PrzedmiotyComboBox.initializeComboBoxForKierunek(przedmiot, null, false);
        EctsComboBox.initializeComboBox(ects);
        przedmiotUpdate = przedmiot.getValue();
        populate();

    }

    @FXML
    void przedmiot(ActionEvent event) {
        przedmiotUpdate = przedmiot.getValue();
        populate();
    }

    @FXML
    void ects(){};

    private void populate() {
        this.przedmiotUpdate = przedmiot.getValue();
        try {
            nazwa.setText(przedmiotUpdate.getNazwa());
            ects.getSelectionModel().select(przedmiotUpdate.getEcts());
        }
        catch(NullPointerException ignored) {

        }
    }

    @FXML
    void checkAdd(ActionEvent event) {
        if(!checkAdd.isSelected()) {
            przedmiot.setDisable(false);
            przedmiotUpdate = przedmiot.getValue();
            populate();
        }
        else {
            przedmiot.setDisable(true);
            przedmiotUpdate = null;
        }
    }

    @FXML
    void dodaj(ActionEvent event) {
        String nazwaVal = nazwa.getText();
        int ectsVal = ects.getValue();
        if(nazwaVal.length() < 3) {
            komunikat2.setText("Nazwa musi miec minimum 3 znaki.");
            return;
        }
        if(przedmiotUpdate== null) {
            if(pr.przedmiotExists(nazwaVal)) {
                komunikat2.setText("Taki przedmiot juz istnieje");
                return;
            }
            pr.insertPrzedmiot(nazwaVal, ectsVal);
            komunikat2.setText("Dodano nowy przedmiot");
            reset();
        }
        else {
            pr.updatePrzedmiot(nazwaVal, ectsVal, przedmiotUpdate.getIdPrzedmiotu());
            komunikat2.setText("Edytowano przedmiot.");
            reset();
        }
    }

    void reset() {
        PrzedmiotyComboBox.initializeComboBoxForKierunek(przedmiot, null, false);
        nazwa.setText("");
        checkAdd.setSelected(false);
        przedmiot.setDisable(false);
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
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ZAJECIA_PODGLAD, event);
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
