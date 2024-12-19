import javax.swing.WindowConstants;
import java.awt.Color;

public class Window {
    public String title = "Pac Man";
    int rowCount = 21;
    int columnCount = 19;
    int tileSize = 32;
    int width = columnCount * tileSize;
    int height = rowCount * tileSize;
    boolean isResizable = false;
    int closeOperation = WindowConstants.EXIT_ON_CLOSE;
    Color backgroundColor = Color.BLACK;



}
