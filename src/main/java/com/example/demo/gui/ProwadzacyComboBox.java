package com.example.demo.gui;

import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.entity.ProwadzacyEntity;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class ProwadzacyComboBox {
    private static ProwadzacyRepository pr;
    public static void initializeComboBox(ComboBox<ProwadzacyEntity> comboBox, ArrayList<ProwadzacyEntity> prowadzacy) {
        if(comboBox == null) return;
        pr = new ProwadzacyRepository();
        if(prowadzacy == null) {
            prowadzacy =  pr.getAllProwadzacy();
        }

        comboBox.getItems().addAll(prowadzacy);

        comboBox.setConverter(new StringConverter<ProwadzacyEntity>() {
            @Override
            public String toString(ProwadzacyEntity p) {
               return p.getImie() + " " + p.getNazwisko() + " " + p.numerAlbumu();
            }

            @Override
            public ProwadzacyEntity fromString(String s) {
                if(s == null) return null;
                String indeks = s.split(" ")[2];
                return comboBox.getItems().stream().filter(p -> p.numerAlbumu().equals(indeks)).findFirst().orElse(null);
            }
        });

    }
}
