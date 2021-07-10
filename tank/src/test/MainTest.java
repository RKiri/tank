package test;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/Kiri/Pictures/Cyberpunk 2077/photomode_17042021_141011.png"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}