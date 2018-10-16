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
 * @author Danillo
 */
public class ArmaDAO{


    private EntityManagerFactory factory;
    private EntityManager gerenciador;

    public ArmaDAO() {
        
    }
    
    public boolean adicionar(Arma arma) {
      
      boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.persist(arma);
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

    public boolean editar(Arma arma) {
     boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.merge(arma);
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

    public List<Arma> findAll() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Arma.findAll");
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

    public List<Arma> findSimilar(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Arma.findSimilar");
                pesquisa.setParameter("nome","%"+value+"%");
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Arma>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public List<Arma> findByName(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Armar.findByName");
                pesquisa.setParameter("nome",value);
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Arma>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public Arma findSpecific(Arma arma) {
        //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Arma.findSpecific");
           pesquisa.setParameter("id",arma.getId());
           return (Arma) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Arma();//generico vazio se algum erro ocorrer
           
       }
       finally
       {
            finalizarTransacao();
       }
       
    }

    public boolean excluir(Arma arma) {
      boolean sucesso;
        try
        {
            iniciarTransacao();
            arma =gerenciador.find(Arma.class, arma.getId());
            gerenciador.remove(arma);
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

    public Arma findWithId(Long id) {
         //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Arma.findSpecific");
           pesquisa.setParameter("id",id);
           return (Arma) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Arma();//generico vazio se algum erro ocorrer
           
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
