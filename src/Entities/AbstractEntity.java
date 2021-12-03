package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.*;
import Level.Coordinates;

public abstract class AbstractEntity implements RenderGraphics {

    protected double x, y;
    protected boolean removed = false;
    protected Sprite sprite;

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract boolean collide(AbstractEntity e);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Lấy tọa độ x của Tile để xét va chạm nhưng trừ đi nửa block để cho chính xác.
    public int getXTile() {
        return Coordinates.pixelToTile(x + sprite.SIZE / 2);
    }

    // Lấy tọa độ y của Tile để xét va chạm nhưng trừ đi nửa block để cho chính xác.
    public int getYTile() {
        return Coordinates.pixelToTile(y - sprite.SIZE / 2); //plus half block
    }
}
