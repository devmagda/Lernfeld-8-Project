package edu.p.eight.actions;

import javax.swing.*;

public abstract class MoveAction extends AbstractAction {
    public KeyStroke keyStroke;
    public String key;

    public MoveAction(KeyStroke keyStroke, String key) {
        this.keyStroke = keyStroke;
        this.key = key;
    }
}
