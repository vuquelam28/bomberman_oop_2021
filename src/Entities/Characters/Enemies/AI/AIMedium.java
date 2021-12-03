package Entities.Characters.Enemies.AI;

import Entities.Characters.Bomber;
import Entities.Characters.Enemies.AbstractEnemy;

public class AIMedium extends AbstractAI {

    Bomber bomber;
    AbstractEnemy enemy;

    public AIMedium(Bomber Bomber, AbstractEnemy e) {

        bomber = Bomber;
        this.enemy = e;
    }

    @Override
    public int calculateDirection() {

        if(bomber == null) {
            return random.nextInt(4);
        }

        int vertical = random.nextInt(2);

        if(vertical == 1) {

            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();
        } else {
            int h = calculateColDirection();

            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }

    }

    protected int calculateColDirection() {

        if (bomber.getXTile() < enemy.getXTile()) {
            return 3;
        } else if (bomber.getXTile() > enemy.getXTile()) {
            return 1;
        }

        return -1;
    }

    protected int calculateRowDirection() {

        if(bomber.getYTile() < enemy.getYTile()) {
            return 0;
        } else if (bomber.getYTile() > enemy.getYTile()) {
            return 2;
        }

        return -1;
    }
}
