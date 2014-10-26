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
	
	int life = 2;
	int score = 0;
	int temp = 0;
	int delay = 0;
	boolean fire = false;
	boolean isGameOver = false;
	boolean isGameClear = false;
	Image bg1, bg2 ,gameover, bggameover, bgClear;
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
		for(EnemySpreadBullet enemybullet : enemybullets){
			enemybullet.render();
		}
		for(Bullet bullet : bullets)bullet.render();
		bg1.draw();
		arg1.drawString("Score : " + score, 500, 25);
		arg1.drawString("Power : " + ship.getPower(), 500, 275);
		arg1.drawString("Life : " + life, 500, 300);
		arg1.drawString("Bomb : " + ship.getbomb(), 500, 325);
		if(isGameOver){
			bggameover.draw();
			gameover.draw();
		}
		if(isGameClear){
			bgClear.draw();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		ship = new Ship(200,400);
		bullets = new Bullet[20];
		enemybullets = new EnemySpreadBullet[151];
		for(int i = 0; i <= 150; i++){
			enemybullets[i] = new EnemySpreadBullet(-10,-10);
		}
		bg1 = new Image("res/bg1.png");
		bg2 = new Image("res/bg2.png");
		gameover = new Image("res/gameover.png");
		bggameover = new Image("res/bggameover.png");
		bgClear = new Image("res/clear.png");
		enemies = start();
		
		for(int i = 0; i <= 19; i++){
			bullets[i] = new Bullet(-100,-10);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		if(!isGameOver){
			Input input = arg0.getInput();
			ship.updateShipMovement(enemies, input);
			enemyfire();
		
			for(EnemySpreadBullet enemybullet : enemybullets){
				enemybullet.update();
				if(enemybullet.hitPlayer(ship) && !ship.isInvincible()){
					life--;
					ship.destroyed();
				}
			}
			fire(input);
		}
		if(life < 0){
			isGameOver = true;
			life = 0;
		}
		if(enemies[enemies.length - 1].getY() >= -10){
			if (isGameClear == false) {
				score += life * 1000;
			}
			isGameClear = true;
			
		}
	}
	
	private void enemyfire() {
		for(EnemyShip enemy : enemies){
			enemy.update();
			if(enemy.isDestroyed() && !enemy.isGet()){
				score += enemy.getscore();
				ship.powerUp();
			}
			if(enemy.isInScreen() && (enemy.getY() >= enemy.getStartFire() && enemy.getY() <= 150 && !enemy.isShoted())){
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
		EnemyShip[] enemies = new EnemyShip[61];
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
		enemies[24] = new EnemyShip(-50, -2700, 5, 3);//
		enemies[25] = new EnemyShip(-10, -2750, 5 ,3);
		enemies[26] = new EnemyShip(20, -2800, 5 ,3);
		enemies[27] = new EnemyShip(50, -2850, 5 ,3);
		enemies[28] = new EnemyShip(80, -2900, 5 ,3);
		enemies[29] = new EnemyShip(540, -3050, -5 , 3);
		enemies[30] = new EnemyShip(510, -3100, -5 , 3);
		enemies[31] = new EnemyShip(480, -3150, -5 , 3);
		enemies[32] = new EnemyShip(450, -3200, -5 , 3);
		enemies[33] = new EnemyShip(420, -3250, -5 , 3);
		enemies[34] = new EnemyShip(-50, -3500, 5 , 3);
		enemies[35] = new EnemyShip(540, -3550, -5 , 3);
		enemies[36] = new EnemyShip(-20, -3600, 5 , 3);
		enemies[37] = new EnemyShip(510, -3650, -5 , 3);
		enemies[38] = new EnemyShip(10, -3700, 5 , 3);
		enemies[39] = new EnemyShip(480, -3750, -5 , 3);
		enemies[40] = new EnemyShip(40, -3800, 5 , 3);
		enemies[41] = new EnemyShip(450, -3850, -5 , 3);
		enemies[42] = new EnemyShip(70, -3900, 5 , 3);
		enemies[43] = new EnemyShip(420, -3950, -5 , 3);
		enemies[44] = new EnemyShip(90, -4200, 0, 1);
		enemies[45] = new EnemyShip(420, -4300, 0, 1);
		enemies[46] = new EnemyShip(90, -4400, 0, 1);
		enemies[47] = new EnemyShip(420, -4500, 0, 1);
		enemies[48] = new EnemyShip(90, -4600, 0, 1);
		enemies[49] = new EnemyShip(420, -4700, 0, 1);
		enemies[50] = new EnemyShip(-50, -5200, 3, 0, 60);
		enemies[51] = new EnemyShip(-50, -5300, 3, 0, 60);
		enemies[52] = new EnemyShip(-50, -5400, 3, 0, 60);
		enemies[53] = new EnemyShip(-50, -5500, 3, 0, 60);
		enemies[54] = new EnemyShip(-50, -5600, 3, 0, 60);
		enemies[55] = new EnemyShip(540, -5900, -3, 0, 60);
		enemies[56] = new EnemyShip(540, -6000, -3, 0, 60);
		enemies[57] = new EnemyShip(540, -6100, -3, 0, 60);
		enemies[58] = new EnemyShip(540, -6200, -3, 0, 60);
		enemies[59] = new EnemyShip(540, -6300, -3, 0, 60);
		enemies[60] = new EnemyShip(-500, -7000, 0, 0);
		
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
			delay = 10 + bullets[i].bulletReloadSpeed;
		}
		for(Bullet bullet : bullets){
			bullet.update();
			bullet.bulletPower(ship);
			for(EnemyShip enemy : enemies){
				if(bullet.isCollide(enemy)){
					bullet.setXY(-100,-10);
					enemy.hited(bullet.getBulletLv());
				}
				enemy.destroyed();
				
			}
		}
		delay -= 1;
	}
	
}
