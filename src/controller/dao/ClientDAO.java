package controller.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Client;
import controller.conection.BDConnection;
import java.sql.SQLException;

public class ClientDAO {
    private static Client client;
    
    public static boolean validate(String userName, String password) {
        String consult = "SELECT * FROM ADMINISTRADOR.CLIENTS WHERE USER_NAME='"+userName+"' AND PASSWORD='"+password+"'";
        try {
            Connection connection = (Connection) BDConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consult);
            if (resultSet.next()) {
                client = new Client(resultSet.getString("USER_NAME"), resultSet.getString("PASSWORD"));
                return client.getUserName().equals(String.format("%-20s", userName)) && client.getPassword().equals(String.format("%-10s", password));
            }
            else {
                System.out.println("An unregistered client has tried to connect."); // debugging
            }
            statement.close();
            BDConnection.desconectar();
        } catch (SQLException ex) {
            // debugging
            System.out.println("The consult couldn't realize correctly.");
            ex.printStackTrace();
        }
        return false;
    }
}
