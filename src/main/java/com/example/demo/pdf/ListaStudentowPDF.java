package com.example.demo.pdf;

import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.ZajeciaEntity;
import com.itextpdf.text.DocumentException;
import com.pdf.wu.ListaStudenowData;
import com.pdf.wu.StudentZaliczeniaPDF;

import java.io.FileNotFoundException;
import java.util.*;

public class ListaStudentowPDF {
    public static void generatePDF(ZajeciaEntity zajecia) throws DocumentException, FileNotFoundException {
        ListaStudenowData data = extractData(zajecia);
        StudentZaliczeniaPDF.generatePDFList(data);
    }

    private static ListaStudenowData extractData(ZajeciaEntity zajeciaEntity) {
        String kierunek = zajeciaEntity.getKierunkiByIdKierunku().getNazwa();
        String przedmiot = zajeciaEntity.getPrzedmiotyByIdPrzedmiotu().getNazwa();
        String zajecia = zajeciaEntity.getFormaZajec();
        ProwadzacyEntity prowadzacyEntity = zajeciaEntity.getProwadzacyByIdProwadzacego();
        String prowadzacy = prowadzacyEntity.getImie() + " " + prowadzacyEntity.getNazwisko();
        HashMap<String, String> studenciIndeksy = new HashMap<>();
        List<StudenciEntity> studenci = (List<StudenciEntity>) zajeciaEntity.getKierunkiByIdKierunku().getStudencisByIdKierunku();
        for(StudenciEntity student: studenci) {
            studenciIndeksy.put(student.numerAlbumu(), student.getNazwisko() + " " + student.getImie() );
        }
        Integer semestr = zajeciaEntity.getSemestr();

        return new ListaStudenowData(kierunek, przedmiot, zajecia, prowadzacy, studenciIndeksy, semestr);
    }
}
