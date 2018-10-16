/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.tgp.managedbeans;

import com.br.tgp.dao.*;
import com.br.tgp.entidades.*;
import com.br.tgp.modelos.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.Random;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author Danillo
 */

@ManagedBean(name = "controle_aventura")//conectar variaveis com layout
@ViewScoped
public class AventuraMB implements Serializable {
    
    
    @ManagedProperty(value="#{login}")
    private Login login;
    private AventuraDAO controleAventura=new AventuraDAO();//antigo ejb
    private LevelDAO controleLevel=new LevelDAO();//antigo ejb
    private MundoDAO controleMundo=new MundoDAO();//antigo ejb
    private EstadoDAO controleEstado=new EstadoDAO();//antigo ejb
    private ClasseDAO controleClasse=new ClasseDAO();//antigo ejb
    private RacaDAO controleRaca=new RacaDAO();//antigo ejb
    private ArmaDAO controleArma=new ArmaDAO();//antigo ejb
    private PericiaDAO controlePericia=new PericiaDAO();//antigo ejb
    private NoplayablecharDAO controleNoplayablechar=new NoplayablecharDAO();//antigo ejb
    private TesouroDAO controleTesouro=new TesouroDAO();//antigo ejb
    
    private HistoricoDAO controleHistorico=new HistoricoDAO();//antigo ejb
    private Tipo_HistoricoDAO controleTipo_Historico=new Tipo_HistoricoDAO();//antigo ejb
    
    
    //private GenericDAO<Indicador> controleIndicador;//antigo ejb
    List<Aventura> cache;
    List<Level> levels;
    List<Mundo> mundos;
    List<Classe> classes;
    List<Raca> racas;
    List<Arma> armas;
    List<Pericia> pericias;
    List<Tesouro> tesouros;
    List<Noplayablechar> noplayablechars;
    
    private List<Classe> classes_aventura;
    
//    @ManagedProperty("#{aventura}")
    private Aventura aventura; 
    private LogicasAventura logicas_aventura;
    //private Indicador indicador_editar;
    //private Long id_indicador;
   
    @PostConstruct
    public void iniciando()
    {
        
        logicas_aventura = new LogicasAventura();
        controleAventura = new AventuraDAO();
        controleLevel = new LevelDAO();
        levels = controleLevel.findAll();
        mundos = controleMundo.findAll();
        classes = controleClasse.findAll();
        racas = controleRaca.findAll();
        armas = controleArma.findAll();
        pericias = controlePericia.findAll();
        noplayablechars = controleNoplayablechar.findAll();
        tesouros = controleTesouro.findAll();
        
        aventura=new Aventura();
              //aventura.setNome("Projeto RFID");
        //aventura.setDescricao("Projeto de rendimento RFID");
aventura.setLevel(levels.get(1));
        //aventura.setClasses(classes.subList(0, 2));
        //aventura.setRacas(racas.subList(0, 2));
        aventura.setPersonagem_criador(login.getPersonagem());

//aventura.setArmas(armas.subList(0, 2));
        Mundo mundo = mundos.get(2);
        Estado estado = controleEstado.findWithId(Long.parseLong(""+1));
        
        aventura.setEstado(estado);
        aventura.setMundo(mundo);
        aventura.setData_inicio(new java.util.Date());
        aventura.setData_criacao(new java.util.Date());
        Date data_fatal = new java.util.Date();
        data_fatal.setMonth(data_fatal.getMonth() + 1);
        aventura.setData_finalizacao(data_fatal);
        aventura.setData_ultima_alteracao(new java.util.Date());
        aventura.setXp(logicas_aventura.calculo_xp_aventura(Double.parseDouble(""+ aventura.getLevel().getFator_multiplicador_xp()),aventura.getData_criacao(), aventura.getData_finalizacao()));
        
        aventura.setForca_necessaria(5);
        aventura.setAgilidade_necessaria(5);
        aventura.setConstituicao_necessario(5);
        aventura.setResistencia_necessaria(5);
        aventura.setSabedoria_necessaria(5);
        aventura.setInteligencia_necessaria(5);
        aventura.setCarisma_necessario(5);
        
    }
    
