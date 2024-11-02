import com.gamestock.client.models.Client;
import com.gamestock.client.models.ServeiSimulat;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ServeiSimulatTest {

    /**
     * Testa que es pot obtenir un client existent pel seu codi.
     */
    @Test
    public void testObtenirClientExistent() {
        ServeiSimulat servei = new ServeiSimulat();
        Optional<Client> client = servei.obtenirClient(1);
        assertTrue(client.isPresent(), "El client hauria d'existir.");
        assertEquals("Joan", client.get().getNom(), "El nom del client hauria de ser Joan.");
    }

    /**
     * Testa que no es pot obtenir un client que no existeix.
     */
    @Test
    public void testObtenirClientInexistent() {
        ServeiSimulat servei = new ServeiSimulat();
        Optional<Client> client = servei.obtenirClient(100);
        assertFalse(client.isPresent(), "El client no hauria d'existir.");
    }

    /**
     * Testa que es llenci una excepció en cas d'error de comunicacions.
     */
    @Test
    public void testErrorComunicacions() {
        ServeiSimulat servei = new ServeiSimulat();
        assertThrows(RuntimeException.class, () -> servei.obtenirClient(200), "Hauria de llençar una RuntimeException per error en les comunicacions.");
    }

    /**
     * Testa que es pot afegir un nou client amb un codi no existent.
     */
    @Test
    public void testAfegirClientNou() {
        ServeiSimulat servei = new ServeiSimulat();
        Client nouClient = new Client(16, "Nou", "nou@ioc.com", "123456789");
        boolean resultat = servei.afegirClient(nouClient);
        assertTrue(resultat, "El client nou hauria d'haver estat afegit correctament.");
        Optional<Client> client = servei.obtenirClient(16);
        assertTrue(client.isPresent(), "El client hauria d'existir després d'afegir-lo.");
        assertEquals("Nou", client.get().getNom(), "El nom del client hauria de ser Nou.");
    }

    /**
     * Testa que no es pot afegir un client amb un codi que ja existeix.
     */
    @Test
    public void testAfegirClientExistint() {
        ServeiSimulat servei = new ServeiSimulat();
        Client clientExistint = new Client(1, "Client Existint", "existint@ioc.com", "987654321");
        boolean resultat = servei.afegirClient(clientExistint);
        assertFalse(resultat, "No hauria de ser possible afegir un client amb un codi existent.");
    }

    /**
     * Testa que es pot eliminar un client existent.
     */
    @Test
    public void testEliminarClientExistint() {
        ServeiSimulat servei = new ServeiSimulat();
        boolean resultat = servei.eliminarClient(1);
        assertTrue(resultat, "El client existent hauria d'haver estat eliminat.");
        Optional<Client> client = servei.obtenirClient(1);
        assertFalse(client.isPresent(), "El client no hauria d'existir després d'haver estat eliminat.");
    }

    /**
     * Testa que no es pot eliminar un client que no existeix.
     */
    @Test
    public void testEliminarClientInexistent() {
        ServeiSimulat servei = new ServeiSimulat();
        boolean resultat = servei.eliminarClient(100);
        assertFalse(resultat, "No hauria de ser possible eliminar un client que no existeix.");
    }
}
