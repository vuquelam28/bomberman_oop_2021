package Entities.MapTiles;

import Entities.AbstractEntity;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;

public class AbstractTile extends AbstractEntity {

    public AbstractTile(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    @Override
    public boolean collide(AbstractEntity e) {
        return false;
    }

    @Override
    public void render(Screen screen) {

        screen.renderEntity(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y), this);
    }

    @Override
    public void update() {}
}
