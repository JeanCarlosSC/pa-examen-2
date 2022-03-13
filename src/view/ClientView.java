package view;

import controller.logic.client.ClientController;
import lib.sRAD_java.gui.sComponent.SFrame;

public class ClientView extends SFrame {
    // constant
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 720;
    
    // reference
    private ClientController controller;

    public ClientView(ClientController controller) {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.controller = controller;

        // frame properties
        setLocation(532, 16);
        setMainBar("Reading app", e -> controller.disconnect());
        setVisible(true);
    }
}
