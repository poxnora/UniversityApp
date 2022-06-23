package com.example.demo.pdf;

import com.example.demo.data.ZaliczenieRow;
import com.example.demo.repository.StudentRepository;
import com.example.demo.data.ZaliczeniaTable;
import com.example.demo.entity.StudenciEntity;
import com.itextpdf.text.DocumentException;
import com.pdf.wu.StudentZaliczeniaData;
import com.pdf.wu.StudentZaliczeniaPDF;
import com.pdf.wu.StudentZaliczeniaRow;
import com.pdf.wu.StudentZaliczeniaTable;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StudentPDF {
    public static void generateZaliczeniaPdf(Integer studentId, Integer semestr) throws DocumentException, FileNotFoundException {
        ArrayList<ZaliczeniaTable> tables = ZaliczeniaTable.fromStudentIdAndSemestr(studentId, semestr);
        ArrayList<StudentZaliczeniaTable> parsedTables = parseTables(tables);
        StudentRepository studentRepository = new StudentRepository();
        StudenciEntity student = studentRepository.getStudentById(studentId);
        StudentZaliczeniaData data = new StudentZaliczeniaData(student.getImie(), student.getNazwisko(), student.numerAlbumu(), student.getKierunkiByIdKierunku().getNazwa(), semestr, parsedTables);
        StudentZaliczeniaPDF.generatePDF(data);
    }

    private static ArrayList<StudentZaliczeniaTable> parseTables(ArrayList<ZaliczeniaTable> tables) {
        ArrayList<StudentZaliczeniaTable> parsed = new ArrayList<>();
        for (ZaliczeniaTable z : tables) {
            ArrayList<StudentZaliczeniaRow> rows = parseRows(z.getZaliczenia());
            parsed.add(new StudentZaliczeniaTable(z.getPrzedmiot(), z.getEcts(), rows));
        }
        return parsed;
    }

    private static ArrayList<StudentZaliczeniaRow> parseRows(ArrayList<ZaliczenieRow> rows) {
        ArrayList<StudentZaliczeniaRow> newRows = new ArrayList<>();
        for(ZaliczenieRow oldRow: rows) {
            StudentZaliczeniaRow newRow = new StudentZaliczeniaRow(oldRow.getFormaZajec(), oldRow.getFormaZaliczenia(), oldRow.getProwadzacy(), oldRow.getLiczbaGodzin(), oldRow.getOcena(), oldRow.getOcenaPoprawkowy(), oldRow.getOcenaKomisyjny());
            newRows.add(newRow);
        }
        return newRows;
    }
}
