import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

    public static void main(String[] args) {
        String host = "127.0.0.1";  // Nom del servidor (localhost per a la mateixa màquina)
        int port = 8080;           // El port on el servidor està escoltant
        
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Enviar un missatge al servidor
            String message = "/api/users/";
            out.println(message);

            // Llegir la resposta del servidor
            String response = in.readLine();
            System.out.println("Resposta del servidor: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}