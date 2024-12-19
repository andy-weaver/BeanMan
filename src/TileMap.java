public class TileMap {
    public static String[] layout;

    public int getNRows() {
        return layout.length;
    }

    public int getNCols() {
        return layout[0].length();
    }

    public String getRow(int r) {
        return layout[r];
    }

    public char getTile(int r, int c) {
        return layout[r].charAt(c);
    }

    public String getCol(int c) {
        StringBuilder col = new StringBuilder();
        for (String row : layout) {
            col.append(row.charAt(c));
        }
        return col.toString();
    }

    public static TileMap makeBaseMap() {
        TileMap map = new TileMap();
        layout = new String[]{
                "XXXXXXXXXXXXXXXXXXX",
                "X        X        X",
                "X XX XXX X XXX XX X",
                "X                 X",
                "X XX X XXXXX X XX X",
                "X    X       X    X",
                "XXXX XXXX XXXX XXXX",
                "OOOX X       X XOOO",
                "XXXX X XXrXX X XXXX",
                "O       bpo       O",
                "XXXX X XXXXX X XXXX",
                "OOOX X       X XOOO",
                "XXXX X XXXXX X XXXX",
                "X        X        X",
                "X XX XXX X XXX XX X",
                "X  X     P     X  X",
                "XX X X XXXXX X X XX",
                "X    X   X   X    X",
                "X XXXXXX X XXXXXX X",
                "X                 X",
                "XXXXXXXXXXXXXXXXXXX"
        };
        return map;
    }
}
