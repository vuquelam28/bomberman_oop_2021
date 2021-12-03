package Level;

import Main.*;

public class Coordinates {

    public static int pixelToTile(double i) {
        return (int)(i / Game.TILE_SIZE);
    }

    public static int tileToPixel(int i) {
        return i * Game.TILE_SIZE;
    }

    public static int tileToPixel(double i) {
        return (int)(i * Game.TILE_SIZE);
    }
}
