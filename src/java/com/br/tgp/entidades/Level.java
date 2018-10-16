/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.entidades;

import java.util.Objects;
import com.br.tgp.converters.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author danillo.xavier
 */
@NamedQueries(
{@NamedQuery(name="Level.findAll", query ="SELECT l FROM Level l")
})
@Entity
public class Level  implements Serializable,SampleEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)         
    Long id;
    private String nome;//facil, normal, dificil
    private String descricao;
    
    private double fator_multiplicador_xp;//0,5 | 1 | 2

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getFator_multiplicador_xp() {
        return fator_multiplicador_xp;
    }

    public void setFator_multiplicador_xp(double fator_multiplicador_xp) {
        this.fator_multiplicador_xp = fator_multiplicador_xp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Level other = (Level) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}