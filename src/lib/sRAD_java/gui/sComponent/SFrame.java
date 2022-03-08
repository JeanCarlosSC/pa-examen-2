package lib.sRAD_java.gui.sComponent;

import lib.sRAD_java.gui.component.MainBar;
import lib.sRAD_java.gui.component.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static lib.sRAD_java.gui.component.Resource.blackBorderTransparent;

public class SFrame extends JFrame {

    /**
     * javax.swing.JFrame constructor
     */
    public SFrame() {
        super();
    }

    /**
     * Set a decorated frame with a width and height defined
     * @param width width of frame
     * @param height height of frame
     */
    public SFrame(int width, int height) {
        setProperties(width, height, Theme.fBg, true, blackBorderTransparent, null, true, EXIT_ON_CLOSE, null);
    }

    /**
     * Default properties of frame
     */
    public void setProperties() {
        setProperties(1280, 720, Theme.fBg, true, blackBorderTransparent, null, true, EXIT_ON_CLOSE, null);
    }

    /**
     * Change properties of frame in one line
     * @param width frame width
     * @param height frame height
     * @param background frame background color
     */
    public void setProperties(int width, int height, Color background) {
        setSize(width, height);
        getContentPane().setBackground(background);
    }

    /**
     * Change properties of frame in one line
     * @param width width of frame
     * @param height height of frame
     * @param background background color of frame
     * @param undecorated undecorated property of frame
     * @param border border of frame
     * @param relativeLocation relative location of frame
     */
    public void setProperties(int width, int height, Color background, Boolean undecorated, Border border, Component relativeLocation) {
        setProperties(width, height, background);
        setUndecorated(undecorated);
        rootPane.setBorder(border);
        setLocationRelativeTo(relativeLocation);
    }

    public void setProperties(int width, int height, Color background, Boolean undecorated, Border border, Component relativeLocation,
                              Boolean visible, int defaultCloseOperation, LayoutManager layout) {
        setProperties(width, height, background, undecorated, border, relativeLocation);
        setDefaultCloseOperation(defaultCloseOperation);
        setLayout(layout);
        setVisible(visible);
    }

    public void setMainBar(String title, ActionListener exitAction) {
        setMainBar(title, "resources/sRAD/exampleLogo.png", exitAction);
    }

    public void setMainBar(String title, String pathLogo, ActionListener exitAction) {
        MainBar mainBar = new MainBar(this, exitAction);
        mainBar.setTitle(title);
        mainBar.setLogo(new ImageIcon(pathLogo));
        add(mainBar);
        repaint();
    }

}
