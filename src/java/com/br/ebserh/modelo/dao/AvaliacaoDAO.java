/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.ebserh.modelo.dao;

import com.br.ebserh.modelo.planejamento.Avaliacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Danillo
 */

public class AvaliacaoDAO{

    private EntityManagerFactory factory;
    private EntityManager gerenciador;
    
    public AvaliacaoDAO() {
        factory=Persistence.createEntityManagerFactory("indicadores");
        gerenciador=factory.createEntityManager();
    }
    
    public boolean adicionar(Avaliacao avaliacao) {
     boolean sucesso;
        try
        {
           iniciarTransacao();
           gerenciador.persist(avaliacao);
           sucesso=true;
        }
        catch(Exception e)
        {
            sucesso=false;
            e.printStackTrace();
        }
        finally
        {
            finalizarTransacao();
        }
       return sucesso;
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
