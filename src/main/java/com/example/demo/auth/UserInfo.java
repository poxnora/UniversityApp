package com.example.demo.auth;

import com.example.demo.repository.AdministratorzyRepository;
import com.example.demo.repository.ProwadzacyRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.AdministratorzyEntity;
import com.example.demo.entity.ProwadzacyEntity;
import com.example.demo.entity.StudenciEntity;
import com.example.demo.entity.UzytkownicyEntity;

import java.util.HashMap;

public class UserInfo {
   private static UzytkownicyEntity currentUser;
   private static UzytkownicyRepository.UserRole currentUserRole;
   private static HashMap<String, String> currentUserInfo = new HashMap<>();


    public static void setCurrentUser(UzytkownicyEntity currentUser) {
        UzytkownicyRepository ur = new UzytkownicyRepository();
        UserInfo.currentUser = currentUser;
        UserInfo.currentUserRole = ur.getRole(currentUser);


        UserInfo.currentUserInfo.put("email", currentUser.getEmail());
        currentUserInfo.put("indeks", currentUser.numerAlbumu());
        currentUserInfo.put("id", currentUser.getIdUzytkownika().toString());
        currentUserInfo.put("haslo", currentUser.getHaslo());
        if(currentUserRole == UzytkownicyRepository.UserRole.ADMIN) {
            AdministratorzyRepository ar = new AdministratorzyRepository();
            AdministratorzyEntity admin = ar.getAministratorById(currentUser.getIdUzytkownika());
            currentUserInfo.put("imie", admin.getImie());
            currentUserInfo.put("nazwisko", admin.getNazwisko());
        }
        else if(currentUserRole == UzytkownicyRepository.UserRole.STUDENT) {
            StudentRepository sr = new StudentRepository();
            StudenciEntity student = sr.getStudentById(currentUser.getIdUzytkownika());
            currentUserInfo.put("imie", student.getImie());
            currentUserInfo.put("nazwisko", student.getNazwisko());
            currentUserInfo.put("semestr", student.getSemestr().toString());
            currentUserInfo.put("kierunek", student.getKierunkiByIdKierunku().getNazwa());
        }
        else {
            ProwadzacyRepository pr = new ProwadzacyRepository();
            ProwadzacyEntity prowadzacy = pr.getProwadzacyById(currentUser.getIdUzytkownika());
            currentUserInfo.put("imie", prowadzacy.getImie());
            currentUserInfo.put("nazwisko", prowadzacy.getNazwisko());
        }
    }

    public static void remove() {
        currentUser = null;
        currentUserRole = null;
        currentUserInfo = new HashMap<>();
    }

    public static UzytkownicyEntity getCurrentUser() {
        return currentUser;
    }

    public static UzytkownicyRepository.UserRole getCurrentUserRole() {
        return currentUserRole;
    }

    public static void setCurrentUserRole(UzytkownicyRepository.UserRole currentUserRole) {
        UserInfo.currentUserRole = currentUserRole;
    }

    public static HashMap<String, String> getCurrentUserInfo() {
        return currentUserInfo;
    }




}
