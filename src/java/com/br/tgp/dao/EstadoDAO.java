/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.dao;

import com.br.tgp.entidades.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author danillo.xavier
 */
public class EstadoDAO {
    
    private EntityManagerFactory factory;
    private EntityManager gerenciador;

    public EstadoDAO() {
        
    }
    
    public boolean adicionar(Estado estado) {
      
      boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.persist(estado);
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
       return sucesso;  }

    public boolean editar(Estado estado) {
     boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.merge(estado);
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
       return sucesso;  }

    public List<Estado> findAll() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Estado.findAll");
           return pesquisa.getResultList();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new ArrayList();//lista vazia se algum erro ocorrer
           
       }
       finally
       {
           
            finalizarTransacao();
       }
    }

    public List<Estado> findSimilar(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Estado.findSimilar");
                pesquisa.setParameter("nome","%"+value+"%");
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Estado>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public List<Estado> findByName(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Estador.findByName");
                pesquisa.setParameter("nome",value);
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Estado>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public Estado findSpecific(Estado estado) {
        //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Estado.findSpecific");
           pesquisa.setParameter("id",estado.getId());
           return (Estado) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Estado();//generico vazio se algum erro ocorrer
           
       }
       finally
       {
            finalizarTransacao();
       }
       
    }

    public boolean excluir(Estado estado) {
      boolean sucesso;
        try
        {
            iniciarTransacao();
            estado =gerenciador.find(Estado.class, estado.getId());
            gerenciador.remove(estado);
            sucesso=true;
        }
        catch(Exception e)
        {
            sucesso=false;
        }
        finally
        {
            finalizarTransacao();
        }
       return sucesso;
    }

    public Estado findWithId(Long id) {
         //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Estado.findWithId");
           pesquisa.setParameter("id",id);
           return (Estado) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Estado();//generico vazio se algum erro ocorrer
           
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
