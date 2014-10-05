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
		if((x <= ship.getX() + ship.HITBOX/2 && x >= ship.getX() - Ship.HITBOX/2
				&& y <= ship.getY() + ship.HITBOX/2 && y >= ship.getY() - ship.HITBOX)
				|| (x + bulletLeft <= ship.getX() + ship.HITBOX/2 && x + bulletLeft >= ship.getX() - Ship.HITBOX/2
				&& y - temp <= ship.getY() + ship.HITBOX/2 && y -temp >= ship.getY() - ship.HITBOX)
				|| (x - bulletRight <= ship.getX() + ship.HITBOX/2 && x - bulletRight >= ship.getX() - Ship.HITBOX/2
				&& y - temp <= ship.getY() + ship.HITBOX/2 && y - temp >= ship.getY() - ship.HITBOX)){
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
