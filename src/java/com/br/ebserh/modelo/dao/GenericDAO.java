/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.ebserh.modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Danillo
 */
public class GenericDAO<E> {

    EntityManagerFactory factory;
    EntityManager gerenciador;
    
    public void adiciona(E objeto)
    {
        try
        {
            iniciarTransacao();
            gerenciador.persist(objeto);
        }
        catch(Exception e)
        {
            
        }
        finally
        {
            finalizarTransacao();
        }
    }
    
    public void iniciarTransacao()
    {
         factory=Persistence.createEntityManagerFactory("indicadores");
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
