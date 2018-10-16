/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.entidades;

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
@Entity

@NamedQueries(
{
  @NamedQuery(name="Tipo_Historico.findWithId", query ="SELECT t FROM Tipo_Historico t WHERE t.id = :id"),
   @NamedQuery(name="Tipo_Historico.findByName", query="SELECT t FROM Tipo_Historico t WHERE lower(t.nome) = :nome")

})
public class Tipo_Historico {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)         
    Long id;
    private String nome;
    private String descricao;

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
    
    
    
}
