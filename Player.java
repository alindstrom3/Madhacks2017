package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import game.GamePanel;
import gamestate.GameState;
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
	private double jumpSpeed = 5;
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

		for(int i =0; i<b.length; i++){
			for(int j = 0; j < b.length; j++){
				if(b[i][j].getID() == 2){
					//right
					if(Collision.playerBlock(new Point(
							ix+width +(int)GameState.xoffset, iy + (int)GameState.yoffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(
									ix + width + (int)GameState.xoffset, iy + height + (int)GameState.yoffset -1), b[i][j])){
						
						right = false;
						System.exit(0);
					}

					//left
					if(Collision.playerBlock(new Point(ix + (int)GameState.xoffset -1, iy + (int)GameState.yoffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(ix + (int)GameState.xoffset -1, iy + height + (int)GameState.yoffset -1), b[i][j])){
						left =false;
						System.exit(0);    
					}

					//top
					if(Collision.playerBlock(new Point(ix + (int)GameState.xoffset +1,iy + (int)GameState.yoffset ), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xoffset-2, iy + (int)GameState.yoffset), b[i][j])){
						
						System.exit(0);
						jumping = false;
						falling = true;
					}

					//bottom
					if(Collision.playerBlock(new Point(ix + (int)GameState.xoffset +2, iy + height + (int)GameState.yoffset +1), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xoffset -2, iy + height + (int)GameState.yoffset +1), b[i][j])){
						y = b[i][j].getY() - height - GameState.yoffset;
						falling = false;
						topCollision = true;
						System.exit(0);
					}else{
						if(!topCollision && !jumping){
							falling = true;
						}
					}
				}
				if(b[i][j].getID() != 0){
					//right
					if(Collision.playerBlock(new Point(
							ix+width +(int)GameState.xoffset, iy + (int)GameState.yoffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(
									ix + width + (int)GameState.xoffset, iy + height + (int)GameState.yoffset -1), b[i][j])){

						right = false;
					}

					//left
					if(Collision.playerBlock(new Point(ix + (int)GameState.xoffset -1, iy + (int)GameState.yoffset + 2), b[i][j])
							|| Collision.playerBlock(new Point(ix + (int)GameState.xoffset -1, iy + height + (int)GameState.yoffset -1), b[i][j])){
						left =false;
					}

					//top
					if(Collision.playerBlock(new Point(ix + (int)GameState.xoffset +1,iy + (int)GameState.yoffset ), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xoffset-2, iy + (int)GameState.yoffset), b[i][j])){
						jumping = false;
						falling = true;
					}

					//bottom
					if(Collision.playerBlock(new Point(ix + (int)GameState.xoffset +2, iy + height + (int)GameState.yoffset +1), b[i][j])
							|| Collision.playerBlock(new Point(ix + width + (int)GameState.xoffset -2, iy + height + (int)GameState.yoffset +1), b[i][j])){
						y = b[i][j].getY() - height - GameState.yoffset;
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
			GameState.xoffset += moveSpeed;
		}
		
		if(left){
			GameState.xoffset -= moveSpeed;
		}
		if(jumping){
			GameState.yoffset -= currentJumpSpeed;
			
			currentJumpSpeed -= .1;
			
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
			
		}
		if(falling){
			GameState.yoffset += currentFallSpeed;
			
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
