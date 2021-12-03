package Entities.Characters.Enemies.AI;

import java.util.Random;

public abstract class AbstractAI {

    protected Random random = new Random();

    public abstract int calculateDirection();
}
