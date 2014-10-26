package shipgame;

import org.newdawn.slick.SlickException;

public class EnemySpreadBullet extends EnemyBullet {

	float bulletLeft, bulletRight, temp, g;
	public EnemySpreadBullet(float x, float y) throws SlickException {
		super(x, y);
	}
	
	@Override
	public void render(){
		bullet.draw(x, y + g);
		bullet.draw(x + bulletLeft, y + temp);
		bullet.draw(x - bulletRight, y + temp);
	}
	
	@Override
	public boolean hitPlayer(Ship ship){
		if (x <= ship.getX() + ship.HITBOX/2 && x >= ship.getX() - Ship.HITBOX/2
				&& y + g <= ship.getY() + ship.HITBOX/2 && y + g >= ship.getY() - ship.HITBOX) {
			g = 500;
			return true;
		}
		if (x + bulletLeft <= ship.getX() + ship.HITBOX/2 && x + bulletLeft >= ship.getX() - Ship.HITBOX/2
				&& y + temp <= ship.getY() + ship.HITBOX/2 && y + temp >= ship.getY() - ship.HITBOX) {
			bulletLeft = -500;
			return true;
		}
		if (x - bulletRight <= ship.getX() + ship.HITBOX/2 && x - bulletRight >= ship.getX() - Ship.HITBOX/2
				&& y + temp <= ship.getY() + ship.HITBOX/2 && y + temp >= ship.getY() - ship.HITBOX) {
			bulletRight = 500;
			return true;
		}
		return false;
	}
	
	@Override
	public void setXY(float x, float y){
		this.x = x;
		this.y = y;
		bulletLeft = 0;
		bulletRight = 0;
		temp = 0;
		g = 0;
	}
	
	@Override
	public void update(){
		if(y >= 10){
			bulletLeft += 3;
			bulletRight += 3;
			temp += 4;
			g += 5;
		}
	}

}
