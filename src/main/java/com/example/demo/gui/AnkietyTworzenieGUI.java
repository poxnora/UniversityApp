package com.example.demo.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AnkietyTworzenieGUI {
    public static void initializeGUI(VBox parent) {
        parent.getChildren().clear();
        parent.setPrefWidth(620);
        parent.setPadding(new Insets(10,10,10,10));
        addPytanie(parent);
    }

    public static void addPytanie(VBox parent) {
        VBox vbox = new VBox();
        vbox.setPrefWidth(600);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label topLabel = new Label("Treść pytania:");
        topLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");
        VBox.setMargin(topLabel, new Insets(0,0,10,0));
        vbox.getChildren().add(topLabel);

        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(50);
        textArea.setPrefRowCount(4);

        VBox.setMargin(vbox, new Insets(0,0,20,0));
        vbox.getChildren().add(textArea);
        parent.getChildren().add(vbox);
    }

    public static void deletePytanie(VBox parent) {
        ObservableList<Node> children = parent.getChildren();
        if(children.size() > 0) {
            Node lastChild = children.get(children.size() - 1);
            children.remove(lastChild);
        }
    }

    public static ArrayList<String> getPytania(VBox parent) {
        ObservableList<Node> vboxes = parent.getChildren();
        ArrayList<String> pytania = new ArrayList<>();
        for(Node v: vboxes) {
            TextArea textArea = (TextArea) v.lookup(".text-area");
            String text = textArea.getText();
            if(text.length() > 0)  {
                pytania.add(text);
            }
        }
        return pytania;
    }

    public static void deleteAll(VBox vbox) {
        vbox.getChildren().clear();

    }
}
