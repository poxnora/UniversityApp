package com.example.demo.gui;
import com.example.demo.Nawigator;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.repository.ZaliczeniaRepository;
import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.ZajeciaEntity;
import com.example.demo.entity.ZaliczeniaEntity;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ZajeciaZaliczeniaGUI {
    private static ZaliczeniaRepository zr ;
    private static StudentRepository sr;

    public static void initializeZajeciaZaliczenia(ZajeciaEntity zajecia, VBox parent, UzytkownicyRepository.UserRole role) {
        zr = new ZaliczeniaRepository();
        sr = new StudentRepository();
        ArrayList<StudenciEntity> studenci = sr.getStudenciByIdZajec(zajecia.getIdZajec());
        addChildren(studenci, zajecia,parent, role);

    }

    private static void addChildren(ArrayList<StudenciEntity> studenci, ZajeciaEntity zajecia, VBox parent, UzytkownicyRepository.UserRole role) {
        parent.setPrefWidth(650);
        parent.getChildren().clear();

        System.out.println(2);
        for(StudenciEntity s: studenci) {
            VBox vBox = makeStudentVbox(s, zajecia, role);
            parent.getChildren().add(vBox);
            VBox.setMargin(vBox, new Insets(0,0,20,0));
        }
    }

    private static VBox makeStudentVbox(StudenciEntity student, ZajeciaEntity zajecia, UzytkownicyRepository.UserRole role) {
       String topText = student.getImie() + " " +student.getNazwisko();
       String middleText = "Numer albumu: " + student.numerAlbumu();

        VBox vBox = new VBox();
        vBox.setPrefWidth(650);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT))));
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

        Label topLabel = new Label(topText);
        topLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        topLabel.setPrefWidth(600);
        topLabel.setWrapText(true);
        topLabel.setPadding(new Insets(0,0,10,0));
        vBox.getChildren().add(topLabel);

        Label middleLabel = new Label(middleText);
        middleLabel.setPrefWidth(600);
        middleLabel.setWrapText(true);
        middleLabel.setPadding(new Insets(0,0,10,0));
        vBox.getChildren().add(middleLabel);

        ArrayList<ZaliczeniaEntity> zaliczeniaArr = zr.getZaliczeniaByStudentIdAndZajeciaId(student.getIdStudenta(), zajecia.getIdZajec());
        ZaliczeniaEntity zaliczenia = zaliczeniaArr.size() > 0 ? zaliczeniaArr.get(0) : null;
        addOceny(zaliczenia, zajecia, student, vBox, role);


        return vBox;
    }

    private static void addOceny(ZaliczeniaEntity zaliczenia, ZajeciaEntity zajecia, StudenciEntity student, VBox vBox, UzytkownicyRepository.UserRole role) {
        Button button = new Button();
        button.setStyle("-fx-border-color: black; -fx-background-color: #ffffff; -fx-border-width: 2px; -fx-font-size: 1em;");
        if(zaliczenia != null) {
            button.setText("Edytuj zaliczenia");
            if(zaliczenia.getOcena() != null) {
                String topText = "Termin I: " + zaliczenia.getOcena() + " - " + zaliczenia.getData().toString();
                Label topLabel = new Label(topText);
                topLabel.setStyle("-fx-font-weight: bold");
                topLabel.setPrefWidth(600);
                topLabel.setWrapText(true);
                topLabel.setPadding(new Insets(0, 0, 10, 0));
                vBox.getChildren().add(topLabel);
                if(zaliczenia.getOcenaPoprawa() != null) {
                    String middleText = "Poprawkowy: " + zaliczenia.getOcenaPoprawa() + " - " + zaliczenia.getDataPoprawa().toString();
                    Label middleLabel = new Label(middleText);
                    middleLabel.setStyle("-fx-font-weight: bold");
                    middleLabel.setPrefWidth(600);
                    middleLabel.setWrapText(true);
                    middleLabel.setPadding(new Insets(0,0,10,0));
                    vBox.getChildren().add(middleLabel);

                    if(zaliczenia.getOcenaKomis() != null) {
                        String bottomText = "Komisyjny: " + zaliczenia.getOcenaKomis() + " - " + zaliczenia.getDataKomis().toString();
                        Label bottomLabel = new Label(bottomText);
                        bottomLabel.setStyle("-fx-font-weight: bold");
                        bottomLabel.setPrefWidth(600);
                        bottomLabel.setWrapText(true);
                        bottomLabel.setPadding(new Insets(0,0,10,0));
                        vBox.getChildren().add(bottomLabel);
                    }
                }
            }
        } else {
            button.setText("Dodaj zaliczenia");
        }

        Nawigator.Resource res = role == UzytkownicyRepository.UserRole.ADMIN ? Nawigator.Resource.ADMIN_ZAJECIA_ZALICZENIA_EDYCJA :
                Nawigator.Resource.PROWADZACY_ZAJECIA_ZALICZENIA_EDYCJA;
        HashMap<String, Object> buttonData = new HashMap<>();
        buttonData.put("zajecia", zajecia);
        buttonData.put("student", student);
        buttonData.put("zaliczenia", zaliczenia);
        button.setOnAction(e -> {
            try {
                Nawigator.changeSceneLoadData(res, buttonData, e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        vBox.getChildren().add(button);
        VBox.setMargin(button, new Insets(0,0,0,450));
    }
}
