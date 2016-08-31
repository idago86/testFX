/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.controllers.LoginFrameController;
import main.controllers.MessaginFrameController;
import model.AdminDB;
import server.IsoServer;

/**
 *
 * @author Mihaela Tudose
 */
public class MainMessFX extends Application {
    
    private Stage window;
    private Scene scene;
    private AnchorPane root;
    public static LoginFrameController lfc;
    public static MessaginFrameController mfc;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        showLoginFrame(primaryStage);
        lfc.setMainMessFX(this);
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
            launch(args);
           
        
    }
    
    public AdminDB showLoginFrame(Stage loginStage) throws IOException{
        this.window=loginStage; 
        FXMLLoader fxml = new FXMLLoader();
        fxml.setLocation(this.getClass().getResource("views/LoginFrame.fxml"));
        root = fxml.load();
        lfc= fxml.getController();
        scene = new Scene(root);
        window.setTitle("Welcome To Israel Messaging App");
        window.setScene(scene);
        window.show();
        return lfc.getAdminDB();
    }
    
    public static void showMessagingFrame(ActionEvent ev) throws IOException{
        Stage window= new Stage(); 
        FXMLLoader fxml = new FXMLLoader();
        fxml.setLocation(MainMessFX.class.getResource("views/MessaginFrame.fxml"));
        AnchorPane root = fxml.load();
        MessaginFrameController mfc = fxml.getController();        
        Scene scene = new Scene(root);        
        window.setTitle("Welcome To Israel Messaging App");
        window.setScene(scene);
        window.show();
        
    }
    
    
    
    
    
    
    
}
