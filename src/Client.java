import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String address = "localhost";
        int port = 6789;
        String num1, num2;
        String data;
        Scanner user_input = new Scanner(System.in);

        //Create connection
        System.out.println("[+] Connecting to server...");
        Socket connection = new Socket("localhost", 6789);
        System.out.println("[+] Successfull connected\n");

        //crete "file descriptor" for i/o
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        DataOutputStream outToServer = new DataOutputStream(connection.getOutputStream());

        System.out.println("Enter first number: ");
        num1 = Integer.toString(user_input.nextInt());

        System.out.println("Enter second number: ");
        num2 = Integer.toString(user_input.nextInt());

        //formatting data
        data = num1 + " " + num2 + "\n";
        //send data to server
        outToServer.writeBytes(data);

        //read data from server
        data = inFromServer.readLine();
        System.out.println("[+] Result: " + data);

        connection.close();
        System.out.println("[+] Connection closed");
    }
}
