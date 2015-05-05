package util;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

public class ImageHandler {

	private static ImageHandler instance;

	private Map<String, ImageIcon> iconMap;

	private ImageHandler() {
		this.iconMap = new TreeMap<String, ImageIcon>();
	}

	public static ImageHandler getInstance() {
		if (instance == null) {
			instance = new ImageHandler();
		}
		return instance;
	}

	public static ImageIcon load(String imagePath) {
		return ImageHandler.getInstance().loadImage(imagePath);
	}

	public ImageIcon loadImage(String imagePath) {
		if (!haveImage(imagePath)) {
			this.iconMap.put(imagePath, new ImageIcon(imagePath));
			
		}	
		
		return this.iconMap.get(imagePath);
	}

	public boolean haveImage(String imagePath) {
		return this.iconMap.containsKey(imagePath);
	}

}
