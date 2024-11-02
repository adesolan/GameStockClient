import com.gamestock.client.views.PantallaPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

class PantallaPrincipalTest {
    private PantallaPrincipal pantallaPrincipal;

    @BeforeEach
    void setUp() {
        pantallaPrincipal = new PantallaPrincipal();
    }

    @Test
    void testCanviaModelTaula() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Column1", "Column2"}, 0);
        pantallaPrincipal.canviaModelTaula(model);
        assertEquals(model, pantallaPrincipal.getModelTaula());
    }

    @Test
    void testGetBotoAfegir() {
        assertNotNull(pantallaPrincipal.getBotoAfegir());
    }

    @Test
    void testGetBotoEliminar() {
        assertNotNull(pantallaPrincipal.getBotoEliminar());
    }

    @Test
    void testGetBotoEditar() {
        assertNotNull(pantallaPrincipal.getBotoEditar());
    }
}
