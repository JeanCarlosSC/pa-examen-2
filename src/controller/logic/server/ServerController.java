package controller.logic.server;

import controller.dao.ClientDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import view.ServerView;

public class ServerController implements Runnable{
    // const
    private static final String defaultIP = "192.168.0.106";
    
    // reference
    private ServerView view;
    
    // field
    private ServerSocket serverSocket;
    private Socket socket;

    public ServerController(ServerView view) {
        this.view = view;
        Thread thread = new Thread(this); // receive data from clients
        thread.start();
    }

    @Override
    public void run() {
        view.printMessage("Waiting for a connection");
        try {
            serverSocket = new ServerSocket(5000);
            while(true) {
                socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String message = dataInputStream.readUTF();
                if(message.equals("VALIDATE") ) {
                    String userName = dataInputStream.readUTF();
                    String password = dataInputStream.readUTF();
                    boolean valid = ClientDAO.validate(userName, password);
                    Socket socketResponse = new Socket(defaultIP, 5050); 
                    DataOutputStream dataOutputStream = new DataOutputStream(socketResponse.getOutputStream());
                    if(valid) {
                        view.printMessage(userName+" has been connected from: localhost");
                        view.printMessage("Waiting for a new connection");
                    }
                    else {
                        view.printMessage("An unregistered client has tried to connect");
                    }
                    dataOutputStream.writeUTF(valid+"");
                    if(valid) {
                        dataOutputStream.writeUTF("Welcome "+userName+"!");
                    }
                    socketResponse.close();
                }
                else {
                    System.out.println("invalid petition code"); // server debugging
                }
            }
        } catch (IOException ex) {
            view.printMessage(ex.getMessage());
            ex.printStackTrace();
        }
    }
}