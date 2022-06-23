package com.example.demo.gui;

import com.example.demo.repository.KierunkiRepository;
import com.example.demo.repository.PrzedmiotRepository;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.PrzedmiotyEntity;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class PrzedmiotyComboBox {
    public static void initializeComboBoxForKierunek(ComboBox<PrzedmiotyEntity> comboBox, KierunkiEntity kierunek, boolean all) {
        ArrayList<PrzedmiotyEntity> przedmioty;
        PrzedmiotRepository pr = new PrzedmiotRepository();
        if(kierunek == null) {
            przedmioty = pr.getAllPrzedmioty();
        }
        else {
            przedmioty = pr.getPrzedmotyByKierunekId(kierunek.getIdKierunku());
        }
        commonSetup(comboBox, przedmioty, all);
    }
    public static void initializeComboBoxForProwadzacy(ComboBox<PrzedmiotyEntity> comboBox, Integer idProwadzacego, boolean all) {
        ArrayList<PrzedmiotyEntity> przedmioty = new ArrayList<>();
        ArrayList<KierunkiEntity> kierunki = new KierunkiRepository().getKierunkiProwadzacego(idProwadzacego);
        PrzedmiotRepository pr = new PrzedmiotRepository();

        przedmioty.addAll(pr.getPrzedmiotyByIdProwadzacego(idProwadzacego));

        commonSetup(comboBox, przedmioty, all);
    }


    private static  void commonSetup(ComboBox<PrzedmiotyEntity> comboBox, ArrayList<PrzedmiotyEntity> przedmioty, boolean all) {
        comboBox.getItems().clear();
        if(all) comboBox.getItems().add(null);
        comboBox.getItems().addAll(przedmioty);

        comboBox.setConverter(new StringConverter<PrzedmiotyEntity>() {
            @Override
            public String toString(PrzedmiotyEntity przedmiotyEntity) {
                if(przedmiotyEntity == null) return "Wszystkie przedmioty";
                return przedmiotyEntity.getNazwa();
            }

            @Override
            public PrzedmiotyEntity fromString(String s) {
                return comboBox.getItems().stream().filter(p -> p.getNazwa().equals(s)).findFirst().orElse(null);
            }
        });

        comboBox.getSelectionModel().select(0);
    }
}
