package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import br.com.fiap.store.bean.Categoria;
import br.com.fiap.store.bean.Despesa;
import br.com.fiap.store.dao.DespesaDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;


public class OracleDespesaDAO implements DespesaDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Despesa despesa) throws DBException {
	    PreparedStatement stmt = null;

	    try {
	        conexao = ConnectionManager.getInstance().getConnection();
	        String sql = "INSERT INTO TB_DESPESA (CD_GASTO, DESC_GASTO, VL_GASTO, DT_GASTO, CAT_GASTO) "
	                + "VALUES (SQ_TB_DESPESA.NEXTVAL, ?, ?, ?, ?)";
	        stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, despesa.getDescricao());
	        stmt.setDouble(2, despesa.getValor());
	        java.sql.Date data = new java.sql.Date(despesa.getDataDespesa().getTimeInMillis());
	        stmt.setDate(3, data);
	        stmt.setString(4, despesa.getCategoria()); // Supondo que Categoria seja uma String

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
	public void atualizar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_DESPESA SET DESC_GASTO = ?, VL_GASTO = ?, DT_GASTO = ?, CAT_GASTO = ? "
					+ "WHERE CD_GASTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getDescricao());
			stmt.setDouble(2, despesa.getValor());
			java.sql.Date data = new java.sql.Date(despesa.getDataDespesa().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, despesa.getCategoria());
			stmt.setInt(5, despesa.getCodigo());

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
				String sql = "DELETE FROM TB_DESPESA WHERE CD_GASTO = ?";
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
	public Despesa buscar(int id) {
		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_DESPESA WHERE TB_DESPESA.CD_GASTO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo = rs.getInt("CD_GASTO");
				String descricao = rs.getString("DESC_GASTO");
				double valor = rs.getDouble("VL_GASTO");
				java.sql.Date data = rs.getDate("DT_GASTO");
				Calendar dataDespesa = Calendar.getInstance();
				dataDespesa.setTimeInMillis(data.getTime());
				String categoria = rs.getString("CAT_GASTO");

				despesa = new Despesa(codigo, descricao, valor, dataDespesa, categoria);
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
		return despesa;
	}

	@Override
	public List<Despesa> listar() {
		List<Despesa> lista = new ArrayList<Despesa>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_DESPESA");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("CD_GASTO");
				String descricao = rs.getString("DESC_GASTO");
				double valor = rs.getDouble("VL_GASTO");
				java.sql.Date data = rs.getDate("DT_GASTO");
				Calendar dataDespesa = Calendar.getInstance();
				dataDespesa.setTimeInMillis(data.getTime());
				String categoria = rs.getString("CAT_GASTO");

				Despesa despesa = new Despesa(codigo, descricao, valor, dataDespesa, categoria);
				lista.add(despesa);
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