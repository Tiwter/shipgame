package shipgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ship{
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	public static final int HITBOX = 20;
	
	protected float x;
	protected float y;
	private boolean invincible = false;
	private int invincibleTime = 0;
	private Image image, imageInvincible;
	private Image bombShow = new Image("res/bg3.png");
	private int animate = 3, bombanimate = 2;
	//private Image [] imageInvincible  = {new Image("res/ship2.png"), new Image("res/ship.png")};
	private int bombReload = 100;
	private int bomb = 2;
	private int power = 0;
	private int shipSpeed = 2;
	
	public Ship(float x, float y) throws SlickException {
		setXY(x, y);
		image = new Image("res/ship.png");
		imageInvincible = new Image("res/ship2.png");
	}
	
	public void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x + WIDTH/2;
	}
	
	public float getY() {
		return y + HEIGHT/2;
	}
	
	public int getPower() {
		return power;
	}
	
	public int getbomb() {
		return bomb;
	}
	
	public void powerUp() {
		power++;
	}
	
	public void speedUp() {
		if (power >= 20 && shipSpeed == 2) {
			shipSpeed += 2;
		}
	}
	public boolean isInvincible() {
		return  invincible;
	}
	
	public void invincibleTime() {
		if (invincible) {
			invincibleTime++;
		}
		if (invincibleTime >= 200) {
			invincibleTime = 0;
			invincible = false;
		}
	}
	
	public void destroyed() {
		invincible = true;
		bomb = 2;
		setXY(200,500);
	}
	
	public void render() {
		if (isInvincible() && animate > 0) {
			imageInvincible.draw(x , y);
			animate--;
		}
		else {
			image.draw(x, y);
			animate--;
			if (animate < -3) {
				animate = 3;
			}
		}
		if (bombReload < 99) {
			if (bombanimate > 0) {
				bombShow.draw(0,0);
				bombanimate--;
			}
			else {
				bombShow.draw(1000, 1000);
				bombanimate--;
				bombanimate = 2;
			}
		}
		else {
			
		}
	}
	
	public boolean bombInUse() {
		if (bombReload < 100) {
			return true;
		}
		else {
			return false;
		}
	}
	public void moveup() {
		if (y >= 20) {
			this.y -= shipSpeed;
		}
	}
	public void movedown() {
		if (getY() + HEIGHT/2 <= Shipgame.GAME_HEIGHT - 20) {
			this.y += shipSpeed;
		}
	}
	
	public void moveright() {
		if (getX() + WIDTH/2 <= Shipgame.GAME_WIDTH - 150) {
			this.x += shipSpeed;
		}
	}
	
	public void moveleft() {
		if (x >= 20) {
			this.x -= shipSpeed;
		}
	}
	
	public void bomb(EnemyShip[] enemies) {
		if (bombReload >= 100) {
			for (int i = 0; i < enemies.length; i ++) {
				if (enemies[i].isInScreen()) {
					enemies[i].getHitBybomb();
				}
			}
			bombReload = 0;
			bomb--;
		}
		//bombReload++;
	}
	
	public void colide(EnemyShip enemy) {
		if(enemy.getX() >= getX() - WIDTH/2 && enemy.getX() <= getX() + WIDTH/2
				&& enemy.getY() >= getY() - WIDTH/2 && enemy.getY() <= getY() + WIDTH/2)
			destroyed();
	}
	
	public void updateShipMovement(EnemyShip[] enemies ,Input input) {
		if (input.isKeyDown(input.KEY_X)) {
			if (bomb > 0) {
				
				bomb(enemies);
			}
		}
		if (input.isKeyDown(input.KEY_UP)) {
			moveup();
		}
		if (input.isKeyDown(input.KEY_DOWN)) {
			movedown();
		}
		if (input.isKeyDown(input.KEY_LEFT)) {
			moveleft();
		}
		if (input.isKeyDown(input.KEY_RIGHT)) {
			moveright();
		}
		if (y >= 420 && isInvincible()) {
				y--;
		}
		invincibleTime();
		speedUp();
		bombReload++;
	}
}
