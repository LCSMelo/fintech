package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.store.bean.Usuario;
import br.com.fiap.store.dao.UsuarioDAO;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {

    @Override
    public boolean validarUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            if (conexao != null) {
                stmt = conexao.prepareStatement("SELECT * FROM TB_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
                stmt.setString(1, usuario.getEmail());
                stmt.setString(2, usuario.getSenha());

                rs = stmt.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
