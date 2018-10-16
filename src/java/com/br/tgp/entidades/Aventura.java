/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.entidades;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
/**
 *
 * @author danillo.xavier
 */
@Entity
@NamedQueries(
{@NamedQuery(name="Aventura.findActiveds", query ="SELECT A FROM Aventura a LEFT JOIN a.estado e WHERE e.nome = 'ATIVA' OR e.nome = 'INCOMPLETA' ORDER BY a.nome"),
 @NamedQuery(name="Aventura.findConcluidas", query ="SELECT A FROM Aventura a LEFT JOIN a.estado e WHERE e.nome = 'CONCLUÍDA' ORDER BY a.data_finalizacao DESC, a.nome"),
// @NamedQuery(name="Aventura.findConcluidas", query ="SELECT A FROM Aventura a LEFT JOIN a.npcs e WHERE e.nome = 'CONCLUÍDA' ORDER BY a.data_finalizacao DESC, a.nome"),
 @NamedQuery(name="Aventura.findSpecific", query ="SELECT A FROM Aventura a WHERE a.id = :id"),
 @NamedQuery(name="Aventura.findByPersonagem", query ="SELECT A FROM Aventura a LEFT JOIN FETCH a.personagens p WHERE p.id = :id"),


})

public class Aventura implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)         
    Long id;
    
    Timestamp data_criacao;  
    
    Timestamp data_inicio; 
    
    Timestamp data_finalizacao;
    
    Timestamp data_ultima_alteracao;

    @ManyToOne
    private Personagem personagem_criador;
    @ManyToOne
    private Mundo mundo;
    private String descricao;
    private String nome;
    
    private int xp;

    @ManyToOne
    private Level level; //facil, normal, dificil
    @ManyToOne
    private Estado estado;//ATIVO, INATIVO, CANCELADO, CONCLUÍDO, INCOMPLETO
    
    private double forca_necessaria;//muito pouca|1,2, pouca|3,4, normal|5,6, muito|7,8, bastante|9,10
    private double agilidade_necessaria;
    private double constituicao_necessario;
    private double resistencia_necessaria;
    private double sabedoria_necessaria;
    private double carisma_necessario; 
    private double inteligencia_necessaria;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Classe> classes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Batalha> batalhas;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Raca> racas;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Arma> armas;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Pericia> pericias;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Noplayablechar> npcs;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Tesouro> tesouros;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Personagem> personagens;

    
    
    public Aventura() {
        pericias = new HashSet();
        armas = new HashSet();
        classes = new HashSet();
        batalhas = new HashSet();
        racas = new HashSet();
        npcs = new HashSet();
        tesouros = new HashSet();
        nome = "";
        descricao = ""; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = new Timestamp(data_criacao.getTime());
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = new Timestamp(data_inicio.getTime());
    }

    public Date getData_finalizacao() {
        return data_finalizacao;
    }

    public void setData_finalizacao(Date data_finalizacao) {
        this.data_finalizacao = new Timestamp(data_finalizacao.getTime());
    }

    public Timestamp getData_ultima_alteracao() {
        return data_ultima_alteracao;
    }

    public void setData_ultima_alteracao(Date data_ultima_alteracao) {
        this.data_ultima_alteracao = new Timestamp(data_ultima_alteracao.getTime());
    }



    public Personagem getPersonagem_criador() {
        return personagem_criador;
    }

    public void setPersonagem_criador(Personagem personagem_criador) {
        this.personagem_criador = personagem_criador;
    }

    public Mundo getMundo() {
        return mundo;
    }

    public void setMundo(Mundo mundo) {
        this.mundo = mundo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }


    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getInteligencia_necessaria() {
        return inteligencia_necessaria;
    }

    public void setInteligencia_necessaria(double inteligencia_necessaria) {
        this.inteligencia_necessaria = inteligencia_necessaria;
    }

    public double getForca_necessaria() {
        return forca_necessaria;
    }

    public void setForca_necessaria(double forca_necessaria) {
        this.forca_necessaria = forca_necessaria;
    }

    public double getAgilidade_necessaria() {
        return agilidade_necessaria;
    }

    public void setAgilidade_necessaria(double agilidade_necessaria) {
        this.agilidade_necessaria = agilidade_necessaria;
    }

    public double getConstituicao_necessario() {
        return constituicao_necessario;
    }

    public void setConstituicao_necessario(double constituicao_necessario) {
        this.constituicao_necessario = constituicao_necessario;
    }

    public double getResistencia_necessaria() {
        return resistencia_necessaria;
    }

    public void setResistencia_necessaria(double resistencia_necessaria) {
        this.resistencia_necessaria = resistencia_necessaria;
    }

    public double getSabedoria_necessaria() {
        return sabedoria_necessaria;
    }

    public void setSabedoria_necessaria(double sabedoria_necessaria) {
        this.sabedoria_necessaria = sabedoria_necessaria;
    }

    public double getCarisma_necessario() {
        return carisma_necessario;
    }

    public void setCarisma_necessario(double carisma_necessario) {
        this.carisma_necessario = carisma_necessario;
    }

    public List<Arma> getArmas() {
        return (List<Arma>)(Object) Arrays.asList(armas.toArray());
//        return new ArrayList(Arrays.asList(armas.toArray()));
    }

    public void setArmas(List<Arma> armas) {
        this.armas = new HashSet<Arma>(armas);        
//this.armas = armas;
    }
    public List<Pericia> getPericias() {
        return (List<Pericia>)(Object) Arrays.asList(pericias.toArray());
      //  return new ArrayList(Arrays.asList(pericias.toArray()));
    }

    public void setPericias(List<Pericia> pericias) {
        this.pericias = new HashSet<Pericia>(pericias);
      //  this.pericias = pericias;
    }

    public List<Personagem> getPersonagens() {
    return new ArrayList(Arrays.asList(personagens.toArray()));        
//return ((List<Personagem>) (Object) Arrays.asList(personagens.toArray()));
   //return null;
    }
    public Set<Personagem> getSetPersonagens()
    {
        return personagens;
    }
    
    public Set<Batalha> getSetBatalhas()
    {
        return batalhas;
    }
    
    public void setPersonagens(List<Personagem> personagens) {
        this.personagens = new HashSet<Personagem>(personagens);
    }

    public List<Classe> getClasses() {
    //    return (List<Classe>)(Object) Arrays.asList(classes.toArray());
    return new ArrayList(Arrays.asList(classes.toArray()));
    }

    public void setClasses(List<Classe> classes) {
        this.classes = new HashSet<Classe>(classes);
        //this.classes = classes;
    }

    public List<Raca> getRacas() {
        return (List<Raca>)(Object) Arrays.asList(racas.toArray());
   // return new ArrayList(Arrays.asList(racas.toArray()));
    }

    public void setRacas(List<Raca> racas) {
        this.racas = new HashSet<Raca>(racas);
      //  this.racas = racas;
    }

    public List<Batalha> getBatalhas() {
  //      return (List<Batalha>)(Object) Arrays.asList(batalhas.toArray());
    return new ArrayList(Arrays.asList(batalhas.toArray()));
    }

    public void setBatalhas(List<Batalha> batalhas) {
        this.batalhas = new HashSet<Batalha>(batalhas);
//        this.batalhas = batalhas;
    }

    public List<Noplayablechar> getNpcs() {
  //      return (List<Noplayablechar>)(Object) Arrays.asList(npcs.toArray());
    return new ArrayList(Arrays.asList(batalhas.toArray()));
    }

    public void setNpcs(List<Noplayablechar> npcs) {
        this.npcs = new HashSet<Noplayablechar>(npcs);
        //this.npcs = npcs;//
    }

    public List<Tesouro> getTesouros() {
       // return (List<Tesouro>)(Object) Arrays.asList(tesouros.toArray());
     return new ArrayList(Arrays.asList(tesouros.toArray()));
    }

    public void setTesouros(List<Tesouro> tesouros) {
        this.tesouros = new HashSet<Tesouro>(tesouros);
       // this.tesouros = tesouros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aventura other = (Aventura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
