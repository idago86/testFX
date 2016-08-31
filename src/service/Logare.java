/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AdminDao;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.AdminDB;
import model.AdminFX;

/**
 *
 * @author Mihaela Tudose
 */
public class Logare {
    
    private EntityManagerFactory emf;
    public static ObservableList<AdminFX> model = FXCollections.observableArrayList();
    private Logare(){
        emf = Persistence.createEntityManagerFactory("testfx2PU");
    }
    
    private static final class SingletonHolder{
        private static final Logare SINGLETON = new Logare();
}
    
    public static Logare getInstance(){
        return SingletonHolder.SINGLETON;
    }
    
    public boolean registerAdmin(String user, String parola){
        EntityManager em = emf.createEntityManager();
        try{
            AdminDao adminDao = new AdminDao(em); 
            em.getTransaction().begin();
            AdminDB adm = new AdminDB();
            adm.setUser(user);
            adm.setParola(parola);
            adminDao.adaugaAdmin(adm);
            em.getTransaction().commit();
            matchPersoaneToModel(); 
            return true;
        }catch(Exception e){
            return false;  
        }finally{
            if(em!=null)em.close();
        }        
    }
    
    public AdminDB login(String user, String parola){
     EntityManager em = emf.createEntityManager();
        try{
            AdminDao adminDao = new AdminDao(em); 
            AdminDB result = adminDao.findAdminByUser(user); 
            if(result.getUser().equals(user)){
                if(result.getParola().equals(parola)){
                 return result;
                }
            }
            //return true;
        }catch(Exception e){
            return null;  
        }finally{
            if(em!=null)em.close();
        } 
        return null;
    }
    
    
    
    public void matchPersoaneToModel(){        
        EntityManager em = this.emf.createEntityManager();
        try{
            AdminDao admDao = new AdminDao(em);
            List<AdminDB> allAdmini = admDao.getAllAdmini();     
            model.clear();
            for(AdminDB ad : allAdmini){
                AdminFX afx = new AdminFX(ad.getId(), ad.getUser(), ad.getParola());
                model.add(afx);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em!=null)em.close();
        } 
    }
    
    
    
    public List<AdminDB> getAllAdmini(){
        EntityManager em = emf.createEntityManager();
        try{
            AdminDao adminDao = new AdminDao(em); 
            List<AdminDB> result = new ArrayList<>();
             result = adminDao.getAllAdmini(); 
            if(result.isEmpty()){
                return null; 
            }else return result; 
            //return true;
        }catch(Exception e){
            return null;  
        }finally{
            if(em!=null)em.close();
        } 
       
    }
    
}
