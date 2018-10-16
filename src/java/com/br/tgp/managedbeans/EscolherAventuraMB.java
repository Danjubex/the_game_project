/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.managedbeans;

import com.br.tgp.dao.AventuraDAO;
import com.br.tgp.dao.EstadoDAO;
import com.br.tgp.dao.HistoricoDAO;
import com.br.tgp.dao.Tipo_HistoricoDAO;
import com.br.tgp.entidades.Batalha;
import com.br.tgp.entidades.Aventura;
import com.br.tgp.entidades.Estado;
import com.br.tgp.entidades.Historico;
import com.br.tgp.entidades.Personagem;
import com.br.tgp.entidades.Tipo_Historico;
import com.br.tgp.modelos.LogicasAventura;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.timeline.TimelineModel;
/**
 *
 * @author danillo.xavier
 */
@ManagedBean(name = "controle_busca_aventura")//conectar variaveis com layout
@ViewScoped

public class EscolherAventuraMB  implements Serializable {
    @ManagedProperty(value="#{login}")
    private Login login;
    
    private Batalha batalha;
    
    private TimelineModel gantt; 
    private Date start;  
    private Date end;
    
    private Aventura currentAventura;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private AventuraDAO controleAventura=new AventuraDAO();//antigo ejb
    
    private EstadoDAO controleEstado=new EstadoDAO();//antigo ejb
    
         private HistoricoDAO controleHistorico=new HistoricoDAO();//antigo ejb
         private Tipo_HistoricoDAO controleTipo_Historico=new Tipo_HistoricoDAO();//antigo ejb
         
    List<Aventura> aventuras;
    List<Aventura> aventuras_concluidas;
    LogicasAventura logicas_aventura = new LogicasAventura();
    
    
     @PostConstruct
    public void iniciando()
    {
        aventuras = controleAventura.findActiveds();
        aventuras_concluidas = controleAventura.findConcluidas();
                batalha = new Batalha();
        //currentAventura = new Aventura();
        //currentAventura = aventuras.get(0);
    }
    
    public String getAventureiros(Aventura a)
    {
        List<Personagem> personagens = a.getPersonagens();
        Iterator i = personagens.iterator();
        String retorno ="";
        while(i.hasNext())
        {
            Personagem p = (Personagem) i.next();
            retorno += "["+p.getApelido() + "]";
        }
        return retorno;
    }
    public String daysLeft(Aventura a)
    {
        return (""+TimeUnit.DAYS.convert(a.getData_finalizacao().getTime() - (new Date()).getTime(), TimeUnit.MILLISECONDS));
        
    }
    
    public String daysSinceStart(Aventura a)
    {
        return (""+TimeUnit.DAYS.convert((new Date()).getTime() - a.getData_inicio().getTime() , TimeUnit.MILLISECONDS));
        
    }
    
    public String gerenciarBatalha(Aventura aventura)
    {
        currentAventura = aventura;
        
        currentAventura = controleAventura.findSpecific(aventura);
                return "gerenciar_batalha.xhtml";
    }
    
