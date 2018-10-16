/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.ebserh.managedbeans;


import com.br.ebserh.modelo.planejamento.Avaliacao;
import java.util.Comparator;

/**
 *
 * @author Danillo
 */
public class AvaliacaoSorter implements Comparator<Avaliacao>{
    
    @Override
    public int compare(Avaliacao a1, Avaliacao a2) {
        int retorno = 0;

    if(a1.getData().getTime() < a2.getData().getTime()){
        retorno =  -1;
    }else if(a1.getData().getTime() > a2.getData().getTime()){
        retorno =  1;
    }else if(a1.getData().getTime() == a2.getData().getTime()){
        retorno =  0;
    }
    return retorno;

    
 }
    
    
}
