package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import GUI.Frame;
import Exceptions.BombermanException;
import Graphics.*;

public class Game extends Canvas {

    // Cài đặt thông số game.
    public static final int TILE_SIZE = 16;
    public static final int WIDTH = TILE_SIZE * (int) (31 / 2), HEIGHT = 13 * TILE_SIZE;
    public static int SCALE_MULTIPLE = 3;

    // Thông số mặc định của người chơi: Số bom đặt, Bán kính bom nổ và Tốc độ di chuyển.
    private static final int BOMB_RATE = 1;
    private static final int BOMB_RADIUS = 1;
    private static final double PLAYER_SPEED = 1.0;

    // Thông số mặc định của game: Thời gian chơi một level, Điểm ghi được và Số mạng.
    public static final int TIME = 200;
    public static final int POINTS = 0;
    public static final int LIVES = 3;

    protected static int SCREEN_DELAY = 3;

    // Thông số riêng của người chơi, có thể thay đổi khi ăn được các power-ups.
    protected static int bombRate = BOMB_RATE;
    protected static int bombRadius = BOMB_RADIUS;
    protected static double playerSpeed = PLAYER_SPEED;

    // Thời gian trong một level.
    protected int screenDelay = SCREEN_DELAY;

    // Biến bắt sự kiện bàn phím.
    private KeyHandler keyHandler;

    // Trạng thái game đang dừng hay đang diễn ra.
    private boolean running = false;
    private boolean paused = true;

    // Sử dụng để render game.
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    // Một số biến khác liên quan tới game: Các board, màn hình game và frame trong game.
    private Board board;
    private Screen screen;
    private Frame frame;

    public Game(Frame frame) throws BombermanException {

        this.frame = frame;
        this.frame.setTitle("Bomberman by Lam and Dung");

        screen = new Screen(WIDTH, HEIGHT);
        keyHandler = new KeyHandler();

        board = new Board(this, keyHandler, screen);
        addKeyListener(keyHandler);
    }

    // Render game, cài đặt để tạo ra FPS lớn nhất có thể tương thích với máy.
    private void renderGame() {

        // Tối ưu render bằng cách vẽ thông qua BufferStrategy.
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        board.render(screen);

        for (int i = 0; i < pixels.length; ++i) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    private void renderScreen() {

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        Graphics g = bs.getDrawGraphics();
        board.drawScreen(g);

        g.dispose();
        bs.show();
    }

    // Update các thông số game: Bảng điểm, bảng thời gian, bàn phím.
    private void update() {

        keyHandler.update();
        board.update();
    }

    // Start game.
    public void start() {

        running = true;

        // Tạo timer delay trong game để mọi thứ di chuyển mượt mà.
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nps = 1000000000.0 / 60.0; // Số nano giây dừng lại mỗi frame để tạo delay. FPS mong muốn là 60.
        double delta = 0;
        int frames = 0, updates = 0;

        requestFocus();

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nps;
            lastTime = now;

            while (delta > 1) {

                update();
                ++updates;
                --delta;
            }

            if (paused) {

                if (screenDelay <= 0) {

                    board.setShow(-1);
                    paused = false;
                }

                renderScreen();
            } else {

                renderGame();
            }

            ++frames;

            // Nếu thời gian dừng mỗi lần bị lớn hơn 1000.
            // thì update các thông số về bảng điểm, số mạng, thời gian game.
            if (System.currentTimeMillis() - timer > 1000) {

                frame.setTime(board.subtractTime());
                frame.setPoints(board.getPoints());
                frame.setLives(board.getLives());

                timer += 1000;

                this.frame.setTitle("Bomberman by Lam and Dung" + " | " + updates + " rate, " + frames + " fps");
                updates = 0;
                frames = 0;

                if (this.board.getShow() == 2) {
                    --screenDelay;
                }
            }
        }
    }

    public static double getPlayerSpeed() {

        return playerSpeed;
    }

    public static int getBombRate() {

        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addPlayerSpeed(double i) {
        playerSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    //screen delay
    public int getScreenDelay() {

        return this.screenDelay;
    }

    public void decreaseScreenDelay() {

        this.screenDelay--;
    }

    public void resetScreenDelay() {

        this.screenDelay = SCREEN_DELAY;
    }

    public KeyHandler getInput() {
        return this.keyHandler;
    }

    public Board getBoard() {
        return this.board;
    }

    public void run() {

        this.running = true;
        this.paused = false;
    }

    public void stop() {

        this.running = false;
    }

    public boolean isRunning() {

        return running;
    }

    public boolean isPaused() {
        
        
        return paused;
    }

    public void pause() {
        
        paused = true;
    }
}