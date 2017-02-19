package gamestate;

import java.awt.Graphics;

import entities.Player;
import mapping.Map;

public class Level5State extends GameState {
	private Player player;
	private Map map;
	
	public Level5State(GameStateManager gsm) {
		super(gsm);
	}

	
	public void init() {
		player = new Player(30, 30);
		map = new Map("/Maps/map1.map");
		
		xOffset = -200;
		yOffset = -400;
	}

	
	public void tick() {
		player.tick(map.getBlocks());		
	}


	public void draw(Graphics g) {
		player.draw(g);
		map.draw(g);
	}

	
	public void keyPressed(int k) {
		player.kePressed(k);		
	}

	
	public void keyReleased(int k) {
		player.keyReleased(k);		
	}
}
