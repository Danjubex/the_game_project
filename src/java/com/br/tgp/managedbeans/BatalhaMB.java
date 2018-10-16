/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.managedbeans;

import com.br.tgp.dao.AventuraDAO;
import com.br.tgp.dao.PersonagemDAO;
import com.br.tgp.dao.EstadoDAO;
import com.br.tgp.dao.BatalhaDAO;
import com.br.tgp.dao.HistoricoDAO;
import com.br.tgp.dao.Tipo_HistoricoDAO;
import com.br.tgp.entidades.Aventura;
import com.br.tgp.entidades.Batalha;
import com.br.tgp.entidades.Estado;
import com.br.tgp.entidades.Historico;
import com.br.tgp.entidades.Personagem;
import com.br.tgp.entidades.Tipo_Historico;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

/**
 *
 * @author danillo.xavier
 */
@ManagedBean(name = "controle_batalha")//conectar variaveis com layout
@SessionScoped

public class BatalhaMB  implements Serializable {
    private AventuraDAO controleAventura=new AventuraDAO();//antigo ejb
    
    private BatalhaDAO controleBatalha=new BatalhaDAO();//antigo ejb
    
    
    private EstadoDAO controleEstado=new EstadoDAO();//antigo ejb

    
    private PersonagemDAO controlePersonagem=new PersonagemDAO();//antigo ejbprivate Aventura aventura;
  
      
    private HistoricoDAO controleHistorico=new HistoricoDAO();//antigo ejb
    private Tipo_HistoricoDAO controleTipo_Historico=new Tipo_HistoricoDAO();//antigo ejb
    
    private String aventuraId;
    
    private List<Batalha> batalhas;
    
    private Aventura aventura;
    
    @ManagedProperty(value="#{login}")
    private Login login;
        
    private TimelineModel gantt; 
    private Date start;  
    private Date end;
    
    @PostConstruct
    public void iniciando()
    {
//       aventuraId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("aventuraId");
    }

    public String change()
    {
     //aventuraId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("aventuraId");
       aventura = controleAventura.findSpecific(aventura);
       batalhas = aventura.getBatalhas();
        return "gerenciar_batalha.xhtml";
    }
    
    
    public void graficoGantt()
    {
     // set initial start / end dates for the axis of the timeline  
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
        Date now = new Date();  
        start = aventura.getData_inicio();
        end = aventura.getData_finalizacao();
        
        List<Batalha> batalhas = aventura.getBatalhas();
        // groups  
        String[] NAMES = new String[] {"User 1", "User 2", "User 3", "User 4", "User 5", "User 6"};  
   
         // create timeline model  
        gantt = new TimelineModel();  
        //Batalha[] b_a = (Batalha[]) batalhas.toArray()
   
         for (String name : NAMES) {  
            now = new Date();  
            Date end = new Date(now.getTime() - 12 * 60 * 60 * 1000);  
   
            for (int i = 0; i < 5; i++) {  
                Date start = new Date(end.getTime() + Math.round(Math.random() * 5) * 60 * 60 * 1000);  
                end = new Date(start.getTime() + Math.round(4 + Math.random() * 5) * 60 * 60 * 1000);  
   
                long r = Math.round(Math.random() * 2);  
                String availability = (r == 0 ? "Unavailable" : (r == 1 ? "Available" : "Maybe"));  
   
                // create an event with content, start / end dates, editable flag, group name and custom style class  
                TimelineEvent event = new TimelineEvent(availability, start, end, true, name, availability.toLowerCase());  
                gantt.add(event);  
            }  
        } 
    }

    public void concluirBatalha(Batalha batalha)
    {
        Batalha b = controleBatalha.findSpecific(batalha);
        //mudar estado
        Estado e = controleEstado.findWithId(Long.parseLong(""+4));//Concluida
        b.setEstado(e);
        //mudar data alteração
        b.setData_ultima_alteracao(new java.util.Date());
        
        //atribuir pontos de batalha para os personagens
        List<Personagem> personagens = aventura.getPersonagens();
        Iterator i = personagens.iterator();
        while(i.hasNext())
        {
            Personagem p = (Personagem) i.next();
            p.setPontos_de_batalha(p.getPontos_de_batalha() + (int)(b.getPontos_de_balalha()/personagens.size()));
            controlePersonagem.editar(p);
        
        }
        
        controleBatalha.editar(b);
        aventura = controleAventura.findSpecific(aventura);
       batalhas = aventura.getBatalhas();
       
                   Tipo_Historico th = this.controleTipo_Historico.findByName("concluir_batalha").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
    }
    
    public void excluirBatalha(Batalha batalha)
    {
        Batalha b = controleBatalha.findSpecific(batalha);
        //mudar estado
        Estado e = controleEstado.findWithId(Long.parseLong(""+3));//Cancelada
        b.setEstado(e);
        //mudar data alteração
        b.setData_ultima_alteracao(new java.util.Date());
        
        controleBatalha.editar(b);
        aventura = controleAventura.findSpecific(aventura);
       batalhas = aventura.getBatalhas();
                   Tipo_Historico th = this.controleTipo_Historico.findByName("excluir_batalha").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
    }
    
    public String initColorControl(Date data)
    {
        Date hoje = new Date();
        if(hoje.compareTo(data) < 0 )//ainda nao aconteceu
            return "#42c8f4";//azul
        else //aconteceu
            return "#2fe02f";//verde
            
    }
    
    public String endColorControl(Date data)
    {
        Date hoje = new Date();
        if(hoje.compareTo(data) < 0 )//ainda nao aconteceu
            return "#2fe02f";//verde
        else //aconteceu
            return "#e0402f";//vermelho

            
    }
    
    public TimelineModel getGantt() {
        return gantt;
    }

    public void setGantt(TimelineModel gantt) {
        this.gantt = gantt;
    }

    public List<Batalha> getBatalhas() {
        return batalhas;
    }

    public void setBatalhas(List<Batalha> batalhas) {
        this.batalhas = batalhas;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
    
    
    public String getAventuraId() {
        return aventuraId;
    }

    public void setAventuraId(String aventuraId) {
        this.aventuraId = aventuraId;
    }

    public Aventura getAventura() {
        return aventura;
    }
    
    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
       // return "gerenciar_batalha.xhtml";
    }
    
    
}
