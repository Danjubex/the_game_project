/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.tgp.managedbeans;

import com.br.ebserh.managedbeans.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Danillo
 */
@ManagedBean (name="controles_gerais")
public class ControlesGeraisMB {
    private Calendar data;
    private String frase;
    
    public int getAno()
    {
        data= new GregorianCalendar();
        return data.getTime().getYear() + 1900;
    }
    
}
