package com.example.demo.controller.student;
import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.SemestrComboBox;
import com.example.demo.gui.ZaliczeniaGUI;
import com.example.demo.pdf.StudentPDF;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class ZaliczeniaController implements Initializable
{
    HashMap<String, String> userInfo;

    @FXML
    private Button b_ankiety;

    @FXML
    private Button raport;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_profil;

    @FXML
    private Button b_wyloguj;

    @FXML
    private Button b_zaliczenia;

    @FXML
    private Label email1;

    @FXML
    private VBox zaliczenia;

    @FXML
    private ComboBox<String> semestrCb;

    @FXML
    private TableView table1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userInfo = UserInfo.getCurrentUserInfo();
        email1.setText(userInfo.get("email"));

        ZaliczeniaGUI.generateZaliczeniaGUI(Integer.parseInt(userInfo.get("id")), Integer.parseInt(userInfo.get("semestr")), zaliczenia);
        SemestrComboBox.initializeComboBox(semestrCb, Integer.parseInt(userInfo.get("semestr")));

    }

    @FXML
    void raport() {
        raport.setDisable(true);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        raport.setDisable(false);
                    }
                }, 3000
        );
        System.out.println("generowanie raportu");
        int id = Integer.parseInt(userInfo.get("id"));
        int semestr = SemestrComboBox.valueToInt(semestrCb.getValue());
        try {
            StudentPDF.generateZaliczeniaPdf(id, semestr);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void selectSemestr(ActionEvent event) {
        String selected = semestrCb.getSelectionModel().getSelectedItem().toString();
        int semestr = SemestrComboBox.valueToInt(selected);
        ZaliczeniaGUI.generateZaliczeniaGUI(Integer.parseInt(userInfo.get("id")), semestr, zaliczenia);
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
    void zaliczenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ZALICZENIA, event);
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
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void exit (ActionEvent event){
        System.exit(0);
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



}
