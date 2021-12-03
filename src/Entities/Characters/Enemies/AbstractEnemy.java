package Entities.Characters.Enemies;

import Entities.AbstractEntity;
import Entities.Bomb.DirectionalExplosion;
import Entities.Characters.Bomber;
import Entities.Characters.Enemies.AI.AbstractAI;
import Entities.Characters.Mob;
import Entities.Message;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;
import Main.Board;
import Main.Game;

import java.awt.*;

public abstract class AbstractEnemy extends Mob {

    protected int points;

    protected double speed; //Speed should change on level transition
    protected AbstractAI ai;

    // Sử dụng để điều chỉnh chính xác bước đi.
    protected double MAX_STEPS = 0;
    protected double rest = 0;
    protected double steps;

    protected int finalAnimation = 30;
    protected Sprite deadSprite;

    public AbstractEnemy(int x, int y, Board board, Sprite dead, double speed, int points) {

        super(x, y, board);

        this.points = points;
        this.speed = speed;

        MAX_STEPS = Game.TILE_SIZE / speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        steps = MAX_STEPS;

        timeAfter = 20;
        deadSprite = dead;
    }

    /*
        Render và Update vị trí, trạng thái của các nhân vật.
     */
    @Override
    public void update() {
        animate();

        if (!alive) {
            afterKill();
            return;
        }

        calculateMove();
    }

    @Override
    public void render(Screen screen) {

        if(alive) {
            chooseSprite();
        } else {
            
            if(timeAfter > 0) {
                sprite = deadSprite;
                animate = 0;
            } else {
                
                sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, 60);
            }
        }

        screen.renderEntity((int)this.x, (int)this.y - sprite.SIZE, this);
    }

    /*
        Di chuyển của các nhân vật trong game.
     */
    @Override
    public void calculateMove() {
        int xa = 0, ya = 0;
        if (steps <= 0){
            
            direction = ai.calculateDirection();
            steps = MAX_STEPS;
        }

        if (direction == 0) {
            ya--;
        }
        if (direction == 2) {
            ya++;
        }
        if (direction == 3) {
            xa--;
        }
        if (direction == 1) {
            xa++;
        }

        if(canMove(xa, ya)) {
            steps -= (1 + rest);
            move(xa * speed, ya * speed);
            moving = true;
        } else {
            steps = 0;
            moving = false;
        }
    }

    @Override
    public void move(double xa, double ya) {
        
        if(!alive) {
            return;
        }

        this.y += ya;
        this.x += xa;
    }

    @Override
    public boolean canMove(double x, double y) {

        double xr = this.x, yr = this.y - 16; //subtract y to get more accurate results

        //the thing is, subtract 15 to 16 (sprite size), so if we add 1 tile we get the next pixel tile with this
        if (direction == 0) {
            yr += sprite.getSize() - 1;
            xr += sprite.getSize() / 2;
        }
        if (direction == 1) {
            yr += sprite.getSize() / 2;
            xr += 1;
        }
        if (direction == 2) {
            xr += sprite.getSize() / 2;
            yr += 1;
        }
        if(direction == 3) {
            xr += sprite.getSize() - 1;
            yr += sprite.getSize() / 2;
        }

        int xx = Coordinates.pixelToTile(xr) + (int)x;
        int yy = Coordinates.pixelToTile(yr) + (int)y;

        AbstractEntity a = board.getEntity(xx, yy, this); //entity of the position we want to go

        return a.collide(this);
    }

    @Override
    public boolean collide(AbstractEntity e) {

        if (e instanceof DirectionalExplosion) {
            kill();
            return false;
        }

        if (e instanceof Bomber) {

            ((Bomber) e).kill();
            return false;
        }

        return true;
    }

    @Override
    public void kill() {

        if (!alive) {
            return;
        }

        alive = false;

        board.addPoints(points);

        Message msg = new Message("+" + points, getXMessage(), getYMessage(), 2, Color.white, 14);
        board.addMessage(msg);
    }


    @Override
    protected void afterKill() {

        if (timeAfter > 0) {
            --timeAfter;
        } else {

            if (finalAnimation > 0) {
                --finalAnimation;
            } else {
                remove();
            }
        }
    }

    protected abstract void chooseSprite();
}
