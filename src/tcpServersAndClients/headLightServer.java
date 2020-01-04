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
public class headLightServer {

    static int sent = 0;
    static boolean on = false;
    static int first = 0;
    static Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("2 Lights On: " + on);
                //Runtime runTime = Runtime.getRuntime();
                //runTime.exec("gpio mode 4 out");
                /*runTime.exec("gpio write 4 1");
                 Thread.sleep(2000);
                 runTime.exec("gpio write 4 0");
                 Thread.sleep(500);
                 runTime.exec("gpio write 4 1");*/

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("4 Lights On: " + on);
                //Runtime runTime = Runtime.getRuntime();
                //runTime.exec("gpio mode 4 out");
                //runTime.exec("gpio write 4 1");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) throws Exception {
        String clientSentence;
        System.out.println("Server Online");
        ServerSocket welcomeSocket = new ServerSocket(6790);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection accepted");

            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            sent = Integer.parseInt(clientSentence);
            if (sent == 99) {
                try {
                    System.out.println("in");
                    /*Runtime runTime = Runtime.getRuntime();
                    runTime.exec("gpio mode 17 out");
                    runTime.exec("gpio write 17 1");
                    Thread.sleep(2000);
                    runTime.exec("gpio write 17 0");
                    Thread.sleep(500);
                    runTime.exec("gpio write 17 1");*/
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
