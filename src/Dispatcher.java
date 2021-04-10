import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Dispatcher {
    public static void main(String[] args) throws Exception {
        Scanner user_input = new Scanner(System.in);

        System.out.println("Enter the port number: ");
        Integer port = user_input.nextInt();
        ServerSocket welcomeSocket = new ServerSocket(port);


        while(true){
            System.out.println("\n[+] Waiting for connection");
            Socket connection = welcomeSocket.accept();
            System.out.println("[+] Connected with: " + connection.getInetAddress() + ":" + connection.getPort());

            //passing the connection to a new worker
            Worker sonThread = new Worker(connection);
            sonThread.start();
        }
    }
}
