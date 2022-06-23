package com.example.demo.controller.student;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.controller.ILoadData;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.ZajeciaEntity;
import com.example.demo.gui.AnkietaWypelnianieGUI;
import com.example.demo.repository.AnkietyRepository;
import com.example.demo.entity.AnkietyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AnkietyWypelnianieController implements Initializable, ILoadData {

    private final Nawigator nawigator = new Nawigator();
    private AnkietyEntity ankieta;
    private AnkietyRepository ar;
    int idStudenta;

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_profil;

    @FXML
    private Button b_wyloguj;

    @FXML
    private Button b_zaliczenia;

    @FXML
    private Label zajecia;

    @FXML
    private Label email1;

    @FXML
    private VBox main;

    @FXML
    private Button zatwierdzenie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));
        ar = new AnkietyRepository();
        idStudenta = Integer.parseInt(userInfo.get("id"));
    }

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ANKIETY_PODGLAD, event);
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
            Nawigator.changeScene(Nawigator.Resource.STUDENT_OGLOSZENIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profil(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_PROFIL, event);
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
    void zaliczenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ZALICZENIA, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void zatwierdzenie(ActionEvent event) {
        HashMap<Integer, String> odpowiedzi = AnkietaWypelnianieGUI.getOdpowiedzi(main);
        if(odpowiedzi.isEmpty()) return;
        int idZestawu = ar.insertZestawOdpowiedzi(idStudenta, ankieta.getIdAnkiety());
        for(Integer id: odpowiedzi.keySet()) {
            ar.insertOdpowiedz(id, idZestawu, odpowiedzi.get(id));
        }
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData(HashMap<String, Object> data) {
        ankieta = (AnkietyEntity) data.get("ankieta");
        AnkietaWypelnianieGUI.initializeGUI(main, ankieta);
        ZajeciaEntity z = ankieta.getZajeciaByIdZajec();
        ProwadzacyEntity p = z.getProwadzacyByIdProwadzacego();
        zajecia.setText(z.getPrzedmiotyByIdPrzedmiotu().getNazwa() + " - " + z.getFormaZajec() + " - " + p.getImie() + " " + p.getNazwisko());
    }
}
