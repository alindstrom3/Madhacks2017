package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import game.GamePanel;
import gamestate.GameState;
import gamestate.IntermediateWinState;
import gamestate.LoseState;
import objects.Block;
import physics.Collision;


public class Player{
	
	//Movement Boolean
	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	
	//Bounds
	private double x,y;
	private int width, height;
	
	//Jump Speed
	private double jumpSpeed = 5.1;
	private double currentJumpSpeed = jumpSpeed;
	
	//Move Speed
	private double moveSpeed = 2.5;
	
	//Fall Speed
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;
	
	public Player(int width, int height){
		x = GamePanel.WIDTH/2;
		y = GamePanel.HEIGHT/2;
		this.width = width;
		this.height = height;
	}
	
	public void tick(Block[][] b){
		
		int ix = (int)x;
		int iy = (int)y;
		
		if (GameState.yOffset > GameState.maxFall) {
			GameState.gsm.states.push(new LoseState(GameState.gsm));
		}

		for(int i =0; i<b.length; i++){
			for(int j = 0; j < b[0].length; j++){
				if(b[i][j].getID() == 3){
					//right
					if(Collision.playerBlock(new Point(
							ix+width +(int)GameState.xOffset, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(
									ix + width + (int)GameState.xOffset, iy + height + (int)GameState.yOffset -1), b[i][j])){
						
						right = false;
						GameState.gsm.states.push(new IntermediateWinState(GameState.gsm));
					}

					//left
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset -1, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(ix + (int)GameState.xOffset -1, iy + height + (int)GameState.yOffset -1), b[i][j])){
						left =false;
						GameState.gsm.states.push(new IntermediateWinState(GameState.gsm));    
					}

					//top
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset +1,iy + (int)GameState.yOffset ), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset-2, iy + (int)GameState.yOffset), b[i][j])){
						jumping = false;
						falling = true;
						GameState.gsm.states.push(new IntermediateWinState(GameState.gsm));
					}

					//bottom
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset +2, iy + height + (int)GameState.yOffset +1), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset -2, iy + height + (int)GameState.yOffset +1), b[i][j])){
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
						GameState.gsm.states.push(new IntermediateWinState(GameState.gsm));
					}else{
						if(!topCollision && !jumping){
							falling = true;
						}
					}
				}
				else if(b[i][j].getID() == 2){
					//right
					if(Collision.playerBlock(new Point(
							ix+width +(int)GameState.xOffset, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(
									ix + width + (int)GameState.xOffset, iy + height + (int)GameState.yOffset -1), b[i][j])){
						
						right = false;
						GameState.gsm.states.push(new LoseState(GameState.gsm));
					}

					//left
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset -1, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(ix + (int)GameState.xOffset -1, iy + height + (int)GameState.yOffset -1), b[i][j])){
						left =false;
						GameState.gsm.states.push(new LoseState(GameState.gsm));    
					}

					//top
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset +1,iy + (int)GameState.yOffset ), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset-2, iy + (int)GameState.yOffset), b[i][j])){
						jumping = false;
						falling = true;
						GameState.gsm.states.push(new LoseState(GameState.gsm));
					}

					//bottom
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset +2, iy + height + (int)GameState.yOffset +1), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset -2, iy + height + (int)GameState.yOffset +1), b[i][j])){
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
						GameState.gsm.states.push(new LoseState(GameState.gsm));
					}else{
						if(!topCollision && !jumping){
							falling = true;
						}
					}
				}
				else if(b[i][j].getID() == 1){
					//right
					if(Collision.playerBlock(new Point(
							ix+width +(int)GameState.xOffset, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(
									ix + width + (int)GameState.xOffset, iy + height + (int)GameState.yOffset -1), b[i][j])){

						right = false;
					}

					//left
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset -1, iy + (int)GameState.yOffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(ix + (int)GameState.xOffset -1, iy + height + (int)GameState.yOffset -1), b[i][j])){
						left =false;
					}

					//top
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset +1,iy + (int)GameState.yOffset ), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset-2, iy + (int)GameState.yOffset), b[i][j])){
						jumping = false;
						falling = true;
					}

					//bottom
					if(Collision.playerBlock(new Point(ix + (int)GameState.xOffset +2, iy + height + (int)GameState.yOffset +1), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xOffset -2, iy + height + (int)GameState.yOffset +1), b[i][j])){
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
					}else{
						if(!topCollision && !jumping){
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
		if(!falling){
			currentFallSpeed = .1;
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