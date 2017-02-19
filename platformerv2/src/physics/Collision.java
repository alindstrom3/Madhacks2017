package physics;

import java.awt.Point;
import java.awt.Rectangle;

import objects.Block;

public class Collision {
	
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
}
