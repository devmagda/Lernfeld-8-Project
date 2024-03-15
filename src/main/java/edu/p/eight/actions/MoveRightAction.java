package edu.p.eight.actions;

import edu.p.eight.model.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveRightAction extends MoveAction {

    public MoveRightAction() {
        super(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "right");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameState.getPlayerCar().moveRight();
    }
}
