package shipgame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Bullet {

	private Image image;
	private float x;
	private float y;
	Graphics g;
	
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
