package Entities.MapTiles.Undestroyable;

import Entities.AbstractEntity;
import Entities.MapTiles.AbstractTile;
import Graphics.Sprite;

public class GrassTile extends AbstractTile {

    public GrassTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(AbstractEntity e) {
        return true;
    }
}
