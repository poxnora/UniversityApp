package com.example.demo.gui;

import com.example.demo.entity.ZajeciaEntity;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class ZajeciaComboBox {
    public static void initializeComboBox(ComboBox<ZajeciaEntity> comboBox, ArrayList<ZajeciaEntity> zajecia) {
        comboBox.getItems().addAll(zajecia);

        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(ZajeciaEntity zajeciaEntity) {
                String przedmiot = zajeciaEntity.getPrzedmiotyByIdPrzedmiotu().getNazwa();
                return przedmiot + " - " + zajeciaEntity.getFormaZajec() + " - " + zajeciaEntity.getFormaZaliczenia();
            }

            @Override
            public ZajeciaEntity fromString(String s) {
                String[] przedmiotFormaZaliczenie = s.split("\\s+");
                return comboBox.getItems().stream()
                        .filter(p -> p.getPrzedmiotyByIdPrzedmiotu().getNazwa().equals(przedmiotFormaZaliczenie[0])
                                && p.getFormaZajec().equals(przedmiotFormaZaliczenie[1])
                                && p.getFormaZaliczenia().equals(przedmiotFormaZaliczenie[2]))
                        .findFirst().orElse(null);
            }
        });
        comboBox.getSelectionModel().select(0);
    }
}
