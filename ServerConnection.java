import java.io.*;
import java.net.*;

public class ServerConnection implements Runnable {
    private Socket server;
    private BufferedReader input;
    private PrintWriter output;
    private String serverResponse;

    public ServerConnection(Socket s) throws IOException {
        server = s;
        input = new BufferedReader(new InputStreamReader(server.getInputStream()));
        output = new PrintWriter(server.getOutputStream(), true);
    }

    @Override
    public void run() {

        try {
            while ((serverResponse = input.readLine()) != null) {
                System.out.println("Server say : " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
