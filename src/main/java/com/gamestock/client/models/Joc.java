package com.gamestock.client.models;

/**
 * Classe que representa un joc disponible per llogar.
 */
public class Joc {
    private int id; // Identificador únic del joc
    private String titol; // Títol del joc
    private String genere; // Gènere del joc (ex: acció, aventura, etc.)
    private String estudi; // Estudi que ha creat el joc
    private double preuLloguer; // Preu de lloguer del joc
    private int stock; // Quants jocs tenim a la botiga

    /**
     * Crea un nou joc amb les dades especificades.
     * Inicialment, el joc està disponible per al lloguer.
     *
     * @param id          Identificador únic del joc.
     * @param titol       Títol del joc.
     * @param genere      Gènere del joc.
     * @param estudi      Estudi que ha creat el joc
     * @param preuLloguer Preu de lloguer del joc.
     * @param stock       Quants jocs tenim a la botiga
     */
    public Joc(int id, String titol, String genere, String estudi, double preuLloguer, int stock) {
        this.id = id;
        this.titol = titol;
        this.genere = genere;
        this.estudi = estudi;
        this.preuLloguer = preuLloguer;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getEstudi() {
        return estudi;
    }

    public void setEstudi(String estudi) {
        this.estudi = estudi;
    }

    public double getPreuLloguer() {
        return preuLloguer;
    }

    public void setPreuLloguer(double preuLloguer) {
        this.preuLloguer = preuLloguer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Joc{" + "id=" + id + ", titol=" + titol + ", genere=" + genere + ", estudi=" + estudi + ", preuLloguer=" + preuLloguer + ", stock=" + stock + '}';
    }
    
    

}
