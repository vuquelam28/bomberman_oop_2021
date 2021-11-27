package Entities;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomber extends AbstractEntity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    private String direction;

    public Bomber(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultInformation();
        getBomberImage();
    }

    public void setDefaultInformation() {

        x = 0;
        y = 0;
        speed = 4;
        direction = "right";
    }

    public void getBomberImage() {

        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_up.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_up_1.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_down.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_down_1.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_left.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_left_1.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_right.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_right_1.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Bomber/player_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update hình ảnh và vị trí của bomber khi di chuyển.
    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed
            || keyHandler.rightPressed || keyHandler.leftPressed) {

            // Update sprite cho bomber theo mỗi 10 lần update (10 frames/lần update).
            ++spriteCounter;
            if (spriteCounter > 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 3;
                } else if (spriteNumber == 3) {
                    spriteNumber = 1;
                }

                spriteCounter = 0;
            }
        }

        // Cập nhật tốc độ và hướng đi cho bomber tương ứng với phím bấm mũi tên.
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
    }

    // Vẽ hình ảnh bomber lên vị trí tương ứng của màn hình.
    public void drawBomber(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                if (spriteNumber == 3) {
                    image = up3;
                }

                break;

            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                if (spriteNumber == 3) {
                    image = down3;
                }

                break;

            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                if (spriteNumber == 3) {
                    image = left3;
                }

                break;

            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                if (spriteNumber == 3) {
                    image = right3;
                }

                break;
        }

        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
