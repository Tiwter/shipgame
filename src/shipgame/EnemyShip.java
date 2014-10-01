package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemyShip {

	public final float WIDTH = 40;
	public final float HEIGHT = 40;
	private float x, y, vx, vy;
	private int hp = 1;
	private int reloadtime = 0;
	private boolean shoted = false;
	private Image image;
	
	public EnemyShip(float x, float y, float vx, float vy)throws SlickException{
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		image = new Image("res/enemyship.png");
	}
	
	public float getX(){
		return x + WIDTH/2;
	}
	
	public float getY(){
		return y + HEIGHT/2;
	}
	public void hited(){
		hp -= 1;
	}
	public boolean isShoted(){
		return shoted;
	}
	public void shoted(){
		shoted = true;
	}
	private void reloaded(){
		if(isShoted()){
			reloadtime++;
			if(reloadtime >= 10){
				shoted = false;
				reloadtime = 0;
			}
		}
	}
	public void destroyed(){
		if(hp <= 0){
			vx = 0;
			vy = 0;
			x = -20;
			y = -20;
		}
	}
	public void render(){
		image.draw(x,y);
	}
	public void update(){
		if(y <= -10){
			y += 2;
		}
		if(y > -10){
			y += vy;
			x += vx;
		}
		reloaded();
	}
}
