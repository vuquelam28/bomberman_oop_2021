package Entities.Characters;

import Entities.AbstractEntity;
import Entities.Bomb.Bomb;
import Entities.Bomb.DirectionalExplosion;
import Entities.Characters.Enemies.AbstractEnemy;
import Entities.MapTiles.PowerUps.Powerup;
import Entities.Message;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;
import Main.Board;
import Main.Game;
import Main.KeyHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomber extends Mob {

    private List<Bomb> bombs;
    protected KeyHandler keyHandler;
    protected int timeBetweenPutBombs = 0;
    public static List<Powerup> powerUps = new ArrayList<Powerup>();

    public Bomber(int x, int y, Board board) {

        super(x, y, board);
        bombs = board.getBombs();
        keyHandler = board.getInput();
        sprite = Sprite.player_right;
    }


    /*
        Update và Render người chơi.
     */
    @Override
    public void update() {
        clearBombs();

        if (alive == false) {
            afterKill();
            return;
        }

        if (timeBetweenPutBombs < -7500) {

            timeBetweenPutBombs = 0;
        } else {

            timeBetweenPutBombs--; // Không để thời gian đặt bom cho phép giữa hai quả bom liên tiếp quá lớn.
        }

        animate();

        calculateMove();

        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (alive) {

            chooseSprite();
        }
        else {

            sprite = Sprite.player_dead1;
        }

        screen.renderEntity((int)x, (int)y - sprite.SIZE, this);
    }

    public void calculateXOffset() {

        int xScroll = Screen.calculateXOffset(board, this);
        Screen.setOffset(xScroll, 0);
    }


    /*
        Từ đây là các đặc tính riêng của người chơi: Đặt bom.
     */
    private void detectPlaceBomb() {

        if (keyHandler.spacePressed && Game.getBombRate() > 0 && timeBetweenPutBombs < 0) {

            int xt = Coordinates.pixelToTile(x + sprite.getSize() / 2);
            //subtract half player height and minus 1 y position.
            int yt = Coordinates.pixelToTile( (y + sprite.getSize() / 2) - sprite.getSize() );

            placeBomb(xt,yt);
            Game.addBombRate(-1);

            timeBetweenPutBombs = 30;
        }
    }

    protected void placeBomb(int x, int y) {

        Bomb b = new Bomb(x, y, board);
        board.addBomb(b);
    }

    private void clearBombs() {
        Iterator<Bomb> bs = bombs.iterator();

        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.isRemoved())  {
                bs.remove();
                Game.addBombRate(1);
            }
        }

    }

    // Xử lý va chạm với mobs và ăn kill.
    @Override
    public void kill() {

        if(!alive) {
            return;
        }

        alive = false;

        board.addLives(-1);

        Message msg = new Message("-1 LIVE", getXMessage(), getYMessage(), 2, Color.white, 14);

        board.addMessage(msg);
    }

    @Override
    protected void afterKill() {

        if (timeAfter > 0) {
            --timeAfter;
        } else {

            if(bombs.size() == 0) {

                if(board.getLives() == 0) {

                    board.endGame();
                } else {

                    board.restartLevel();
                }
            }
        }
    }

    /*
        Từ đây trở đi sử dụng để tính toán các vấn đề liên quan tới di chuyển của người chơi.
     */
    @Override
    protected void calculateMove() {

        int xa = 0, ya = 0;
        if(keyHandler.upPressed) {
            ya--;
        }
        if(keyHandler.downPressed) {
            ya++;
        }
        if(keyHandler.leftPressed) {
            xa--;
        }
        if(keyHandler.rightPressed) {
            xa++;
        }

        if (xa != 0 || ya != 0)  {

            move(xa * Game.getPlayerSpeed(), ya * Game.getPlayerSpeed());
            moving = true;
        } else {

            moving = false;
        }

    }

    // Kiểm tra xem người chơi có thể di chuyển tiếp hay không.
    @Override
    public boolean canMove(double x, double y) {

        // Kiểm tra va chạm với 4 góc xung quanh.
        for (int c = 0; c < 4; c++) {

            double xt = ((this.x + x) + c % 2 * 11) / Game.TILE_SIZE; // divide with tile size to pass to tile coordinate
            double yt = ((this.y + y) + c / 2 * 12 - 13) / Game.TILE_SIZE; // these values are the best from multiple tests

            AbstractEntity a = board.getEntity(xt, yt, this);

            if(!a.collide(this))
                return false;
        }

        return true;
    }

    @Override
    public void move(double xa, double ya) {

        if (xa > 0) {
            direction = 1;
        }
        if (xa < 0) {
            direction = 3;
        }
        if (ya > 0) {
            direction = 2;
        }
        if (ya < 0) {
            direction = 0;
        }

        // Di chuyển từng chiều một: y rồi tới x.
        if (canMove(0, ya)) {

            this.y += ya;
        }

        if (canMove(xa, 0)) {

            this.x += xa;
        }
    }

    @Override
    public boolean collide(AbstractEntity e) {

        if(e instanceof DirectionalExplosion) {
            kill();
            return false;
        }


        if(e instanceof AbstractEnemy) {

            kill();
            return true;
        }

        return true;

    }

    public void addPowerup(Powerup p) {

        if(p.isRemoved()) {
            return;
        }

        powerUps.add(p);

        p.setValues();
    }

    public void clearUsedPowerups() {

        Powerup p;
        for (int i = 0; i < powerUps.size(); i++) {

            p = powerUps.get(i);
            if(!p.isActive())
                powerUps.remove(i);
        }
    }

    public void removePowerups() {

        for (int i = 0; i < powerUps.size(); i++) {
            powerUps.remove(i);
        }
    }

    /**
     * Xử lý sprite của nhân vật.
     */
    private void chooseSprite() {

        switch (direction) {

            case 0:
                sprite = Sprite.player_up;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                }
                break;

            case 2:
                sprite = Sprite.player_down;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                }
                break;

            case 1: default:
                sprite = Sprite.player_right;
                if(moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                }
                break;
        }
    }
}
