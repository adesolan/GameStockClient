package com.gamestock.client.models;

import java.time.LocalDate;

/**
 * Classe que representa un lloguer d'un joc per part d'un client.
 */
public class Lloguer {
    private int id; // Identificador únic del lloguer
    private Joc joc; // Joc que està sent llogat
    private Client client; // Client que ha realitzat el lloguer
    private LocalDate dataLloguer; // Data en què s'ha realitzat el lloguer
    private LocalDate dataRetorn; // Data en què es retorna el joc (pot ser null si encara no s'ha retornat)

    /**
     * Crea un nou lloguer amb les dades especificades.
     *
     * @param id          Identificador únic del lloguer.
     * @param joc        Joc que es lloga.
     * @param client    Client que lloga el joc.
     * @param dataLloguer  Data en què s'ha realitzat el lloguer.
     */
    public Lloguer(int id, Joc joc, Client client, LocalDate dataLloguer) {
        this.id = id;
        this.joc = joc;
        this.client = client;
        this.dataLloguer = dataLloguer;
        this.dataRetorn = null; // Per defecte, el joc no ha estat retornat
    }

}
