package com.example.demo.gui;

import com.example.demo.entity.AnkietyEntity;
import com.example.demo.entity.PytaniaEntity;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;

public class AnkietaWypelnianieGUI {
    public static void initializeGUI(VBox parent, AnkietyEntity ankieta) {
        parent.setPrefWidth(700);
        parent.setPadding(new Insets(10,10,10,10));
        List<PytaniaEntity> pytania = (List<PytaniaEntity>) ankieta.getZestawyPytanByIdZestawuPytan().getPytaniasByIdZestawuPytan();
        addChildren(parent, pytania);
    }

    private static void addChildren(VBox parent, List<PytaniaEntity> pytania) {
        parent.getChildren().clear();

        for(PytaniaEntity p: pytania) {
            VBox vbox = new VBox();
            vbox.setId(p.getIdPytania().toString());
            vbox.setPrefWidth(600);
            vbox.setPadding(new Insets(10,10,10,10));
            vbox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
            vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            Label topLabel = new Label(p.getTresc());
            topLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
            topLabel.setMinWidth(500);
            topLabel.setWrapText(true);
            VBox.setMargin(topLabel, new Insets(0,0,10,0));
            vbox.getChildren().add(topLabel);

            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(4);

            VBox.setMargin(vbox, new Insets(0,0,20,0));
            vbox.getChildren().add(textArea);
            parent.getChildren().add(vbox);
        }
    }

    public static HashMap<Integer, String> getOdpowiedzi(VBox parent) {
        ObservableList<Node> vboxes = parent.getChildren();
        HashMap<Integer, String> odpowiedzi = new HashMap<>();
        for(Node v: vboxes) {
            int id = Integer.parseInt(v.getId());
            TextArea textArea = (TextArea) v.lookup(".text-area");
            String text = textArea.getText();
            if(text.length() > 0)  {
               odpowiedzi.put(id, text);
            }
        }
        return odpowiedzi;
    }
}
