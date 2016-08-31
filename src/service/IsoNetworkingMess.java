/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Mihaela Tudose
 */
public class IsoNetworkingMess {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
       
    
    private IsoNetworkingMess(){
        try {
            socket = new Socket("localhost", 31286);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static final class SingletonHolder{
        private static final IsoNetworkingMess SINGLETON = new IsoNetworkingMess();
    }
    
    public static IsoNetworkingMess getInstance(){
        return SingletonHolder.SINGLETON;
    }
    
    public String receiveMessage() throws IOException{
        return in.readLine(); 
    }
    
    public void sendMessage(String message){
        out.println(message);
    }
}
