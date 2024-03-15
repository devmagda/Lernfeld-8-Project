package edu.p.eight.actions;

import edu.p.eight.model.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveLeftAction extends MoveAction {

    public MoveLeftAction() {
        super(KeyStroke.getKeyStroke(KeyEvent.VK_A,0), "left");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameState.getPlayerCar().moveLeft();
    }
}
