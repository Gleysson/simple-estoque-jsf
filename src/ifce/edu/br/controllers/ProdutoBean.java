package ifce.edu.br.controllers;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ifce.edu.br.dao.ProdutoDAO;
import ifce.edu.br.model.Produto;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean 
@ApplicationScoped
public class ProdutoBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private HtmlDataTable dataTable;
	private static Produto produto;	
	private ProdutoDAO dao;
	private List<Produto> listaProduto;
	
	public ProdutoBean(){
		this.dao = new ProdutoDAO();
		ProdutoBean.produto = new Produto();
		this.listaProduto =  dao.findAll();
		System.out.println(this.listaProduto);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		ProdutoBean.produto = produto;
	}
	public ProdutoDAO getDao() {
		return dao;
	}
	public void setDao(ProdutoDAO dao) {
		this.dao = dao;
	}
	
	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public void cadastrar() {
		try {
			this.dao.salvar(ProdutoBean.produto);
		    setListaProduto(dao.findAll());
			FacesContext .getCurrentInstance().getExternalContext().redirect("lista_produto.jsf");	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editar() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("prod") != null && !req.getParameter("prod").equals(""))
		       parametro = req.getParameter("prod");
	
	   Produto selected = dao.getById(Integer.parseInt(parametro));  
	   setProduto(selected);
	   try {
		   FacesContext .getCurrentInstance().getExternalContext().redirect("form_produto.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void excluir() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("prod") != null && !req.getParameter("prod").equals(""))
		       parametro = req.getParameter("prod");
	
	   dao.excluir(Integer.parseInt(parametro));  
	   setListaProduto(dao.findAll());
	}
	
	
	public List<Produto> listar() {
		return this.dao.findAll();
	}
	
	
	public void redirect() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("page") != null && !req.getParameter("page").equals(""))
		       parametro = req.getParameter("page");
	
	   try {
		   ProdutoBean.produto = new Produto();
		   FacesContext .getCurrentInstance().getExternalContext().redirect(parametro);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}


   public HtmlDataTable getDataTable() {
         return dataTable;
   }

   public void setDataTable(HtmlDataTable dataTable) {
         this.dataTable = dataTable;
   }
	
	
	
	

}
