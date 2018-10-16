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
public class AventuraDAO{


    private EntityManagerFactory factory;
    private EntityManager gerenciador;

    public AventuraDAO() {
        
    }
    
    public boolean adicionar(Aventura aventura) {
      
      boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.persist(aventura);
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

    public boolean editar(Aventura aventura) {
     boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.merge(aventura);
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

    public List<Aventura> findAll() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Aventura.findAll");
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

    public List<Aventura> findByPersonagem(Personagem p) {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Aventura.findByPersonagem");
           pesquisa.setParameter("id",p.getId());
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
    
       public List<Aventura> findConcluidas() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           String n;
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Aventura.findConcluidas");
           pesquisa.setMaxResults(50);
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
       
             public List<Aventura> findActiveds() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Aventura.findActiveds");
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
    public List<Aventura> findSimilar(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Aventura.findSimilar");
                pesquisa.setParameter("nome","%"+value+"%");
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Aventura>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public List<Aventura> findByName(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Aventurar.findByName");
                pesquisa.setParameter("nome",value);
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Aventura>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public Aventura findSpecific(Aventura aventura) {
        //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Aventura.findSpecific");
           pesquisa.setParameter("id",aventura.getId());
           return (Aventura) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Aventura();//generico vazio se algum erro ocorrer
           
       }
       finally
       {
            finalizarTransacao();
       }
       
    }

    public boolean excluir(Aventura aventura) {
      boolean sucesso;
        try
        {
            iniciarTransacao();
            aventura =gerenciador.find(Aventura.class, aventura.getId());
            gerenciador.remove(aventura);
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

    public Aventura findWithId(Long id) {
         //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Aventura.findSpecific");
           pesquisa.setParameter("id",id);
           return (Aventura) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Aventura();//generico vazio se algum erro ocorrer
           
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
