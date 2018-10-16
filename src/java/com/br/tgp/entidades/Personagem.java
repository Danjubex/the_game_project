/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author danillo.xavier
 */
@NamedQueries(
{@NamedQuery(name="Personagem.logar", query ="SELECT P FROM Personagem p WHERE p.login = :user AND p.senha = :pass ORDER BY p.nome"),
    @NamedQuery(name="Personagem.findAll", query ="SELECT P FROM Personagem p ORDER BY p.nome")
        
})
@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)         
    Long id;
    
    private String login;
    private String senha;
    private String email;
    @ManyToOne
    private Perfil perfil;
    @ManyToOne
    private Raca raca;
    @ManyToOne
    private Classe classe;
    private int nivel;
    private int xp;
    private int pontos_de_batalha;
    
    private String nome;
    private String apelido;
    
    private int image_number;
    
    private double forca;
    private double agilidade;
    private double constituicao;
    private double resistencia;
    private double sabedoria;
    private double carisma;
    private double inteligencia;
    private int iniciativa;
    
    private int pontos_de_vida;
    private int pontos_de_cura;
    
    private int classe_de_armadura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getLogin() {
        return login;
    }

    public double getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(double inteligencia) {
        this.inteligencia = inteligencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getImage_number() {
        return image_number;
    }

    public void setImage_number(int image_number) {
        this.image_number = image_number;
    }

    public int getPontos_de_batalha() {
        return pontos_de_batalha;
    }

    public void setPontos_de_batalha(int pontos_de_batalha) {
        this.pontos_de_batalha = pontos_de_batalha;
    }

    public double getForca() {
        return forca;
    }

    public void setForca(double forca) {
        this.forca = forca;
    }

    public double getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(double agilidade) {
        this.agilidade = agilidade;
    }

    public double getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(double constituicao) {
        this.constituicao = constituicao;
    }

    public double getResistencia() {
        return resistencia;
    }

    public void setResistencia(double resistencia) {
        this.resistencia = resistencia;
    }

    public double getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(double sabedoria) {
        this.sabedoria = sabedoria;
    }

    public double getCarisma() {
        return carisma;
    }

    public void setCarisma(double carisma) {
        this.carisma = carisma;
    }

    public int getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(int iniciativa) {
        this.iniciativa = iniciativa;
    }

    public int getPontos_de_vida() {
        return pontos_de_vida;
    }

    public void setPontos_de_vida(int pontos_de_vida) {
        this.pontos_de_vida = pontos_de_vida;
    }

    public int getPontos_de_cura() {
        return pontos_de_cura;
    }

    public void setPontos_de_cura(int pontos_de_cura) {
        this.pontos_de_cura = pontos_de_cura;
    }

    public int getClasse_de_armadura() {
        return classe_de_armadura;
    }

    public void setClasse_de_armadura(int classe_de_armadura) {
        this.classe_de_armadura = classe_de_armadura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Personagem other = (Personagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
