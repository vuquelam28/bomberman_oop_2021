package Entities.Characters.Enemies.AI;

public class AILow extends AbstractAI {

    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
