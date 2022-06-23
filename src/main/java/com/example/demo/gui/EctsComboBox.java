package com.example.demo.gui;

import javafx.scene.control.ComboBox;

public class EctsComboBox {
    public static void initializeComboBox(ComboBox<Integer> comboBox) {
        for(int i = 0; i < 7; i++) {
            comboBox.getItems().add(i);
        }
    }
}
