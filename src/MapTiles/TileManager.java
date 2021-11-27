package MapTiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    char[][] mapTiles;

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTiles = new char[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {

        try {

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/MapTiles/brick.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/MapTiles/wall.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/MapTiles/grass.png")));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/MapTiles/portal.png")));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void loadMap() {

        try {
            InputStream input = getClass().getResourceAsStream("/MapLevels/level1.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

                // Đọc dữ liệu từng dòng.
                String line = reader.readLine();

                while (col < gamePanel.maxScreenColumn) {

                    mapTiles[col][row] = line.charAt(col);
                    ++col;
                }

                if (col == gamePanel.maxScreenColumn) {
                    col = 0;
                    ++row;
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawMap(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

            char tileCharacter = mapTiles[col][row];

            if (tileCharacter == ' ') {
                g2.drawImage(tiles[2].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            } else if (tileCharacter == '#') {
                g2.drawImage(tiles[1].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            } else if (tileCharacter == 'x') {
                g2.drawImage(tiles[3].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            } else if (tileCharacter == '*') {
                g2.drawImage(tiles[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            ++col;
            x += gamePanel.tileSize;

            if (col == gamePanel.maxScreenColumn) {
                col = 0;
                x = 0;
                ++row;
                y += gamePanel.tileSize;
            }
        }
    }
}
