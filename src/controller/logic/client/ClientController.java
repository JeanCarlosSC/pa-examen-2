package controller.logic.client;

import controller.logic.server.ServerController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

import static javax.swing.JOptionPane.showInputDialog;
import view.Ventana;

public class ClientController implements Runnable{
    
    // class field
    private static final int PORT = 5050;
    
    public ClientController() {
        String serverAddress = showInputDialog("Server address");
        String userName = JOptionPane.showInputDialog("User name");
        String password = JOptionPane.showInputDialog("Password");
        try {
            Socket socket = new Socket(serverAddress, 5000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("VALIDATE");
            dataOutputStream.writeUTF(userName);
            dataOutputStream.writeUTF(password);
            dataOutputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor: "+ex.getMessage());
        }
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Listening server responses.");
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            Socket socket;
            while(true) {
                socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String response = dataInputStream.readUTF();
                if(response.equals("true")) {
                    System.out.println("The client is registered.");
                    JOptionPane.showMessageDialog(null, dataInputStream.readUTF());
                    new Ventana(this, dataInputStream.readUTF());
                }
                else if(response.equals("false")) {
                    JOptionPane.showMessageDialog(null, "The client is not registered.");
                }
                else {
                    JOptionPane.showMessageDialog(null, response);
                }
            }
        } catch (Exception e) {
        }
    }
    
    public void disconnect(Ventana view) {
        try {
            Socket socket = new Socket(ServerController.defaultIP, 5000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("DISCONNECT");
            dataOutputStream.writeUTF(view.getUserName());
            dataOutputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido desconectar del servidor: "+ex.getMessage());
        }
        view.setVisible(false);
    }
    
    public void read(String text) {
        try {
            Socket socket = new Socket(ServerController.defaultIP, 5000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("READ");
            dataOutputStream.writeUTF(text);
            dataOutputStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo leer: "+ex.getMessage());
        }
    }
}
