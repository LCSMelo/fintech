package br.com.fiap.store.factory;

import br.com.fiap.store.dao.CategoriaDAO;
import br.com.fiap.store.dao.DespesaDAO;
import br.com.fiap.store.dao.ReceitaDAO;
import br.com.fiap.store.dao.UsuarioDAO;
import br.com.fiap.store.dao.impl.OracleCategoriaDAO;
import br.com.fiap.store.dao.impl.OracleDespesaDAO;
import br.com.fiap.store.dao.impl.OracleReceitaDAO;
import br.com.fiap.store.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

	public static DespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}

	public static CategoriaDAO getCategoriaDAO() {
		return new OracleCategoriaDAO();
	}

	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

	public static ReceitaDAO getReceitaDAO() {
		return new OracleReceitaDAO();
	}

}