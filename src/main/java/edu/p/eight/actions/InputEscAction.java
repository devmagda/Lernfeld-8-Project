package edu.p.eight.actions;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Stats;
import edu.p.eight.view.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InputEscAction extends InputAction {

    private GUI gui;
    private boolean toggle;

    public InputEscAction(GUI gui) {
        super(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "escape");
        this.gui = gui;
        this.toggle = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Stats.dead) {
            this.gui.close();
        }
    }
}
