import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {
    private static final int PORT = 9090;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            System.out.println(" [Server] Waiting for client connection....");
            Socket client = listener.accept();
            for (ClientHandler s : clients) {
                System.out.println(s);
            }
            System.out.println(" [Server] connect to client!");
            ClientHandler client_thread = new ClientHandler(client, clients);
            clients.add(client_thread);
            Thread starter = new Thread(client_thread);
            starter.start();
        }

    }      
    
}