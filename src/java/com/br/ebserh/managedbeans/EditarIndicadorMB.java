/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.br.ebserh.managedbeans;

import com.br.ebserh.modelo.dao.AvaliacaoDAO;
import com.br.ebserh.modelo.dao.IndicadorDAO;
import com.br.ebserh.modelo.planejamento.Avaliacao;
import com.br.ebserh.modelo.planejamento.Indicador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Danillo
 */
@ManagedBean(name = "editar_indicadorMB")//conectar variaveis com layout
@SessionScoped
public class EditarIndicadorMB implements Serializable {
    
        
    private IndicadorDAO controleIndicador;//antigo ejb
    private AvaliacaoDAO controleAvaliacao;//antigo ejb 
    private Indicador indicador_editar;
    private Long id_indicador;
    private Avaliacao avaliacao;

    private Double maior;
    
    //CHARTS
    private BarChartModel barModel;
    
    @PostConstruct
    public void iniciar()
    {
        controleIndicador=new IndicadorDAO();
        controleAvaliacao=new AvaliacaoDAO();
        maior=0.0;
        avaliacao=new Avaliacao();
        
//        this.indicador_editar=new Indicador();
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
//        .getRequest();
//
//        this.id_indicador = Long.parseLong(request.getParameter("id"));
        
    }
    
    public String editarIndicador(Long id_indicador)
    {
        this.id_indicador = id_indicador;//serve pra nada essa linha
//        this.indicador_editar=this.controleIndicador.findWithId(id_indicador);
//        System.out.println(id_indicador);
        createBarModel();
         return "editar_indicador?faces-redirect=true";
    }
    
    public void addAvaliacao()
    {
        boolean resultado= this.controleAvaliacao.adicionar(avaliacao);
        List<Avaliacao> avaliacoes=indicador_editar.getAvaliacoes();
         if(resultado==true)
        {
            if(avaliacoes==null)
            {
                avaliacoes=new ArrayList<Avaliacao>();//crio uma nova
            }
                avaliacoes.add(avaliacao);
                this.controleIndicador.editar(indicador_editar);//MERGE
        
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","A avaliação foi feita com sucesso!"));
            limpar();
        }
         else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));
    
    }
    
    public void limpar()
    {
        avaliacao=new Avaliacao();
        createBarModel();
     }
    
    public void atualizar()
    {
       boolean resultado = this.controleIndicador.editar(indicador_editar);
       if(resultado==true)
       {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso","Indicador alterado com sucesso!"));
            limpar();
       
       }
       else
       {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro","Verifique sua conexão!"));
    
       }
    }
    
    
    //GRAFICO
    private BarChartModel setupBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries valores = new ChartSeries();
        this.indicador_editar=this.controleIndicador.findWithId(id_indicador);
        List<Avaliacao> avaliacoes=indicador_editar.getAvaliacoes();
       
        valores.setLabel("Valores");
       if(avaliacoes!=null)
       {
//           System.out.println("Tem avaliacoes");
            Collections.sort(avaliacoes,new AvaliacaoSorter());
            for(int i=0;i<avaliacoes.size();i++)
            {
                Avaliacao avaliacao=avaliacoes.get(i);
                valores.set(this.conversorMes(avaliacao.getData().getMonth())+"/"+(avaliacao.getData().getYear()+1900)
                        , avaliacao.getValor());
                
                if(avaliacao.getValor()>maior)
                {
                    maior=avaliacao.getValor();
                }
            }
       }
       
     
       
        model.addSeries(valores);
        return model;
    }
    private void createBarModel() {
        barModel = setupBarModel();
         
        barModel.setTitle("Histograma");
        barModel.setLegendPosition("ne");//nordestE (direita)
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mês");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valor");
        yAxis.setMin(0);
        yAxis.setMax(maior + (maior*0.1));
    }
    
    public void gerarGrafico()
    {
       createBarModel();
    }
    //GETTERS AND SETTERS
    public String conversorMes(int mes)
    {
        mes+=1;
        String m="";
        switch(mes)
        {
            case 1: m = "Jan";break;
            case 2: m = "Fev";break;
            case 3: m = "Mar";break;
            case 4: m = "Abr";break;
            case 5: m = "Mai";break;
            case 6: m = "Jun";break;
            case 7: m = "Jul";break;
            case 8: m = "Ago";break;
            case 9: m = "Set";break;
            case 10: m = "Out";break;
            case 11: m = "Nov";break;
            case 12: m = "Dez";break;
        }
        return m;
    }
    public Indicador getIndicador_editar() {
        return indicador_editar;
    }

    public void setIndicador_editar(Indicador indicador_editar) {
        this.indicador_editar = indicador_editar;
    }

    public Long getId_indicador() {
        return id_indicador;
    }

    public void setId_indicador(Long id_indicador) {
        this.id_indicador = id_indicador;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    
    
}
