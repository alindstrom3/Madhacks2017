package gamestate;

import java.awt.Graphics;

public abstract class GameState {
	
	public static GameStateManager gsm;
	public static double xOffset, yOffset;
	public static int currentLevel = 0;
	public static int attempts = 0;
	public static int maxFall = 3500;
	
	public GameState(GameStateManager gsm){
		GameState.gsm = gsm;
		init();
	}

	public abstract void init();
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
