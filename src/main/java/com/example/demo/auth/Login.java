package com.example.demo.auth;

import com.example.demo.repository.UzytkownicyRepository;
import com.example.demo.entity.UzytkownicyEntity;

import java.util.Objects;

public class Login {
    public static boolean login(String email, String haslo) {
        UzytkownicyRepository ur = new UzytkownicyRepository();
        UzytkownicyEntity user = ur.getUzytkownikByEmail(email);
        if (user == null || !Objects.equals(user.getHaslo(), HashPassword.hash(haslo)) || user.getZarchiwizowany() == 1) return false;
        else {
            UserInfo.setCurrentUser(user);
        }
        return true;
    }

}

