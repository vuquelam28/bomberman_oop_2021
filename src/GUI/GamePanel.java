package GUI;

import Exceptions.BombermanException;
import Main.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE_MULTIPLE, Game.HEIGHT * Game.SCALE_MULTIPLE));

        try {
            game = new Game(frame);

            add(game);

            game.setVisible(true);

        } catch (BombermanException e) {
            e.printStackTrace();
            System.exit(0);
        }
        setVisible(true);
        setFocusable(true);

    }

    public void changeSize() {

        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE_MULTIPLE, Game.HEIGHT * Game.SCALE_MULTIPLE));
        revalidate();
        repaint();
    }

    public Game getGame() {
        return game;
    }
}
