public enum Direction {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    public final char value;

    Direction(char value) {
        this.value = Character.toUpperCase(value);
    }

    public static Direction fromChar(char value) {
        for (Direction direction : Direction.values()) {
            if (direction.value == Character.toUpperCase(value)) {
                return direction;
            }
        }
        return null;
    }
}
