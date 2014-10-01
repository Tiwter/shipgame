package shipgame;

import org.newdawn.slick.SlickException;

public class EnemyBullet extends Bullet {
	public EnemyBullet(float x, float y) throws SlickException {
		super(x, y);
		image.rotate(180);
	}
	@Override
	public void render(){
		image.draw(x,y);
		image.draw(x+10, y);
		image.draw(x-10, y);
	}
	@Override
	public void update(){
		if(y >= 10){
			y += 5;
		}
	}

}
