package shipgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Shipgame extends BasicGame{

	Ship ship;
	Bullet[] bullets;
	int delay = 0;
	boolean fire = false;
	public Shipgame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		ship.render();
		for(Bullet bullet : bullets)bullet.render();
		/*for(Bullet bullet : bullets){
			if(fire == true || bullet.getY() > 0){
				if(bullet.getY() < 0)
					bullet.setXY(ship.getX(), ship.getY());
				bullet.update();
			}
			else {
			
			}
		}*/
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		ship = new Ship(200,400);
		bullets = new Bullet[20];
		for(int i = 0; i <= 19; i++){
			bullets[i] = new Bullet(-10,-10);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input input = arg0.getInput();
		ship.updateShipMovement(input);
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
