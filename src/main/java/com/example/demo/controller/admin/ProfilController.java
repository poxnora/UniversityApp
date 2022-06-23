package com.example.demo.controller.admin;

import com.example.demo.auth.HashPassword;
import com.example.demo.auth.UserInfo;
import com.example.demo.repository.AdministratorzyRepository;
import com.example.demo.entity.AdministratorzyEntity;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.validation.UzytkownikWalidacja;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import com.example.demo.Nawigator;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {
    private AdministratorzyRepository repository;
    private AdministratorzyEntity admin;
    private final Nawigator nawigator = new Nawigator();
    private HashMap<String, String> userInfo = UserInfo.getCurrentUserInfo();
    UzytkownicyRepository ur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.repository = new AdministratorzyRepository();
        ur = new UzytkownicyRepository();
        imie.setText(userInfo.get("imie"));
        nazwisko.setText(userInfo.get("nazwisko"));
        email.setText(userInfo.get("email"));
        email1.setText(userInfo.get("email"));

     }

    @FXML
    private Label imie;
    @FXML
    private  Label nazwisko;
    @FXML
    private Label email;

    @FXML
    private Label email1;

    @FXML
    private Label labelChangePassword;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_uzytkownicy;



    @FXML
    protected void onChangePassword() {
        String oldPassword = userInfo.get("haslo");
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
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ANKIETY_PODGLAD, event);
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
    void kierunki(ActionEvent event) {
        try {
           Nawigator.changeScene(Nawigator.Resource.ADMIN_KIERUNKI_PODGLAD, event);
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
    void exit (ActionEvent event){
        System.exit(0);
    }




}