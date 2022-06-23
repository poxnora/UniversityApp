package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.controller.ILoadData;
import com.example.demo.gui.UzytkownicyAwansGUI;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.StudenciEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UzytkownicyAwansController implements Initializable , ILoadData {

    private final Nawigator nawigator = new Nawigator();
    private ObservableList<StudenciEntity> studenci;
    private HashSet<Integer> checkedIds = new HashSet<>() ;
    private StudentRepository sr = new StudentRepository();
    private UzytkownicyRepository ur = new UzytkownicyRepository();

    public interface CheckHandler {
        void includeId(Integer id, boolean include);
    }

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
    private VBox main;

    @FXML
    private Button zatwierdzenie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));

    }

    @Override
    public void loadData(HashMap<String, Object> data) {
        studenci = (ObservableList<StudenciEntity>)data.get("studenci");
        UzytkownicyAwansGUI.initializeGUI(studenci, main, ((id, include) -> {
            if(include) {
               checkedIds.add(id);
            }
            else {
                checkedIds.remove(id);
            }
        }));

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
    void zatwierdzenie(ActionEvent event) {
        for(Integer id: checkedIds) {
            StudenciEntity student = sr.getStudentById(id);
            if(student.getSemestr() > 9) {
                ur.archiwizuj(id);
            }
            else {
                sr.awans(id);
            }

        }
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_UZYTKOWNICY_PODGLAD, event);
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
