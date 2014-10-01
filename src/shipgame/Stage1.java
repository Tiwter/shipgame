package shipgame;

import org.newdawn.slick.SlickException;

public class Stage1 {
	public EnemyShip[] start() throws SlickException{
		EnemyShip[] enemies = new EnemyShip[10];
		enemies[0] = new EnemyShip(100, -100, 2, 0);
		enemies[1] = new EnemyShip(100, -120, 2, 0);
		enemies[2] = new EnemyShip(100, -140, 2, 0);
		enemies[3] = new EnemyShip(100, -160, 2, 0);
		enemies[4] = new EnemyShip(100, -180, 2, 0);
		enemies[5] = new EnemyShip(420, -300, 2, 0);
		enemies[6] = new EnemyShip(420, -320, -2, 0);
		enemies[7] = new EnemyShip(420, -340, -2, 0);
		enemies[8] = new EnemyShip(420, -360, -2, 0);
		enemies[9] = new EnemyShip(420, -380, -2, 0);
		return enemies;
		
	}
}
