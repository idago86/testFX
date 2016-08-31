/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihaela Tudose
 */
public class IsoServer {
    private static ServerSocket ss;
    private static List<IsoServerThreadService> users = new ArrayList<>();

   
    public static void main(String []args) throws IOException{
        ss = new ServerSocket(31286);         
        while(true){
            Socket socket = ss.accept();
            System.out.println("Server Running");
            IsoServerThreadService st = new IsoServerThreadService(socket);
            users.add(st);
            st.start();  
            System.out.println("s a connectat "+ socket.getRemoteSocketAddress());
        }   
    }
            
     public static void sendMessageToAllUsers(String message){        
            users.forEach(c -> c.sendMessageToUser(message)); 
     } 
    
    
    
}
