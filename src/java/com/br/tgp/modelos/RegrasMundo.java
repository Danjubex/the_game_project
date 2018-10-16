/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.modelos;

import com.br.tgp.dao.AventuraDAO;
import com.br.tgp.dao.HistoricoDAO;
import com.br.tgp.dao.PersonagemDAO;
import com.br.tgp.dao.Tipo_HistoricoDAO;
import com.br.tgp.entidades.Aventura;
import com.br.tgp.entidades.Historico;
import com.br.tgp.entidades.Personagem;
import com.br.tgp.entidades.Tipo_Historico;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 *
 * @author danillo.xavier
 */
public class RegrasMundo implements Job {
    	@Override
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
            
            AventuraDAO controleAventura=new AventuraDAO();//antigo ejb
            
            PersonagemDAO controlePersonagem=new PersonagemDAO();//antigo ejb
            
                  
    HistoricoDAO controleHistorico=new HistoricoDAO();//antigo ejb
    Tipo_HistoricoDAO controleTipo_Historico=new Tipo_HistoricoDAO();//antigo ejb
    
            List<Personagem> personagens = controlePersonagem.findAll();
            Iterator i = personagens.iterator();
            //Aventura aventura = controleAventura.findActiveds().get(0);
	//	System.out.println("JSF 2 + Quartz 2 example: "+aventura.getNome());

                //calcular pontos de vida de cada personagem
                while(i.hasNext())
                {
                    Personagem p = (Personagem) i.next();
                    //procurar todas as aventuras do personagem
                    List<Aventura> aventuras = controleAventura.findByPersonagem(p);
                    Iterator i_av = aventuras.iterator();
                    while(i_av.hasNext())
                    {
                                //para cada aventura verificar se está em atrazo
                        Aventura av = (Aventura) i_av.next();
                       
                        if(av.getEstado().getNome().equals("INCOMPLETA") && av.getData_criacao().before(av.getData_finalizacao()))//so aventuras em atividade
                        {
                            System.out.println("P.NOME: "+p.getNome() +" - A.NOME: "+av.getNome()+ " - A.ESTADO: "+av.getEstado().getNome());
                             //se estiver então deve ser descontado a quantidade de horas em atraso dos pontos de vida
                            //considerar a classe de armadura
                            //se houverem mais horas de atraso do que o CA entao descontar cada hora
                            //dias seguintes descontar somente o que passou
                            Date hoje = new Date();
                            if(hoje.after(av.getData_finalizacao()))//atraso
                            {
                                int atraso_horas = (int)(hoje.getTime() - av.getData_finalizacao().getTime())/(1000*60*60);//24
                                if(atraso_horas > p.getClasse_de_armadura())//dano real CA 12
                                {
                                    int dano = 0;
                                    //se o atraso - CA for maior do que 24 significa que ja houve desconto de ca
                                    if(atraso_horas - p.getClasse_de_armadura() > 24)
                                    {
                                        dano = 24;//dano de um dia de atraso
                                    }else//primeira pancada
                                    {
                                        dano = atraso_horas - p.getClasse_de_armadura(); //12
                                    
                                    }
                                    p.setPontos_de_vida(p.getPontos_de_vida() - dano);
                                }
                            }
                        }
                        
                        controlePersonagem.editar(p);
                        
                            /*Tipo_Historico th = controleTipo_Historico.findByName("join_aventura").get(0);
                            Historico hist = new Historico();
                            hist.setPersonagem(login.getPersonagem());
                            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
                            hist.setTipo(th);
                            controleHistorico.adicionar(hist);
*/
                    }//FIM DO WHILE
            
                            Tipo_Historico th = controleTipo_Historico.findByName("alteracao_status").get(0);
                            Historico hist = new Historico();
                            hist.setPersonagem(p);
                            hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
                            hist.setTipo(th);
                            controleHistorico.adicionar(hist);
                }
                //reduzir pontos de batalha de cada personagem
                
                //calcular iniciativa média de cada personagem
                
	}
}
