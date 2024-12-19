public class Point {
    public int x;
    public int y;
    private final Window window = new Window();

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point coordinateToPixel() {
        return new Point(x * window.tileSize, y * window.tileSize);
    }
}
