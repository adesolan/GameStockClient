package com.gamestock.client.models;

import java.time.LocalDate;

/**
 * Classe que representa un lloguer d'un joc per part d'un client.
 */
public class Lloguer {
    private int idClient;
    private int idJoc;
    private String dataLloguer;
    private String dataRetorn;
    private double preu;

    /**
     * Crea un nou lloguer amb les dades especificades.
     *
     * @param idClient  Identificador del client
     * @param idJoc     Identificador del joc llogat
     * @param dataLloguer   Data de quan es va llogar el joc
     * @param dataRetorn    Data de quan s'ha de retornar el joc
     * @param preu          Import de la transacció (import es una paraula reservada de java i no es pot utilitzar)
     */
    public Lloguer(int idClient, int idJoc, String dataLloguer, String dataRetorn, double preu) {
        this.idClient = idClient;
        this.idJoc = idJoc;
        this.dataLloguer = dataLloguer;
        this.dataRetorn = dataRetorn;
        this.preu = preu;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdJoc() {
        return idJoc;
    }

    public void setIdJoc(int idJoc) {
        this.idJoc = idJoc;
    }

    public String getDataLloguer() {
        return dataLloguer;
    }

    public void setDataLloguer(String dataLloguer) {
        this.dataLloguer = dataLloguer;
    }

    public String getDataRetorn() {
        return dataRetorn;
    }

    public void setDataRetorn(String dataRetorn) {
        this.dataRetorn = dataRetorn;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Lloguer{" + "idClient=" + idClient + ", idJoc=" + idJoc + ", dataLloguer=" + dataLloguer + ", dataRetorn=" + dataRetorn + ", preu=" + preu + '}';
    }
}
