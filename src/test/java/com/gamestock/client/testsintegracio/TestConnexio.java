package com.gamestock.client.testsintegracio;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.serveis.ServeiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//Aquesta classe encara esta per implementar perque em faltarien metodes per a recobrar
//els usuaris que es creen, ja que no puc saber l'ID
public class TestConnexio {
    private ServeiClient conexioServidor;
    
    
    @BeforeEach
    void setUp() {
        conexioServidor = ServeiClient.getInstance("http://127.0.0.1:9090");
    }
    
    @Test
    void testConnexio() {
        
    }
}

