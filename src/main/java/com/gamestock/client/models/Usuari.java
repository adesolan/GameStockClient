package com.gamestock.client.models;

/**
 * Classe que representa un usuari amb les seves dades bàsiques.
 */
public class Usuari {
    private String username; // Nom d'usuari
    private String password; // Contrasenya
    private String email;    // Correu electrònic
    private String role;     // Rol de l'usuari ("admin" o "usuari")

    /**
     * Crea un nou usuari amb les dades especificades.
     *
     * @param username Nom d'usuari.
     * @param password Contrasenya de l'usuari.
     * @param email    Correu electrònic de l'usuari.
     * @param role     Rol de l'usuari ("admin" o "usuari").
     */
    public Usuari(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuaris{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}