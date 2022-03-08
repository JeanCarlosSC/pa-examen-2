package lib.sRAD_java.gui.component;

import lib.sRAD_java.gui.component.Theme;
import lib.sRAD_java.gui.sComponent.SFrame;

public class VentanaEmergente extends SFrame {
    private final SFrame frame;

    public VentanaEmergente(SFrame frame, int width, int height) {
        this.frame = frame;
        setProperties(width, height, Theme.veBg);
    }

    public void lanzar() {
        frame.setEnabled(false);
        setVisible(true);
    }

    public void cerrar() {
        frame.setEnabled(true);
        this.setVisible(false);
    }
}