    public void cadastrar()
    {
        boolean resultado;
        //aventura.setClasses(classes_aventura);
        controleAventura=new AventuraDAO();
        resultado=controleAventura.adicionar(aventura);
        if(resultado==true)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","A aventura foi cadastrada com sucesso!"));
            limpar();
            //cadastrar
            Tipo_Historico th = this.controleTipo_Historico.findByName("criar_aventura").get(0);
            Historico hist = new Historico();
            hist.setPersonagem(login.getPersonagem());
            hist.setData_criacao(new Timestamp(new java.util.Date().getTime()));
            hist.setTipo(th);
            controleHistorico.adicionar(hist);
        }
         else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));
    
    }
    /*
 
     public String setId_indicador(Long id_indicador) {
        this.id_indicador = id_indicador;//serve pra nada essa linha
        this.indicador_editar=this.controleIndicador.findWithId(id_indicador);
        
        return "/editar_indicador";
    }
     */
    
    public void recalcular_xp()
    {
        aventura.setXp(logicas_aventura.calculo_xp_aventura(Double.parseDouble(""+ aventura.getLevel().getFator_multiplicador_xp()),aventura.getData_inicio(), aventura.getData_finalizacao()));
        
    }
    
    public void initialDateChange(SelectEvent event) 
    {  
        Date data = (Date)event.getObject();
        aventura.setXp(logicas_aventura.calculo_xp_aventura(Double.parseDouble(""+ aventura.getLevel().getFator_multiplicador_xp()),(Date)event.getObject(), aventura.getData_finalizacao()));
        aventura.setData_inicio(data);
    }
    
     public void finalDateChange(SelectEvent event) 
    {  
        aventura.setXp(logicas_aventura.calculo_xp_aventura(Double.parseDouble(""+ aventura.getLevel().getFator_multiplicador_xp()),aventura.getData_inicio(),(Date)event.getObject() ));
        Date data = (Date)event.getObject();
        aventura.setData_finalizacao(data);
    }
    public void limpar()
    {
        aventura=new Aventura();
        aventura.setLevel(levels.get(1));
        aventura.setPersonagem_criador(login.getPersonagem());
        Mundo mundo = mundos.get(2);
        Estado estado = controleEstado.findWithId(Long.parseLong(""+1));
        
        aventura.setEstado(estado);
        aventura.setMundo(mundo);
        aventura.setData_inicio(new java.util.Date());
        aventura.setData_criacao(new Date());
        Date data_fatal = new java.util.Date();
        data_fatal.setMonth(data_fatal.getMonth() + 1);
        aventura.setData_finalizacao(data_fatal);
        aventura.setData_ultima_alteracao(new java.util.Date());
        
        aventura.setXp(logicas_aventura.calculo_xp_aventura(Double.parseDouble(""+ aventura.getLevel().getFator_multiplicador_xp()),aventura.getData_criacao(), aventura.getData_finalizacao()));
       // aventura.setXp();
        aventura.setForca_necessaria(5);
        aventura.setAgilidade_necessaria(5);
        aventura.setConstituicao_necessario(5);
        aventura.setResistencia_necessaria(5);
        aventura.setSabedoria_necessaria(5);
        aventura.setCarisma_necessario(5);
        aventura.setInteligencia_necessaria(5);
        
    }
    
    public String selectBg()
    {
        Random r= new Random();
        int result = 1 + r.nextInt(12);
        return "rpg_bkg"+result+".jpg";
    }
    //GETTERS AND SETTERS

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public NoplayablecharDAO getControleNoplayablechar() {
        return controleNoplayablechar;
    }

    public void setControleNoplayablechar(NoplayablecharDAO controleNoplayablechar) {
        this.controleNoplayablechar = controleNoplayablechar;
    }

    public List<Noplayablechar> getNoplayablechars() {
        return noplayablechars;
    }

    public void setNoplayablechars(List<Noplayablechar> noplayablechars) {
        this.noplayablechars = noplayablechars;
    }

    public PericiaDAO getControlePericia() {
        return controlePericia;
    }

    public void setControlePericia(PericiaDAO controlePericia) {
        this.controlePericia = controlePericia;
    }

    public List<Pericia> getPericias() {
        return pericias;
    }

    public void setPericias(List<Pericia> pericias) {
        this.pericias = pericias;
    }

    public RacaDAO getControleRaca() {
        return controleRaca;
    }

    public ArmaDAO getControleArma() {
        return controleArma;
    }

    public void setControleArma(ArmaDAO controleArma) {
        this.controleArma = controleArma;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public void setArmas(List<Arma> armas) {
        this.armas = armas;
    }

    public void setControleRaca(RacaDAO controleRaca) {
        this.controleRaca = controleRaca;
    }

    public List<Raca> getRacas() {
        return racas;
    }

    public void setRacas(List<Raca> racas) {
        this.racas = racas;
    }

    public ClasseDAO getControleClasse() {
        return controleClasse;
    }

    public void setControleClasse(ClasseDAO controleClasse) {
        this.controleClasse = controleClasse;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public List<Classe> getClasses_aventura() {
        return classes_aventura;
    }

    public void setClasses_aventura(List<Classe> classes_aventura) {
        this.classes_aventura = classes_aventura;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public AventuraDAO getControleAventura() {
        return controleAventura;
    }

    public void setControleAventura(AventuraDAO controleAventura) {
        this.controleAventura = controleAventura;
    }

    public TesouroDAO getControleTesouro() {
        return controleTesouro;
    }

    public void setControleTesouro(TesouroDAO controleTesouro) {
        this.controleTesouro = controleTesouro;
    }

    public List<Tesouro> getTesouros() {
        return tesouros;
    }

    public void setTesouros(List<Tesouro> tesouros) {
        this.tesouros = tesouros;
    }

    public EstadoDAO getControleEstado() {
        return controleEstado;
    }

    public void setControleEstado(EstadoDAO controleEstado) {
        this.controleEstado = controleEstado;
    }

    public LevelDAO getControleLevel() {
        return controleLevel;
    }

    public MundoDAO getControleMundo() {
        return controleMundo;
    }

    public void setControleMundo(MundoDAO controleMundo) {
        this.controleMundo = controleMundo;
    }

    public List<Mundo> getMundos() {
        return mundos;
    }

    public void setMundos(List<Mundo> mundos) {
        this.mundos = mundos;
    }

    public void setControleLevel(LevelDAO controleLevel) {
        this.controleLevel = controleLevel;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public LogicasAventura getLogicas_aventura() {
        return logicas_aventura;
    }

    public void setLogicas_aventura(LogicasAventura logicas_aventura) {
        this.logicas_aventura = logicas_aventura;
    }

    public List<Aventura> getCache() {
        return cache;
    }

    public void setCache(List<Aventura> cache) {
        this.cache = cache;
    }

    public Aventura getAventura() {
        return aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }

    
    
}
