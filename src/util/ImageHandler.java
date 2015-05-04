package util;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

public class ImageHandler {

	private static Map<String, ImageIcon> iconMap = new TreeMap<String, ImageIcon>();

	public static ImageIcon load(String iconPath) {
		if (!iconMap.containsKey(iconPath)) {
			iconMap.put(iconPath, new ImageIcon(iconPath));
		}
		return iconMap.get(iconPath);
	}
}
