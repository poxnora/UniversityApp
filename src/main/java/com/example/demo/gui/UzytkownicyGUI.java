package com.example.demo.gui;

import com.example.demo.repository.AdministratorzyRepository;
import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.entity.AdministratorzyEntity;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.StudenciEntity;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class UzytkownicyGUI {
    private static AdministratorzyRepository ar;
    private static ProwadzacyRepository pr;
    private static StudentRepository sr;

    public static void initializeTableAdmin(TableView<AdministratorzyEntity> table) {
        table.getColumns().clear();
        table.getItems().clear();
        ar = new AdministratorzyRepository();
        ArrayList<AdministratorzyEntity> administratorzy = ar.getAllAdministratorzy();
        ObservableList<AdministratorzyEntity> rows = FXCollections.observableArrayList(administratorzy);

        TableColumn<AdministratorzyEntity, String> colImie = new TableColumn<>("Imie");
        colImie.setPrefWidth(200);
        colImie.setCellValueFactory(new PropertyValueFactory<AdministratorzyEntity, String>("imie"));

        TableColumn<AdministratorzyEntity, String> colNazwisko= new TableColumn<>("Nazwisko");
        colNazwisko.setPrefWidth(200);
        colNazwisko.setCellValueFactory(new PropertyValueFactory<AdministratorzyEntity, String>("nazwisko"));

        TableColumn<AdministratorzyEntity, Integer> colIndeks = new TableColumn<>("Indeks");
        colIndeks.setPrefWidth(200);
        colIndeks.setCellValueFactory(new PropertyValueFactory<AdministratorzyEntity, Integer>("idAdministratora"));

        colIndeks.setCellFactory(c -> new TableCell<AdministratorzyEntity, Integer>() {
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%06d", id));
                }
            }
        });
        table.getColumns().addAll(colImie,colNazwisko,colIndeks);

        table.setItems(rows);
        table.setFixedCellSize(27);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());

    }

    public static void initializeTableProwadzacy(TableView<ProwadzacyEntity> table) {
        table.getColumns().clear();
        table.getItems().clear();
       pr = new ProwadzacyRepository();
        ArrayList<ProwadzacyEntity> prowadzacy = pr.getAllProwadzacy();
        ObservableList<ProwadzacyEntity> rows = FXCollections.observableArrayList(prowadzacy);

        TableColumn<ProwadzacyEntity, String> colImie = new TableColumn<>("Imie");
        colImie.setPrefWidth(200);
        colImie.setCellValueFactory(new PropertyValueFactory<ProwadzacyEntity, String>("imie"));

        TableColumn<ProwadzacyEntity, String> colNazwisko= new TableColumn<>("Nazwisko");
        colNazwisko.setPrefWidth(200);
        colNazwisko.setCellValueFactory(new PropertyValueFactory<ProwadzacyEntity, String>("nazwisko"));

        TableColumn<ProwadzacyEntity, Integer> colIndeks = new TableColumn<>("Indeks");
        colIndeks.setPrefWidth(200);
        colIndeks.setCellValueFactory(new PropertyValueFactory<ProwadzacyEntity, Integer>("idProwadzacego"));

        colIndeks.setCellFactory(c -> new TableCell<ProwadzacyEntity, Integer>() {
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%06d", id));
                }
            }
        });
        table.getColumns().addAll(colImie,colNazwisko,colIndeks);

        table.setItems(rows);
        table.setFixedCellSize(27);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());

    }

    public static void initializeTableStudenci(TableView<StudenciEntity> table, KierunkiEntity kierunek) {
        table.getColumns().clear();
        table.getItems().clear();
        sr = new StudentRepository();
        ArrayList<StudenciEntity> studenci = sr.getStudenciByKierunekId(kierunek.getIdKierunku());
        ObservableList<StudenciEntity> rows = FXCollections.observableArrayList(studenci);

        TableColumn<StudenciEntity, String> colImie = new TableColumn<>("Imie");
        colImie.setPrefWidth(150);
        colImie.setCellValueFactory(new PropertyValueFactory<StudenciEntity, String>("imie"));

        TableColumn<StudenciEntity, String> colNazwisko= new TableColumn<>("Nazwisko");
        colNazwisko.setPrefWidth(150);
        colNazwisko.setCellValueFactory(new PropertyValueFactory<StudenciEntity, String>("nazwisko"));

        TableColumn<StudenciEntity, Integer> colIndeks = new TableColumn<>("Indeks");
        colIndeks.setPrefWidth(150);
        colIndeks.setCellValueFactory(new PropertyValueFactory<StudenciEntity, Integer>("idStudenta"));

        colIndeks.setCellFactory(c -> new TableCell<StudenciEntity, Integer>() {
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%06d", id));
                }
            }
        });
        TableColumn<StudenciEntity, Integer> colSemestr = new TableColumn<>("Semestr");
        colSemestr.setPrefWidth(150);
        colSemestr.setCellValueFactory(new PropertyValueFactory<StudenciEntity, Integer>("semestr"));

        table.getColumns().addAll(colImie,colNazwisko,colSemestr,colIndeks);

        table.setItems(rows);
        table.setFixedCellSize(27);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());

    }
}
