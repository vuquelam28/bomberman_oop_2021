package Entities;

import java.awt.image.BufferedImage;

public class AbstractEntity {

    public int x, y;
    public int speed;

    // Lưu các ảnh của sprites.
    public BufferedImage up1, up2, up3, down1, down2, down3;
    public BufferedImage left1, left2, left3, right1, right2, right3;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
