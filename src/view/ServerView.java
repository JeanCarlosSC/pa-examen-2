package view;

import lib.sRAD_java.gui.sComponent.SFrame;

public class ServerView extends SFrame {
    public ServerView() {
        super(300, 720);
        setLocation(16,16);
        setMainBar("Server", e -> System.exit(0));
        setVisible(true);
    }
}
