package shipgame;

import org.newdawn.slick.SlickException;

public class EnemyBullet extends Bullet {
	
	public EnemyBullet(float x, float y) throws SlickException {
		super(x, y);
		bullet.rotate(180);
	}
	
	@Override
	public void render() {
		bullet.draw(x,y);
		bullet.draw(x+10, y);
		bullet.draw(x-10, y);
	}
	
	@Override
	public void update() {
		if(y >= 10){
			y += 5;
		}
	}

}
