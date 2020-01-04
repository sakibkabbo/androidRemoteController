/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse461project;

import java.io.*;
import java.net.*;

/**
 *
 * @author Muhtasim
 */
public class TCPClient2 {

   static String fromServer;
   static String sentence;

    public static void main(String[]args) throws Exception {
        String sentence;
        String modifiedSentence;
        InetAddress ipAddress = null;
        BufferedReader inFromUser
                = new BufferedReader(new InputStreamReader(System.in));
        try {
            ipAddress = InetAddress.getByName("58.97.149.175");
            System.out.println(ipAddress);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }
        Socket clientSocket = new Socket(ipAddress, 6789);

        DataOutputStream outToServer
                = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer
                = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();

        outToServer.writeBytes(sentence + '\n');

        modifiedSentence = inFromServer.readLine();
        fromServer = modifiedSentence;
        System.out.println("FROM SERVER: " + modifiedSentence);
        System.out.println(fromServer);

    }

    public void setName(String s) {
        fromServer = s;
    }
}
