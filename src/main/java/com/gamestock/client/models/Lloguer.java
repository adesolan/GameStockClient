package com.gamestock.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa un lloguer d'un joc per part d'un client
 */
public class Lloguer {
    @JsonProperty("id")
    private long id;
    @JsonProperty("cliente_id")
    private long idClient;
    @JsonProperty("juego_id")
    private long idJoc;
    @JsonProperty("fechaAlquiler")
    private String dataLloguer;
    @JsonProperty("fechaDevolucion")
    private String dataRetorn;
    @JsonProperty("importe")
    private double preu;
    @JsonProperty("activo")
    private boolean actiu;

    /**
     * Crea un nou lloguer amb les dades especificades.
     *
     * @param id Identificador de la transacció
     * @param idClient Identificador del client que realitza el lloguer.
     * @param idJoc Identificador del joc que es lloga.
     * @param dataLloguer Data de quan es va llogar el joc.
     * @param dataRetorn Data de quan s'ha de retornar el joc.
     * @param preu Import de la transacció del lloguer.
     * @param actiu Camp que identifica si encara no s'ha tornat el joc
     */
    public Lloguer(long id, long idClient, long idJoc, String dataLloguer, String dataRetorn, double preu, boolean actiu) {
        this.id = id;
        this.idClient = idClient;
        this.idJoc = idJoc;
        this.dataLloguer = dataLloguer;
        this.dataRetorn = dataRetorn;
        this.preu = preu;
        this.actiu = actiu;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    /**
     * Obté l'identificador del client que ha llogat el joc.
     *
     * @return Identificador del client.
     */
    public long getIdClient() {
        return idClient;
    }

    /**
     * Assigna un identificador al client que ha llogat el joc.
     *
     * @param idClient Identificador del client.
     */
    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    /**
     * Obté l'identificador del joc llogat.
     *
     * @return Identificador del joc.
     */
    public long getIdJoc() {
        return idJoc;
    }

    /**
     * Assigna un identificador al joc que es lloga.
     *
     * @param idJoc Identificador del joc.
     */
    public void setIdJoc(long idJoc) {
        this.idJoc = idJoc;
    }

    /**
     * Obté la data en què es va realitzar el lloguer del joc.
     *
     * @return Data de lloguer del joc.
     */
    public String getDataLloguer() {
        return dataLloguer;
    }

    /**
     * Assigna una data de lloguer per al joc.
     *
     * @param dataLloguer Data de lloguer a assignar.
     */
    public void setDataLloguer(String dataLloguer) {
        this.dataLloguer = dataLloguer;
    }

    /**
     * Obté la data de retorn del joc.
     *
     * @return Data de retorn del joc.
     */
    public String getDataRetorn() {
        return dataRetorn;
    }

    /**
     * Assigna una data de retorn al joc.
     *
     * @param dataRetorn Data de retorn a assignar.
     */
    public void setDataRetorn(String dataRetorn) {
        this.dataRetorn = dataRetorn;
    }

    /**
     * Obté el preu de la transacció de lloguer del joc.
     *
     * @return Preu del lloguer del joc.
     */
    public double getPreu() {
        return preu;
    }

    /**
     * Assigna un preu per al lloguer del joc.
     *
     * @param preu Preu a assignar per al lloguer del joc.
     */
    public void setPreu(double preu) {
        this.preu = preu;
    }

    /**
     * Retorna una representació en forma de cadena de caràcters del lloguer.
     *
     * @return Cadena amb la representació del lloguer.
     */
    @Override
    public String toString() {
        return "Lloguer{" + "idClient=" + idClient + ", idJoc=" + idJoc + ", dataLloguer=" + dataLloguer + ", dataRetorn=" + dataRetorn + ", preu=" + preu + ", actiu=" + actiu + '}';
    }
}
