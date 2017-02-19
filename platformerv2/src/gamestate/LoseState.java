package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.GamePanel;

public class LoseState extends GameState {

	private String[] options = {"Try Again", "Return to Main Menu", "Quit"};
	private int currentSelection = 0;
	
	public LoseState(GameStateManager gsm) {
		super(gsm);
	}

	
	public void init() {}

	
	public void tick() {}

	
	public void draw(Graphics g) {
		g.setColor(new Color(200, 100, 50));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for(int i = 0; i < options.length; i++) {
			if(i == currentSelection){
				g.setColor(Color.RED);
			}else{
				g.setColor(Color.WHITE);
			}
			
			//g.drawLine(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT);
			g.setFont(new Font("Arial", Font.PLAIN, 72));
			g.drawString(options[i], GamePanel.WIDTH/2 - 400, 200 + i*150);
		}	
		g.setColor(Color.DARK_GRAY);
		g.drawString("You Lose!", GamePanel.WIDTH / 2 - 200, 100);
	}

	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_DOWN){
			currentSelection++;
			if(currentSelection >= options.length){
				currentSelection = 0;
			}
		}else if(k == KeyEvent.VK_UP){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = options.length - 1;
			}
		}
		
		if(k == KeyEvent.VK_ENTER){
			if(currentSelection == 0){
				GameState.currentLevel = 1;
				GameState.attempts++;
				GameState.maxFall = 1000;
				gsm.states.push(new Level1State(gsm));
			}
			else if(currentSelection == 1) {
				gsm.states.push(new MenuState(gsm));
			}
			else if(currentSelection == 2){
				System.exit(0);
			}
		}
	}

	
	public void keyReleased(int k) {
		
		
	}
	
}
