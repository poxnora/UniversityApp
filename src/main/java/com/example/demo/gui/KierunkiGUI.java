package com.example.demo.gui;

import com.example.demo.repository.KierunkiRepository;
import com.example.demo.entity.KierunkiEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class KierunkiGUI {
    public static void generateTableRows(TableView<KierunkiEntity> table) {
        KierunkiRepository kr = new KierunkiRepository();
        ObservableList<KierunkiEntity> kierunki = FXCollections.observableArrayList(kr.getAllKierunki());

        TableColumn<KierunkiEntity, String> col1 = (TableColumn<KierunkiEntity, String>) table.getColumns().get(0);
        TableColumn<KierunkiEntity, Integer> col2 = (TableColumn<KierunkiEntity, Integer>) table.getColumns().get(1);

        col1.setCellValueFactory(new PropertyValueFactory<KierunkiEntity, String>("nazwa"));
        col2.setCellValueFactory(new PropertyValueFactory<>("rocznik"));

        table.setItems(kierunki);
    }
}
