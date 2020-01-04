/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cse461project;


import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhtasim
 */
public class onfiveSec {

    public static void main(String[] args) throws Exception {
        try {
            Runtime runTime = Runtime.getRuntime();
           while(true){    
            runTime.exec("gpio mode 5 out");
                runTime.exec("gpio write 5 0");
                Thread.sleep(3000);
                runTime.exec("gpio write 5 1");
                 Thread.sleep(1000);
           }      
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
