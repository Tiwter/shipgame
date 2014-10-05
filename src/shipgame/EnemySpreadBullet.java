package shipgame;

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
	public boolean hitPlayer(Ship ship){
		if((x <= ship.getX() + ship.WIDTH/2 && x >= ship.getX() - Ship.WIDTH/2
				&& y <= ship.getY() + ship.HEIGHT/2 && y >= ship.getY() - ship.HEIGHT)
				|| (x + bulletLeft <= ship.getX() + ship.WIDTH/2 && x + bulletLeft >= ship.getX() - Ship.WIDTH/2
				&& y - temp <= ship.getY() + ship.HEIGHT/2 && y -temp >= ship.getY() - ship.HEIGHT)
				|| (x - bulletRight <= ship.getX() + ship.WIDTH/2 && x - bulletRight >= ship.getX() - Ship.WIDTH/2
				&& y - temp <= ship.getY() + ship.HEIGHT/2 && y - temp >= ship.getY() - ship.HEIGHT)){
			setXY(-10, -10);
			return true;
		}
		return false;
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
