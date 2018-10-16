/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.tgp.managedbeans;
import com.br.tgp.dao.HistoricoDAO;
import java.io.Serializable;
import com.br.tgp.dao.LoginDAO;
import com.br.tgp.dao.Tipo_HistoricoDAO;
import com.br.tgp.entidades.Historico;
import com.br.tgp.entidades.Personagem;
import com.br.tgp.entidades.Tipo_Historico;
import com.br.tgp.modelos.SessionUtils;
import java.sql.Timestamp;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author danillo.xavier
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {
	private String pwd;
	private String msg;
	private String user;
        private LoginDAO dao;
        private Personagem personagem;

         private HistoricoDAO controleHistorico=new HistoricoDAO();//antigo ejb
         private Tipo_HistoricoDAO controleTipo_Historico=new Tipo_HistoricoDAO();//antigo ejb

    public Login() {
        dao = new LoginDAO();
    }
        
        

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

	//validate login
	public String validateUsernamePassword() {
		personagem = dao.validate(user, pwd);
		if (personagem != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
                        
                        //logou
                        Tipo_Historico th = this.controleTipo_Historico.findByName("login").get(0);
                        Historico hist = new Historico();
                        hist.setPersonagem(personagem);
                        hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
                        hist.setTipo(th);
                        controleHistorico.adicionar(hist);
                        
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuário e senha nao correspondem",
							"Por favor, informe usuário e senha corretamente"));
			return "login";
		}
	}
        
        public void refresh()
        {
            personagem = dao.validate(user, pwd);
        }
        
        public String getProgressLevel()
        {
            return ""+personagem.getXp() + " / " + (int) Math.pow(personagem.getNivel(), 3);
        }
        
        public int getProgress()
        {
            //9 atual - 27 - maximo

            return (int) (100*((personagem.getXp()) - Math.pow(personagem.getNivel()-1, 3)) /(Math.pow(personagem.getNivel(), 3) - Math.pow(personagem.getNivel()-1, 3)));
        }
	//logout event, invalidate session
	public String logout() {
                           //deslogou
                        Tipo_Historico th = this.controleTipo_Historico.findByName("logout").get(0);
                        Historico hist = new Historico();
                        hist.setPersonagem(personagem);
                        hist.setData_criacao(new Timestamp((new java.util.Date()).getTime()));
                        hist.setTipo(th);
                        controleHistorico.adicionar(hist);
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
  
                
		return "login?faces-redirect=true";
	}
}
