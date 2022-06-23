package com.example.demo.gui;

import com.example.demo.repository.OgloszeniaRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.OgloszeniaEntity;
import com.example.demo.entity.UzytkownicyEntity;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class OgloszeniaGUI {
    private static final Font topFont = Font.font("System",FontWeight.BOLD, 20) ;
    private static final Font mainFont = new Font(18);

    public static void generateOgloszeniaGUI(VBox parent, KierunkiEntity kierunek) {
        ArrayList<OgloszeniaEntity> ogloszenia = getOgloszenia(kierunek);
        generateOgloszeniaGUIfromArray(parent, ogloszenia);
    }

    public static void generateOgloszeniaGUIfromArray(VBox parent, ArrayList<OgloszeniaEntity> ogloszenia) {
        UzytkownicyRepository ur = new UzytkownicyRepository();
        parent.getChildren().clear();
        parent.setPrefWidth(800);
        for(OgloszeniaEntity o: ogloszenia) {
            UzytkownicyEntity user = o.getUzytkownicyByIdUzytkownika();
            String signature = ur.getSingature(user);
            VBox child = generateOgloszenie(o, signature);
            parent.getChildren().add(child);
            VBox.setMargin(child, new Insets(0,0,20,0));
        }
    }

    private static VBox generateOgloszenie(OgloszeniaEntity ogloszenie, String signature) {
        VBox vBox = new VBox();
        vBox.setPrefWidth(700);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT))));
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

        Label tytul = new Label();
        tytul.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        tytul.setPrefWidth(600);
        tytul.setWrapText(true);
        tytul.setText(ogloszenie.getTytul());
        tytul.setPadding(new Insets(0,0,10,0));
        vBox.getChildren().add(tytul);

        Label tresc = new Label();
        tresc.setPrefWidth(650);
        tresc.setText(ogloszenie.getTresc());
        tresc.setPadding(new Insets(0,0,10,0));
        vBox.getChildren().add(tresc);

        Label podpis = new Label();
        podpis.setStyle("-fx-font-weight: bold");
        podpis.setPrefWidth(500);
        podpis.setText(signature);
        tresc.setPadding(new Insets(10,0,10,10));
        vBox.getChildren().add(podpis);


        return vBox;
    }


    private static ArrayList<OgloszeniaEntity> getOgloszenia(KierunkiEntity kierunek) {
        ArrayList<OgloszeniaEntity> ogloszenia;
        OgloszeniaRepository or = new OgloszeniaRepository();
        if(kierunek != null) {
            ogloszenia = or.getOgloszeniaByKierunek(kierunek.getNazwa(), kierunek.getRocznik());
        } else {
            ogloszenia = or.getOgloszeniaOgolnouczelniane();
        }
        return ogloszenia;
    }
}
