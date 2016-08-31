/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 *
 * @author Mihaela Tudose
 */
public class IsoServerThreadService extends Thread{
    private Socket mysocket;
    private PrintWriter out;
    private BufferedReader in; 

    public IsoServerThreadService(Socket socket) throws IOException {
        this.mysocket=socket;
        this.out = new PrintWriter(socket.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    
    public void sendMessageToUser(String message){
        out.println(message); 
    }
    
     public String receiveMessageFromClient() throws IOException{
       return in.readLine();  
    }
    
    @Override
     public void run(){
         try {
            while(true){
                if(receiveMessageFromClient()!=null){
                    String message = receiveMessageFromClient();
                    IsoServer.sendMessageToAllUsers(message);                  
                }          
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     
     }
}
