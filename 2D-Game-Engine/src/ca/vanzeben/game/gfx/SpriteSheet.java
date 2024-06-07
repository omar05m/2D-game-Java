package ca.vanzeben.game.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpriteSheet {
    public String path;
    public int width;
    public int height;
    public int[] pixels;

    public SpriteSheet(String path) {
        BufferedImage image = null;
        // Use a leading "/" to indicate an absolute path from the classpath root
        InputStream resourceStream = SpriteSheet.class.getResourceAsStream("/levels/" + path);

        if (resourceStream == null) {
            System.err.println("Resource not found: /levels/" + path);
            return;
        }

        try {
            image = ImageIO.read(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            System.err.println("Failed to load image: /levels/" + path);
            return;
        }

        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = image.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = (pixels[i] & 0xff) / 64;
        }
    }

    public static void main(String[] args) {
        // Test the SpriteSheet loading
        new SpriteSheet("sprite_sheet.png"); // Ensure this file exists in res/levels/
    }
}
