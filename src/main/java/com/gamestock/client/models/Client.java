package com.gamestock.client.models;

/**
 * Classe que representa un client amb les seves dades bàsiques.
 */
public class Client {
    private int id; // Codi únic identificador del client
    private String nom; // Nom del client
    private String cognom1; // Cognom del client
    private String cognom2; // Segon cognom del client
    private String email; // Correu electrònic del client
    private String telefon; // Número de telèfon del client

    /**
     * Crea un nou client amb les dades especificades.
     *
     * @param id      Identificador únic del client.
     * @param nom     Nom del client.
     * @param cognom1 Cognom del client
     * @param cognom2 Segon cognom del client
     * @param email   Correu electrònic del client.
     * @param telefon Número de telèfon del client.
     */
    public Client(int id, String nom, String cognom1, String cognom2, String email, String telefon) {
        this.id = id;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.email = email;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", email=" + email + ", telefon=" + telefon + '}';
    }   
    
}
