import java.util.*;
import java.io.*;
import java.net.*;


public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws IOException {
        this.client = client;
        this.clients = clients;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run(){
        String stream ;
        
        try {
            while ((stream = input.readLine()) != null){
                System.out.println(stream);
                output.println(stream);
                if(stream.startsWith("hi")){
                    output.println("Welcome to my server");
                }else{
                    outToAll(stream);
                }
                
                
            }
        } catch (IOException e){
            System.err.println("Error reading");
            System.err.println(e.getStackTrace());
        } finally {
            output.close();
            try {
                input.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void outToAll(String msg){
        for (ClientHandler aClient : clients) {
            aClient.output.println(msg);
        }
    }
}       
