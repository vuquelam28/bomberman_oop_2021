package Entities.MapTiles.Undestroyable;

import Entities.AbstractEntity;
import Entities.Characters.Bomber;
import Entities.MapTiles.AbstractTile;
import Graphics.Sprite;
import Main.Board;

public class PortalTile extends AbstractTile {

    protected Board _board;

    public PortalTile(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        _board = board;
    }

    @Override
    public boolean collide(AbstractEntity e) {

        if(e instanceof Bomber) {

            if(!_board.detectNoEnemies())
                return false;

            if(e.getXTile() == getX() && e.getYTile() == getY()) {
                if(_board.detectNoEnemies())
                    _board.nextLevel();
            }

            return true;
        }

        return false;
    }
}
