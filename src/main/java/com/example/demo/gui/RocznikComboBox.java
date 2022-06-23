package com.example.demo.gui;

import com.example.demo.repository.KierunkiRepository;
import javafx.scene.control.ComboBox;

import java.util.Calendar;

public class RocznikComboBox {
    public static void initializeComboBox(ComboBox<Integer> comboBox)
    {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int minYear = 2000;

        for(int i = minYear; i <= currentYear; i++) {
            comboBox.getItems().add(i);
        }


    }
}
