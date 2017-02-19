package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gamestate.GameState;
import resources.Images;

public class Block extends Rectangle {
	public static final long serialVersionUID = 1L;
	
	public static final int blockSize = 64;
	private int id;
	
	public Block(int x, int y, int id) {
		setBounds(x, y, blockSize, blockSize);
		this.id = id;
	}
	
	public void tick() {
	}
	
	public void draw(Graphics g) {
		if (id == 3) {
			g.setColor(Color.GREEN);
			g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);
		}
		else if (id == 2) {
			g.setColor(Color.RED);
			g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);
		}
		else 
			g.setColor(Color.BLACK);
		if(id != 0 && id != 2 && id != 3) {
			g.drawImage(Images.blocks[id - 1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
		}
	}
	
	// getters and setters
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
}
