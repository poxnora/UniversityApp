package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.controller.ILoadData;
import com.example.demo.gui.ZaliczeniaGUI;
import com.example.demo.repository.ZaliczeniaRepository;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.ZajeciaEntity;
import com.example.demo.entity.ZaliczeniaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ZajeciaZaliczeniaEdycjaController implements Initializable, ILoadData {
    private final Nawigator nawigator = new Nawigator();
    private ZajeciaEntity zajecia;
    private StudenciEntity student;
    private ZaliczeniaEntity zaliczenia;
    private String forma;
    private ZaliczeniaRepository zr = new ZaliczeniaRepository();

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_kierunki;

    @FXML
    private Button b_ogloszenia;

    @FXML
    private Button b_profil;

    @FXML
    private Button b_przedmioty;

    @FXML
    private Button b_uzytkownicy;

    @FXML
    private Button zatwierdz;

    @FXML
    private Button b_wyloguj;

    @FXML
    private Button b_zajecia;

    @FXML
    private DatePicker data1;

    @FXML
    private DatePicker data2;

    @FXML
    private DatePicker data3;

    @FXML
    private Label email1;

    @FXML
    private Label imie;

    @FXML
    private Label komunikat;

    @FXML
    private Label nazwisko;

    @FXML
    private Label prowadzacy;

    @FXML
    private Label rodzaj_zajec;

    @FXML
    private ComboBox<String> select1;

    @FXML
    private ComboBox<String> select2;

    @FXML
    private ComboBox<String> select3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        email1.setText(currentUserInfo.get("email"));

    }
    @Override
    public void loadData(HashMap<String, Object> data) {
        zajecia = (ZajeciaEntity) data.get("zajecia");
        zaliczenia = (ZaliczeniaEntity) data.get("zaliczenia");
        student = (StudenciEntity) data.get("student");
        ProwadzacyEntity prowadzacy = zajecia.getProwadzacyByIdProwadzacego();

        rodzaj_zajec.setText(zajecia.getPrzedmiotyByIdPrzedmiotu().getNazwa() + " - " +zajecia.getFormaZajec() + " - " + prowadzacy.getImie() + " " + prowadzacy.getNazwisko());


        ComboBox<String>[] selectyOcen = new ComboBox[]{select1, select2, select3};
        String[] selectItems;
        if(zajecia.getFormaZaliczenia().equals("Zaliczenie")) {
            selectItems = ZaliczeniaGUI.ocenyZaliczenie;
            forma = "zal";
        }
        else {
            selectItems = ZaliczeniaGUI.oceny;
            forma = "ocena";
        }
        for(ComboBox<String> select: selectyOcen) {
            select.getItems().addAll(selectItems);
            select.getSelectionModel().select(0);
        }
        disableSecond(true);
        disableThird(true);
        data1.setValue(null);
        data1.setDisable(true);

    }



    @FXML
    void select1(ActionEvent event) {
        String val = select1.getSelectionModel().getSelectedItem();
        if(!val.equals("2.0") && !val.equals("nzal")) {
          disableSecond(true);
          disableThird(true);
        }
        else {
            disableSecond(false);
        }
        if(val.equals("brak")) {
            data1.setValue(null);
            data1.setDisable(true);
        } else {
            data1.setDisable(false);
        }
    }

    @FXML
    void select2(ActionEvent event) {
        String val = select2.getSelectionModel().getSelectedItem();
        if(!val.equals("2.0") && !val.equals("nzal")) {
           disableThird(true);
        }
        else {
            disableThird(false);
        }
        if(val.equals("brak")) {
            data2.setValue(null);
            data2.setDisable(true);
        } else {
            data2.setDisable(false);
        }

    }

    private void disableSecond(Boolean disable) {
        if(disable) {
            select2.getSelectionModel().select(0);
            data2.setValue(null);
            select2.setDisable(true);
            data2.setDisable(true);
        }
        else {
            select2.setDisable(false);
            data2.setDisable(false);
        }
    }

    private void disableThird(Boolean disable) {
        if(disable) {
            select3.getSelectionModel().select(0);
            data3.setValue(null);
            select3.setDisable(true);
            data3.setDisable(true);
        }
        else {
            select3.setDisable(false);
            data3.setDisable(false);
        }
    }

    @FXML
    void select3(ActionEvent event) {
        String val = select3.getSelectionModel().getSelectedItem();
        if(val.equals("brak")) {
            data3.setValue(null);
            data3.setDisable(true);
        } else {
            data3.setDisable(false);
        }
    }

    @FXML
    void data1(ActionEvent event) {

    }

    @FXML
    void data2(ActionEvent event) {

    }

    @FXML
    void data3(ActionEvent event) {

    }

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void exit (ActionEvent event){
        System.exit(0);
    }

    @FXML
    void kierunki(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_KIERUNKI_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ogloszenia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_OGLOSZENIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void profil(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_PROFIL, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void zatwierdz(ActionEvent event) {
        Integer zajeciaId = zajecia.getIdZajec();
        Integer studentId = student.getIdStudenta();
        String ocena = select1.getValue().equals("brak") ? null : select1.getValue();
        String ocenaPoprawa = select2.getValue().equals("brak") ? null : select2.getValue();
        String ocenaKomis = select3.getValue().equals("brak") ? null : select3.getValue();
        Date data = data1.getValue() == null ? null : Date.valueOf(data1.getValue()) ;
        Date dataPoprawa = data2.getValue() == null ? null : Date.valueOf(data2.getValue()) ;
        Date dataKomis = data3.getValue() == null ? null :Date.valueOf(data3.getValue()) ;

        if(ocena == null) {
            komunikat("Uzupełnij oceny");
            return;
        }
        if((ocena != null && data ==null) || (ocenaPoprawa != null && dataPoprawa == null) || (ocenaKomis != null && dataKomis == null)) {
            komunikat("Uzupełnij daty wszystkich zaliczeń.");
            return;
        }
        try {

            if (zaliczenia == null) {
                zr.insertZaliczenieGetId(zajeciaId, studentId, ocena, data, ocenaPoprawa, dataPoprawa, ocenaKomis, dataKomis);
            } else {
                Integer zaliczenieId = zaliczenia.getIdZaliczenia();
                zr.updateZaliczenie(zaliczenieId, ocena, data, ocenaPoprawa, dataPoprawa, ocenaKomis, dataKomis);
            }
            HashMap<String, Object> prevViewData = new HashMap<>();
            prevViewData.put("zajecia", zajecia);
            Nawigator.changeSceneLoadData(Nawigator.Resource.ADMIN_ZAJECIA_ZALICZENIA_PODGLAD, prevViewData, event);
        } catch (Error | IOException e) {
            komunikat("Coś poszło nie tak.");
            System.out.println(e.getMessage());

        }
    }

    private void komunikat(String s) {
        komunikat.setVisible(true);
        komunikat.setText(s);
    }


    @FXML
    void przedmioty(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_PRZEDMIOTY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void uzytkownicy(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_UZYTKOWNICY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void wyloguj(ActionEvent event) {
        try {
            UserInfo.remove();
            Nawigator.changeScene(Nawigator.Resource.LOGOWANIE, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void zajecia(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ZAJECIA_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
