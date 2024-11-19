package com.gamestock.client.serveis;

import com.gamestock.client.models.Client;
import com.gamestock.client.models.Joc;
import com.gamestock.client.models.Lloguer;
import com.gamestock.client.models.Usuari;
import com.gamestock.client.models.RespostaLogin;
import java.util.ArrayList;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ServeiClient {

    private static ServeiClient instance;
    private final WebClient webClient;
    private final String baseUrl;

    // Constructor privat per al singleton
    private ServeiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl) // Assigna la base URL passada com a paràmetre
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

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

    public static ServeiClient getInstance() {
        if (instance == null) {
            throw new IllegalStateException("L'instància encara no ha estat inicialitzada. Crida a getInstance(baseUrl) primer.");
        }
        return instance;
    }

    // Obtenir tots els clients
    public List<Client> obtenirClients() {
        String url = "clients";
        return obtenirDades(url, Client.class);
    }

    // Obtenir tots els jocs
    public List<Joc> obtenirJocs() {
        String url = "jocs";
        return obtenirDades(url, Joc.class);
    }

    // Obtenir tots els lloguers
    public List<Lloguer> obtenirLloguers() {
        String url = "lloguers";
        return obtenirDades(url, Lloguer.class);
    }

    // Obtenir tots els usuaris
    public List<Usuari> obtenirUsuaris() {
        String url = "usuaris";
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

// Validar credencials d'un usuari
    public Boolean[] validarCredencials(String usuari, String contrasenya) {
        String url = "login/validate?username=" + usuari + "&password=" + contrasenya;

        // Petició GET que retorna un objecte RespostaLogin
        Mono<RespostaLogin> resposta = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(RespostaLogin.class);

        // Transformar l'objecte RespostaLogin a Boolean[]
        RespostaLogin respostaLogin = resposta.block();
        if (respostaLogin == null) {
            return new Boolean[]{false, false}; // En cas d'error, valors per defecte
        }

        return new Boolean[]{respostaLogin.isAutenticat(), respostaLogin.isAdministrador()};
    }

    // Afegir un client
    public boolean afegirClient(Client nouClient) {
        String url = "clients";
        Mono<Boolean> response = webClient.post()
                .uri(url)
                .bodyValue(nouClient)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Eliminar un client per ID
    public boolean eliminarClient(int id) {
        String url = "clients/" + id;
        Mono<Boolean> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Editar un client
    public boolean editarClient(Client clientEditat) {
        String url = "clients/" + clientEditat.getId();
        Mono<Boolean> response = webClient.put()
                .uri(url)
                .bodyValue(clientEditat)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Afegir un joc
    public boolean afegirJoc(Joc nouJoc) {
        String url = "jocs";
        Mono<Boolean> response = webClient.post()
                .uri(url)
                .bodyValue(nouJoc)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Eliminar un joc per ID
    public boolean eliminarJoc(int id) {
        String url = "jocs/" + id;
        Mono<Boolean> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Editar un joc
    public boolean editarJoc(Joc jocEditat) {
        String url = "jocs/" + jocEditat.getId();
        Mono<Boolean> response = webClient.put()
                .uri(url)
                .bodyValue(jocEditat)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Afegir un lloguer
    public boolean afegirLloguer(Lloguer nouLloguer) {
        String url = "lloguers";
        Mono<Boolean> response = webClient.post()
                .uri(url)
                .bodyValue(nouLloguer)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Eliminar un lloguer
    public boolean eliminarLloguer(int id) {
        String url = "lloguers/" + id;
        Mono<Boolean> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Editar un lloguer
    public boolean editarLloguer(Lloguer lloguerEditat) {
        String url = "lloguers/" + lloguerEditat.getIdClient();
        Mono<Boolean> response = webClient.put()
                .uri(url)
                .bodyValue(lloguerEditat)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Afegir un usuari
    public boolean afegirUsuari(Usuari nouUsuari) {
        String url = "usuaris";
        Mono<Boolean> response = webClient.post()
                .uri(url)
                .bodyValue(nouUsuari)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Eliminar un usuari
    public boolean eliminarUsuari(int id) {
        String url = "usuaris/" + id;
        Mono<Boolean> response = webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }

    // Editar un usuari
    public boolean editarUsuari(Usuari usuariEditat) {
        String url = "usuaris/" + usuariEditat.getUsername();
        Mono<Boolean> response = webClient.put()
                .uri(url)
                .bodyValue(usuariEditat)
                .retrieve()
                .bodyToMono(Boolean.class);

        return response.block();
    }
}
