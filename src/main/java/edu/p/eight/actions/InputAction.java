package edu.p.eight.actions;

import javax.swing.*;

public abstract class InputAction extends AbstractAction {
    public KeyStroke keyStroke;
    public String key;

    public InputAction(KeyStroke keyStroke, String key) {
        this.keyStroke = keyStroke;
        this.key = key;
    }
}
