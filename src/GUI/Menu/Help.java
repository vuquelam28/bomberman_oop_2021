package GUI.Menu;

import GUI.InfoDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Help extends JMenu {

    public Help(Frame _frame)  {
        super("Help");

        /*
            Hướng dẫn cách chơi.
         */
        JMenuItem instructions = new JMenuItem("How To Play");
        instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        instructions.addActionListener(new MenuActionListener(_frame));
        add(instructions);

        /*
         * Credits
         */
        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        about.addActionListener(new MenuActionListener(_frame));
        add(about);

    }

    class MenuActionListener implements ActionListener {

        public Frame _frame;
        public MenuActionListener(Frame frame) {
            _frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equals("How to play")) {
                new InfoDialog((GUI.Frame) _frame, "How to Play",
                        "Movement: W,A,S,D or UP,DOWN, RIGHT, LEFT\nPut Bombs: SPACE, X", JOptionPane.QUESTION_MESSAGE);
            }

            if(e.getActionCommand().equals("About")) {
                new InfoDialog((GUI.Frame) _frame, "About","\n Author: Lam and Dung", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}
