package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CodeDialog implements WindowListener, ActionListener {

    private Frame frame;
    private JFrame dialog;
    private JTextField code;
    private boolean valid = false;

    public CodeDialog(Frame f) {

        frame = f;

        dialog = new JFrame("Enter a Valid Code");
        final JButton button = new JButton("Load!");
        button.addActionListener(this);

        JPanel pane = new JPanel(new BorderLayout());
        code = new JTextField("code");

        pane.add(new JLabel("Code: "), BorderLayout.WEST);
        pane.add(code, BorderLayout.CENTER);
        pane.add(button, BorderLayout.PAGE_END);

        dialog.getContentPane().add(pane);
        dialog.setLocationRelativeTo(f);
        dialog.setSize(400, 100);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        frame.pauseGame();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        if(valid == false)
            frame.resumeGame();
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String _code = code.getText();

        if (frame.validCode(_code)) {

            frame.changeLevelByCode(_code);
            valid = true;
            dialog.dispose();
        } else {

            JOptionPane.showMessageDialog(frame, "That code isn't correct boy..", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
