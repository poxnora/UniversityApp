package com.example.demo.gui;

import javafx.scene.control.ComboBox;

public class RolaComboBox {
    public static void initializeComboBox(ComboBox<String> comboBox) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll("Admin", "Student", "Prowadzący");
        comboBox.getSelectionModel().select(0);

    }
}
