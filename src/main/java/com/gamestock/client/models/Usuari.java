package com.gamestock.client.models;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa un usuari
 */
public class Usuari {
    @JsonProperty("id")
    private long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password; // Contrasenya
    @JsonProperty("email")
    private String email;    // Correu electrònic
    @JsonProperty("role")
    private String role;     // Rol de l'usuari ("admin" o "usuari")

    /**
     * Crea un nou usuari amb les dades especificades.
     *
     * @param id       the id
     * @param username Nom d'usuari.
     * @param password Contrasenya de l'usuari.
     * @param email    Correu electrònic de l'usuari.
     * @param role     Rol de l'usuari ("admin" o "usuari").
     */
    public Usuari(long id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Obté el nom d'usuari.
     *
     * @return El nom d'usuari.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Assigna el nom d'usuari.
     *
     * @param username El nom d'usuari.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obté la contrasenya de l'usuari.
     *
     * @return La contrasenya de l'usuari.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Assigna la contrasenya a l'usuari.
     *
     * @param password La contrasenya de l'usuari.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obté el correu electrònic de l'usuari.
     *
     * @return El correu electrònic de l'usuari.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Assigna el correu electrònic a l'usuari.
     *
     * @param email El correu electrònic de l'usuari.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obté el rol de l'usuari.
     *
     * @return El rol de l'usuari ("admin" o "usuari").
     */
    public String getRole() {
        return role;
    }

    /**
     * Assigna el rol a l'usuari.
     *
     * @param role El rol de l'usuari ("admin" o "usuari").
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retorna una representació en forma de cadena de caràcters de l'usuari
     *
     * @return Cadena amb la representació de l'usuari
     */
    @Override
    public String toString() {
        return "Usuari{" +
               "id='" + id + '\'' +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}