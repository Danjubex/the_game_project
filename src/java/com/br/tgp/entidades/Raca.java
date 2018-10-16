/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.entidades;

import com.br.tgp.converters.SampleEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 *
 * @author danillo.xavier
 */
@Entity
        @NamedQueries(
{@NamedQuery(name="Raca.findAll", query ="SELECT R FROM Raca r")
})
public class Raca  implements Serializable,SampleEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)         
    Long id;
    
    private String nome;
    private String descricao;
    @OneToMany
    private List<Pericia> pericias_basicas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pericia> getPericias_basicas() {
        return pericias_basicas;
    }

    public void setPericias_basicas(List<Pericia> pericias_basicas) {
        this.pericias_basicas = pericias_basicas;
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

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Raca other = (Raca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
