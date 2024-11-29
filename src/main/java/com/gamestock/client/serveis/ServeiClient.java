package com.gamestock.client.serveis;

/**
 *
 * @author Avery Lopez Jordan
 */
import com.gamestock.client.models.Client;
import com.gamestock.client.models.Joc;
import com.gamestock.client.models.Lloguer;
import com.gamestock.client.models.Usuari;
import com.gamestock.client.models.RespostaLogin;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * The type Servei client.
 */
public class ServeiClient {

    private static ServeiClient instance;
    private final WebClient webClient;
    private final String baseUrl;
    private static String usuariActual;

    // Constructor privat per al singleton
    private ServeiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl) // Assigna la base URL passada com a paràmetre
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Gets instance.
     *
     * @param baseUrl the base url
     * @return the instance
     */
// Mètode estàtic per obtenir la instància del singleton
    public static ServeiClient getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (ServeiClient.class) {
                if (instance == null) {
                    instance = new ServeiClient(baseUrl);
                }
            }
        }
        return instance;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServeiClient getInstance() {
        if (instance == null) {
            throw new IllegalStateException("L'instància encara no ha estat inicialitzada. Crida a getInstance(baseUrl) primer.");
        }
        return instance;
    }

    /**
     * Obtenir clients list.
     *
     * @return the list
     */
// Obtenir tots els clients
    public List<Client> obtenirClients() {
        String url = "/api/clientes";
        return obtenirDades(url, Client.class);
    }

    /**
     * Obtenir jocs list.
     *
     * @return the list
     */
// Obtenir tots els jocs
    public List<Joc> obtenirJocs() {
        String url = "/api/juegos";
        return obtenirDades(url, Joc.class);
    }

    /**
     * Obtenir lloguers list.
     *
     * @return the list
     */
// Obtenir tots els lloguers
    public List<Lloguer> obtenirLloguers() {
        String url = "/api/alquileres";
        return obtenirDades(url, Lloguer.class);
    }

    /**
     * Obtenir usuaris list.
     *
     * @return the list
     */
// Obtenir tots els usuaris
    public List<Usuari> obtenirUsuaris() {
        String url = "/api/users";
        return obtenirDades(url, Usuari.class);
    }

    // Mètode genèric per obtenir dades des del servidor
    private <T> List<T> obtenirDades(String url, Class<T> clss) {
        CountDownLatch latch = new CountDownLatch(1);
        List<T> result = new ArrayList<>();  // Inicialitzem la llista abans de passar-la al flux

        // Realitzem la petició amb WebClient
        webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(clss)
                .doOnTerminate(latch::countDown) // Quan acabi la petició, alliberem el CountDownLatch
                .doOnError(WebClientResponseException.class, ex -> {
                    System.err.println("Error: " + ex.getMessage());
                    latch.countDown();
                })
                .subscribe(result::add);  // Afegim els elements a la llista

        try {
            latch.await(5, TimeUnit.SECONDS);  // Esperem fins a 5 segons per la resposta
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Interrupció del fil si es produeix un error
        }

        return result;  // Retornem la llista de resultats
    }

    /**
     * Afegir client boolean.
     *
     * @param nouClient the nou client
     * @return the boolean
     */
// Afegir un client
    public boolean afegirClient(Client nouClient) {
        String url = "/api/clientes";
        System.out.println(nouClient);
        try {
            webClient.post()
                    .uri(url)
                    .bodyValue(nouClient)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            return true;
        } catch (WebClientResponseException e) {
            System.err.println("Error afegint client: " + e.getStatusCode() + " - " + e.getMessage());
            return false;
        }
    }

    /**
     * Eliminar client boolean.
     *
     * @param id the id
     * @return the boolean
     */
