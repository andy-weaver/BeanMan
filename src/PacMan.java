import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class PacMan extends JPanel implements ActionListener, KeyListener {
    public Window window = new Window();
    public TileMap tileMap = TileMap.makeBaseMap();
    HashSet<Block> walls = new HashSet<>();
    HashSet<Block> food = new HashSet<>();
    HashSet<Block> ghosts = new HashSet<>();
    Block pacMan;
    private Image wallImage;
    private Image foodImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image redGhostImage;
    private Image pinkGhostImage;
    private Image pacManUpImage;
    private Image pacManDownImage;
    private Image pacManLeftImage;
    private Image pacManRightImage;

    PacMan() {
        setPreferredSize(new Dimension(window.width, window.height));
        setBackground(window.backgroundColor);
        addKeyListener(this);
        setFocusable(true);

        loadImages();
        loadMap();

        Timer gameLoop = new Timer(20, this);
        gameLoop.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        draw(g);
    }

    public void move() {
        Vector pacManCurrentVelocity = pacMan.velocity;
        movePacMan();
        for (Block wall : walls) {
            if (isColliding(pacMan, wall)) {
                pacMan.velocity = pacManCurrentVelocity;
                break;
            }
        }
        moveGhosts();
    }

    private void movePacMan() {
        pacMan.move();
        undoMoveIfTouchingWall();

    }

    private void moveGhosts() {
        for (Block ghost : ghosts) {
            ghost.move();
        }
    }

    private void undoMoveIfTouchingWall() {
        for (Block wall : walls) {
            if (isColliding(pacMan, wall)) {
                pacMan.undoMove();
                break;
            }
        }
    }

    public boolean isColliding(Block a, Block b) {
        return a.getX() < b.getX() + b.width &&
                a.getX() + a.width > b.getX() &&
                a.getY() < b.getY() + b.height &&
                a.getY() + a.height > b.getY();
    }

    public void draw(Graphics g) {
        drawPacMan(g);
        drawGhosts(g);
        drawWalls(g);
        drawFood(g);
    }

    private void drawFood(Graphics g) {
        for (Block food : food) {
            g.drawImage(food.image, food.getX(), food.getY(), food.width, food.height, null);
        }
    }

    private void drawWalls(Graphics g) {
        for (Block wall : walls) {
            g.drawImage(wall.image, wall.getX(), wall.getY(), wall.width, wall.height, null);
        }
    }

    private void drawGhosts(Graphics g) {
        for (Block ghost : ghosts) {
            g.drawImage(ghost.image, ghost.getX(), ghost.getY(), ghost.width, ghost.height, null);
        }
    }

    private void drawPacMan(Graphics g) {
        g.drawImage(pacMan.image, pacMan.getX(), pacMan.getY(), pacMan.width, pacMan.height, null);
    }

    void loadMap() {
        for (int r = 0; r < tileMap.getNRows(); r++) {
            for (int c = 0; c < tileMap.getNCols(); c++) {
                char tileMapChar = tileMap.getTile(r, c);
                Point pos = new Point(c * window.tileSize, r * window.tileSize);
                int tileSize = window.tileSize;

                switch (tileMapChar) {
                    case 'X':
                        walls.add(new Block(wallImage, pos.x, pos.y, tileSize, tileSize));
                        break;
                    case 'b':
                        ghosts.add(new Block(blueGhostImage, pos.x, pos.y, tileSize, tileSize));
                        break;
                    case 'o':
                        ghosts.add(new Block(orangeGhostImage, pos.x, pos.y, tileSize, tileSize));
                        break;
                    case 'p':
                        ghosts.add(new Block(pinkGhostImage, pos.x, pos.y, tileSize, tileSize));
                        break;
                    case 'r':
                        ghosts.add(new Block(redGhostImage, pos.x, pos.y, tileSize, tileSize));
                        break;
                    case 'P':
                        pacMan = new Block(pacManRightImage, pos.x, pos.y, tileSize, tileSize);
                        break;
                    case ' ':
                        food.add(Block.centeredBlock(foodImage, pos.x, pos.y, tileSize, tileSize));
                        break;
                }
            }
        }
    }

    public void loadImages() {
        loadEnvironmentSprites();
        loadGhostSprites();
        loadPacManSprites();
    }

    public void loadEnvironmentSprites() {
        wallImage = getImage("wall");
        foodImage = getImage("food");
    }

    public void loadPacManSprites() {
        pacManUpImage = getImage("pacmanUp");
        pacManDownImage = getImage("pacmanDown");
        pacManLeftImage = getImage("pacmanLeft");
        pacManRightImage = getImage("pacmanRight");
    }

    public void loadGhostSprites() {
        blueGhostImage = getImage("blueGhost");
        orangeGhostImage = getImage("orangeGhost");
        pinkGhostImage = getImage("pinkGhost");
        redGhostImage = getImage("redGhost");
    }

    public Image getImage(String imageName) {
        return new ImageIcon(Objects.requireNonNull(getImageResource(imageName))).getImage();
    }

    public URL getImageResource(String imageName) {
        String path = getImagePath(imageName);
        return getClass().getResource(path);
    }

    public String getImagePath(String imageName) {
        return "./main/img/" + imageName + ".png";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        updatePacMan(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        updatePacMan(e);
    }

    private void updatePacMan(KeyEvent e) {
        Direction currentDirection = pacMan.velocity.direction;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case 'k':
                pacMan.updateDirection('U', walls);
                if (currentDirection == pacMan.velocity.direction) {
                    pacMan.image = pacManUpImage;
                }
                break;
            case KeyEvent.VK_DOWN:
            case 'j':
                pacMan.updateDirection('D', walls);
                if (currentDirection == pacMan.velocity.direction) {
                    pacMan.image = pacManDownImage;
                }
                break;
            case KeyEvent.VK_LEFT:
            case 'h':
                pacMan.updateDirection('L', walls);
                if (currentDirection == pacMan.velocity.direction) {
                    pacMan.image = pacManLeftImage;
                }
                break;
            case KeyEvent.VK_RIGHT:
            case 'l':
                pacMan.updateDirection('R', walls);
                if (currentDirection == pacMan.velocity.direction) {
                    pacMan.image = pacManRightImage;
                }
                break;
        }
    }

    private void updateGhost(Block ghost) {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 1:
                ghost.updateDirection('U', walls);
                break;
            case 2:
                ghost.updateDirection('D', walls);
                break;
            case 3:
                ghost.updateDirection('L', walls);
                break;
            default:
                ghost.updateDirection('R', walls);
                break;
        }
    }
}
