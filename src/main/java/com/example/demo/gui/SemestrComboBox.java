package com.example.demo.gui;

import javafx.scene.control.ComboBox;

public class SemestrComboBox {
    public static final String[] values = new String[]{"I", "II", "III", "IV", "V", "VI", "VII","VIII", "IX", "X"};

    public static void initializeComboBox(ComboBox<String> cb, Integer maxSemestr) {
        for(int i = 0; i < maxSemestr; i++) {
            cb.getItems().add("Semestr " + values[i]) ;
        }
        cb.getSelectionModel().select(maxSemestr - 1);
    }

    public static int valueToInt(String value) {
        for(int i = 0; i < values.length; i++) {
            if(value.endsWith(" " + values[i])) {
                return i+1;
            }
        }
        return 0;
    }
}
