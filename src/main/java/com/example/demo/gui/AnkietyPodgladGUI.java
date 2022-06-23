package com.example.demo.gui;

import com.example.demo.Nawigator;
import com.example.demo.repository.AnkietyRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.repository.ZajeciaRepository;
import com.example.demo.entity.AnkietyEntity;
import com.example.demo.entity.ZajeciaEntity;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AnkietyPodgladGUI {
    private static ZajeciaRepository zr;
    private static AnkietyRepository ar;
    private static int semestr;
    private static int studentId;

    public static void setStudentData(int semestr, int studentId) {
        AnkietyPodgladGUI.semestr = semestr;
        AnkietyPodgladGUI.studentId = studentId;
    }

    public static void initializeGUI(ZajeciaEntity zajecia, VBox parent, UzytkownicyRepository.UserRole role) {
        parent.setPrefWidth(600);
        parent.getChildren().clear();
        parent.setPadding(new Insets(10,10,10,10));
        Collection<AnkietyEntity> ankiety;

        if(zajecia == null && role == UzytkownicyRepository.UserRole.STUDENT) {
            zr = new ZajeciaRepository();
            ar = new AnkietyRepository();
            ankiety = new ArrayList<>();
            ArrayList<ZajeciaEntity> zajeciaStudenta = zr.getZajeciaByStudentIdAndSemestr(studentId, semestr);
            if(zajeciaStudenta.size() < 1) return;
            for(ZajeciaEntity z: zajeciaStudenta) {
                List<AnkietyEntity> ankietyZajec = (List<AnkietyEntity>) z.getAnkietiesByIdZajec();
                for(AnkietyEntity a: ankietyZajec) {
                    if(!ar.zestawOdpowiedziExists(studentId, a.getIdAnkiety())) {
                        ankiety.add(a);
                    }
                }
            }
        }
      else {
            ankiety = zajecia.getAnkietiesByIdZajec();
        }
        addChildren(ankiety, parent, role);

    }

    private static void addChildren(Collection<AnkietyEntity> ankiety, VBox parent, UzytkownicyRepository.UserRole role) {
        for(AnkietyEntity ankieta: ankiety) {
            VBox vBox = new VBox();
            VBox.setMargin(vBox, new Insets(0,0,20,0));
            vBox.setPrefWidth(500);
            vBox.setPadding(new Insets(10,10,10,10));
            vBox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT))));
            vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

            Label topLabel = new Label("Ankieta nr " + ankieta.getIdAnkiety());
            topLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
            ZajeciaEntity zajecia = ankieta.getZajeciaByIdZajec();

            String bottomString = "Prowadzacy: " + zajecia.getProwadzacyByIdProwadzacego().getImie() + " " +zajecia.getProwadzacyByIdProwadzacego().getNazwisko();
            Label bottomLabel = new Label(bottomString);
            bottomLabel.setMinWidth(250);

            String middleString = zajecia.getPrzedmiotyByIdPrzedmiotu().getNazwa() + " - " + zajecia.getFormaZajec();
            Label middleLabel = new Label(middleString);

            topLabel.setPadding(new Insets(5,0,5,0));
            bottomLabel.setPadding(new Insets(5,0,5,0));

            HBox bottomHbox = new HBox();
            bottomHbox.setPrefWidth(500);
            String buttonText = role == UzytkownicyRepository.UserRole.STUDENT ? "Wypełnij" : "Wyświetl odpowiedzi";
            Integer buttonMargin = role == UzytkownicyRepository.UserRole.STUDENT ? 225 : 175;
            Button button = new Button(buttonText);
            button.setStyle("-fx-border-color: black; -fx-background-color: #ffffff; -fx-border-width: 2px; -fx-font-size: 1em;");
            HashMap<String, Object> buttonData = new HashMap<>();
            buttonData.put("ankieta", ankieta);
            Nawigator.Resource resource = role == UzytkownicyRepository.UserRole.ADMIN ? Nawigator.Resource.ADMIN_ANKIETY_ODPOWIEDZI_PODGLAD
                    : role == UzytkownicyRepository.UserRole.PROWADZACY ? Nawigator.Resource.PROWADZACY_ANKIETY_ODPOWIEDZI_PODGLAD
                    : Nawigator.Resource.STUDENT_ANKIETY_WYPELNIANIE;
            button.setOnAction(event -> {
                try {
                    Nawigator.changeSceneLoadData(resource, buttonData, event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            HBox.setMargin(button, new Insets(0,0,0,buttonMargin));
            bottomHbox.getChildren().addAll(bottomLabel, button);

            vBox.getChildren().add(topLabel);
            if(role == UzytkownicyRepository.UserRole.STUDENT) vBox.getChildren().add(middleLabel);
            vBox.getChildren().add(bottomHbox) ;
            parent.getChildren().add(vBox);
        }
    }
}
