package com.gamestock.client.models;

/**
 *
 * @author Avery Lopez Jordan
 */

/**
 * Classe que encapsula les dades d'una petició de login.
 */
public class RespostaLogin {
    private String username;
    private String password;

    /**
     * Instantiates a new Resposta login.
     *
     * @param username the username
     * @param password the password
     */
    public RespostaLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}