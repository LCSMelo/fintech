package br.com.fiap.store.teste;

import java.util.List;

import br.com.fiap.store.bean.Categoria;
import br.com.fiap.store.dao.CategoriaDAO;
import br.com.fiap.store.factory.DAOFactory;

public class TesteCategoriaDAO {

	public static void main(String[] args) {
		CategoriaDAO dao = DAOFactory.getCategoriaDAO();

		List<Categoria> lista = dao.listar();
		for (Categoria categoria : lista) {
			System.out.println(categoria.getCodigo() + " " + categoria.getNome());
		}
	}

}