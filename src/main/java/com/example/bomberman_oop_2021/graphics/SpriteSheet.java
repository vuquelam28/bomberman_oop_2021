package com.example.bomberman_oop_2021.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    private String sheetPath;
    public int SIZE = 0;
    public int[] pixels;
    public BufferedImage image;

    // Khởi tạo Sprite Sheet với kích thước size -> nó sẽ có size * size pixels.
    public SpriteSheet(String path, int size) {
        this.sheetPath = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        loadSpriteSheet();
    }

    private void loadSpriteSheet() {
        try {
            // Lấy đường dẫn của file Sprite Sheet đặt trong folder project.
            URL url = SpriteSheet.class.getResource(sheetPath);
            image = ImageIO.read(url);

            int width = image.getWidth();
            int height = image.getHeight();

            // Tải ảnh lên bằng cách lấy mọi pixel của tấm ảnh, lưu vào một mảng 1 chiều pixels
            // thông qua phương thức getRBG của thư viện Java OpenCV.
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
