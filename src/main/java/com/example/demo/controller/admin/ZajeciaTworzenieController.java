package com.example.demo.controller.admin;

import com.example.demo.Nawigator;
import com.example.demo.auth.UserInfo;
import com.example.demo.gui.KierunkiComboBox;
import com.example.demo.gui.ProwadzacyComboBox;
import com.example.demo.gui.PrzedmiotyComboBox;
import com.example.demo.gui.SemestrComboBox;
import com.example.demo.repository.ZajeciaRepository;
import com.example.demo.entity.KierunkiEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.PrzedmiotyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ZajeciaTworzenieController implements Initializable {
    private final Nawigator nawigator = new Nawigator();
    private ZajeciaRepository zr;

    @FXML
    private Button b_ankiety;

    @FXML
    private Button b_dodaj_zajecia;

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
    private Button b_wyloguj;

    @FXML
    private Button b_zajecia;

    @FXML
    private Label email1;

    @FXML
    private Label komunikat2;

    @FXML
    private TextArea l_godzin;

    @FXML
    private TextArea l_godzin_aud;

    @FXML
    private TextArea l_godzin_lab;

    @FXML
    private TextArea l_godzin_projekt;

    @FXML
    private TextArea l_godzin_sem;

    @FXML
    private ComboBox<KierunkiEntity> select1;

    @FXML
    private ComboBox<PrzedmiotyEntity> select2;

    @FXML
    private ComboBox<String> select3;

    @FXML
    private ComboBox<ProwadzacyEntity> selectprow;

    @FXML
    private ComboBox<ProwadzacyEntity> selectprow_aud;

    @FXML
    private ComboBox<ProwadzacyEntity> selectprow_lab;

    @FXML
    private ComboBox<ProwadzacyEntity> selectprow_projekt;

    @FXML
    private ComboBox<ProwadzacyEntity> selectprow_sem;

    @FXML
    private CheckBox wykladCheck;

    @FXML
    private CheckBox egzaminCheck;

    @FXML
    private CheckBox cwiczeniaAudCheck;

    @FXML
    private CheckBox cwiczeniaLabCheck;

    @FXML
    private CheckBox cwiczeniaProCheck;

    @FXML
    private CheckBox semCheck;

    private CheckBox[] checkBoxes;
    private ComboBox<ProwadzacyEntity>[] comboBoxes;
    private TextArea[] textAreas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> currentUserInfo = UserInfo.getCurrentUserInfo();
        zr = new ZajeciaRepository();
        email1.setText(currentUserInfo.get("email"));
        checkBoxes = new CheckBox[]{wykladCheck, egzaminCheck, cwiczeniaAudCheck, cwiczeniaLabCheck, cwiczeniaProCheck, semCheck};
        comboBoxes = new ComboBox[]{selectprow, null, selectprow_aud, selectprow_lab, selectprow_projekt, selectprow_sem};
        textAreas = new TextArea[]{l_godzin, null, l_godzin_aud, l_godzin_lab, l_godzin_projekt, l_godzin_sem};

        KierunkiComboBox.initializeComboBox(select1, null,false);
        select1.getSelectionModel().select(0);
        PrzedmiotyComboBox.initializeComboBoxForKierunek(select2, null,false);
        select2.getSelectionModel().select(0);
        SemestrComboBox.initializeComboBox(select3, 10);
        select3.getSelectionModel().select(0);
        egzaminCheck.setDisable(true);

        for(ComboBox cb: comboBoxes) {
            if(cb!=null) {
                ProwadzacyComboBox.initializeComboBox(cb,null);
            }
        }

    }

    @FXML
    void select1(ActionEvent event) {
        System.out.println("select1 listener");
        select2.getSelectionModel().select(0);
    }

    private void disableInputs(int i, Boolean disable) {
        if(comboBoxes[i] != null) {
            comboBoxes[i].setDisable(disable);
            comboBoxes[i].getSelectionModel().select(0);
        }
        if(textAreas[i] != null) {
            textAreas[i].setDisable(disable);
            textAreas[i].setText("");
        }
    }

    private void disableCheckbox(int i, Boolean disable) {
        if(disable)checkBoxes[i].setSelected(false);
        checkBoxes[i].setDisable(disable);
    }

    private void refreshSemCheck() {
        for(int i=0; i <= 4; i++) {
            if(checkBoxes[i].isSelected()) {
                disableRow(5, true);
                return;
            }
        }
        disableRow(5, false);
    }

    private void disableRow(int i, Boolean disable) {
        disableInputs(i, disable);
        disableCheckbox(i, disable);
    }

    @FXML
    void wykladCheck(ActionEvent event) {
        boolean checked = wykladCheck.isSelected();
        disableInputs(0, !checked);
        disableCheckbox(1, !checked);
        refreshSemCheck();
    }

    @FXML
    void egzaminCheck(ActionEvent event) {
        boolean checked = egzaminCheck.isSelected();
        refreshSemCheck();
    }

    @FXML
    void cwiczeniaAudCheck(ActionEvent event) {
        boolean checked = cwiczeniaAudCheck.isSelected();
        disableInputs(2, !checked);
        refreshSemCheck();
    }

    @FXML
    void cwiczeniaLabCheck(ActionEvent event) {
        boolean checked = cwiczeniaLabCheck.isSelected();
        disableInputs(3, !checked);
        refreshSemCheck();
    }

    @FXML
    void cwiczeniaProCheck(ActionEvent event) {
        boolean checked = cwiczeniaProCheck.isSelected();
        disableInputs(4, !checked);
        refreshSemCheck();
    }

    @FXML
    void semCheck(ActionEvent event) {
        boolean checked = semCheck.isSelected();
        for(int i = 0; i<=4; i++) {
            disableRow(i, checked);
        }
    }

    @FXML
    void ankiety(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_ANKIETY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getGodziny(int i) {
        if(i == 1) return 0;
        String input = textAreas[i].getText();
        int value = Integer.valueOf(input);
        if(value > 1 && value < 120) return value;
        return -1;
    }

    int getProwadzacyId(int i) {
        if(i == 1) i = 0;
        return comboBoxes[i].getSelectionModel().getSelectedItem().getIdProwadzacego();
    }

    @FXML
    void dodaj_zajecia(ActionEvent event) {
        Integer idKierunku = select1.getSelectionModel().getSelectedItem().getIdKierunku();
        Integer idPrzedmiotu = select2.getSelectionModel().getSelectedItem().getIdPrzedmiotu();
        Integer semestr = SemestrComboBox.valueToInt(select3.getValue());
        for(int i = 0; i<=5;i++) {
            if(checkBoxes[i].isSelected()) {
                Integer godziny =getGodziny(i);
                if(godziny < 0) {
                    komunikat("Podaj liczbę godzin z przedziału 1-120.");
                    return;
                }
                Integer prowadzacyId = getProwadzacyId(i);
                String formaZajec = checkBoxes[i].getText();
                if(!zr.checkIfZajecia(idPrzedmiotu, prowadzacyId, formaZajec)) {
                    String formaZaliczenia = i == 1 ? "Egzamin"
                            : i == 0 || i == 5 ? "Zaliczenie"
                            : "Zaliczenie na ocenę";
                    zr.insertZajecia(idPrzedmiotu,prowadzacyId,idKierunku,formaZajec,formaZaliczenia,godziny,semestr);
                } else {
                    komunikat("Duplikat: " + formaZajec);
                    return;
                }
            }
        }
        komunikat("Dodano!");
        resetInput();
    }

    void resetInput() {
        for(int i = 0; i < 5; i++) {
            disableRow(i, true);
            disableRow(i, false);
        }
        disableRow(5, true);
    }

    private void komunikat(String s) {
        komunikat2.setVisible(true);
        komunikat2.setText(s);
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
    void przedmioty(ActionEvent event) {
        try {
            Nawigator.changeScene(Nawigator.Resource.ADMIN_PRZEDMIOTY_PODGLAD, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void select2(ActionEvent event) {

    }

    @FXML
    void select3(ActionEvent event) {

    }

    @FXML
    void selectprow(ActionEvent event) {

    }

    @FXML
    void selectprow_aud(ActionEvent event) {

    }

    @FXML
    void selectprow_lab(ActionEvent event) {

    }

    @FXML
    void selectprow_projekt(ActionEvent event) {

    }

    @FXML
    void selectprow_sem(ActionEvent event) {

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
