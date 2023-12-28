package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Categoria;
import br.com.fiap.store.dao.CategoriaDAO;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleCategoriaDAO implements CategoriaDAO{

	private Connection conexao;

	@Override
	public List<Categoria> listar() {
		List<Categoria> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_CATEGORIA");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("CD_CATEGORIA");
				String nome = rs.getString("NM_CATEGORIA");
				Categoria categoria = new Categoria(codigo,nome);
				lista.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
}