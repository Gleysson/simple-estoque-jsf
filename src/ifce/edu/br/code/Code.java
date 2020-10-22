package ifce.edu.br.code;

import java.util.List;

import javax.persistence.EntityManager;

import ifce.edu.br.dao.ProdutoDAO;
import ifce.edu.br.model.Produto;
import ifce.edu.br.utils.JPAUtil;


public class Code {

	public static void main(String[] args) {
		
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> p = dao.findAll();
		System.out.println(p);
	}

}
