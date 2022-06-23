package com.example.demo.gui;

import com.example.demo.controller.admin.UzytkownicyAwansController;
import com.example.demo.entity.StudenciEntity;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UzytkownicyAwansGUI {
    public static void initializeGUI(ObservableList<StudenciEntity> studenci, VBox parent, UzytkownicyAwansController.CheckHandler handler) {
        parent.getChildren().clear();
        parent.setPrefWidth(400);
        parent.setPadding(new Insets(10,10,10,10));
        addStudenci(studenci, parent, handler);

    }

    private static void addStudenci(ObservableList<StudenciEntity> studenci, VBox parent, UzytkownicyAwansController.CheckHandler handler) {
        HBox hbox;
        CheckBox checkBox;
        Label label;
        Label label2;
        for(StudenciEntity s: studenci) {
            hbox = new HBox();
            hbox.setPrefWidth(400);
            hbox.setPadding(new Insets(5,5,5,5));
            hbox.setBorder(new Border((new BorderStroke(Color.valueOf("#111111"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
            hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            checkBox = new CheckBox(s.numerAlbumu());
            CheckBox finalCheckBox = checkBox;
            checkBox.setOnAction(event -> {
                Integer id = Integer.valueOf(finalCheckBox.getText());
                handler.includeId(id, finalCheckBox.isSelected());
            });
            HBox.setMargin(checkBox, new Insets(0,20,0,0));
            hbox.getChildren().add(checkBox);

            label = new Label(s.getImie() + " " + s.getNazwisko());
            HBox.setMargin(label, new Insets(0,20,0,0));
            hbox.getChildren().add(label);

            label2 = new Label("Semestr: " + s.getSemestr());
            hbox.getChildren().add(label2);


            VBox.setMargin(hbox, new Insets(0,0,20,0));
            parent.getChildren().add(hbox);
        }
    }
}
