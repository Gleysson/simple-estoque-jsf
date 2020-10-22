package ifce.edu.br.controllers;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ifce.edu.br.dao.FornecedorDAO;
import ifce.edu.br.model.Fornecedor;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean 
@ApplicationScoped
public class FornecedorBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;	
	private FornecedorDAO dao;
	private List<Fornecedor> lista;
	
	public FornecedorBean(){
		this.dao = new FornecedorDAO();
		this.lista = this.dao.findAll();
		this.fornecedor = new Fornecedor();
	}
	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public FornecedorDAO getDao() {
		return dao;
	}
	public void setDao(FornecedorDAO dao) {
		this.dao = dao;
	}
	public List<Fornecedor> getLista() {
		return lista;
	}
	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void cadastrar() {
		try {
			this.dao.salvar(this.fornecedor);
			setLista(dao.findAll());
			FacesContext .getCurrentInstance().getExternalContext().redirect("lista_fornecedor.jsf");	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editar() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("forn") != null && !req.getParameter("forn").equals(""))
		       parametro = req.getParameter("forn");
	
	   Fornecedor selected = dao.getById(Integer.parseInt(parametro));  
	   setFornecedor(selected);
	   
	   try {
		   setLista(dao.findAll());
		   FacesContext .getCurrentInstance().getExternalContext().redirect("form_fornecedor.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void excluir() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("forn") != null && !req.getParameter("forn").equals(""))
		       parametro = req.getParameter("forn");
	
	   dao.excluir(Integer.parseInt(parametro));  
	   setLista(dao.findAll());
	}
	
	
	public List<Fornecedor> listar() {
		return this.dao.findAll();
	}
	
	
	public void redirect() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("page") != null && !req.getParameter("page").equals(""))
		       parametro = req.getParameter("page");
	
	   try {
		   this.fornecedor = new Fornecedor();
		   FacesContext .getCurrentInstance().getExternalContext().redirect(parametro);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
