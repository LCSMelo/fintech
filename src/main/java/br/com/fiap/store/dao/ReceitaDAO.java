package br.com.fiap.store.dao;

import java.util.List;

import br.com.fiap.store.bean.Receita;
import br.com.fiap.store.exception.DBException;

public interface ReceitaDAO {

	void cadastrar(Receita receita) throws DBException;
	void atualizar(Receita receita) throws DBException;
	void remover(int codigo) throws DBException;
	Receita buscar(int id);
	List<Receita> listar();
}