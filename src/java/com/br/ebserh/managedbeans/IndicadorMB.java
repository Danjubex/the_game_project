/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.ebserh.managedbeans;

import com.br.ebserh.modelo.dao.GenericDAO;
import com.br.ebserh.modelo.dao.IndicadorDAO;
import com.br.ebserh.modelo.planejamento.Avaliacao;
import com.br.ebserh.modelo.planejamento.Indicador;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Danillo
 */

@ManagedBean(name = "controle_indicador")//conectar variaveis com layout
@RequestScoped
public class IndicadorMB {
    
    
    private IndicadorDAO controleIndicador;//antigo ejb
    //private GenericDAO<Indicador> controleIndicador;//antigo ejb
    
    List<Indicador> cache;
    String nome = "THIAGO";
    private Indicador indicador; 
    private Indicador indicador_editar;
    private Long id_indicador;
    
    @PostConstruct
    public void iniciando()
    {
        controleIndicador=new IndicadorDAO();
        indicador=new Indicador();
        //cache=controleIndicador.findAll();
    }
    
    public void cadastrar()
    {
        boolean resultado;
        resultado=controleIndicador.adicionar(indicador);
        if(resultado==true)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","O indicador foi cadastrado com sucesso!"));
            limpar();
        }
         else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));
    
    }
    
     public void excluir(Indicador indicador)
    {
        
        boolean resultado;
        resultado=controleIndicador.excluir(indicador);
        if(resultado==true)
        {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","O indicador foi excluído com sucesso!"));
                this.cache=this.controleIndicador.findAll();
        }
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","O indicador não pôde ser excluído!"));
   
        
        
    }
 
     public String setId_indicador(Long id_indicador) {
        this.id_indicador = id_indicador;//serve pra nada essa linha
        this.indicador_editar=this.controleIndicador.findWithId(id_indicador);
        
        return "/editar_indicador";
    }
     
    public void limpar()
    {
        indicador=new Indicador();
    }
    //GETTERS AND SETTERS
    public Indicador getIndicador() {
        return indicador;
    }

    public String getNome()
    {return nome;}
    
    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public List<Indicador> getCache() {
        return cache;
    }

    public void setCache(List<Indicador> cache) {
        this.cache = cache;
    }

    public Long getId_indicador() {
        return id_indicador;
    }

    public Indicador getIndicador_editar() {
        return indicador_editar;
    }

    public void setIndicador_editar(Indicador indicador_editar) {
        this.indicador_editar = indicador_editar;
    }

    
    
}
