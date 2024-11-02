package com.gamestock.client.models;

/**
 * Classe que representa un joc disponible per llogar.
 */
public class Joc {
    private int id; // Identificador únic del joc
    private String titol; // Títol del joc
    private String genere; // Gènere del joc (ex: acció, aventura, etc.)
    private double preuLloguer; // Preu de lloguer del joc
    private boolean estaDisponible; // Indica si el joc està disponible per al lloguer

    /**
     * Crea un nou joc amb les dades especificades.
     * Inicialment, el joc està disponible per al lloguer.
     *
     * @param id          Identificador únic del joc.
     * @param titol       Títol del joc.
     * @param genere       Gènere del joc.
     * @param preuLloguer Preu de lloguer del joc.
     */
    public Joc(int id, String titol, String genere, double preuLloguer) {
        this.id = id;
        this.titol = titol;
        this.genere = genere;
        this.preuLloguer = preuLloguer;
        this.estaDisponible = true; // Per defecte, el joc està disponible
    }

}
