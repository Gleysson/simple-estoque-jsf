package ifce.edu.br.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;

@ManagedBean 
@RequestScoped
public class UsuarioBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	
	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void login() {
		try {

			if(usuario.equals("admin") && senha.equals("admin")) {
				System.out.println("Entrou no Sistema");
				FacesContext .getCurrentInstance().getExternalContext().redirect("dashboard.jsf");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
