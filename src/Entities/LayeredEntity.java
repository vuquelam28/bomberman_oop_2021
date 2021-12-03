package Entities;

import Entities.MapTiles.DestroyableTiles.DestroyableTile;
import Graphics.Screen;

import java.util.LinkedList;

public class LayeredEntity extends AbstractEntity {

    protected LinkedList<AbstractEntity> _entities = new LinkedList<AbstractEntity>();

    public LayeredEntity(int x, int y, AbstractEntity ... entities) {
        this.x = x;
        this.y = y;

        for (int i = 0; i < entities.length; i++) {

            _entities.add(entities[i]);

            if(i > 1) { //Add to destroyable tiles the bellow sprite for rendering in explosion
                if(entities[i] instanceof DestroyableTile)
                    ((DestroyableTile) entities[i]).addBelowSprite(entities[i - 1].getSprite());
            }
        }
    }

    @Override
    public void update() {

        clearRemoved();
        getTopEntity().update();
    }

    @Override
    public void render(Screen screen) {
        getTopEntity().render(screen);
    }

    public AbstractEntity getTopEntity() {

        return _entities.getLast();
    }

    private void clearRemoved() {

        AbstractEntity top = getTopEntity();

        if(top.isRemoved())  {
            _entities.removeLast();
        }
    }

    public void addBeforeTop(AbstractEntity e) {

        _entities.add(_entities.size() - 1, e);
    }

    @Override
    public boolean collide(AbstractEntity e) {

        return getTopEntity().collide(e);
    }
}
