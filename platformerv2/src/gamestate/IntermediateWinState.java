package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.GamePanel;

public class IntermediateWinState extends GameState {

	private String[] options = {"Continue to Next Level", "Replay Level", "ReturnToMainMenu", "Quit"};
	private int currentSelection = 0;
	
	
	public IntermediateWinState(GameStateManager gsm){
		super(gsm);
	}

	public void init() {}
				
	public void tick() {
				
	}

	public void draw(Graphics g) {
		if(GameState.currentLevel == 3) {
			gsm.states.push(new WinState(gsm));
		}
		g.setColor(new Color(200, 100, 50));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			
		for(int i =0; i < options.length; i++){
			if(i == currentSelection){
				g.setColor(Color.RED);
			}else{
				g.setColor(Color.GRAY);
			}
				
			//g.drawLine(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT);
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString(options[i], GamePanel.WIDTH/2 - 400, 200 + i*100);
		}
		g.setColor(Color.DARK_GRAY);
		g.drawString("You Passed the Level!", GamePanel.WIDTH / 2 - 250, 100);
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
				if (GameState.currentLevel == 1) {
					GameState.currentLevel = 2;
					gsm.states.push(new Level2State(gsm));
				}
				else if(GameState.currentLevel == 2) {
					GameState.currentLevel = 3;
					gsm.states.push(new Level3State(gsm));
				}
			}
			else if(currentSelection == 1){
				if (GameState.currentLevel == 1) {
					gsm.states.push(new Level1State(gsm));
				}
				else if(GameState.currentLevel == 2) {
					gsm.states.push(new Level2State(gsm));
				}
			}
			else if(currentSelection == 2) {
				gsm.states.push(new MenuState(gsm));
			}
			else if(currentSelection == 3) {
				System.exit(0);
			}
		}
				
	}

	public void keyReleased(int k) {
				
	}
	
}
