package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemySpreadBullet extends EnemyBullet {

	float bulletLeft, bulletRight, temp;
	public EnemySpreadBullet(float x, float y) throws SlickException {
		super(x, y);
	}
	@Override
	public void render(){
		image.draw(x, y);
		image.draw(x + bulletLeft, y - temp);
		image.draw(x - bulletRight, y - temp);
	}
	@Override
	public void update(){
		if(y >= 10){
			bulletLeft += 3;
			bulletRight += 3;
			temp += 1;
			y += 5;
		}
	}

}
