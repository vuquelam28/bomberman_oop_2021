package Entities.MapTiles.DestroyableTiles;


import Entities.AbstractEntity;
import Entities.Bomb.DirectionalExplosion;
import Entities.Characters.Enemies.Kondoria;
import Entities.MapTiles.AbstractTile;
import Graphics.Screen;
import Graphics.Sprite;
import Level.Coordinates;

public class BrickTile extends DestroyableTile {
	
	public BrickTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		int _x = Coordinates.tileToPixel(x);
		int _y = Coordinates.tileToPixel(y);
		
		if(_destroyed) {
			sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
			
			screen.renderEntityWithBelowSprite(_x, _y, this, belowSprite);
		}
		else
			screen.renderEntity(_x, _y, this);
	}
	
	@Override
	public boolean collide(AbstractEntity e) {
		
		if (e instanceof DirectionalExplosion) {

			destroy();
		}
		
		if (e instanceof Kondoria)
			return true;
			
		return false;
	}
	
	
}
