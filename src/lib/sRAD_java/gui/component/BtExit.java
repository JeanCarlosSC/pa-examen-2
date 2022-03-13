package lib.sRAD_java.gui.component;

import lib.sRAD_java.gui.sComponent.SButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BtExit extends SButton implements MouseListener {

    private final static ImageIcon iBtExitOn = new ImageIcon("resource/sRAD/mainBar/btExitOn.png");
    private final static ImageIcon iBtExitOff = new ImageIcon("resource/sRAD/mainBar/btExitOff.png");

    public BtExit(int x, int y) {
        super(x, y, iBtExitOff);
        addMouseListener(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setIcon(iBtExitOff);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setIcon(iBtExitOn);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