    public boolean canComplete()//se pode concluir aventura
    {
        Personagem p = login.getPersonagem();
        if(!p.getPerfil().getNome().equals("Aprendiz"))
            return true;
        return false;
    }
    public void joinAventura(Aventura a)
    {
       // List<Personagem> personagens = a.getPersonagens();
       // if(personagens == null)
         //   personagens = new ArrayList<Personagem>();
         Personagem p = login.getPersonagem();
         if(p.getPontos_de_vida() > 0)//personagem não morto
         {
                a.getSetPersonagens().add(p);
                Estado e = controleEstado.findWithId(Long.parseLong(""+6));//INCOMPLETA
                a.setEstado(e);

                a.setData_ultima_alteracao(new java.util.Date());

                boolean resultado = controleAventura.editar(a);
                if(resultado==true)
                {
                    //entrar
            Tipo_Historico th = this.controleTipo_Historico.findByName("join_aventura").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","Você ingressou nesta aventura"));
                }
                 else
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));

         }else//personagem morto
         {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seu personagem morreu","Peça para o Mestre da Aventura pontos de cura"));
         }
        
    }

    public void leftAventura(Aventura a)
    {
        //List<Personagem> personagens = a.getPersonagens();
       // if(personagens == null)
         //   personagens = new ArrayList<Personagem>();
        Personagem p = login.getPersonagem();
        a.getSetPersonagens().remove(p);
        //a.setPersonagens(personagens);
        a.setData_ultima_alteracao(new java.util.Date());
        controleAventura.editar(a);
        
            Tipo_Historico th = this.controleTipo_Historico.findByName("left_aventura").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
    }
     public void freezeAventura(Aventura a)
    {
        //List<Personagem> personagens = a.getPersonagens();
       // if(personagens == null)
         //   personagens = new ArrayList<Personagem>();
        //a.setPersonagens(personagens);
        Estado e = controleEstado.findWithId(Long.parseLong(""+2));//CONGELADO
        a.setEstado(e);
        a.setData_ultima_alteracao(new java.util.Date());
        controleAventura.editar(a);
                
            Tipo_Historico th = this.controleTipo_Historico.findByName("congelar_aventura").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
    }
     
     public void cancelAventura(Aventura a)
    {
        //List<Personagem> personagens = a.getPersonagens();
       // if(personagens == null)
         //   personagens = new ArrayList<Personagem>();
        //a.setPersonagens(personagens);
        Estado e = controleEstado.findWithId(Long.parseLong(""+3));//CANCELADO
        a.setEstado(e);
        a.setData_ultima_alteracao(new java.util.Date());
        controleAventura.editar(a);
        
                
            Tipo_Historico th = this.controleTipo_Historico.findByName("cancelar_aventura").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
    }
     
     public void concluirAventura(Aventura aa)
    {
        Aventura a = controleAventura.findSpecific(aa);
        List<Personagem> personagens = a.getPersonagens();
        Iterator i = personagens.iterator();
        if(a.getEstado().getNome().equals("INCOMPLETA"))//SE A AVENTURA AINDA NAO FOI FINALIZADA
        {
        //atualizar data de alteraççao
        a.setData_ultima_alteracao(new java.util.Date());
        //mudar estado para concluido
        Date hoje = new Date();
        if(a.getData_finalizacao().getTime() - hoje.getTime() >= 0 )//concluído antes do prazo
        {
            Estado e = controleEstado.findWithId(Long.parseLong(""+4));//CONCLUÍDA
            a.setEstado(e);
            //somar pontos de vida entre participantes
            while(i.hasNext())
            {
                Personagem p = (Personagem) i.next();
                int pv_somar = logicas_aventura.calculo_PV_final_aventura(a.getData_finalizacao());
                p.setPontos_de_vida((int) (p.getPontos_de_vida() + pv_somar / personagens.size()));//dividindo pv entre os integrantes
                //incrementar classe de armadura
                //cresce 10% da aventura concluída dividida entre os participantes
                p.setClasse_de_armadura((int) (p.getClasse_de_armadura() + (0.10*a.getXp())/personagens.size()));
            }
        }
        else if(a.getData_criacao().getTime() - a.getData_finalizacao().getTime() >= 0 )//aventura retroativa
        {
        
            Estado e = controleEstado.findWithId(Long.parseLong(""+4));//CONCLUÍDA
            a.setEstado(e);
            //somar pontos de vida entre participantes
            while(i.hasNext())
            {
                Personagem p = (Personagem) i.next();
                    
                //Não somar pv

                //incrementar classe de armadura
                //cresce 10% da aventura concluída dividida entre os participantes
                p.setClasse_de_armadura((int) (p.getClasse_de_armadura() + (0.10*a.getXp())/personagens.size()));
            }
        }
        else//concluído com atrazo
        {
            Estado e = controleEstado.findWithId(Long.parseLong(""+5));//FINALIZADA
            a.setEstado(e);
        
        }
        //distribuir xp entre participantes
        i = personagens.iterator();
        int xp_aventura = a.getXp()/personagens.size();//dividir xp entre personagens
        while(i.hasNext())
        {
            Personagem p = (Personagem) i.next();
            int maximo =  logicas_aventura.calcular_xp_maximo(p.getNivel());
            if(xp_aventura + p.getXp() > maximo)//se o xp ganho for maior do que o xp maximo do nível
            {
                p.setXp(maximo);
                //calcular nivel de todos os participantes
                p.setNivel(p.getNivel() + 1);
            }
            else //o xp não é suficiente pra passar de nivel
            {
                p.setXp(p.getXp() + xp_aventura);
            }
            
            //distribuir pontos de habilidade
            p.setForca(( (int)p.getForca()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getForca(),
                        (int)a.getForca_necessaria()));
            
            p.setAgilidade(( (int)p.getAgilidade()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getAgilidade(),
                        (int)a.getAgilidade_necessaria()));
            
            p.setResistencia(( (int)p.getResistencia()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getResistencia(),
                        (int)a.getResistencia_necessaria()));
            
            p.setConstituicao(((int)p.getConstituicao()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getConstituicao(),
                        (int)a.getConstituicao_necessario()));
            
            p.setSabedoria(((int)p.getSabedoria()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getSabedoria(),
                        (int)a.getSabedoria_necessaria()));
            
            p.setCarisma(((int)p.getCarisma()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getCarisma(),
                        (int)a.getCarisma_necessario()));
            
            p.setInteligencia(((int)p.getInteligencia()) + 
                        logicas_aventura.calcular_aumento_atributo(
                        p.getNivel(),
                        (int) p.getInteligencia(),
                        (int)a.getInteligencia_necessaria()));
        }
        
        boolean resultado = controleAventura.editar(a);
        if(resultado==true)
        {
            login.refresh();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","Você concluiu esta aventura"));
            Tipo_Historico th = this.controleTipo_Historico.findByName("concluir_aventura").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
        }
         else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));
    
        }//
        else//aventura ja foi finalizada por outra pessoa
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Essa aventura ja foi finalizada por outro personagem!"));
    
        
    }
     
     public void criarBatalha()
     {
        Aventura a = controleAventura.findSpecific(currentAventura);
        if(a.getEstado().getNome().equals("INCOMPLETA"))
        {
                //só criar se a data final for maior do q a data inicial
                //só criar se a data final for menor do q a data de finalização
                if(batalha.getData_finalizacao().compareTo(batalha.getData_inicio()) > 0 
                   && batalha.getData_finalizacao().compareTo(currentAventura.getData_finalizacao()) <=0)
                {
                            Estado e = controleEstado.findWithId(Long.parseLong(""+1));//Ativo
                    batalha.setEstado(e);
                    int pontos=(int)(batalha.getData_finalizacao().getTime() - batalha.getData_inicio().getTime())/(1000*60*60*24);
                    
                    batalha.setPontos_de_balalha(pontos);
                    batalha.setPersonagem_criador(login.getPersonagem());
                    batalha.setData_criacao(new java.util.Date());
                    batalha.setData_ultima_alteracao(new java.util.Date());
                    a.getSetBatalhas().add(batalha);
                    //a.setData_criacao(new Date());
                    a.setData_ultima_alteracao(new java.util.Date());
                    
                    boolean resultado = controleAventura.editar(a);
                    if(resultado==true)
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","Você criou uma nova batalha"));
                            
            Tipo_Historico th = this.controleTipo_Historico.findByName("criar_batalha").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
                    }
                     else
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));


                    //limpar
                    currentAventura = new Aventura();
                    batalha = new Batalha();
                }
            
        }
        else
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","A Aventura não está disponível"));
    
     }
     //BATALHAS
     
     
    public boolean checkJoined(Aventura a)
    {
        List<Personagem> personagens = a.getPersonagens();
        Iterator it=personagens.iterator();
        while(it.hasNext()) {
            Personagem p = (Personagem)it.next();
            if(p.equals(login.getPersonagem()))
                return true;
        }
        return false;
    }
    
    public boolean checkAtivo(Aventura a)
    {
        if (a.getEstado().getNome().equals("ATIVA"))
            return true;
        return false;
    }
    public boolean checkCreator(Aventura a)
    {
        try
        {
            if(a != null && login != null)
            if(a.getPersonagem_criador().equals(login.getPersonagem()) )
            return true;
        }catch(Exception e)
        {
            return false;
        }
        return false;
    }
    public Login getLogin() {
        return login;
    }
    
    public Date getMinDate(){
        Date hoje = new Date(); 
        if(currentAventura.getData_inicio().compareTo(hoje) > 0 )//data de inicio futura
        {
            return currentAventura.getData_inicio();
        }
        return hoje;
    }
    
    public Date getMaxDate(){
        Date hoje = new Date(); 
        if(currentAventura.getData_finalizacao().compareTo(hoje) > 0 )
        {
            return currentAventura.getData_finalizacao();
        }
        return hoje;
    }
 
    
    public void setLogin(Login login) {
        this.login = login;
    }

    public boolean isAtiva(Aventura a)//verifica se a aventura é ativa
    {
        Estado e = a.getEstado();
        if(e.getNome().equals("ATIVA"))
            return true;
        return false;
    }
    
     public boolean isDone(Aventura a)//verifica se a aventura foi concluída
    {
        Estado e = a.getEstado();
        if(e.getNome().equals("CONCLUÍDA"))
            return true;
        return false;
    }
    
    public boolean isCancelled(Aventura a)//verifica se a aventura foi cancelada
    {
        Estado e = a.getEstado();
        if(e.getNome().equals("CANCELADA"))
            return true;
        return false;
    }
    
    public boolean isFreezed(Aventura a)//verifica se a aventura foi congelada
    {
        Estado e = a.getEstado();
        if(e.getNome().equals("CONGELADA"))
            return true;
        return false;
    }   
    
    public boolean isFinished(Aventura a)//verifica se a aventura foi finalizada com pendencias
    {
        Estado e = a.getEstado();
        if(e.getNome().equals("FINALIZADA"))
            return true;
        return false;
    }  
    
    public boolean isUnfinished(Aventura a)//verifica se a aventura está sendo feita
    {
        Estado e = a.getEstado();
        if(e.getNome().equals("INCOMPLETA"))
            return true;
        return false;
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

    public String atributoColor(double a, int i)
    {
        double v=5;
        double v2 = 2;
        if(i == 1)//força
        {
               v = login.getPersonagem().getForca();
        }else if(i == 2)//agilidade
        {
            v = login.getPersonagem().getAgilidade();
        }else if(i == 3)//constituição
        {
             v = login.getPersonagem().getConstituicao();
        }
        else if(i == 4)//resistencia
        {
             v = login.getPersonagem().getResistencia();
        }
        else if(i == 5)//sabedoria
        {
            v = login.getPersonagem().getSabedoria();
        }
        
        else if(i == 6)//carisma
        {
            v = login.getPersonagem().getCarisma();
        }
        else if(i == 7)//inteligencia
        {
            v = login.getPersonagem().getInteligencia();
        }
        double valor = (int) v % 10;//Math.pow(10.0, Double.parseDouble(""+(size+3)));
        
        if(valor < a)
        {
            //vermelho
          return  "#e0402f";
        }
            else
              return "#138c33";  //verde
    }
    
    public Aventura getCurrentAventura() {
        return currentAventura;
    }

    public void setCurrentAventura(Aventura currentAventura) {
        this.currentAventura = currentAventura;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public TimelineModel getGantt() {
        return gantt;
    }

    public void setModel(TimelineModel gantt) {
        this.gantt = gantt;
    }
        
    public List<Aventura> getAventuras() {
        return aventuras;
    }

    public List<Aventura> getAventuras_concluidas() {
        return aventuras_concluidas;
    }

    public void setAventuras_concluidas(List<Aventura> aventuras_concluidas) {
        this.aventuras_concluidas = aventuras_concluidas;
    }

    public void setAventuras(List<Aventura> aventuras) {
        this.aventuras = aventuras;
    }

    public Batalha getBatalha() {
        return batalha;
    }

    public void setBatalha(Batalha batalha) {
        this.batalha = batalha;
    }
    
    
        
        
}
