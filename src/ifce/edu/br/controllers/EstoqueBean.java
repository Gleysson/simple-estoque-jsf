package ifce.edu.br.controllers;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ifce.edu.br.dao.EstoqueDAO;
import ifce.edu.br.dao.FornecedorDAO;
import ifce.edu.br.dao.ProdutoDAO;
import ifce.edu.br.model.Estoque;
import ifce.edu.br.model.Produto;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean 
@ApplicationScoped
public class EstoqueBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Estoque estoque;	
	private EstoqueDAO dao;
	private ProdutoDAO daoProduto;
	private FornecedorDAO daoFornecedor;
	
	private List<Estoque> lista;
	private Integer produtoId;
	private Integer fornecedorId;
	
	public EstoqueBean(){
		this.dao = new EstoqueDAO();
		this.daoProduto = new ProdutoDAO();
		this.daoFornecedor = new FornecedorDAO();

		this.estoque = new Estoque();
		this.lista =  dao.findAll();
	}

	
	
	public ProdutoDAO getDaoProduto() {
		return daoProduto;
	}
	public void setDaoProduto(ProdutoDAO daoProduto) {
		this.daoProduto = daoProduto;
	}
	public FornecedorDAO getDaoFornecedor() {
		return daoFornecedor;
	}
	public void setDaoFornecedor(FornecedorDAO daoFornecedor) {
		this.daoFornecedor = daoFornecedor;
	}
	public Integer getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	public Integer getFornecedorId() {
		return fornecedorId;
	}
	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public EstoqueDAO getDao() {
		return dao;
	}
	public void setDao(EstoqueDAO dao) {
		this.dao = dao;
	}
	public List<Estoque> getLista() {
		return lista;
	}
	public void setLista(List<Estoque> lista) {
		this.lista = lista;
	}

	public void cadastrar() {
		
		this.estoque.setFornecedor(this.daoFornecedor.getById(this.fornecedorId));
		this.estoque.setProduto(this.daoProduto.getById(this.produtoId));
		this.dao.salvar(this.estoque);
		setLista(dao.findAll());
		
		try {
		   FacesContext .getCurrentInstance().getExternalContext().redirect("lista_estoque.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public void editar() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("id") != null && !req.getParameter("id").equals(""))
		       parametro = req.getParameter("id");
	
	   Estoque selected = dao.getById(Integer.parseInt(parametro));
	   this.produtoId = selected.getProduto().getId();
	   this.fornecedorId = selected.getFornecedor().getId();
	   setEstoque(selected);
	   
	   try {
		   FacesContext .getCurrentInstance().getExternalContext().redirect("form_estoque.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	public void excluir() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("id") != null && !req.getParameter("id").equals(""))
		       parametro = req.getParameter("id");
	
	   dao.excluir(Integer.parseInt(parametro));  
	   setLista(dao.findAll());
	}
	
	
	public List<Estoque> listar() {
		return this.dao.findAll();
	}
	
	
	public void redirect() {
        String parametro = "";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        
		if(req.getParameter("page") != null && !req.getParameter("page").equals(""))
		       parametro = req.getParameter("page");
	
	   try {
		   this.estoque = new Estoque();
		   FacesContext .getCurrentInstance().getExternalContext().redirect(parametro);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	

}
