package com.example.demo.gui;

import javafx.scene.control.ComboBox;

public class OgloszeniaComboBox {

    public static void initializeComboBoxForStudent(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("Ogólnouczelniane", "Kierunki");
        comboBox.getSelectionModel().select(0);
    }

    public static void initializeComboBoxForProwadzacy(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("Ogólnouczelniane", "Moje");
        comboBox.getSelectionModel().select(0);
    }

    public static void initializaComboBoxForProwadzacyTworzenie(ComboBox<String> comboBox) {
        comboBox.getItems().addAll("Wszyscy studenci", "Zajęcia");
        comboBox.getSelectionModel().select(0);
    }

}
