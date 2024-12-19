import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacManTest {
    private final String[] imageNames = {"wall", "powerFood", "pacmanUp", "pacmanDown", "pacmanLeft", "pacmanRight", "blueGhost", "orangeGhost", "pinkGhost", "redGhost"};

    @Test
    void testGetImagePath() {
        for (String s : imageNames) {
            String expectedPath = "./main/img/" + s + ".png";
            String actualPath = new PacMan().getImagePath(s);
            assertEquals(expectedPath, actualPath);
        }
    }

    @Test
    void testGetImageResource() {
        for (String s : imageNames) {
            URL expectedImageResource = getClass().getResource("./main/img/" + s + ".png");
            URL actualImageResource = new PacMan().getImageResource(s);
            assertEquals(expectedImageResource, actualImageResource);
        }
    }

    @Test
    void testGetImage() {
        for (String s : imageNames) {
            String path = new PacMan().getImagePath(s);
            URL imageResource = getClass().getResource(path);
            assertEquals(new PacMan().getImage(s), new ImageIcon(Objects.requireNonNull(imageResource)).getImage());
        }
    }
}
