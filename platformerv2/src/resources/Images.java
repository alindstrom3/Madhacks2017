package resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	public static BufferedImage[] blocks;

	public Images() {
		blocks = new BufferedImage[3];
		try {
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/pixelblock.jpg"));
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/Blocks/lava.jpg"));
			blocks[2] = ImageIO.read(getClass().getResourceAsStream("/Blocks/victory.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
