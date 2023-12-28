package br.com.fiap.store.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.fiap.store.singleton.ConnectionManager;

public class ConnectionManager {

    private static ConnectionManager connectionManager;

    private ConnectionManager() {
    }

    public static synchronized ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM98286", "230891");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    // Método para testar a conexão
    public static void main(String[] args) {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        try (Connection connection = connectionManager.getConnection()) {
            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
