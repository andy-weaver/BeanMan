public class Vector extends Point {

    public Direction direction;

    Vector(int x, int y, Direction direction) {
        super(x, y);
        this.direction = direction;
    }

    Vector(int x, int y, char direction) {
        super(x, y);
        this.direction = Direction.fromChar(direction);
    }
}
