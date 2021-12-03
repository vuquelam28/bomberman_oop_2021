package Entities.Bomb;

import Entities.AbstractEntity;
import Entities.Characters.Mob;
import Graphics.Screen;
import Main.Board;

public class DirectionalExplosion extends AbstractEntity {

    protected Board board;
    protected int direction;
    private final int radius;
    protected int xStart, yStart;
    protected Explosion[] explosion;

    public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
        
        xStart = x;
        yStart = y;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.radius = radius;
        this.board = board;

        explosion = new Explosion[calculatePermitedDistance()];
        createExplosions();
    }

    private void createExplosions() {

        boolean last = false;
        int _x = (int) x;
        int _y = (int) y;

        for (int i = 0; i < explosion.length; i++) {

            last = (i == explosion.length - 1);

            switch (direction) {

                case 0: _y--; break;
                case 1: _x++; break;
                case 2: _y++; break;
                case 3: _x--; break;
            }
            explosion[i] = new Explosion(_x, _y, direction, last, board);
        }
    }

    private int calculatePermitedDistance() {

        int curRadius = 0;
        int _x = (int)x;
        int _y = (int)y;
        while (curRadius < radius) {

            if(direction == 0) {
                _y--;
            }
            if(direction == 1) {
                _x++;
            }
            if(direction == 2) {
                _y++;
            }
            if(direction == 3) {
                _x--;
            }

            AbstractEntity a = board.getEntity(_x, _y, null);

            if(a instanceof Mob) {
                ++curRadius; //explosion has to be below the mob
            }
            if(!a.collide(this)) {// Nếu như đường nổ của bomb chạm phải một vật thể không thể va chạm thì break;
                break;
            }

            ++curRadius;
        }

        return curRadius;
    }

    public Explosion explosionAt(int x, int y) {

        for (int i = 0; i < explosion.length; i++) {
            if(explosion[i].getX() == x && explosion[i].getY() == y)
                return explosion[i];
        }
        return null;
    }

    @Override
    public void update() {}

    @Override
    public void render(Screen screen) {

        for (int i = 0; i < explosion.length; i++) {
            explosion[i].render(screen);
        }
    }

    @Override
    public boolean collide(AbstractEntity e) {
        return true;
    }
}
