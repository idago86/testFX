/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.AdminDB;

/**
 *
 * @author Mihaela Tudose
 */
public class AdminDao {
    
    private EntityManager em;
    public AdminDao(EntityManager em){
    this.em= em; 
    }
    
    public void adaugaAdmin(AdminDB admin){
        em.persist(admin);
    }
    
    public AdminDB findAdminByUser(String user){
        TypedQuery q = em.createNamedQuery("AdminDB.findByUser", AdminDB.class);
        q.setParameter("user", user); 
        List<AdminDB> result = q.getResultList();
        return result.isEmpty()?null:result.get(0);
    }
    
    public List<AdminDB> getAllAdmini(){
        TypedQuery q = em.createNamedQuery("AdminDB.findAll", AdminDB.class);        
        List<AdminDB> result = q.getResultList();
        return result;
    }
    
}
