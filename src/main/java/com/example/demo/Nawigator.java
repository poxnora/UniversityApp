package com.example.demo;

import com.example.demo.controller.ILoadData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Nawigator {
    public enum Resource {
        ADMIN_OGLOSZENIA_PODGLAD,
        ADMIN_OGLOSZENIA_TWORZENIE,
        ADMIN_PROFIL,
        ADMIN_UZYTKOWNICY_PODGLAD,
        ADMIN_UZYTKOWNICY_AWANS,
        ADMIN_UZYTKOWNICY_TWORZENIE,
        ADMIN_UZYTKOWNICY_EDYCJA,
        ADMIN_KIERUNKI_PODGLAD,
        ADMIN_KIERUNKI_EDYCJA,
        ADMIN_PRZEDMIOTY_PODGLAD,
        ADMIN_PRZEDMIOTY_EDYCJA,
        ADMIN_ZAJECIA_PODGLAD,
        ADMIN_ZAJECIA_TWORZENIE,
        ADMIN_ZAJECIA_ZALICZENIA_PODGLAD,
        ADMIN_ZAJECIA_ZALICZENIA_EDYCJA,
        ADMIN_ANKIETY_PODGLAD,
        ADMIN_ANKIETY_ODPOWIEDZI_PODGLAD,
        ADMIN_ANKIETY_TWORZENIE,
        PROWADZACY_PROFIL,
        PROWADZACY_OGLOSZENIA_TWORZENIE,
        PROWADZACY_OGLOSZENIA_PODGLAD,
        PROWADZACY_ZAJECIA_PODGLAD,
        PROWADZACY_ZAJECIA_ZALICZENIA_PODGLAD,
        PROWADZACY_ZAJECIA_ZALICZENIA_EDYCJA,
        PROWADZACY_ANKIETY_PODGLAD,
        PROWADZACY_ANKIETY_ODPOWIEDZI_PODGLAD,
        STUDENT_OGLOSZENIA_PODGLAD,
        STUDENT_ANKIETY_PODGLAD,
        STUDENT_ANKIETY_WYPELNIANIE,
        STUDENT_PROFIL,
        STUDENT_ZALICZENIA,
        LOGOWANIE,
    }

     public static String getPath(Resource resource) {
        String path = "/fxml/" +  switch(resource) {
            case ADMIN_OGLOSZENIA_PODGLAD -> "admin/ogloszenia_podglad";
            case ADMIN_OGLOSZENIA_TWORZENIE -> "admin/ogloszenia_tworzenie";
            case ADMIN_PROFIL -> "admin/profil";
            case ADMIN_UZYTKOWNICY_TWORZENIE -> "admin/uzytkownicy_tworzenie";
            case ADMIN_UZYTKOWNICY_EDYCJA -> "admin/uzytkownicy_edycja";
            case ADMIN_UZYTKOWNICY_PODGLAD -> "admin/uzytkownicy_podglad";
            case ADMIN_UZYTKOWNICY_AWANS -> "admin/uzytkownicy_awans";
            case ADMIN_PRZEDMIOTY_PODGLAD -> "admin/przedmioty_podglad";
            case ADMIN_PRZEDMIOTY_EDYCJA -> "admin/przedmioty_edycja";
            case ADMIN_KIERUNKI_PODGLAD -> "admin/kierunki_podglad";
            case ADMIN_KIERUNKI_EDYCJA -> "admin/kierunki_edycja";
            case ADMIN_ZAJECIA_PODGLAD -> "admin/zajecia_podglad";
            case ADMIN_ZAJECIA_TWORZENIE -> "admin/zajecia_tworzenie";
            case ADMIN_ZAJECIA_ZALICZENIA_PODGLAD -> "admin/zajecia_zaliczenia_podglad";
            case ADMIN_ZAJECIA_ZALICZENIA_EDYCJA -> "admin/zajecia_zaliczenie_edycja";
            case ADMIN_ANKIETY_PODGLAD -> "admin/ankiety_podglad";
            case ADMIN_ANKIETY_ODPOWIEDZI_PODGLAD -> "admin/ankiety_odpowiedzi_podglad";
            case ADMIN_ANKIETY_TWORZENIE-> "admin/ankiety_tworzenie";
            case PROWADZACY_OGLOSZENIA_PODGLAD -> "prowadzacy/ogloszenia_podglad";
            case PROWADZACY_PROFIL -> "prowadzacy/profil";
            case PROWADZACY_OGLOSZENIA_TWORZENIE -> "prowadzacy/ogloszenia_tworzenie";
            case PROWADZACY_ZAJECIA_PODGLAD -> "prowadzacy/zajecia_podglad";
            case PROWADZACY_ZAJECIA_ZALICZENIA_PODGLAD -> "prowadzacy/zajecia_zaliczenia_podglad";
            case PROWADZACY_ZAJECIA_ZALICZENIA_EDYCJA-> "prowadzacy/zajecia_zaliczenie_edycja";
            case PROWADZACY_ANKIETY_PODGLAD -> "prowadzacy/ankiety_podglad";
            case PROWADZACY_ANKIETY_ODPOWIEDZI_PODGLAD -> "prowadzacy/ankiety_odpowiedzi_podglad";
            case STUDENT_ANKIETY_PODGLAD -> "student/ankiety_podglad";
            case STUDENT_ANKIETY_WYPELNIANIE -> "student/ankiety_wypelnianie";
            case STUDENT_PROFIL -> "student/profil";
            case STUDENT_OGLOSZENIA_PODGLAD -> "student/ogloszenia_podglad";
            case STUDENT_ZALICZENIA -> "student/zaliczenia";
            case LOGOWANIE -> "logowanie";
        } + ".fxml";
        return path;
    }


    public static void changeScene(Nawigator.Resource resource, ActionEvent event) throws IOException {
        String resourcePath = Nawigator.getPath(resource);
        Parent root = FXMLLoader.load(Nawigator.class.getResource(resourcePath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeSceneLoadData(Nawigator.Resource resource, HashMap<String, Object> data, ActionEvent event) throws IOException {
        String resourcePath = Nawigator.getPath(resource);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Nawigator.class.getResource(resourcePath));
        Parent root = loader.load();
        try {
            ((ILoadData)loader.getController()).loadData(data);
        } catch (Error e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
