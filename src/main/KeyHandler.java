package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean[] keys = new boolean[1000]; // Mảng lưu trạng thái bấm - nhả của các phím.
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    public void update() {

        upPressed = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        downPressed = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        leftPressed = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        rightPressed = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        spacePressed = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        keys[e.getKeyCode()] = false;
    }
}
