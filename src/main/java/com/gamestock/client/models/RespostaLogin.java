package com.gamestock.client.models;

/**
 * Classe que encapsula les dades d'una petició de login.
 */
public class RespostaLogin {
    private String username;
    private String password;

    public RespostaLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}