package GUI.Menu;

import GUI.CodeDialog;
import GUI.InfoDialog;
import GUI.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Game extends JMenu {

    public Frame frame;

    public Game(Frame frame) {
        super("Game");
        this.frame = frame;

        // Khởi động New Game.
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGame.addActionListener(new MenuActionListener(frame));
        add(newGame);

        // Điểm số.
        JMenuItem scores = new JMenuItem("Top Scores");
        scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        scores.addActionListener(new MenuActionListener(frame));
        add(scores);

        // Codes.
        JMenuItem codes = new JMenuItem("Codes");
        codes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        codes.addActionListener(new MenuActionListener(frame));
        add(codes);
    }

    class MenuActionListener implements ActionListener {

        public Frame _frame;
        public MenuActionListener(Frame frame) {
            _frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equals("New Game")) {
                _frame.newGame();
            }

            if(e.getActionCommand().equals("Top Scores")) {
                new InfoDialog(_frame, "Top Scores", "If i had more time..", JOptionPane.INFORMATION_MESSAGE);
            }

            if(e.getActionCommand().equals("Codes")) {
                new CodeDialog(_frame);
            }

        }
    }

}
