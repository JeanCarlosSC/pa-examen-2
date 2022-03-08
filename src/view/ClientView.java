package view;

import lib.sRAD_java.gui.sComponent.SFrame;

public class ClientView extends SFrame {
    public ClientView() {
        super(1000, 720);
        setLocation(332, 16);
        setMainBar("Reading app", e -> setVisible(false));
        setVisible(true);
    }
}
