import java.awt.*;
import java.util.HashSet;

public class Block {
    private final Window window = new Window();
    public Point position;
    public int width;
    public int height;
    Image image;

    Point start;

    Vector velocity = new Vector(0, 0, 'R');
    Vector previousVelocity = new Vector(0, 0, 'R');

    Block(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.position = new Point(x, y);
        this.width = width;
        this.height = height;
        start = new Point(position.x, position.y);
    }

    public static Block centeredBlock(Image image, int x, int y, int imageWidth, int imageHeight) {
        int xCenter = x + 3;
        int yCenter = y + 3;
        return new Block(image, xCenter, yCenter, imageWidth, imageHeight);
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public void updateDirection(char direction, HashSet<Block> walls) {
        changeDir(direction);

        if (isHittingAnyWall(walls)) {
            undoMove();
            changeDir(previousVelocity.direction.value);
        }
    }

    public boolean isColliding(Block a, Block b) {
        return a.getX() < b.getX() + b.width &&
                a.getX() + a.width > b.getX() &&
                a.getY() < b.getY() + b.height &&
                a.getY() + a.height > b.getY();
    }

    public boolean isHittingAnyWall(HashSet<Block> walls) {
        for (Block wall : walls) {
            if (isColliding(this, wall)) {
                return true;
            }
        }
        return false;
    }

    private void changeDir(char direction) {
        previousVelocity = new Vector(velocity.x, velocity.y, velocity.direction.value);
        switch (direction) {
            case 'U':
                changeDirToUp();
                break;
            case 'D':
                changeDirToDown();
                break;
            case 'L':
                changeDirToLeft();
                break;
            case 'R':
                changeDirToRight();
                break;
        }
    }

    private void changeDirToRight() {
        velocity.x = width/4;
        velocity.y = 0;
    }

    private void changeDirToLeft() {
        velocity.x = -width/4;
        velocity.y = 0;
    }

    private void changeDirToDown() {
        velocity.x = 0;
        velocity.y = height/4;
    }

    private void changeDirToUp() {
        velocity.x = 0;
        velocity.y = -height/4;
    }

    public void move() {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public void undoMove() {
        position.x -= velocity.x;
        position.y -= velocity.y;

        velocity = previousVelocity;
    }

}
