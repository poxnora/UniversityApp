package com.example.demo.validation;

public class UzytkownikWalidacja {
    public static Boolean isGoodPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    }
}
