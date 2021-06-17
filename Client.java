import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        ServerConnection connection = new ServerConnection(socket);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username : ");
        String username = scan.nextLine();

        new Thread(connection).start();

        while (true) {
            System.out.print(" : ");
            String command = keyboard.readLine();

            if (command.equals("quit"))
                break;
            output.println(command);
        }

        socket.close();
        System.exit(0);
    }
}