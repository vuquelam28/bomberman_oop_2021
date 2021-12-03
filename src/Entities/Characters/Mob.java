package Entities.Characters;

import Entities.AnimatedEntity;
import Graphics.Screen;
import Main.Board;
import Main.Game;

public abstract class Mob extends AnimatedEntity {

    protected Board board;
    protected int direction = -1;
    protected boolean alive = true;
    protected boolean moving = false;
    public int timeAfter = 80;

    public Mob(int x, int y, Board board) {

        this.x = x;
        this.y = y;
        this.board = board;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    public abstract void kill();

    protected abstract void afterKill();

    protected abstract boolean canMove(double x, double y);

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getDirection() {
        return direction;
    }

    protected double getXMessage() {
        return (x * Game.SCALE_MULTIPLE) + (sprite.SIZE / 2 * Game.SCALE_MULTIPLE);
    }

    protected double getYMessage() {
        return (y * Game.SCALE_MULTIPLE) - (sprite.SIZE / 2 * Game.SCALE_MULTIPLE);
    }
}
