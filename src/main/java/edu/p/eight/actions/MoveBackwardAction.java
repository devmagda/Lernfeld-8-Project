package edu.p.eight.actions;

import edu.p.eight.exceptions.EndOfLaneException;
import edu.p.eight.model.GameState;
import edu.p.eight.model.Stats;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveBackwardAction extends MoveAction {

    public MoveBackwardAction() {
        super(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "backward");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float distanceBefore = GameState.getPlayerCar().getDistance();
        try {
            GameState.getPlayerCar().backward(Stats.speed * -0.33f);
        } catch (EndOfLaneException ex) {
            GameState.getPlayerCar().setDistance(distanceBefore);
        }
    }
}
