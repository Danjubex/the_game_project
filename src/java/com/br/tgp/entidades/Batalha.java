/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.entidades;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author danillo.xavier
 */
@Entity
@NamedQueries(
{@NamedQuery(name="Batalha.findActiveds", query ="SELECT B FROM Batalha b ORDER BY b.nome"),
 @NamedQuery(name="Batalha.findSpecific", query ="SELECT B FROM Batalha b WHERE b.id = :id")


})
public class Batalha {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)         
    Long id;
    
    Timestamp data_criacao;    
    
    Timestamp data_inicio;
    
    Timestamp data_finalizacao;
    
    Timestamp data_ultima_alteracao;
    
    @OneToOne    
    private Personagem personagem_criador;
    @ManyToOne
    private Estado estado;//ATIVO, INATIVO, CANCELADO, CONCLUÍDO, INCOMPLETO
    
    private String nome;
    
    private int pontos_de_balalha;

    public Batalha() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_criacao() {
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

    public Date getData_ultima_alteracao() {
        return data_ultima_alteracao;
    }

    public void setData_ultima_alteracao(Date data_ultima_alteracao) {
        this.data_ultima_alteracao = new Timestamp(data_ultima_alteracao.getTime());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Personagem getPersonagem_criador() {
        return personagem_criador;
    }

    public void setPersonagem_criador(Personagem personagem_criador) {
        this.personagem_criador = personagem_criador;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getPontos_de_balalha() {
        return pontos_de_balalha;
    }

    public void setPontos_de_balalha(int pontos_de_balalha) {
        this.pontos_de_balalha = pontos_de_balalha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Batalha other = (Batalha) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
