import com.gamestock.client.models.Client;
import com.gamestock.client.views.DialegClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DialegClientTest {
    private DialegClient dialog;

    @BeforeEach
    void setUp() {
        dialog = new DialegClient(new Frame(), "Test Dialog");
    }

    @Test
    void testConfirmarWithValidData() {
        dialog.getTextCodi().setText("1");
        dialog.getTextNom().setText("Test Client");
        dialog.getTextEmail().setText("test@example.com");
        dialog.getTextTelefon().setText("123456789");

        dialog.confirmar();

        Client client = dialog.getClient();
        assertNotNull(client);
        assertEquals(1, client.getCodi());
        assertEquals("Test Client", client.getNom());
        assertEquals("test@example.com", client.getEmail());
        assertEquals("123456789", client.getTelefon());
    }

    @Test
    void testConfirmarWithInvalidData() {
        dialog.getTextCodi().setText("abc");
        dialog.confirmar();

        assertNull(dialog.getClient());
    }

    @Test
    void testConfirmarWithEmptyNameAndEmail() {
        dialog.getTextCodi().setText("1");
        dialog.getTextNom().setText("");
        dialog.getTextEmail().setText("");

        dialog.confirmar();

        assertNull(dialog.getClient());
    }

    @Test
    void testCancelar() {
        dialog.cancelar();
        assertNull(dialog.getClient());
    }
}
