package ifce.edu.br.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ifce.edu.br.model.Produto;

public class ProdutoDAO extends GenericDao<Produto> {

	public ProdutoDAO() {
		super(Produto.class);
	}
	

}
