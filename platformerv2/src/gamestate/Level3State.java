package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import entities.Player;
import game.GamePanel;
import mapping.Map;

public class Level3State extends GameState {
	private Player player;
	private Map map;
	
	public Level3State(GameStateManager gsm) {
		super(gsm);
	}

	
	public void init() {
		player = new Player(30, 30);
		map = new Map("/Maps/map3.map");
		
		xOffset = -200;
		yOffset = -400;
	}

	
	public void tick() {
		player.tick(map.getBlocks());		
	}


	public void draw(Graphics g) {
		player.draw(g);
		map.draw(g);
		g.setFont(new Font("Arial", Font.BOLD, 42));
		g.setColor(Color.DARK_GRAY);
		g.drawString("Level: 3", GamePanel.WIDTH / 2 + 250, 50);
		g.drawString("Attempts: " + GameState.attempts, GamePanel.WIDTH / 2 - 440, 50);
	}

	
	public void keyPressed(int k) {
		player.kePressed(k);		
	}

	
	public void keyReleased(int k) {
		player.keyReleased(k);		
	}
}
