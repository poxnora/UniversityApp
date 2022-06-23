package com.example.demo.gui;

import com.example.demo.repository.KierunkiRepository;
import com.example.demo.entity.KierunkiEntity;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class KierunkiComboBox {
    private static KierunkiRepository kr;
    public static void initializeComboBox(ComboBox<KierunkiEntity> comboBox, ArrayList<KierunkiEntity> kierunki, boolean includeAll) {
        if(kierunki == null) {
            kr = new KierunkiRepository();
            kierunki = kr.getAllKierunki();
        }

        comboBox.getItems().clear();
        if(includeAll) comboBox.getItems().add(null);
        comboBox.getItems().addAll(kierunki);

        comboBox.setConverter(new StringConverter<KierunkiEntity>() {
            @Override
            public String toString(KierunkiEntity kierunkiEntity) {
                if(kierunkiEntity == null) return "Wszystkie kierunki";
               return kierunkiEntity.getNazwa() + " " + kierunkiEntity.getRocznik();
            }

            @Override
            public KierunkiEntity fromString(String s) {
                if(s.equals("Wszystkie kierunki")) return null;
                String[] nazwaRocznik = s.split("\\s+");
                return comboBox.getItems().stream()
                        .filter(k -> k.getNazwa().equals(nazwaRocznik[0]) && k.getRocznik().toString().equals(nazwaRocznik[1]))
                        .findFirst().orElse(null);
            }
        });

        comboBox.getSelectionModel().select(0);
    }
}
