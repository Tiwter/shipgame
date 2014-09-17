package shipgame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Shipgame extends BasicGame{

	Ship ship;
	public Shipgame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		ship.render();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		ship = new Ship(200,50);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input input = arg0.getInput();
		ship.updateShipMovement(input, arg1);	
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
