package controller.logic.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import view.ClientView;
import java.net.*;
import javax.swing.*;

import static javax.swing.JOptionPane.showInputDialog;

public class ClientController implements Runnable{
    
    // field
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
                String valid = dataInputStream.readUTF();
                if(valid.equals("true")) {
                    System.out.println("The client is registered.");
                    JOptionPane.showMessageDialog(null, dataInputStream.readUTF());
                    new ClientView(this);
                }
                else {
                    JOptionPane.showMessageDialog(null, "The client is not registered.");
                }
            }
        } catch (Exception e) {
        }
    }
}
