package com.example.demo.data;

import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.ZaliczeniaRepository;
import com.example.demo.entity.ZajeciaEntity;
import com.example.demo.entity.ZaliczeniaEntity;

import java.util.ArrayList;

public class ZaliczenieRow {
   private String formaZajec;
   private String formaZaliczenia;
   private String prowadzacy;
   private Integer liczbaGodzin;
   private String ocena;
   private String ocenaPoprawkowy;
   private String ocenaKomisyjny;

    public ZaliczenieRow(String formaZajec, String formaZaliczenia, String prowadzacy, Integer liczbaGodzin, String ocena, String ocenaPoprawkowy, String ocenaKomisyjny) {
        this.formaZajec = formaZajec;
        this.formaZaliczenia = formaZaliczenia;
        this.prowadzacy = prowadzacy;
        this.liczbaGodzin = liczbaGodzin;
        this.ocena = ocena;
        this.ocenaPoprawkowy = ocenaPoprawkowy;
        this.ocenaKomisyjny = ocenaKomisyjny;
    }

    public static ArrayList<ZaliczenieRow> fromZajeciaAndStudentId(ArrayList<ZajeciaEntity> zajeciaArray, Integer studentId) {
        ZaliczeniaRepository zaliczeniaRepository = new ZaliczeniaRepository();
        ProwadzacyRepository prowadzacyRepository = new ProwadzacyRepository();
        ArrayList<ZaliczenieRow> rows = new ArrayList<>();
        for(ZajeciaEntity zajecia: zajeciaArray) {
            String prowadzacy = prowadzacyRepository.getFullNameById(zajecia.getIdProwadzacego());
            ArrayList<ZaliczeniaEntity> zaliczenia = zaliczeniaRepository.getZaliczeniaByStudentIdAndZajeciaId(studentId, zajecia.getIdZajec());
            for(ZaliczeniaEntity zaliczenie: zaliczenia) {
                ZaliczenieRow newRow = new ZaliczenieRow(zajecia.getFormaZajec(), zajecia.getFormaZaliczenia(), prowadzacy, zajecia.getLiczbaGodzin(), zaliczenie.getOcena(), zaliczenie.getOcenaPoprawa(), zaliczenie.getOcenaKomis());
                rows.add(newRow);
            }
        }
        return rows;
    }

    //tylko do testow
    public String toString() {
        return "forma zajec: " + formaZajec + "\n" +
                "forma zaliczenia" + formaZaliczenia + '\n';
    }

    public String getFormaZajec() {
        return formaZajec;
    }

    public String getFormaZaliczenia() {
        return formaZaliczenia;
    }

    public String getProwadzacy() {
        return prowadzacy;
    }

    public Integer getLiczbaGodzin() {
        return liczbaGodzin;
    }

    public String getOcena() {
        return ocena;
    }

    public String getOcenaPoprawkowy() {
        return ocenaPoprawkowy;
    }

    public String getOcenaKomisyjny() {
        return ocenaKomisyjny;
    }
}
