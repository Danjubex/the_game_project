/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.dao;

import com.br.tgp.entidades.Personagem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author danillo.xavier
 */
public class LoginDAO {
    
        private EntityManagerFactory factory;
    private EntityManager gerenciador;

    public LoginDAO() {
        
    }
    
    public Personagem validate(String user, String password) {
		
        try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Personagem.logar");
           pesquisa.setParameter("user",user);
           pesquisa.setParameter("pass",password);
           Personagem personagem = (Personagem) pesquisa.getSingleResult();
           if(personagem!=null)
           {
               return personagem;
           }
           else
               return null;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;//generico vazio se algum erro ocorrer
           
       }
       finally
       {
            finalizarTransacao();
       }
    }
    
     
      
        public void iniciarTransacao()
    {
         factory=Persistence.createEntityManagerFactory("tgp");
         gerenciador=factory.createEntityManager();
         gerenciador.getTransaction().begin();
    }
    
    public void finalizarTransacao()
    {
        
           gerenciador.getTransaction().commit();
           gerenciador.close();
           factory.close();
    }
}
