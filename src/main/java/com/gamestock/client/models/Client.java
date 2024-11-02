package com.gamestock.client.models;

/**
 * Classe que representa un client amb les seves dades bàsiques.
 */
public class Client {
    private int id; // Codi únic identificador del client
    private String nom; // Nom del client
    private String email; // Correu electrònic del client
    private String telefon; // Número de telèfon del client

    /**
     * Crea un nou client amb les dades especificades.
     *
     * @param id      Identificador únic del client.
     * @param nom     Nom del client.
     * @param email   Correu electrònic del client.
     * @param telefon Número de telèfon del client.
     */
    public Client(int id, String nom, String email, String telefon) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telefon = telefon;
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
     * Obté l'identificador únic del client.
     *
     * @return Identificador del client.
     */
    public int getCodi() {
        return id;
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
     * Obté el número de telèfon del client.
     *
     * @return Telèfon del client.
     */
    public String getTelefon() { return telefon; }
}
