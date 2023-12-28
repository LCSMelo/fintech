package br.com.fiap.store.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Despesa;
import br.com.fiap.store.dao.DespesaDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

public class TesteDespesaDAO {

	public static void main(String[] args) {
		DespesaDAO dao = DAOFactory.getDespesaDAO();

		//Cadastrar um produto
		Despesa despesa = new Despesa(0,"Caderno",20,Calendar.getInstance(), "Outros");
		try {
			dao.cadastrar(despesa);
			System.out.println("Despesa cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar um produto pelo código e atualizar
		despesa = dao.buscar(1);
		despesa.setDescricao("Caderno capa dura");
		despesa.setValor(30);
		try {
			dao.atualizar(despesa);
			System.out.println("Despesa atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Listar os produtos
		List<Despesa> lista = dao.listar();
		for (Despesa item : lista) {
			System.out.println(item.getDescricao() + " " + " " + item.getValor());
		}

		//Remover um produto
		try {
			dao.remover(1);
			System.out.println("Despesa removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
