package com.example.bomberman_oop_2021.graphics;

import static com.example.bomberman_oop_2021.graphics.Sprite.DEFAULT_SIZE;
import static com.example.bomberman_oop_2021.graphics.Sprite.original;

/**
 * Khởi tạo thông tin của các sprites trong SpriteSheet.
 */
public class SpriteInfo {
    // Bản đồ.
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, original, 16, 16);
    public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0,original, 16, 16);
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, original, 16, 16);
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, original, 14, 14);

    // Main Player
    public static Sprite playerUp = new Sprite(DEFAULT_SIZE, 0, 0, original, 12, 16);
    public static Sprite playerDown = new Sprite(DEFAULT_SIZE, 2, 0, original, 12, 15);
    public static Sprite playerLeft = new Sprite(DEFAULT_SIZE, 3, 0, original, 10, 15);
    public static Sprite playerRight = new Sprite(DEFAULT_SIZE, 1, 0, original, 10, 16);

    public static Sprite playerUp1 = new Sprite(DEFAULT_SIZE, 0, 1, original, 12, 16);
    public static Sprite playerUp2 = new Sprite(DEFAULT_SIZE, 0, 2, original, 12, 15);

    public static Sprite playerDown1 = new Sprite(DEFAULT_SIZE, 2, 1, original, 12, 15);
    public static Sprite playerDown2 = new Sprite(DEFAULT_SIZE, 2, 2, original, 12, 16);

    public static Sprite playerLeft1 = new Sprite(DEFAULT_SIZE, 3, 1, original, 11, 16);
    public static Sprite playerLeft2 = new Sprite(DEFAULT_SIZE, 3, 2, original, 12 ,16);

    public static Sprite playerRight1 = new Sprite(DEFAULT_SIZE, 1, 1, original, 11, 16);
    public static Sprite playerRight2 = new Sprite(DEFAULT_SIZE, 1, 2, original, 12, 16);

    public static Sprite playerDead1 = new Sprite(DEFAULT_SIZE, 4, 2, original, 14, 16);
    public static Sprite playerDead2 = new Sprite(DEFAULT_SIZE, 5, 2, original, 13, 15);
    public static Sprite playerDead3 = new Sprite(DEFAULT_SIZE, 6, 2, original, 16, 16);

    // Ballom Enemy.
    public static Sprite ballomLeft1 = new Sprite(DEFAULT_SIZE, 9, 0, original, 16, 16);
    public static Sprite ballomLeft2 = new Sprite(DEFAULT_SIZE, 9, 1, original, 16, 16);
    public static Sprite ballomLeft3 = new Sprite(DEFAULT_SIZE, 9, 2, original, 16, 16);

    public static Sprite ballomRight1 = new Sprite(DEFAULT_SIZE, 10, 0, original, 16, 16);
    public static Sprite balloom_right2 = new Sprite(DEFAULT_SIZE, 10, 1, original, 16, 16);
    public static Sprite balloom_right3 = new Sprite(DEFAULT_SIZE, 10, 2, original, 16, 16);

    public static Sprite ballomDead = new Sprite(DEFAULT_SIZE, 9, 3, original, 16, 16);

    // Oneal enemy.
    public static Sprite onealLeft1 = new Sprite(DEFAULT_SIZE, 11, 0, original, 16, 16);
    public static Sprite onealLeft2 = new Sprite(DEFAULT_SIZE, 11, 1, original, 16, 16);
    public static Sprite onealLeft3 = new Sprite(DEFAULT_SIZE, 11, 2, original, 16, 16);

    public static Sprite onealRight1 = new Sprite(DEFAULT_SIZE, 12, 0, original, 16, 16);
    public static Sprite onealRight2 = new Sprite(DEFAULT_SIZE, 12, 1, original, 16, 16);
    public static Sprite onealRight3 = new Sprite(DEFAULT_SIZE, 12, 2, original, 16, 16);

    public static Sprite onealDead = new Sprite(DEFAULT_SIZE, 11, 3, original, 16, 16);

    // Doll Enemy.
    public static Sprite dollLeft1 = new Sprite(DEFAULT_SIZE, 13, 0, original, 16, 16);
    public static Sprite dollLeft2 = new Sprite(DEFAULT_SIZE, 13, 1, original, 16, 16);
    public static Sprite dollLeft3 = new Sprite(DEFAULT_SIZE, 13, 2, original, 16, 16);

    public static Sprite dollRight1 = new Sprite(DEFAULT_SIZE, 14, 0, original, 16, 16);
    public static Sprite dollRight2 = new Sprite(DEFAULT_SIZE, 14, 1, original, 16, 16);
    public static Sprite dollRight3 = new Sprite(DEFAULT_SIZE, 14, 2, original, 16, 16);

    public static Sprite dollDead = new Sprite(DEFAULT_SIZE, 13, 3, original, 16, 16);

    // Minvo Enemy.
    public static Sprite minvoLeft1 = new Sprite(DEFAULT_SIZE, 8, 5, original, 16, 16);
    public static Sprite minvoLeft2 = new Sprite(DEFAULT_SIZE, 8, 6, original, 16, 16);
    public static Sprite minvoLeft3 = new Sprite(DEFAULT_SIZE, 8, 7, original, 16, 16);

    public static Sprite minvoRight1 = new Sprite(DEFAULT_SIZE, 9, 5, original, 16, 16);
    public static Sprite minvoRight2 = new Sprite(DEFAULT_SIZE, 9, 6, original, 16, 16);
    public static Sprite minvoRight3 = new Sprite(DEFAULT_SIZE, 9, 7, original, 16, 16);

    public static Sprite minvoDead = new Sprite(DEFAULT_SIZE, 8, 8, original, 16, 16);

    // Kondoria enemy.
    public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 10, 5, original, 16, 16);
    public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 10, 6, original, 16, 16);
    public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 10, 7, original, 16, 16);

    public static Sprite kondoriaRight1 = new Sprite(DEFAULT_SIZE, 11, 5, original, 16, 16);
    public static Sprite kondoriaRight2 = new Sprite(DEFAULT_SIZE, 11, 6, original, 16, 16);
    public static Sprite kondoriaRight3 = new Sprite(DEFAULT_SIZE, 11, 7, original, 16, 16);

    public static Sprite kondoriaDead= new Sprite(DEFAULT_SIZE, 10, 8, original, 16, 16);

    // Dead for all enemy.
    public static Sprite mobDead1 = new Sprite(DEFAULT_SIZE, 15, 0, original, 16, 16);
    public static Sprite mobDead2 = new Sprite(DEFAULT_SIZE, 15, 1, original, 16, 16);
    public static Sprite mobDead3 = new Sprite(DEFAULT_SIZE, 15, 2, original, 16, 16);

    // Bombs
    public static Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 3, original, 15, 15);
    public static Sprite bomb1 = new Sprite(DEFAULT_SIZE, 1, 3, original, 13, 15);
    public static Sprite bomb2 = new Sprite(DEFAULT_SIZE, 2, 3, original, 12, 14);

    // FlameSegment of Bombs.
    public static Sprite bombExploded = new Sprite(DEFAULT_SIZE, 0, 4, original, 16, 16);
    public static Sprite bombExploded1 = new Sprite(DEFAULT_SIZE, 0, 5, original, 16, 16);
    public static Sprite bombExploded2 = new Sprite(DEFAULT_SIZE, 0, 6, original, 16, 16);

    public static Sprite explosionVertical = new Sprite(DEFAULT_SIZE, 1, 5, original, 16, 16);
    public static Sprite explosionVertical1 = new Sprite(DEFAULT_SIZE, 2, 5, original, 16, 16);
    public static Sprite explosionVertical2 = new Sprite(DEFAULT_SIZE, 3, 5, original, 16, 16);

    public static Sprite explosionHorizontal = new Sprite(DEFAULT_SIZE, 1, 7, original, 16, 16);
    public static Sprite explosionHorizontal1 = new Sprite(DEFAULT_SIZE, 1, 8, original, 16, 16);
    public static Sprite explosionHorizontal2 = new Sprite(DEFAULT_SIZE, 1, 9, original, 16, 16);

    public static Sprite explosionHorizontalLeftLast = new Sprite(DEFAULT_SIZE, 0, 7, original, 16, 16);
    public static Sprite explosionHorizontalLeftLast1 = new Sprite(DEFAULT_SIZE, 0, 8, original, 16, 16);
    public static Sprite explosionHorizontalLeftLast2 = new Sprite(DEFAULT_SIZE, 0, 9, original, 16, 16);

    public static Sprite explosionHorizontalRightLast = new Sprite(DEFAULT_SIZE, 2, 7, original, 16, 16);
    public static Sprite explosionHorizontalRightLast1 = new Sprite(DEFAULT_SIZE, 2, 8, original, 16, 16);
    public static Sprite explosionHorizontalRightLast2 = new Sprite(DEFAULT_SIZE, 2, 9, original, 16, 16);

    public static Sprite explosionVerticalTopLast = new Sprite(DEFAULT_SIZE, 1, 4, original, 16, 16);
    public static Sprite explosionVerticalTopLast1 = new Sprite(DEFAULT_SIZE, 2, 4, original, 16, 16);
    public static Sprite explosionVerticalTopLast2 = new Sprite(DEFAULT_SIZE, 3, 4, original, 16, 16);

    public static Sprite explosionVerticalDownLast = new Sprite(DEFAULT_SIZE, 1, 6, original, 16, 16);
    public static Sprite explosionVerticalDownLast1 = new Sprite(DEFAULT_SIZE, 2, 6, original, 16, 16);
    public static Sprite explosionVerticalDownLast2 = new Sprite(DEFAULT_SIZE, 3, 6, original, 16, 16);

    // Brick exploded
    public static Sprite brickExploded = new Sprite(DEFAULT_SIZE, 7, 1, original, 16, 16);
    public static Sprite brickExploded1 = new Sprite(DEFAULT_SIZE, 7, 2, original, 16, 16);
    public static Sprite brickExploded2 = new Sprite(DEFAULT_SIZE, 7, 3, original, 16, 16);

    // Power-ups
    public static Sprite powerUpBombs = new Sprite(DEFAULT_SIZE, 0, 10, original, 16, 16);
    public static Sprite powerUpFlames = new Sprite(DEFAULT_SIZE, 1, 10, original, 16, 16);
    public static Sprite powerUpSpeed= new Sprite(DEFAULT_SIZE, 2, 10, original, 16, 16);
    public static Sprite powerUpWallPass = new Sprite(DEFAULT_SIZE, 3, 10, original, 16, 16);
    public static Sprite powerUpDetonator = new Sprite(DEFAULT_SIZE, 4, 10, original, 16, 16);
    public static Sprite powerUpBombPass = new Sprite(DEFAULT_SIZE, 5, 10, original, 16, 16);
    public static Sprite powerUpFlamePass = new Sprite(DEFAULT_SIZE, 6, 10, original, 16, 16);
}
