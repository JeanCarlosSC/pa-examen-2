package controller.control;

import view.ServerView;

public class ServerController {
    // reference
    private ServerView view;

    public ServerController(ServerView view) {
        this.view = view;
        view.printMessage("Waiting for a connection");
    }
}
