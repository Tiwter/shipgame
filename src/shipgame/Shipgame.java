package shipgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Shipgame extends BasicGame{

	public static final float GAME_WIDTH = 640;
	public static final float GAME_HEIGHT = 480;
	
	Ship ship;
	Bullet[] bullets;
	EnemyShip[] enemies;
	EnemySpreadBullet[] enemybullets;
	Stage1 stage1;
	
	int power = 0;
	int life = 1;
	int score = 0;
	int temp = 0;
	int delay = 0;
	boolean fire = false;
	Image bg1, bg2;
	public Shipgame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		bg2.draw();
		ship.render();
		for(EnemyShip enemy : enemies){
			enemy.render();
		}
		for(EnemyBullet enemybullet : enemybullets){
			enemybullet.render();
		}
		for(Bullet bullet : bullets)bullet.render();
		bg1.draw();
		arg1.drawString("Score : " + score, 500, 25);
		arg1.drawString("Power : " + power, 500, 275);
		arg1.drawString("Life : " + life, 500, 300);
		arg1.drawString("Bombe : " + ship.getBombe(), 500, 325);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		ship = new Ship(200,400);
		bullets = new Bullet[20];
		enemybullets = new EnemySpreadBullet[101];
		for(int i = 0; i <= 100; i++){
			enemybullets[i] = new EnemySpreadBullet(-10,-10);
		}
		bg1 = new Image("res/bg1.png");
		bg2 = new Image("res/bg2.png");
		enemies = start();
		
		for(int i = 0; i <= 19; i++){
			bullets[i] = new Bullet(-10,-10);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input input = arg0.getInput();
		ship.updateShipMovement(enemies, input);
		enemyfire();
		
		for(EnemyBullet enemybullet : enemybullets){
			enemybullet.update();
			if(enemybullet.hitPlayer(ship) && !ship.isInvincible()){
				life--;
				ship.destroyed();
				//ship.setXY(250, 500);
			}
		}
		fire(input);
	}
	
	private void enemyfire() {
		for(EnemyShip enemy : enemies){
			enemy.update();
			if(enemy.isDestroyed() && !enemy.isGet()){
				score += enemy.getscore();
			}
			if(enemy.getY() >= 50 && enemy.getY() <= 150 && !enemy.isShoted()){
				if(temp == 100){
					temp = 0;
				}
				enemybullets[temp].setXY(enemy.getX(), enemy.getY());
				enemy.shoted();
				temp++;
			}
		}
		
	}

	public static void main(String[] args){
	 try {
		 Shipgame game = new Shipgame("Ship game");
	     AppGameContainer appgc = new AppGameContainer(game);
	     appgc.setDisplayMode(640, 480, false);
	     //appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
	     appgc.setTargetFrameRate(60);
	     appgc.start();
	 } catch (SlickException e) {
	     e.printStackTrace();
	   }
	}
	public EnemyShip[] start() throws SlickException{
		EnemyShip[] enemies = new EnemyShip[24];
		enemies[0] = new EnemyShip(100, -100, 2, 2);
		enemies[1] = new EnemyShip(120, -150, 2, 2);
		enemies[2] = new EnemyShip(140, -200, 2, 2);
		enemies[3] = new EnemyShip(160, -250, 2, 2);
		enemies[4] = new EnemyShip(180, -300, 2, 2);
		enemies[5] = new EnemyShip(420, -800, -2, 2);
		enemies[6] = new EnemyShip(400, -850, -2, 2);
		enemies[7] = new EnemyShip(380, -900, -2, 2);
		enemies[8] = new EnemyShip(360, -950, -2, 2);
		enemies[9] = new EnemyShip(340, -1000, -2, 2);
		enemies[10] = new EnemyShip(100, -1500, 0, 3);
		enemies[11] = new EnemyShip(420, -1500, 0, 3);
		enemies[12] = new EnemyShip(120, -1510, 0, 3);
		enemies[13] = new EnemyShip(400, -1510, 0, 3);
		enemies[14] = new EnemyShip(140, -1520, 0, 3);
		enemies[15] = new EnemyShip(380, -1520, 0, 3);
		enemies[16] = new EnemyShip(160, -1530, 0, 3);
		enemies[17] = new EnemyShip(360, -1530, 0, 3);
		enemies[18] = new EnemyShip(100, -1800, 0, 2);
		enemies[19] = new EnemyShip(400, -1950, 0, 2);
		enemies[20] = new EnemyShip(100, -2100, 0, 2);
		enemies[21] = new EnemyShip(400, -2250, 0, 2);
		enemies[22] = new EnemyShip(100, -2400, 0, 2);
		enemies[23] = new EnemyShip(400, -2550, 0, 2);
		return enemies;
		
	}
	public void fire(Input input){
		int i = 0;
		for(; i <= 19; i++){
			if(!bullets[i].inUse()){
				break;
			}
		}
		if(delay <= 0){
			bullets[i].shoot(input, ship.getX(), ship.getY());
			delay = 10;
		}
		for(Bullet bullet : bullets){
			bullet.update();
			for(EnemyShip enemy : enemies){
				if(bullet.isCollide(enemy)){
					bullet.setXY(-10,-10);
					enemy.hited();
				}
				enemy.destroyed();
			}
		}
		delay -= 1;
	}

}
