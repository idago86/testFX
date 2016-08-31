/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.MainMessFX;
import model.AdminFX;
import service.IsoNetworkingMess;
import service.Logare;

/**
 * FXML Controller class
 *
 * @author Mihaela Tudose
 */
public class MessaginFrameController implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private Label labelApplicatiei;
    @FXML
    private ListView<AdminFX> listView;
    @FXML
    private Label nrUsersLbl;
    @FXML
    private TextArea textAreaFld;
    @FXML
    private Button sendMessageBtn;
    @FXML
    private TextField textMessageFdl;
    
    
    private MainMessFX mainMessFX;

    public void setMainMessFX(MainMessFX mainMessFX) {
        this.mainMessFX = mainMessFX;
    }

    public MessaginFrameController() {
        
        new DisplayThread().start();
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        Logare.getInstance().matchPersoaneToModel();
        listView.setItems(Logare.getInstance().model);
       // textMessageFdl.setEditable(false);
      //  sendMessageBtn.setVisible(false);
        
    }    
    
    @FXML
    private AdminFX onListAction(){        
            return listView.getSelectionModel().getSelectedItem(); 
  }
    
    @FXML
    private void onSendMessageBtnClick(ActionEvent ev){
       textMessageFdl.setText(null);
//        while (true) {               
//            //AdminFX adm = onListAction();
//            String message = textMessageFdl.getText();
//            
//                IsoNetworkingMess.getInstance().sendMessage(
//                        //adm.getUser()+ 
//                        " : "+message);
//                textMessageFdl.setText(null);
//         
//                    
//        }
    }
    
    private class DisplayThread extends Thread{
    
        @Override
        public void run(){
            try {
                while (true) {                    
                    String message = IsoNetworkingMess.getInstance().receiveMessage();
                    textAreaFld.appendText(message+"\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
}
