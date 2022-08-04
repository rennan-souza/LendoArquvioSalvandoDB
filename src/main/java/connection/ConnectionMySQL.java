package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQL {

    private static String banco = "jdbc:mysql://localhost:3306/LendoArquvioSalvandoDB?autoReconnect=true";
    private static String user = "root";
    private static String senha = "docker";
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    static {
        conectar();
    }

    public ConnectionMySQL() {
        conectar();
    }

    private static void conectar() {
        try {

            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(banco, user, senha);
                connection.setAutoCommit(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
