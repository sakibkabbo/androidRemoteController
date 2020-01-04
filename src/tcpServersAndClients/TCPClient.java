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

class TCPClient {

    static String sentence;
    String fromServer;

    public static void main(String argv[]) throws Exception {
        TCPClient client = new TCPClient();
        String sentence;
        String modifiedSentence;
        InetAddress ipAddress = null;
        BufferedReader inFromUser
                = new BufferedReader(new InputStreamReader(System.in));
        try {
            ipAddress = InetAddress.getLocalHost();
            System.out.println(ipAddress);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }
        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer
                = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer
                = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = "vgvh";

        outToServer.writeBytes(sentence + '\n');

        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);
        client.setName(modifiedSentence);
        clientSocket.close();

    }

    public void setName(String s) {
        fromServer = s;
        System.out.println(fromServer);
    }
}
