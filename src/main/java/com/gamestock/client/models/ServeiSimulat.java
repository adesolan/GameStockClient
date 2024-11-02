package com.gamestock.client.models;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que simula un servei de gestió de clients, emmagatzemant una llista de clients
 * i proporcionant operacions per afegir, eliminar i editar clients.
 */
public class ServeiSimulat {
    private final List<Client> clients; // Llista de clients que es gestionen en aquest servei

    /**
     * Constructor que inicialitza el servei amb una llista de clients ficticis.
     */
    public ServeiSimulat() {
        clients = new ArrayList<>();
        // Afegim alguns clients ficticis inicials
        clients.add(new Client(1, "Joan", "joan@ioc.com", "654123987"));
        clients.add(new Client(2, "Anna", "anna@ioc.com", "652689952"));
        clients.add(new Client(3, "Marc", "marc@ioc.com", "691324556"));
        clients.add(new Client(4, "Laura", "laura@ioc.com", "625998877"));
        clients.add(new Client(5, "Carles", "carles@ioc.com", "679876543"));
        clients.add(new Client(6, "Marta", "marta@ioc.com", "634112233"));
        clients.add(new Client(7, "Pere", "pere@ioc.com", "677223344"));
        clients.add(new Client(8, "Sofia", "sofia@ioc.com", "610334455"));
        clients.add(new Client(9, "David", "david@ioc.com", "678998877"));
        clients.add(new Client(10, "Isabel", "isabel@ioc.com", "699112233"));
        clients.add(new Client(11, "Ramon", "ramon@ioc.com", "622334455"));
        clients.add(new Client(12, "Clara", "clara@ioc.com", "631556677"));
        clients.add(new Client(13, "Gerard", "gerard@ioc.com", "655778899"));
        clients.add(new Client(14, "Núria", "nuria@ioc.com", "698223344"));
        clients.add(new Client(15, "Oriol", "oriol@ioc.com", "664332211"));
    }


    /**
     * Retorna una llista de tots els clients registrats.
     *
     * @return Llista de clients.
     */
    public List<Client> obtenirTotsClients() {
        return new ArrayList<>(clients);
    }
    /**
     * Obté un client pel seu codi.
     *
     * @param codi Codi del client a buscar.
     * @return Un Optional que conté el client si es troba, o un Optional buit si no.
     * @throws RuntimeException si el codi és 200, simulant un error de comunicacions.
     */
    public Optional<Client> obtenirClient(int codi) {
        if (codi == 100) {
            return Optional.empty(); // client inexistent
        } else if (codi == 200) {
            throw new RuntimeException("Error en les comunicacions");
        } else {
            return clients.stream().filter(c -> c.getCodi() == codi).findFirst();
        }
    }

    /**
     * Afegeix un nou client al servei.
     *
     * @param client Client a afegir.
     * @return true si s'ha afegit correctament, false si el codi del client ja existeix.
     */
    public boolean afegirClient(Client client) {
        // Verifiquem si el codi ja existeix
        if (clients.stream().anyMatch(c -> c.getCodi() == client.getCodi())) return false;
        clients.add(client);
        return true;
    }

    /**
     * Elimina un client pel seu codi.
     *
     * @param codi Codi del client a eliminar.
     * @return true si el client s'ha eliminat correctament, false si no s'ha trobat.
     */
    public boolean eliminarClient(int codi) {
        return clients.removeIf(c -> c.getCodi() == codi);
    }

    /**
     * Edita la informació d'un client existent.
     *
     * @param client Client amb les dades actualitzades.
     * @return true si s'ha trobat i actualitzat correctament, false si no s'ha trobat el client.
     */
    public boolean editarClient(Client client) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getCodi() == client.getCodi()) {
                clients.set(i, client); // Substitueix el client existent pel nou
                return true;
            }
        }
        return false; // Retorna false si no s'ha trobat el client amb el codi especificat
    }
}