package shipgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ship{
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	protected float x;
	protected float y;
	private Image image;
	
	public Ship(float x, float y)throws SlickException{
		this.x = x;
		this.y = y;
		image = new Image("res/ship.png");
	}
	public float getX(){
		return x + WIDTH/2;
	}
	public float getY(){
		return y + HEIGHT/2;
	}
	public void render(){
		image.draw(x, y);
	}
	public void moveup(){
		if(y >= 20){
			this.y -= 3;
		}
	}
	public void movedown(){
		if(getY() + HEIGHT/2 <= Shipgame.GAME_HEIGHT - 20){
			this.y += 3;
		}
	}
	public void moveright(){
		if(getX() + WIDTH/2 <= Shipgame.GAME_WIDTH - 150){
			this.x += 3;
		}
	}
	public void moveleft(){
		if(x >= 20){
			this.x -= 3;
		}
	}
	public void updateShipMovement(Input input){
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
