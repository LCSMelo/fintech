package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Receita;
import br.com.fiap.store.dao.ReceitaDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;


public class OracleReceitaDAO implements ReceitaDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Receita receita) throws DBException {
	    PreparedStatement stmt = null;

	    try {
	        conexao = ConnectionManager.getInstance().getConnection();
	        String sql = "INSERT INTO TB_RECEITA (CD_RECEITA, DESC_RECEITA, VL_RECEITA, DT_RECEITA, CAT_RECEITA) "
	                + "VALUES (SQ_TB_RECEITA.NEXTVAL, ?, ?, ?, ?)";
	        stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, receita.getDescricao());
	        stmt.setDouble(2, receita.getValor());
	        java.sql.Date data = new java.sql.Date(receita.getDataReceita().getTimeInMillis());
	        stmt.setDate(3, data);
	        stmt.setString(4, receita.getCategoria());

	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("Erro ao cadastrar.");
	    } finally {
	        try {
	            stmt.close();
	            conexao.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	@Override
	public void atualizar(Receita receita) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_RECEITA SET DESC_RECEITA = ?, VL_RECEITA = ?, DT_RECEITA = ?, CAT_RECEITA = ? "
					+ "WHERE CD_RECEITA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, receita.getDescricao());
			stmt.setDouble(2, receita.getValor());
			java.sql.Date data = new java.sql.Date(receita.getDataReceita().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, receita.getCategoria());
			stmt.setInt(5, receita.getCodigo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(int codigo) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM TB_RECEITA WHERE CD_RECEITA = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigo);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Erro ao remover.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	@Override
	public Receita buscar(int id) {
		Receita receita = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_RECEITA WHERE CD_RECEITA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo = rs.getInt("CD_RECEITA");
				String descricao = rs.getString("DESC_RECEITA");
				double valor = rs.getDouble("VL_RECEITA");
				java.sql.Date data = rs.getDate("DT_RECEITA");
				Calendar dataReceita = Calendar.getInstance();
				dataReceita.setTimeInMillis(data.getTime());
				String categoria = rs.getString("CAT_RECEITA");

				receita = new Receita(codigo, descricao, valor, dataReceita, categoria);
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
		return receita;
	}

	@Override
	public List<Receita> listar() {
		List<Receita> lista = new ArrayList<Receita>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_RECEITA");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("CD_RECEITA");
				String descricao = rs.getString("DESC_RECEITA");
				double valor = rs.getDouble("VL_RECEITA");
				java.sql.Date data = rs.getDate("DT_RECEITA");
				Calendar dataReceita = Calendar.getInstance();
				dataReceita.setTimeInMillis(data.getTime());
				String categoria = rs.getString("CAT_RECEITA");

				Receita receita = new Receita(codigo, descricao, valor, dataReceita, categoria);
				lista.add(receita);
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