/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cse461project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Muhtasim
 */
public class motorServer {

    static int sent = 0;

    public static void main(String[] args) throws Exception {
        String clientSentence;
        System.out.println("Server Online");
        ServerSocket welcomeSocket = new ServerSocket(6791);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection accepted");

            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            if (clientSentence.equals("F")) {
                try {
                    System.out.println("going forward");
                    Runtime runTime = Runtime.getRuntime();
                     runTime.exec("gpio mode 5 out");
                     runTime.exec("gpio mode 13 out");
                     runTime.exec("gpio write 5 0");
                     runTime.exec("gpio write 13 0");
                     Thread.sleep(1000);
                     runTime.exec("gpio write 5 1");
                     runTime.exec("gpio write 13 1");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (clientSentence.equals("B")) {
                try {
                    System.out.println("going back");
                    Runtime runTime = Runtime.getRuntime();
                     runTime.exec("gpio mode 6 out");
                     runTime.exec("gpio mode 19 out");
                     runTime.exec("gpio write 6 0");
                     runTime.exec("gpio write 19 0");
                     Thread.sleep(1000);
                     runTime.exec("gpio write 6 1");
                     runTime.exec("gpio write 19 1");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (clientSentence.equals("L")) {
                try {
                    System.out.println("going left");
                    Runtime runTime = Runtime.getRuntime();
                     runTime.exec("gpio mode 5 out");
                     runTime.exec("gpio mode 13 out");
                     runTime.exec("gpio write 13 0");
                     Thread.sleep(200);
                     runTime.exec("gpio write 5 0");
                     Thread.sleep(1000);
                     runTime.exec("gpio write 13 1");
                     runTime.exec("gpio write 5 1");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (clientSentence.equals("R")) {
                try {
                    System.out.println("going right");
                    Runtime runTime = Runtime.getRuntime();
                     runTime.exec("gpio mode 5 out");
                     runTime.exec("gpio mode 13 out");
                     runTime.exec("gpio write 5 0");
                     Thread.sleep(200);
                     runTime.exec("gpio write 13 0");
                     Thread.sleep(1000);
                     runTime.exec("gpio write 5 1");
                     runTime.exec("gpio write 13 1");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
