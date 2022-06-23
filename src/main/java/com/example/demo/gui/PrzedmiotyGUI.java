package com.example.demo.gui;

import com.example.demo.repository.PrzedmiotRepository;
import com.example.demo.entity.PrzedmiotyEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrzedmiotyGUI {
        public static void populatePrzedmiotyTable(TableView<PrzedmiotyEntity> table) {
            PrzedmiotRepository pr = new PrzedmiotRepository();
            ObservableList<PrzedmiotyEntity> przedmioty = FXCollections.observableArrayList(pr.getAllPrzedmioty());

            TableColumn<PrzedmiotyEntity, String> col1 = (TableColumn<PrzedmiotyEntity, String>) table.getColumns().get(0);
            TableColumn<PrzedmiotyEntity, Integer> col2 = (TableColumn<PrzedmiotyEntity, Integer>) table.getColumns().get(1);

            col1.setCellValueFactory(new PropertyValueFactory("nazwa"));
            col2.setCellValueFactory(new PropertyValueFactory("ects"));

            table.setItems(przedmioty);

        }
}
