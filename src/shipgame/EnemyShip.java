package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EnemyShip {

	public final float WIDTH = 40;
	public final float HEIGHT = 40;
	private float x, y, vx, vy, startfire = 50;
	private int hp = 3;
	private int reloadtime = 0;
	private boolean destroyed = false;
	private boolean shoted = false;
	private boolean isGet = false;
	private Image image;
	
	public EnemyShip(float x, float y, float vx, float vy)throws SlickException{
		setXY(x, y);
		this.vx = vx;
		this.vy = vy;
		image = new Image("res/enemyship.png");
	}
	public EnemyShip(float x, float y, float vx, float vy, float startfire)throws SlickException{
		setXY(x, y);
		this.vx = vx;
		this.vy = vy;
		this.startfire = startfire;
		image = new Image("res/enemyship.png");
	}
	public void setXY(float x, float y){
		this.x = x;
		this.y = y;
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
			if(reloadtime >= 15){
				shoted = false;
				reloadtime = 0;
			}
		}
	}
	public void getHitByBombe(){
		hp = 0;
	}
	public void destroyed(){
		if(hp <= 0){
			vx = 0;
			vy = 0;
			x = -30;
			y = -30;
			destroyed = true;
		}
	}
	public boolean isDestroyed(){
		return destroyed;
	}
	public boolean isGet(){
		return isGet;
	}
	public int getscore(){
		isGet = true;
		return 100;
	}
	protected boolean isInScreen(){
		if(getX() >= 20-WIDTH/2 && getX() <= 490-WIDTH/2
				&& getY() >= 20-HEIGHT/2 && getY() <= 460-HEIGHT/2){
			return true;
		}
		return false;
	}
	public float getStartFire(){
		return startfire;
	}
	public void render(){
		image.draw(x,y);
	}
	public void update(){
		if(y <= -10 || (y <= startfire && y != 50)){
			y += 2;
		}
		if(y > -10 || x <= 0){
			y += vy;
			x += vx;
		}
		reloaded();
	}
}
