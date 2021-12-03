package Entities.MapTiles.DestroyableTiles;

import Entities.AbstractEntity;
import Entities.Bomb.DirectionalExplosion;
import Entities.MapTiles.AbstractTile;
import Graphics.Sprite;

public class DestroyableTile extends AbstractTile {

	private final int MAX_ANIMATE = 7500; //save the animation status and don't let this get too big
	private int animate = 0;
	protected boolean _destroyed = false;
	protected int timeToDisappear = 20;
	protected Sprite belowSprite = Sprite.grass; //default
	
	public DestroyableTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		if(_destroyed) {
			if(animate < MAX_ANIMATE) animate++; else animate = 0; //reset animation
			if(timeToDisappear > 0) 
				timeToDisappear--;
			else
				remove();
		}
	}

	public boolean isDestroyed() {
		return _destroyed;
	}
	
	public void destroy() {
		_destroyed = true;
	}
	
	@Override
	public boolean collide(AbstractEntity e) {
		
		if (e instanceof DirectionalExplosion)
			destroy();
			
		return false;
	}
	
	public void addBelowSprite(Sprite sprite) {
		belowSprite = sprite;
	}
	
	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {

		int calc = animate % 30;
		
		if(calc < 10) {
			return normal;
		}
			
		if(calc < 20) {
			return x1;
		}
			
		return x2;
	}
	
}
