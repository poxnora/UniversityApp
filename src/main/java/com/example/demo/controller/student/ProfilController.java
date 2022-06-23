package com.example.demo.controller.student;

import com.example.demo.Nawigator;
import com.example.demo.auth.HashPassword;
import com.example.demo.auth.UserInfo;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.validation.UzytkownikWalidacja;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {
    private final Nawigator nawigator = new Nawigator();
    private final HashMap<String, String> userInfo = UserInfo.getCurrentUserInfo();
    private final UzytkownicyRepository ur = new UzytkownicyRepository();

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
    private Button b_zaliczenia;

    @FXML
    private Label email;

    @FXML
    private Label email1;

    @FXML
    private Label imie;

    @FXML
    private Label kierunek;

    @FXML
    private Label labelChangePassword;

    @FXML
    private Label nazwisko;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private Label semestr;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imie.setText(userInfo.get("imie"));
        nazwisko.setText(userInfo.get("nazwisko"));
        semestr.setText(userInfo.get("semestr"));
        email.setText(userInfo.get("email"));
        email1.setText(userInfo.get("email"));
        kierunek.setText(userInfo.get("kierunek"));
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
    void ogloszenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.STUDENT_OGLOSZENIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onChangePassword() {
        String oldPassword = userInfo.get("haslo");
        System.out.println(oldPassword);
        if (!HashPassword.hash(pass1.getText()).equals(oldPassword)) {
            labelChangePassword.setText("Komunikat: Nieprawidłowe hasło!");
            labelChangePassword.setTextFill(Color.web("#FF0000"));
        } else if (!UzytkownikWalidacja.isGoodPassword(pass2.getText())){
            labelChangePassword.setText("Hasło powinno zawierać co najmniej 8 znaków\n i musi zawierać liczbę oraz litery różnej wielkości");
            labelChangePassword.setTextFill(Color.web("#FF0000"));
        } else {
            ur.changePassword(Integer.parseInt(userInfo.get("id")), HashPassword.hash(pass2.getText()));
            labelChangePassword.setText("Komunikat: Ustawiono nowe hasło!");
            labelChangePassword.setTextFill(Color.web("0BA42C"));
        }
        pass1.setText("");
        pass2.setText("");
        labelChangePassword.setVisible(true);
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


}
