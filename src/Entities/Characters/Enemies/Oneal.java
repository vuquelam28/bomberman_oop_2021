package Entities.Characters.Enemies;

import Entities.Characters.Enemies.AI.AIMedium;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

public class Oneal extends AbstractEnemy {

    public Oneal(int x, int y, Board board) {
        super(x, y, board, Sprite.oneal_dead, Game.getPlayerSpeed(), 200);

        sprite = Sprite.oneal_left1;

        ai = new AIMedium(this.board.getPlayer(), this);
        direction = ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {

        switch (direction) {
            
            case 0: case 1:
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60);
                } else {
                    sprite = Sprite.oneal_left1;
                }
                
                break;
                
            case 2: case 3:
                
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60);
                } else {
                    sprite = Sprite.oneal_left1;
                }
                
                break;
        }
    }
}
