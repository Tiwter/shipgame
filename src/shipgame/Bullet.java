package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bullet {

	protected Image image;
	protected float x;
	protected float y;
	
	public Bullet(float x, float y)throws SlickException{
		this.x = x;
		this.y = y;
		image = new Image("res/Bullet.png");
	}
	public void setXY(float x, float y){
		this.x = x;
		this.y = y;
	}
	public float getY(){
		return y;
	}
	public float getX(){
		return x;
	}
	public boolean inUse(){
		if(y == -10){
			return false;
		}
		else return true;
	}
	public boolean isCollide(EnemyShip enemyship){
		if(x <= enemyship.getX() + enemyship.WIDTH/2 && x >= enemyship.getX() - enemyship.WIDTH/2
				&& y <= enemyship.getY() + enemyship.HEIGHT/2 && y >= enemyship.getY() - enemyship.HEIGHT){
			return true;
		}
		return false;
	}
	public boolean hitPlayer(Ship ship){
		if(x <= ship.getX() + ship.HITBOX/2 && x >= ship.getX() - Ship.HITBOX/2
				&& y <= ship.getY() + ship.HITBOX/2 && y >= ship.getY() - ship.HITBOX/2){
			return true;
		}
		return false;
	}
	public void render(){
		image.draw(x, y);
	}
	public void shoot(Input input, float shipX, float shipY){
		if(input.isKeyDown(input.KEY_Z) && y == -10){
			setXY(shipX - 5/2, shipY - 20);
		}
	}
	public void update(){
		if(y >= 0){
			y -= 10;
		}
		if(y < 0){
			setXY(-10, -10);
		}
	}
}
