package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.GamePanel;
import gamestate.GameState;
import jdk.nashorn.internal.ir.Block;
import physics.Collision;

public class Player{

	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	
	private double x,y;
	private int width, height;
	
	// move speed
	private double moveSpeed = 2.5;
	
	//Jump Speed
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;
	
	//Fall Speed
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;
	
	public Player(int width, int height){
		x = GamePanel.WIDTH/2;
		y = GamePanel.HEIGHT/2;
		this.width = width;
		this.height = height;
	}
	
	public void tick(objects.Block[][] b){
		
		int iX = (int)x;
		int iY = (int)y;
		
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				if(b[i][j].getID() != 0) {
					// right
					if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset - 1), b[i][j])) {
						right = false;
					}
					
					// left
					if (Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), b[i][j]))
						left = false;
					
					// top
					if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i][j])
							|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), b[i][j])) {
						jumping = false;
						falling = true;
					}
					
					// bottom
					if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i][j])
							|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), b[i][j])) {
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
					} 
					else {
						if(!topCollision && !jumping) {
							falling = true;
						}
					}
				}
			}
		}
		
		topCollision = false;
		
		if(right){
			GameState.xOffset += moveSpeed;
		}
		
		if(left){
			GameState.xOffset -= moveSpeed;
		}
		if(jumping){
			GameState.yOffset -= currentJumpSpeed;
			
			currentJumpSpeed -= .1;
			
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
			
		}
		if(falling){
			GameState.yOffset += currentFallSpeed;
			
			if(currentFallSpeed < maxFallSpeed){
				currentFallSpeed += .1;
			}
		}
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect((int)x,(int) y, width, height);
	}
	
	public void kePressed(int k){
		if(k== KeyEvent.VK_D){
			right = true;
		}
		if(k==KeyEvent.VK_A){
			left = true;
		}
		if(k == KeyEvent.VK_SPACE && !jumping && !falling){
			jumping = true;
		}
	}
	
	public void keyReleased(int k){
		if(k== KeyEvent.VK_D){
			right = false;
		}
		if(k==KeyEvent.VK_A){
			left = false;
		}
	}
}
