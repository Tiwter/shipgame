package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bullet {

	protected Image bullet, redBullet;
	protected float x;
	protected float y;
	
	private int bulletLv = 1;
	
	public int bulletReloadSpeed = 0;
	
	public Bullet(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		bullet = new Image("res/Bullet.png");
		redBullet = new Image("res/RedBullet.png");
	}
	
	public void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
	
	public float getX() {
		return x;
	}
	
	public boolean inUse() {
		if (y == -10) {
			return false;
		}
		else return true;
	}
	
	public int getBulletLv() {
		return bulletLv;
	}
	
	public void bulletPower(Ship ship) {
		if (ship.getPower() >= 15 && bulletLv == 1) {
			bulletLv++;
			bulletReloadSpeed -= 2;
		}
		if (ship.getPower() >= 30 && bulletLv == 2) {
			bulletLv++;
			bulletReloadSpeed -= 2;
		}
		if (ship.getPower() >= 40 && bulletLv == 3) {
			bulletLv++;
			bulletReloadSpeed -= 2;
		}
		if (ship.getPower() >= 50 && bulletLv == 4) {
			bulletLv++;
			bulletReloadSpeed -= 1;
		}
	}
	public boolean isCollide(EnemyShip enemyship){
		if (x <= enemyship.getX() + enemyship.WIDTH/2 && x >= enemyship.getX() - enemyship.WIDTH/2
				&& y <= enemyship.getY() + enemyship.HEIGHT/2 && y >= enemyship.getY() - enemyship.HEIGHT) {
			return true;
		}
		return false;
	}
	
	public boolean hitPlayer(Ship ship) {
		if (x <= ship.getX() + ship.HITBOX/2 && x >= ship.getX() - Ship.HITBOX/2
				&& y <= ship.getY() + ship.HITBOX/2 && y >= ship.getY() - ship.HITBOX/2) {
			return true;
		}
		return false;
	}
	
	public void render() {
		if (bulletLv == 1) {
			bullet.draw(x, y);
		}
		if (bulletLv > 1) {
			redBullet.draw(x ,y);
		}
	}
	
	public void shoot(Input input, float shipX, float shipY) {
		if (input.isKeyDown(input.KEY_Z) && y == -10) {
			setXY(shipX - 5/2, shipY - 20);
		}
	}
	
	public void update() {
		if (y >= 0) {
			if (bulletLv == 2) {
				y -= 15;
			}
			else if (bulletLv == 3) {
				y -= 20;
			}
			else if (bulletLv == 4) {
				y -= 25;
			}
			else if (bulletLv == 5) {
				y -= 30;
			}
			else {
				y -= 10;
			}
		}
		if (y < 0) {
			setXY(-100, -10);
		}
	}
}
