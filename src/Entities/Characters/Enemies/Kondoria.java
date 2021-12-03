package Entities.Characters.Enemies;

import Entities.Characters.Enemies.AI.AIMedium;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

public class Kondoria extends AbstractEnemy {

    public Kondoria(int x, int y, Board board) {
        super(x, y, board, Sprite.kondoria_dead, Game.getPlayerSpeed() / 4, 1000);

        sprite = Sprite.kondoria_right1;

        ai = new AIMedium(this.board.getPlayer(), this);
        direction = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {

        switch(direction) {

            case 0: case 1:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 60);
                } else {
                    sprite = Sprite.kondoria_left1;
                }

                break;

            case 2: case 3:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 60);
                } else {
                    sprite = Sprite.kondoria_left1;
                }

                break;
        }
    }
}
