package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ship{
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	protected float x;
	protected float y;
	private boolean invincible = false;
	private int invincibleTime = 0;
	private Image image, imageInvincible;
	private int bombeReload = 20;
	private int bombe = 2;
	
	public Ship(float x, float y)throws SlickException{
		setXY(x, y);
		image = new Image("res/ship.png");
		imageInvincible = new Image("res/ship2.png");
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
	public int getBombe(){
		return bombe;
	}
	public boolean isInvincible(){
		return invincible;
	}
	public void invincibleTime(){
		if(invincible){
			invincibleTime++;
		}
		if(invincibleTime >= 200){
			invincibleTime = 0;
			invincible = false;
		}
	}
	public void destroyed(){
		invincible = true;
		setXY(200,500);
	}
	public void render(){
		if(isInvincible()){
			imageInvincible.draw(x , y);
		}
		else{
			image.draw(x, y);
		}
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
	public void bombe(EnemyShip[] enemies){
		if(bombeReload >= 10){
			for(int i = 0; i < enemies.length; i ++){
				if(enemies[i].isInScreen()){
					enemies[i].getHitByBombe();
				}
			}
			bombeReload = 0;
			bombe--;
		}
		bombeReload++;
		}
	public void updateShipMovement(EnemyShip[] enemies ,Input input){
		if(input.isKeyDown(input.KEY_X)){
			bombe(enemies);
		}
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
		if(y >= 400 && isInvincible()){
				y--;
		}
		invincibleTime();

	}
}
