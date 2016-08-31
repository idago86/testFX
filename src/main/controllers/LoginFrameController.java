/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.MainMessFX;
import model.AdminDB;
import service.Logare;

/**
 * FXML Controller class
 *
 * @author Mihaela Tudose
 */
public class LoginFrameController implements Initializable {   
    
    @FXML
    private AnchorPane root;    
    @FXML
    private TextField usernameFLD;
    @FXML
    private TextField parolaFLD;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;    
    private MainMessFX mainMessFX;
    private AdminDB adminDB;

    public void setMainMessFX(MainMessFX mainMessFX) {
        this.mainMessFX = mainMessFX;
    }

    public AdminDB getAdminDB() {
        return adminDB;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onRegisterClickBtn(ActionEvent ev){
        String username = usernameFLD.getText();
        String parola = parolaFLD.getText();
        if(!username.isEmpty() && !parola.isEmpty()){
            boolean rez = Logare.getInstance().registerAdmin(username, parola);
            if(rez){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Adaugare Realizata");
                alert.setContentText("Welcome "+username+ " to the best messaging system ever :)");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Eroare");
                alert.setContentText("Nu s-a putut realizat adaugarea");
                alert.showAndWait();           
            }
        }else alertCampuriileNok();       
    }

    private void alertCampuriileNok() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Attentie");
        alert.setContentText("Completati toate campurille inainte");
        alert.showAndWait();
    }
        
    @FXML
    private void onLoginClickBtn(ActionEvent ev){
        String username = usernameFLD.getText();
        String parola = parolaFLD.getText();
        if(!username.isEmpty() && !parola.isEmpty()){
            AdminDB rez = Logare.getInstance().login(username, parola);
           // adminDB = rez;
            if(rez!=null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Login Reusit");
                alert.setContentText("Welcome back "+username+ " to the best messaging system ever :)");
                alert.showAndWait(); 
                try {
                    mainMessFX.showMessagingFrame(ev);
                } catch (IOException ex) {
                    Logger.getLogger(LoginFrameController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //hiding the LoginFrame 
               ((Node) (ev.getSource())).getScene().getWindow().hide();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Eroare");
                alert.setContentText("Login Esuat...Reincercati");
                alert.showAndWait();           
            }
        }else alertCampuriileNok();         
    }
    
    
    
    
    
    
    
    
    
    
}
