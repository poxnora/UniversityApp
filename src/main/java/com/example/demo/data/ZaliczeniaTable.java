package com.example.demo.data;

import com.example.demo.repository.PrzedmiotRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.entity.ZajeciaEntity;

import java.util.*;

public class ZaliczeniaTable {
    private String przedmiot;
    private Integer semestr;
    private Integer ects;
    private ArrayList<ZaliczenieRow> zaliczenia;

    public String getPrzedmiot() {
        return przedmiot;
    }

    public Integer getSemestr() {
        return semestr;
    }

    public Integer getEcts() {
        return ects;
    }

    public ArrayList<ZaliczenieRow> getZaliczenia() {
        return zaliczenia;
    }

    public ZaliczeniaTable(String przedmiot, Integer semestr, Integer ects, ArrayList<ZaliczenieRow> zaliczenia) {
        this.przedmiot = przedmiot;
        this.semestr = semestr;
        this.ects = ects;
        this.zaliczenia = zaliczenia;
    }

    public static ArrayList<ZaliczeniaTable> fromStudentIdAndSemestr(Integer studentId, Integer semestr) {
        StudentRepository studentRepository = new StudentRepository();
        PrzedmiotRepository przedmiotRepository = new PrzedmiotRepository();
        ArrayList<ZajeciaEntity> zajeciaSemestr = studentRepository.getZajeciaByIdAndSemestr(studentId, semestr);
        Map<String, ArrayList<ZajeciaEntity>> zajeciaByPrzedmiot = groupZajeciaByPrzedmiot(zajeciaSemestr);
        ArrayList<ZaliczeniaTable> tables = new ArrayList<>();
        for(String przedmiot: zajeciaByPrzedmiot.keySet()) {
            ArrayList<ZaliczenieRow> rows = ZaliczenieRow.fromZajeciaAndStudentId(zajeciaByPrzedmiot.get(przedmiot), studentId);
            Integer ects = przedmiotRepository.getEctsByNazwa(przedmiot);
            ZaliczeniaTable newTable = new ZaliczeniaTable(przedmiot, semestr, ects, rows);
            tables.add(newTable);
        }
        return tables;
    }

    private static Map<String, ArrayList<ZajeciaEntity>> groupZajeciaByPrzedmiot(ArrayList<ZajeciaEntity> zajecia) {
        Map<String, ArrayList<ZajeciaEntity>> grouped = new HashMap<>();
        for(ZajeciaEntity z: zajecia) {
            String przedmiot = z.getPrzedmiotyByIdPrzedmiotu().getNazwa();
                ArrayList<ZajeciaEntity> zajeciaZPrzedmiotu = null;
                if(!grouped.containsKey(przedmiot)) {
                    zajeciaZPrzedmiotu = new ArrayList<>() ;
                }
                else {
                    zajeciaZPrzedmiotu = grouped.get(przedmiot);
                }
                zajeciaZPrzedmiotu.add(z);
                grouped.put(przedmiot, zajeciaZPrzedmiotu);
        }
        return grouped;
    }

    //METODA SLUZY DO TESTOWANIA
    public String toString() {
        String result = "--------------------\n\n" +
                "Przedmiot: " + this.przedmiot + "\nSemestr: " + this.semestr + "\n" + "Ects :" + this.ects + "\n";
        for(ZaliczenieRow z: zaliczenia) {
            result = result + z.getFormaZajec() + " -- " + z.getFormaZaliczenia() + " -- " + z.getProwadzacy() + " -- " + z.getLiczbaGodzin() + " -- " + z.getOcena() + " -- " + z.getOcenaPoprawkowy() + " -- " + z.getOcenaKomisyjny() + '\n';
        }
        return result + "\n----------";
    }

}

