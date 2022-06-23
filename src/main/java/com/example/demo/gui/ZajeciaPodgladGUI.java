package com.example.demo.gui;

import com.example.demo.Nawigator;
import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.PrzedmiotyEntity;
import com.example.demo.entity.ZajeciaEntity;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ZajeciaPodgladGUI {
    public static void initializeZajeciaGUI(KierunkiEntity kierunek, PrzedmiotyEntity przedmiot, VBox parent, UzytkownicyRepository.UserRole role) {
        ArrayList<ZajeciaEntity> zajecia = new ArrayList<>(kierunek.getZajeciasByIdKierunku());
        if(przedmiot != null) {
            zajecia = filterByPrzedmiot(zajecia, przedmiot);
        }
        parent.setPrefWidth(650);
        addChildren(zajecia, parent, role);
    }

    public static void initializeZajeciaGUIforProwadzacyPrzedmiot(Integer idProwadzacego, PrzedmiotyEntity przedmiot, VBox parent, UzytkownicyRepository.UserRole role)
    {
        ProwadzacyEntity prowadzacy= new ProwadzacyRepository().getProwadzacyById(idProwadzacego);
        ArrayList<ZajeciaEntity> zajecia = new ArrayList<>(prowadzacy.getZajeciasByIdProwadzacego());

        if(przedmiot != null) {
            zajecia = filterByPrzedmiot(zajecia, przedmiot);
        }
        parent.setPrefWidth(650);
        addChildren(zajecia, parent, role);
    }

    private static ArrayList<ZajeciaEntity> filterByPrzedmiot(ArrayList<ZajeciaEntity> zajecia, PrzedmiotyEntity przedmiot) {
            ArrayList<ZajeciaEntity> zajeciaFiltered = new ArrayList<>();
            for(ZajeciaEntity z: zajecia) {
                if(z.getIdPrzedmiotu() == przedmiot.getIdPrzedmiotu()) zajeciaFiltered.add(z);
            }
            return zajeciaFiltered;
    }

    private static void addChildren(ArrayList<ZajeciaEntity> zajecia, VBox parent, UzytkownicyRepository.UserRole role) {
        parent.getChildren().clear();
        for(ZajeciaEntity z: zajecia) {
            VBox vBox = makeZajeciaVbox(z, role);
            parent.getChildren().add(vBox);
            VBox.setMargin(vBox, new Insets(0,0,20,0));
        }
    }

    private static VBox makeZajeciaVbox(ZajeciaEntity zajecia, UzytkownicyRepository.UserRole role) {
        String topText = zajecia.getPrzedmiotyByIdPrzedmiotu().getNazwa() + " - " + zajecia.getFormaZajec();
        String middleText = zajecia.getFormaZaliczenia();
        String bottomText = "Prowadzący: " + zajecia.getProwadzacyByIdProwadzacego().getImie() + " " +zajecia.getProwadzacyByIdProwadzacego().getNazwisko();
        String bottomText2 = "Semestr: " + SemestrComboBox.values[zajecia.getSemestr() - 1];

        Button button = new Button("Studenci i zaliczenia");
        button.setStyle("-fx-border-color: black; -fx-background-color: #ffffff; -fx-border-width: 2px; -fx-font-size: 0.94em;");
        button.setPadding(new Insets(5,10,10,5));
        Nawigator.Resource res = role == UzytkownicyRepository.UserRole.ADMIN ? Nawigator.Resource.ADMIN_ZAJECIA_ZALICZENIA_PODGLAD :
                Nawigator.Resource.PROWADZACY_ZAJECIA_ZALICZENIA_PODGLAD;
        

        button.setOnAction(event -> {
            HashMap<String, Object> data = new HashMap<>();
            data.put("zajecia", zajecia);

            try {
                Nawigator.changeSceneLoadData(res, data, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


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

        Label bottomLabel = new Label(bottomText);
        bottomLabel.setPrefWidth(600);
        bottomLabel.setWrapText(true);
        bottomLabel.setPadding(new Insets(0,0,10,0));
        vBox.getChildren().add(bottomLabel);

        Label bottomLabel2 = new Label(bottomText2);
        bottomLabel2.setPrefWidth(600);
        bottomLabel2.setWrapText(true);
        bottomLabel2.setPadding(new Insets(0,0,10,0));
        vBox.getChildren().add(bottomLabel2);

        vBox.getChildren().add(button);
        VBox.setMargin(button, new Insets(0,0,0,485));
        return vBox;
    }
}
