package com.example.demo.gui;

import com.example.demo.data.ZaliczeniaTable;
import com.example.demo.data.ZaliczenieRow;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ZaliczeniaGUI {
    public static String[] oceny = new String[]{"brak","2.0", "3.0", "3.5","4.0", "4,5", "5.0"};
    public static String[] ocenyZaliczenie = new String[]{"brak", "zal", "nzal"};
    private static final Font topFont = new Font(18);

    public static void generateZaliczeniaGUI(Integer studentId, Integer semestr, Pane parent) {
        parent.getChildren().clear();
        ArrayList<ZaliczeniaTable> tableData = ZaliczeniaTable.fromStudentIdAndSemestr(studentId, semestr);
        for(ZaliczeniaTable data: tableData) {
            VBox vbox = new VBox();
            vbox.setPrefWidth(880);

            AnchorPane topAnchorPane = topAnchorPane(data.getPrzedmiot(), data.getEcts().toString());
            vbox.getChildren().addAll(topAnchorPane);

            TableView<ZaliczenieRow> table = singleTable(data);
            vbox.getChildren().add(table);

            parent.getChildren().add(vbox);
        }
    }

    private static AnchorPane topAnchorPane(String przedmiot, String ects) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(880);

        Label przedmiotLabel = new Label(przedmiot);
        przedmiotLabel.setFont(topFont);
        przedmiotLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");

        Label ectsLabel = new Label("Suma ECTS: " + ects);
        ectsLabel.setFont(topFont);
        ectsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");

        AnchorPane.setTopAnchor(przedmiotLabel, 10.0);
        AnchorPane.setLeftAnchor(przedmiotLabel, 10.0);
        AnchorPane.setBottomAnchor(przedmiotLabel, 10.0);

        AnchorPane.setTopAnchor(ectsLabel, 10.0);
        AnchorPane.setRightAnchor(ectsLabel, 10.0);
        AnchorPane.setBottomAnchor(ectsLabel, 10.0);

        anchorPane.getChildren().addAll(przedmiotLabel, ectsLabel);
        return anchorPane;
    }

    private static TableView<ZaliczenieRow> singleTable(ZaliczeniaTable tableData) {
        TableView<ZaliczenieRow> table = new TableView<>();

        ArrayList<ZaliczenieRow> rows = tableData.getZaliczenia();

        TableColumn<ZaliczenieRow, String> formaZajecCol = new TableColumn<>("Forma zajec");
        formaZajecCol.setPrefWidth(150);
        TableColumn<ZaliczenieRow, String> formaZaliczeniaCol = new TableColumn<>("Forma zaliczenia");
        formaZaliczeniaCol.setPrefWidth(150);
        TableColumn<ZaliczenieRow, String> prowadzacyCol = new TableColumn<>("Prowadzacy");
        prowadzacyCol.setPrefWidth(200);
        TableColumn<ZaliczenieRow, Integer> liczbaGodzinCol = new TableColumn<>("Liczba godzin");
        liczbaGodzinCol.setPrefWidth(100);
        TableColumn<ZaliczenieRow, String> termin1Col = new TableColumn<>("Termin I");
        termin1Col.setPrefWidth(100);
        TableColumn<ZaliczenieRow, String> poprawkowyCol = new TableColumn<>("Poprawkowy");
        poprawkowyCol.setPrefWidth(100);
        TableColumn<ZaliczenieRow, String> komisyjnyCol = new TableColumn<>("Komisyjny");
        komisyjnyCol.setPrefWidth(100);

        table.getColumns().addAll(formaZajecCol, formaZaliczeniaCol, prowadzacyCol, liczbaGodzinCol, termin1Col, poprawkowyCol, komisyjnyCol);

        String[] properties = new String[]{"formaZajec", "formaZaliczenia", "prowadzacy", "liczbaGodzin", "ocena", "ocenaPoprawkowy", "ocenaKomisyjny"};
        for (int i = 0; i < table.getColumns().size(); i++) {
            TableColumn column = table.getColumns().get(i);
            column.setCellValueFactory(new PropertyValueFactory<ZaliczenieRow, String>(properties[i]));
        }

        ObservableList<ZaliczenieRow> rowList = FXCollections.observableArrayList(rows);
        table.setItems(rowList);

        table.setFixedCellSize(26);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.05)));
        table.setMinHeight(table.getItems().size()*26 + 40);
        table.maxHeightProperty().bind(table.prefHeightProperty());

        return table;
    }

}
