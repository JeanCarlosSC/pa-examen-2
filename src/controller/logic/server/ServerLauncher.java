package controller.logic.server;

import controller.logic.server.ServerController;
import view.ServerView;

public class ServerLauncher {
    public static void main(String[] args) {
        ServerView serverView = new ServerView();
        new ServerController(serverView);
    }
}
