package Entities.MapTiles.PowerUps;

import Entities.AbstractEntity;
import Entities.Characters.Bomber;
import Graphics.Sprite;
import Main.Game;

public class PowerupSpeed extends Powerup {

	public PowerupSpeed(int x, int y, int level, Sprite sprite) {

		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(AbstractEntity e) {
		
		if(e instanceof Bomber) {
			((Bomber) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {

		_active = true;
		Game.addPlayerSpeed(0.1);
	}
}
