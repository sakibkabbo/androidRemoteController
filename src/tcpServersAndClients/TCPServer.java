/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pakhi
 */
import java.io.*;
import java.net.*;

class TCPServer {

    private static final int STEERING_CENTER = 1400;
    private static final int STEERING_RIGHT_MAX = 1130;
    private static final int STEERING_LEFT_MAX = 1670;
    private static final int GPIO_PIN = 23;

    public static void main(String argv[]) throws Exception {
        
        String clientSentence;
        String capitalizedSentence;
        System.out.println("Server Online");
        ServerSocket welcomeSocket = new ServerSocket(6789);
while(true){
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connection accepted");
       
        BufferedReader inFromClient
                = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        clientSentence = inFromClient.readLine();
        System.out.println(clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
        System.out.println(capitalizedSentence);
        outToClient.writeBytes(capitalizedSentence);
}        
        //connectionSocket.close();
        //welcomeSocket.close();

    }

    static void write(PrintWriter output, String message) {
        System.out.println("Sending: " + message);
        output.println(message + '\n');
    }
}
