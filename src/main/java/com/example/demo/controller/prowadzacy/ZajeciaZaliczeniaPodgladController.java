package com.example.demo.controller.prowadzacy;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.controller.ILoadData;
import com.example.demo.gui.ZajeciaZaliczeniaGUI;
import com.example.demo.pdf.ListaStudentowPDF;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.ZajeciaEntity;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ZajeciaZaliczeniaPodgladController implements Initializable, ILoadData {
    private final UzytkownicyRepository ur = new UzytkownicyRepository();
    private final HashMap<String,String> userInfo = UserInfo.getCurrentUserInfo();
    private ZajeciaEntity zajecia;

    @FXML
    private Button b_ankiety;

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
    private VBox zajecia_main;

    @FXML
    private Button raport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email1.setText(userInfo.get("email"));

    }

    @Override
    public void loadData(HashMap<String, Object> data) {
        this.zajecia = (ZajeciaEntity) data.get("zajecia");
        ZajeciaZaliczeniaGUI.initializeZajeciaZaliczenia(zajecia, zajecia_main, UzytkownicyRepository.UserRole.PROWADZACY);
    }

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_ANKIETY_PODGLAD, event);
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
    void raport(ActionEvent event) {
        raport.setDisable(true);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        raport.setDisable(false);
                    }
                }, 3000
        );
        try {
            ListaStudentowPDF.generatePDF(zajecia);
        } catch(FileNotFoundException | DocumentException e) {
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
            Nawigator.changeScene(Nawigator.Resource.PROWADZACY_ZAJECIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
