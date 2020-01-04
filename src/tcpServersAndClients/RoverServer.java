/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse461project;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tanmaster
 */
public class RoverServer {
    private int port,count;
    private ArrayList<RoverClient> st;
    
    public RoverServer(int port)
    {
        this.port = port;
        System.out.println(port);
        st = new ArrayList<RoverClient>();
        count = 0;
    }
    
    public void start()
    {
        //SerialFromArduino ser = new SerialFromArduino();
        //ser.initialize();
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true)
            {
                System.out.println("Server waiting for client " + count + " on port " + port);
                Socket socket = serverSocket.accept();
                count++;
                RoverClient t = new RoverClient(socket, count);
                st.add(t);
                
                t.start();
                
                while(true)
                {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("humidity" + "87" + ",latitude" + RoverData.latitude + "98");
                    System.out.println(RoverData.humidity + " " + RoverData.latitude);
                    writer.flush();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RoverServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        int port = 8093;
        
//        if(args.length != 1)
//        {
//            System.out.println("Usage: java -jar example.jar [port]");
//            return;
//        }
        //port = Integer.parseInt(args[0]);
        RoverServer iutserver = new RoverServer(port);
        iutserver.start();
        
    }
    
    class RoverClient extends Thread
    {
        Socket socket;
        private RoverClient(Socket socket, int count) {
            this.socket = socket;
            System.out.println("Server instance " + count + " is started.");
        }
        
    }
}
