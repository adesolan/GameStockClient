import com.gamestock.client.views.PantallaLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class PantallaLoginTest {
    private PantallaLogin pantallaLogin;

    @BeforeEach
    void setUp() {
        pantallaLogin = new PantallaLogin();
    }

    @Test
    void testGetUsuari() {
        pantallaLogin.getTextUsuari().setText("user123");
        assertEquals("user123", pantallaLogin.getUsuari());
    }

    @Test
    void testGetContrasenya() {
        pantallaLogin.getTextContrasenya().setText("password");
        assertEquals("password", pantallaLogin.getContrasenya());
    }

    @Test
    void testButtonLoginExists() {
        JButton botoLogin = pantallaLogin.getBotoLogin();
        assertNotNull(botoLogin);
        assertEquals("Login", botoLogin.getText());
    }
}
