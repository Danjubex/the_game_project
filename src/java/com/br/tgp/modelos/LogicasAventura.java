/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.modelos;
import java.util.Date;
/**
 *
 * @author danillo.xavier
 */
public class LogicasAventura {

    public int calculo_xp_aventura(double fator_multiplicador, Date inicio, Date fim)
    {
        //quantidade de semanas multiplicada por 44 horas efetivas
        long mili = fim.getTime() - inicio.getTime();
        long hours = mili/1000/60/60;
        long dias = hours/24;
        long semanas = dias/7;
        double calculo = (double) (( semanas * 44 ) * fator_multiplicador) ;
        return (int) calculo;//MILLI
    }
    //calcula quantos pontos de vida cada personagem merece
    public int calculo_PV_final_aventura(Date fim)
    {
        Date hoje=new Date();
        //quantidade de semanas multiplicada por 44 horas efetivas
        long mili = fim.getTime() - hoje.getTime();
        long hours = mili/1000/60/60;
        long dias = hours/24;
        long semanas = dias/7;
        double calculo = (double) (( semanas * 44 )) ;
        
        if(calculo < 0)
           return 0;
        else
           return (int) calculo;
    }
    
    public int calcular_xp_maximo(int nivel)
    {
        return (int) Math.pow(nivel,3);
    }
    //calcula quanto cada atributo de habilidades de cada personagem deve aumentar
    public int calcular_aumento_atributo(int nivel, int atributo_personagem, int atributo_aventura)
    {
        int atributo_maximo = nivel*10;// ex nvl 2 - max 20 atual 15
        int atributo_equivalente_aventura = atributo_maximo - 10 + atributo_aventura; //ex 18
        int atributo_equivalente_personagem = atributo_maximo - 10 + atributo_personagem%10;
        System.out.println("P: "+atributo_equivalente_personagem);
        System.out.println("A: "+atributo_equivalente_aventura+"\n");
        if(atributo_equivalente_aventura > atributo_equivalente_personagem)//8
        {
            int acrescimo = atributo_equivalente_aventura - atributo_equivalente_personagem;
            return acrescimo;
        }
                return 0;
                
                
    //nv l4
    //max 40
    //atual 24
    //formula 40 -10 + 4 = 34;
    //add 40 - 10 + 8 = 38
    //acrescimo 4
    }
}
