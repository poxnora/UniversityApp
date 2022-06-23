package com.example.demo.gui;

import com.example.demo.entity.AnkietyEntity;
import com.example.demo.entity.OdpowiedziEntity;
import com.example.demo.entity.PytaniaEntity;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class AnkietaPodgladOdpowiedziGUI {
    public static void initializeGUI(AnkietyEntity ankieta, VBox parent, ComboBox<String> select) {
        parent.setPrefWidth(600);
        List<PytaniaEntity> pytania = (List<PytaniaEntity>) ankieta.getZestawyPytanByIdZestawuPytan().getPytaniasByIdZestawuPytan();

        if(pytania.size() < 1) return;

        setupSelect(parent, select, pytania);
        select.getSelectionModel().select(0);
        fetchOdpowiedzi(parent, pytania.get(0));
    }

    private static void setupSelect(VBox parent, ComboBox<String> select, List<PytaniaEntity> pytania) {
        select.getItems().clear();
        for(int i = 1; i <= pytania.size(); i++) {
            select.getItems().add("Pytanie nr " + i);
        }
        select.setOnAction(event -> {
            int index = select.getSelectionModel().getSelectedIndex();
            fetchOdpowiedzi(parent, pytania.get(index));
        });

    }

    private static void fetchOdpowiedzi(VBox parent, PytaniaEntity pytanie) {
        List<OdpowiedziEntity> odpowiedzi = (List<OdpowiedziEntity>) pytanie.getOdpowiedzisByIdPytania();
        parent.getChildren().clear();
        Label topLabel = new Label(pytanie.getTresc());
        topLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        VBox.setMargin(topLabel, new Insets(0,0,10,0));
        parent.getChildren().add(topLabel);

        for(OdpowiedziEntity odp: odpowiedzi) {
            VBox vBox = new VBox();
            VBox.setMargin(vBox, new Insets(0,0,20,0));
            vBox.setPrefWidth(550);
            vBox.setPadding(new Insets(10,10,10,10));
            vBox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT))));
            vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

            Label odpLabel = new Label(odp.getTresc());
            odpLabel.setMinWidth(500);
            odpLabel.setWrapText(true);

            vBox.getChildren().add(odpLabel);
            parent.getChildren().add(vBox);
        }

    }
}
