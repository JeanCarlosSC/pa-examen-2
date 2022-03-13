package controller.conection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    // const
    private static final String URLBD = "jdbc:derby://localhost:1527/examen";
    private static final String usuario = "administrador";
    private static final String contrasena = "administrador";
    
    // class field
    private static Connection connection = null;
    private static Driver driver = new org.apache.derby.jdbc.ClientDriver();
    
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URLBD, usuario, contrasena);
        return connection;
    }
    public static void desconectar(){
      connection = null;
   }
}