// Eliminar un client per ID
    public boolean eliminarClient(long id) {
        String url = "/api/clientes/" + id;
        Mono<String> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Editar client boolean.
     *
     * @param clientEditat the client editat
     * @return the boolean
     */
// Editar un client
    public boolean editarClient(Client clientEditat) {
        String url = "/api/clientes/" + clientEditat.getId();
        Mono<String> response = webClient.put()
                .uri(url)
                .bodyValue(clientEditat)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Afegir joc boolean.
     *
     * @param nouJoc the nou joc
     * @return the boolean
     */
// Afegir un joc
    public boolean afegirJoc(Joc nouJoc) {
        String url = "/api/juegos";
        Mono<String> response = webClient.post()
                .uri(url)
                .bodyValue(nouJoc)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Eliminar joc boolean.
     *
     * @param id the id
     * @return the boolean
     */
// Eliminar un joc per ID
    public boolean eliminarJoc(long id) {
        String url = "/api/juegos/" + id;
        Mono<String> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Editar joc boolean.
     *
     * @param jocEditat the joc editat
     * @return the boolean
     */
// Editar un joc
    public boolean editarJoc(Joc jocEditat) {
        String url = "/api/juegos/" + jocEditat.getId();
        Mono<String> response = webClient.put()
                .uri(url)
                .bodyValue(jocEditat)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Afegir lloguer boolean.
     *
     * @param nouLloguer the nou lloguer
     * @return the boolean
     */
// Afegir un lloguer
    public boolean afegirLloguer(Lloguer nouLloguer) {
        String url = "/api/alquileres";
        Mono<String> response = webClient.post()
                .uri(url)
                .bodyValue(nouLloguer)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Eliminar lloguer boolean.
     *
     * @param id the id
     * @return the boolean
     */
// Eliminar un lloguer
    public boolean eliminarLloguer(long id) {
        String url = "/api/alquileres/" + id;
        Mono<String> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Editar lloguer boolean.
     *
     * @param lloguerEditat the lloguer editat
     * @return the boolean
     */
// Editar un lloguer
    public boolean editarLloguer(Lloguer lloguerEditat) {
        String url = "/api/alquileres/" + lloguerEditat.getIdClient();
        Mono<String> response = webClient.put()
                .uri(url)
                .bodyValue(lloguerEditat)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Afegir usuari boolean.
     *
     * @param nouUsuari the nou usuari
     * @return the boolean
     */
// Afegir un usuari
    public boolean afegirUsuari(Usuari nouUsuari) {
        String url = "/api/users";
        Mono<String> response = webClient.post()
                .uri(url)
                .bodyValue(nouUsuari)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Eliminar usuari boolean.
     *
     * @param id the id
     * @return the boolean
     */
// Eliminar un usuari
    public boolean eliminarUsuari(long id) {
        String url = "/api/users/" + id;
        Mono<String> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Editar usuari boolean.
     *
     * @param usuariEditat the usuari editat
     * @return the boolean
     */
// Editar un usuari
    public boolean editarUsuari(Usuari usuariEditat) {
        String url = "/api/users/" + usuariEditat.getId();
        Mono<String> response = webClient.put()
                .uri(url)
                .bodyValue(usuariEditat)
                .retrieve()
                .bodyToMono(String.class);

        return !StringUtils.isEmpty(response.block());
    }

    /**
     * Obtenir client per id client.
     *
     * @param id the id
     * @return the client
     */
// Obtenir un client pel seu ID
    public Client obtenirClientPerId(long id) {
        String url = "/api/clientes/" + id;
        try {
            return webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Client.class)
                    .block();
        } catch (WebClientResponseException e) {
            System.err.println("Error obtenint client per ID: " + e.getStatusCode() + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Validar credencials boolean.
     *
     * @param usuari      the usuari
     * @param contrasenya the contrasenya
     * @return the boolean
     */
    public boolean validarCredencials(String usuari, String contrasenya) {
    String url = "/api/users/login";
    try {
        // Construir la petició
        RespostaLogin loginRequest = new RespostaLogin(usuari, contrasenya);

        // Enviar la petició al servidor
        webClient.post()
                .uri(url)
                .bodyValue(loginRequest)
                .retrieve()
                .toBodilessEntity()
                .block();

        // Si no hi ha cap error, el login ha estat exitós
        usuariActual = usuari;
        return true;

    } catch (WebClientResponseException e) {
        if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            System.err.println("Credencials incorrectes: " + e.getMessage());
        } else {
            System.err.println("Error inesperat: " + e.getMessage());
        }
        return false;
    }
}

    /**
     * Tancar sessio boolean.
     *
     * @return the boolean
     */
// Tancar sessió
    public boolean tancarSessio() {
        String url = "/api/users/logout";
        try {
            webClient.post()
                    .uri(url)
                    .bodyValue(Map.of("username", usuariActual))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        } catch (WebClientResponseException e) {
            System.err.println("Error tancant sessió: " + e.getStatusCode() + " - " + e.getMessage());
            return false;
        }
    }

}
