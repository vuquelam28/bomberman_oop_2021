package Entities.Characters.Enemies;

import Entities.Characters.Enemies.AI.AIMedium;
import Graphics.Sprite;
import Main.Board;
import Main.Game;

public class Minvo extends AbstractEnemy {

    public Minvo(int x, int y, Board board) {
        super(x, y, board, Sprite.minvo_dead, Game.getPlayerSpeed() * 2, 800);

        sprite = Sprite.minvo_right1;

        ai = new AIMedium(this.board.getPlayer(), this);
        direction  = ai.calculateDirection();
    }
   
    @Override
    protected void chooseSprite() {
        switch(direction) {

            case 0: case 1:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 60);
                else
                    sprite = Sprite.minvo_left1;
                break;

            case 2: case 3:
                if (moving)
                    sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 60);
                else
                    sprite = Sprite.minvo_left1;
                break;
        }
    }
}
