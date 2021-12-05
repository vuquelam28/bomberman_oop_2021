package Entities.Bomb;

import Entities.AbstractEntity;
import Entities.AnimatedEntity;
import Entities.Characters.Bomber;
import Entities.Characters.Mob;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;
import Main.Board;
import Main.Game;

public class Bomb extends AnimatedEntity {

    // Cài đặt thông số cho bomb.
    protected double timeToExplode = 120; // 2 giây mới nổ một quả bom.
    public int timeAfter = 20; // Thời gian để vết lửa biến mất.

    protected Board board;
    protected boolean allowToPassThrough = true; // Quả bom có được phép xuyên qua tường k.
    protected DirectionalExplosion[] explosions = null; // Hướng lửa cháy.
    protected boolean exploded = false; // Bom đã nổ hay chưa.

    public Bomb(int x, int y, Board board) {

        this.x = x;
        this.y = y;
        this.board = board;
        this.sprite = Sprite.bomb;
    }

    @Override
    public void update() {

        if(timeToExplode > 0) {
            --timeToExplode;
        } else {

            if(!exploded)
                explosion();
            else
                updateExplosions();

            if (timeAfter > 0) {
                --timeAfter;
            } else {
                remove();
            }
        }

        animate();
    }

    @Override
    public void render(Screen screen) {

        if (exploded) {

            this.sprite =  Sprite.bomb_exploded2;
            renderExplosions(screen);
        } else {
            this.sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, this.animate, 60);
        }

        int xt = (int) x << 4;
        int yt = (int) y << 4;

        screen.renderEntity(xt, yt , this);
    }

    public void renderExplosions(Screen screen) {

        for (int i = 0; i < explosions.length; ++i) {

            explosions[i].render(screen);
        }
    }

    public void updateExplosions() {
        Game.playSE(2);

        for (int i = 0; i < explosions.length; ++i) {
            explosions[i].update();
        }
    }

    public void explode() {

        timeToExplode = 0;
    }

    protected void explosion() {

        allowToPassThrough = true;
        exploded = true;

        Mob a = board.getMobAt(x, y);
        if (a != null) {

            a.kill();
        }

        explosions = new DirectionalExplosion[4];
        for (int i = 0; i < explosions.length; ++i) {
            explosions[i] = new DirectionalExplosion((int) x, (int) y, i, Game.getBombRadius(), board);
        }
    }

    // Tạo nổ tại điểm (x, y) trên màn hình.
    public Explosion explosionAt(int x, int y) {

        if (!exploded) {
            return null;
        }

        for (int i = 0; i < explosions.length; ++i) {
            if (explosions[i] == null) {
                return null;
            }

            Explosion e = explosions[i].explosionAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
    }

    public boolean isExploded() {

        return exploded;
    }

    // Xử lý va chạm của quả bom khi gặp người chơi hoặc gặp mob hoặc gặp tile.
    @Override
    public boolean collide(AbstractEntity e) {

        if (e instanceof Bomber) {

            double diffX = e.getX() - Coordinates.tileToPixel(getX());
            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            // Nếu như người chơi đã đi ra khỏi khoảng va chạm của lửa bom thì không bị làm sao cả.
            if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) {
                allowToPassThrough = false;
            }

            return allowToPassThrough;
        }

        if (e instanceof DirectionalExplosion) {

            explode();
            return true;
        }

        return false;
    }
}
