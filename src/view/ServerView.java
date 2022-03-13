package view;

import lib.sRAD_java.gui.sComponent.SFrame;
import lib.sRAD_java.gui.sComponent.SScrollPane;
import lib.sRAD_java.gui.sComponent.STextArea;

public class ServerView extends SFrame {
    // constant
    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 720;

    // component
    private STextArea taLog;

    public ServerView() {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);

        // components
        taLog = new STextArea(0, 0, SCREEN_WIDTH-32, SCREEN_HEIGHT-32);
        taLog.setEditable(false);

        SScrollPane scrollPane = new SScrollPane(16, 44, SCREEN_WIDTH-32, SCREEN_HEIGHT-64, taLog);
        add(scrollPane);

        // frame properties
        setLocation(16,16);
        setMainBar("Server", e -> System.exit(0));
        setVisible(true);
    }

    public void printMessage(String message) {
        taLog.append(message + ".\n");
    }
}
