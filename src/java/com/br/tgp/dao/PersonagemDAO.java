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
public class PersonagemDAO{


    private EntityManagerFactory factory;
    private EntityManager gerenciador;

    public PersonagemDAO() {
        
    }
    
    public boolean adicionar(Personagem personagem) {
      
      boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.persist(personagem);
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

    public boolean editar(Personagem personagem) {
     boolean sucesso;
        try
        {
            iniciarTransacao();
            gerenciador.merge(personagem);
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

    public List<Personagem> findAll() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Personagem.findAll");
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

       public List<Personagem> findActiveds() {
      //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Personagem.findActiveds");
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
    public List<Personagem> findSimilar(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Personagem.findSimilar");
                pesquisa.setParameter("nome","%"+value+"%");
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Personagem>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public List<Personagem> findByName(String value) {
     try
            {
                iniciarTransacao();
                Query pesquisa=gerenciador.createNamedQuery("Personagem.findByName");
                pesquisa.setParameter("nome",value);
                return pesquisa.getResultList();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new ArrayList<Personagem>();//generico vazio se algum erro ocorrer

            }
            finally
            {
                finalizarTransacao();
            }
    }

    public Personagem findSpecific(Personagem personagem) {
        //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Personagem.findSpecific");
           pesquisa.setParameter("id",personagem.getId());
           return (Personagem) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Personagem();//generico vazio se algum erro ocorrer
           
       }
       finally
       {
            finalizarTransacao();
       }
       
    }

    public boolean excluir(Personagem personagem) {
      boolean sucesso;
        try
        {
            iniciarTransacao();
            personagem =gerenciador.find(Personagem.class, personagem.getId());
            gerenciador.remove(personagem);
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

    public Personagem findWithId(Long id) {
         //Query pesquisa=gerenciador.createQuery("SELECT o FROM Obra o left join fetch o.autores");
       try
       {
           iniciarTransacao();
           Query pesquisa=gerenciador.createNamedQuery("Personagem.findSpecific");
           pesquisa.setParameter("id",id);
           return (Personagem) pesquisa.getSingleResult();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return new Personagem();//generico vazio se algum erro ocorrer
           
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
