package GUI.Menu;

import GUI.Frame;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {

    public Menu(Frame frame) {

        add(new Game(frame));
        add(new Options(frame));
        add(new Help(frame) );
    }
}
