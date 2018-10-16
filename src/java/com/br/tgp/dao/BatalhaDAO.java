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
public class BatalhaDAO{


    private EntityManagerFactory factory;
    private EntityManager gerenciador;

    public BatalhaDAO() {
        
    }
    
    public boolean adicionar(Batalha batalha) {
      
      boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.persist(batalha);
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

    public boolean editar(Batalha batalha) {
     boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.merge(batalha);
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

    public List<Batalha> findAll() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Batalha.findAll");
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

       public List<Batalha> findActiveds() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Batalha.findActiveds");
           if(!pesquisa.getResultList().isEmpty())
               return pesquisa.getResultList();
           else
            return new ArrayList();
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
    public List<Batalha> findSimilar(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Batalha.findSimilar");
                pesquisa.setParameter("nome","%"+value+"%");
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Batalha>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public List<Batalha> findByName(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Batalha.findByName");
                pesquisa.setParameter("nome",value);
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Batalha>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public Batalha findSpecific(Batalha batalha) {
        //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Batalha.findSpecific");
           pesquisa.setParameter("id",batalha.getId());
           return (Batalha) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Batalha();//generico vazio se algum erro ocorrer
           
       }
       finally
       {
            finalizarTransacao();
       }
       
    }

    public boolean excluir(Batalha batalha) {
      boolean sucesso;
        try
        {
            iniciarTransacao();
            batalha =gerenciador.find(Batalha.class, batalha.getId());
            gerenciador.remove(batalha);
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

    public Batalha findWithId(Long id) {
         //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Batalha.findSpecific");
           pesquisa.setParameter("id",id);
           return (Batalha) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Batalha();//generico vazio se algum erro ocorrer
           
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
