package shipgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
	EnemyShip enemy;
	
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
		enemy.render();
		for(Bullet bullet : bullets)bullet.render();
		bg1.draw();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		ship = new Ship(200,400);
		bullets = new Bullet[20];
		enemy = new EnemyShip(200,100, 0, 0);
		bg1 = new Image("res/bg1.png");
		bg2 = new Image("res/bg2.png");
		Color background = new Color(128,128,128);
		for(int i = 0; i <= 19; i++){
			bullets[i] = new Bullet(-10,-10);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input input = arg0.getInput();
		ship.updateShipMovement(input);
		enemy.update();
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
			if(bullet.isCollide(enemy)){
				bullet.setXY(-10,-10);
				enemy.destroyed();
			}
		}
		delay -= 1;
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
}
