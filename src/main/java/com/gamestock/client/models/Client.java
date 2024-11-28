package com.gamestock.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa un client de la botiga
 */
public class Client {
    @JsonProperty("id")
    private long id;
    @JsonProperty("nombre")
    private String nom;
    @JsonProperty("apellido1")
    private String cognom1;
    @JsonProperty("apellido2")
    private String cognom2;
    @JsonProperty("email")
    private String email;
    @JsonProperty("telefono")
    private String telefon;

    /**
     * Crea un nou client amb les dades especificades.
     *
     * @param id      Identificador únic del client.
     * @param nom     Nom del client.
     * @param cognom1 Primer cognom del client.
     * @param cognom2 Segon cognom del client.
     * @param email   Correu electrònic del client.
     * @param telefon Número de telèfon del client.
     */
    public Client(long id, String nom, String cognom1, String cognom2, String email, String telefon) {
        this.id = id;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.email = email;
        this.telefon = telefon;
    }

    /**
     * Obté l'identificador únic del client.
     *
     * @return Identificador del client.
     */
    public long getId() {
        return id;
    }

    /**
     * Assigna un identificador únic al client.
     *
     * @param id Identificador a assignar.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obté el nom del client.
     *
     * @return Nom del client.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigna un nom al client.
     *
     * @param nom Nom a assignar al client.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obté el primer cognom del client.
     *
     * @return Primer cognom del client.
     */
    public String getCognom1() {
        return cognom1;
    }

    /**
     * Assigna un primer cognom al client.
     *
     * @param cognom1 Primer cognom a assignar.
     */
    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    /**
     * Obté el segon cognom del client.
     *
     * @return Segon cognom del client.
     */
    public String getCognom2() {
        return cognom2;
    }

    /**
     * Assigna un segon cognom al client.
     *
     * @param cognom2 Segon cognom a assignar.
     */
    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    /**
     * Obté el correu electrònic del client.
     *
     * @return Correu electrònic del client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Assigna un correu electrònic al client.
     *
     * @param email Correu electrònic a assignar.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obté el número de telèfon del client.
     *
     * @return Número de telèfon del client.
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Assigna un número de telèfon al client.
     *
     * @param telefon Número de telèfon a assignar.
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Retorna una representació en forma de cadena de caràcters del client.
     *
     * @return Cadena amb la representació del client.
     */
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", email=" + email + ", telefon=" + telefon + '}';
    }
}