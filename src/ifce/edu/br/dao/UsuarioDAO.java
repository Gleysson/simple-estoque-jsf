package ifce.edu.br.dao;

import ifce.edu.br.model.Produto;


public class UsuarioDAO extends GenericDao<Produto> {

	public UsuarioDAO() {
		super(Produto.class);
	}

}
