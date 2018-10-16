/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.ebserh.modelo.planejamento;

import com.br.ebserh.modelo.users.Usuario;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Danillo
 */

@NamedQueries(
{@NamedQuery(name="Indicador.findAll", query ="SELECT i FROM Indicador i"),
 @NamedQuery(name="Indicador.findSpecific", query ="SELECT i FROM Indicador i WHERE i.id = :id"),       
 @NamedQuery(name="Indicador.findWithId", query ="SELECT i FROM Indicador i WHERE i.id = :id"),       
 @NamedQuery(name="Indicador.findSimilar", query="SELECT i FROM Indicador i WHERE lower(i.nome) LIKE :nome"),//tem q usar %+nome+%
 @NamedQuery(name="Indicador.findByName", query="SELECT i FROM Indicador i WHERE lower(i.nome) = :nome")

})
@Entity//anotação para salvar dados (persistir)
public class Indicador {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;//auto generated
    
    
    String nome;
    String descricao;
    @ManyToOne
    Usuario responsavel;//funcionario responsável pelo indicador
    String setor_de_coleta;
    String metas_curto_prazo;//3 meses
    String metas_longo_prazo;
    String metodo_de_analise;
    String importancia_do_indicador;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Avaliacao> avaliacoes;
    String nome_chefe_imediato;
    String unidade_indicador;
    
    //mes
    //unidade
    
    
    
    
    public String getUnidade_indicador() {
        return unidade_indicador;
    }

    public void setUnidade_indicador(String unidade_indicador) {
        this.unidade_indicador = unidade_indicador;
    }
    
    
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

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getSetor_de_coleta() {
        return setor_de_coleta;
    }

    public void setSetor_de_coleta(String setor_de_coleta) {
        this.setor_de_coleta = setor_de_coleta;
    }

    public String getNome_chefe_imediato() {
        return nome_chefe_imediato;
    }

    public void setNome_chefe_imediato(String nome_chefe_imediato) {
        this.nome_chefe_imediato = nome_chefe_imediato;
    }

    public String getMetas_curto_prazo() {
        return metas_curto_prazo;
    }

    public void setMetas_curto_prazo(String metas_curto_prazo) {
        this.metas_curto_prazo = metas_curto_prazo;
    }

    public String getMetas_longo_prazo() {
        return metas_longo_prazo;
    }

    public void setMetas_longo_prazo(String metas_longo_prazo) {
        this.metas_longo_prazo = metas_longo_prazo;
    }

    public String getMetodo_de_analise() {
        return metodo_de_analise;
    }

    public void setMetodo_de_analise(String metodo_de_analise) {
        this.metodo_de_analise = metodo_de_analise;
    }

    public String getImportancia_do_indicador() {
        return importancia_do_indicador;
    }

    public void setImportancia_do_indicador(String importancia_do_indicador) {
        this.importancia_do_indicador = importancia_do_indicador;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.responsavel);
        hash = 19 * hash + Objects.hashCode(this.setor_de_coleta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Indicador other = (Indicador) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.responsavel, other.responsavel)) {
            return false;
        }
        if (!Objects.equals(this.setor_de_coleta, other.setor_de_coleta)) {
            return false;
        }
        return true;
    }
    
    
    
}
