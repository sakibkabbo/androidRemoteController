/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cse461project;

import java.net.*;
import java.io.*;

/**
 *
 * @author Muhtasim
 */
public class ServoServer {

    static int sent = 0;
    static boolean on = false;

    public static void main(String[] args) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        System.out.println("Server Online");
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection accepted");

            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            sent = Integer.parseInt(clientSentence);

            if (sent == 98) {
                System.out.println(sent);
                try {
                    Runtime runTime = Runtime.getRuntime();
                    runTime.exec("gpio mode 4 out");
                    runTime.exec("gpio write 4 1");
                    Thread.sleep(2000);
                    runTime.exec("gpio write 4 0");
                    Thread.sleep(2000);
                    runTime.exec("gpio write 4 1");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (sent == 99) {
                System.out.println(sent);
                on = !on;
                try {                    
                    Runtime runTime = Runtime.getRuntime();
                    runTime.exec("gpio mode 4 out");
                    while (on) {
                        clientSentence = inFromClient.readLine();
                        int sentinloop = Integer.parseInt(clientSentence);
                        if (sentinloop == 99){
                            on = !on;
                        }
                        System.out.println("Lights On: " + on);
                        runTime.exec("gpio write 4 1");
                        Thread.sleep(2000);
                        runTime.exec("gpio write 4 0");
                        Thread.sleep(500);
                        runTime.exec("gpio write 4 1");
                    }
                    runTime.exec("gpio write 4 1");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    Runtime runTime = Runtime.getRuntime();
                    runTime.exec("gpio mode 1 pwm");
                    runTime.exec("gpio pwm-ms");
                    runTime.exec("gpio pwmc 192");
                    runTime.exec("gpio pwmr 2000");
                    //runTime.exec("gpio pwm 1 152"); // ~center
                    int j = sent;
                    for (int i = 0; i < j; i++){
                    runTime.exec("gpio pwm 1 " + i);
                }

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
