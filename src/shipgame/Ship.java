package shipgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ship{
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	private float x;
	private float y;
	private Image image;
	
	public Ship(float x, float y)throws SlickException{
		this.x = x;
		this.y = y;
		image = new Image("res/ship.png");
	}
	public void render(){
		image.draw(x, y);
	}
	public void moveup(){
		this.y -= 1;
	}
	public void movedown(){
		this.y += 1;
	}
	public void moveright(){
		this.x += 1;
	}
	public void moveleft(){
		this.x -= 1;
	}
	public void updateShipMovement(Input input, int delta){
		if(input.isKeyDown(input.KEY_UP)){
			moveup();
		}
		if(input.isKeyDown(input.KEY_DOWN)){
			movedown();
		}
		if(input.isKeyDown(input.KEY_LEFT)){
			moveleft();
		}
		if(input.isKeyDown(input.KEY_RIGHT)){
			moveright();
		}
	}
}
