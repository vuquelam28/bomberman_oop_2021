package Main;

import Entities.Bomber;
import MapTiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Cài đặt một số thuộc tính cho màn hình game.
    private final int originalTileSize = 16; // Kích thước cho một tile của background.
    private final int scaleMultiple = 2;
    public final int tileSize = originalTileSize * scaleMultiple; // 48 * 48 tile.
    public final int maxScreenColumn = 25;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenColumn; // Chiều rộng màn hình 20 * 48 pixels.
    public final int screenHeight = tileSize * maxScreenRow; // Chiều cao màn hình 15 * 48 pixels.

    // FPS game.
    private int FPS = 60;

    // Bắt sự kiện bàn phím.
    KeyHandler keyHandler = new KeyHandler();
    // Tạo một đối tượng Thread để giữ cho chương trình game chạy liên tục.
    Thread gameThread;
    // Tạo người chơi chính.
    Bomber bomber = new Bomber(this, keyHandler);
    // Đối tượng quản lý bản đồ.
    TileManager tileManager = new TileManager(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Vẽ lại màn hình sau mỗi khoảng drawInterval.
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // Trong vòng lặp này, thực hiện hai công việc:
        // 1. Update thông tin về các thực thể của game.
        // 2. Vẽ lại bản đồ khi có thông tin mới.
        while (gameThread != null) {

            long currentTime = System.nanoTime();
            long currentTime2 = System.currentTimeMillis();

            // Update thông tin thực thể game.
            update();
            // Vẽ lại màn hình.
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; // Chuyển thời gian ngừng giữa hai lần vẽ về milisecond.

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                // Tạm dừng thread trong một khoảng thời gian ngắn.
                Thread.sleep((long) remainingTime);

                // Lần vẽ tiếp theo.
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm dùng để update thông tin của hình ảnh.
    public void update() {

        bomber.update();
    }

    // Hàm để vẽ ảnh lên màn hình.
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.drawMap(g2);
        bomber.drawBomber(g2);

        g2.dispose();
    }
}
