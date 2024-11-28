package com.gamestock.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa un joc disponible per llogar
 */
public class Joc {
    @JsonProperty("id")
    private long id;
    @JsonProperty("nombre")
    private String titol;
    @JsonProperty("genero")
    private String genere;
    @JsonProperty("estudio")
    private String estudi;
    @JsonProperty("precio")
    private double preuLloguer;
    @JsonProperty("stock")
    private int stock;

    /**
     * Crea un nou joc amb les dades especificades.
     *
     * @param id          Identificador únic del joc.
     * @param titol       Títol del joc.
     * @param genere      Gènere del joc (ex: acció, aventura, etc.).
     * @param estudi      Estudi que ha creat el joc.
     * @param preuLloguer Preu de lloguer del joc.
     * @param stock       Quantitat de jocs disponibles a la botiga.
     */
    public Joc(long id, String titol, String genere, String estudi, double preuLloguer, int stock) {
        this.id = id;
        this.titol = titol;
        this.genere = genere;
        this.estudi = estudi;
        this.preuLloguer = preuLloguer;
        this.stock = stock;
    }

    /**
     * Obté l'identificador únic del joc.
     *
     * @return Identificador del joc.
     */
    public long getId() {
        return id;
    }

    /**
     * Assigna un identificador únic al joc.
     *
     * @param id Identificador a assignar al joc.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obté el títol del joc.
     *
     * @return Títol del joc.
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Assigna un títol al joc.
     *
     * @param titol Títol a assignar al joc.
     */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     * Obté el gènere del joc.
     *
     * @return Gènere del joc.
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Assigna un gènere al joc.
     *
     * @param genere Gènere a assignar al joc.
     */
    public void setGenere(String genere) {
        this.genere = genere;
    }

    /**
     * Obté l'estudi que ha creat el joc.
     *
     * @return Estudi que ha creat el joc.
     */
    public String getEstudi() {
        return estudi;
    }

    /**
     * Assigna un estudi al joc.
     *
     * @param estudi Estudi a assignar al joc.
     */
    public void setEstudi(String estudi) {
        this.estudi = estudi;
    }

    /**
     * Obté el preu de lloguer del joc.
     *
     * @return Preu de lloguer del joc.
     */
    public double getPreuLloguer() {
        return preuLloguer;
    }

    /**
     * Assigna un preu de lloguer al joc.
     *
     * @param preuLloguer Preu de lloguer a assignar al joc.
     */
    public void setPreuLloguer(double preuLloguer) {
        this.preuLloguer = preuLloguer;
    }

    /**
     * Obté la quantitat de jocs disponibles a la botiga.
     *
     * @return Nombre de jocs disponibles.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Assigna un valor al stock de jocs disponibles a la botiga.
     *
     * @param stock Nombre de jocs a assignar al stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retorna una representació en forma de cadena de caràcters del joc.
     *
     * @return Cadena amb la representació del joc.
     */
    @Override
    public String toString() {
        return "Joc{" + "id=" + id + ", titol=" + titol + ", genere=" + genere + ", estudi=" + estudi + ", preuLloguer=" + preuLloguer + ", stock=" + stock + '}';
    }
}