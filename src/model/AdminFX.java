/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Mihaela Tudose
 */
public class AdminFX {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty user;
    private SimpleStringProperty parola;

    public AdminFX() {
    }

    public AdminFX(int id, String user, String parola) {
        this.id = new SimpleIntegerProperty(id);
        this.user = new SimpleStringProperty(user);
        this.parola = new SimpleStringProperty(parola);
    }
    

    public Integer getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getParola() {
        return parola.get();
    }

    public void setParola(String parola) {
        this.parola.set(parola);
    }

    @Override
    public String toString() {
        return user.get() ;
    }
    
    
    
}
