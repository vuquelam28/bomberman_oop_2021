package Entities.Bomb;

import Entities.AbstractEntity;
import Entities.Characters.Mob;
import Graphics.Screen;
import Graphics.Sprite;
import Main.Board;

public class Explosion extends AbstractEntity {

    protected boolean last = false; // Kiểm tra xem bom còn cháy không.
    protected Board board;
    protected Sprite sprite1, sprite2;

    public Explosion(int x, int y, int direction, boolean last, Board board) {

        this.x = x;
        this.y = y;
        this.last = last;
        this.board = board;

        switch (direction) {

            case 0:
                if (!last) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_top_last2;
                }

                break;

            case 1:
                if (!last) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_right_last2;
                }

                break;
                
            case 2:
                if (!last) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_down_last2;
                }

                break;

            case 3:
                if (!last) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_left_last2;
                }

                break;
        }
    }

    @Override
    public void render(Screen screen) {
        int xt = (int) x << 4;
        int yt = (int) y << 4;

        screen.renderEntity(xt, yt , this);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(AbstractEntity e) {

        if (e instanceof Mob) {

            ((Mob) e).kill();
        }

        return true;
    }
}
