package com.example.bomberman_oop_2021.graphics;

import javafx.scene.image.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Lưu thông tin của một sprite lấy ra từ sprite sheet
 * cùng với các phương thức đi kèm nó.
 */
public class Sprite {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public int spriteSize; // Kích thước của một sprite.
    private int x, y; // Tọa độ (x, y) góc trái trên.
    public int[] pixels; // Các pixels của một sprite.
    protected int actualWidth;
    protected int actualHeight;
    private final SpriteSheet spriteSheet;
    public static SpriteSheet original = new SpriteSheet("../resource/com.../texture/classic.png", 256);
    
    /**
     * Khởi tạo một sprite.
     * @param size kích thước.
     * @param x hoành độ góc phải dưới.
     * @param y tung độ góc phải dưới.
     * @param sheet sprites sheet chứa sprite cần tạo.
     * @param actualWidth Chiều rộng thực sự của sprite.
     * @param actualHeight Chiều cao thực sự của sprite.
     */
    public Sprite(int size, int x, int y, SpriteSheet sheet,
                  int actualWidth, int actualHeight) {
        this.spriteSize = size;
        this.x = x;
        this.y = y;
        this.spriteSheet = sheet;
        pixels = new int[spriteSize * spriteSize];
        this.actualWidth = actualWidth;
        this.actualHeight = actualHeight;
        // loadSprite();
    }

    public int getSpriteSize() {
        return spriteSize;
    }

    public int[] getPixels() {
        return pixels;
    }

    /**
     * Tạo màu cho hình ảnh. Gán mọi pixel của ảnh bằng màu color.
     * @param color màu muốn tô.
     */
    private void setColor(int color) {
        Arrays.fill(pixels, color);
    }

    /**
     * Tải các pixels của một sprite lên mảng pixels.
     */
    private void loadSpriteFromSheet() {
        BufferedImage sprite = original.image.getSubimage(x, y, spriteSize, spriteSize);
        sprite.getRGB(0, 0, spriteSize, spriteSize, pixels, 0, spriteSize);
    }

    /**
     * Di chuyển các sprite gồm có 3 ảnh.
     * @param normal trạng thái bình thường.
     * @param x1 trạng thái 1.
     * @param x2 trạng thái 2.
     * @param animate hiệu ứng.
     * @param time thời gian di chuyển.
     * @return trạng thái sprite.
     */
    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(spriteSize, spriteSize);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < spriteSize; x++) {
            for (int y = 0; y < spriteSize; y++) {
                if (pixels[x + y * spriteSize] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, pixels[x + y * spriteSize]);
                }
            }
        }

        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}
