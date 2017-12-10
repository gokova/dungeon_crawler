package com.crawler.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteHelper {

	private static SpriteHelper instance;
	private BufferedImage img;

	private SpriteHelper() {
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("resources/images/sprite_map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SpriteHelper getInstance() {
		if (instance == null) {
			instance = new SpriteHelper();
		}
		return instance;
	}

	public BufferedImage getImage(int spriteNo) {
		int x = (spriteNo % GlobalStatics.SPRITE_PER_ROW) * GlobalStatics.GRID_SIZE;
		int y = (spriteNo / GlobalStatics.SPRITE_PER_ROW) * GlobalStatics.GRID_SIZE;
		return img.getSubimage(x, y, GlobalStatics.GRID_SIZE, GlobalStatics.GRID_SIZE);
	}

}
