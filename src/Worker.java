import java.net.*;
import java.io.*;
public class Worker extends Thread{
    private Socket connection = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private String data;
    private int num1,num2;
    private String[] numbers;

    public Worker(Socket newSocket){
        connection = newSocket;
        try{
            inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outToClient = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            //read data from client
            data = inFromClient.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data != null){
            //extract numbers from data
            numbers = data.split(" ");
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[1]);

            //adds the numbers and returns a string
            data = Integer.toString(num1+num2);

            try {
                //put the thread to sleep to demonstrate the async execution
                Thread.sleep(10000);

                //send data to client and close connection
                outToClient.writeBytes(data);
                System.out.println("[+] Connection closed with: " + connection.getInetAddress() + ":" + connection.getPort());

                connection.close();
                System.out.println("\n[+] Waiting for connection");
            }
            catch(IOException | InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
