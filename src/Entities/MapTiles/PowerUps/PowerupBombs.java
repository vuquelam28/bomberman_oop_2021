package Entities.MapTiles.PowerUps;

import Entities.AbstractEntity;
import Entities.Characters.Bomber;
import Graphics.Sprite;
import Main.Game;

public class PowerupBombs extends Powerup {

	public PowerupBombs(int x, int y, int level, Sprite sprite) {

		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(AbstractEntity e) {
		
		if (e instanceof Bomber) {
			((Bomber) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {

		_active = true;
		Game.addBombRate(1);
	}
}
