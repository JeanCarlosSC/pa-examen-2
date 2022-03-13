package view;

import lib.sRAD_java.gui.sComponent.SFrame;

public class ClientView extends SFrame {
    // constant
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 720;

    public ClientView() {
        super(SCREEN_WIDTH, SCREEN_HEIGHT);

        // frame properties
        setLocation(532, 16);
        setMainBar("Reading app", e -> setVisible(false));
        setVisible(true);
    }
}
