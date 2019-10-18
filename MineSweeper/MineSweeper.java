import java.util.Random;
import java.util.Set;
import java.util.HashSet;

// 0  0  0  0  0
// 0  0  0  0  0
// 1  1  1  0  0
// -  -  1  0  0
// -  -  2  1  0
// -  -  -  1  0
// -  -  -  1  0

class MineSweeper {
    public static void main(String[] args) {
        int nbrMines = 3;
        int width = 5;
        int height = 7;

        PlayingField field = new PlayingField(nbrMines, width, height);
        System.out.print(field);
    }
}

class PlayingField {
    private int width;
    private int height;
    private int[][] field;

    PlayingField(int nbrMines, int width, int height) {
        this.field = new int[height][width];
        this.width = width;
        this.height = height;

        int nbrCoords = (width * height) / 2;
        if (nbrMines < nbrCoords) {
            this.generateRandomMines(nbrMines);
        } else {
            this.generateRandomEmtpyPlaces();
        }
    }

    private void generateRandomMines(int nbrMines) {
        Set<Coord> mineCoords = new HashSet<Coord>();
        Random random = new Random();
        while (mineCoords.size() < nbrMines) {
            int x = random.nextInt(this.width);
            int y = random.nextInt(this.height);
            Coord cord = new Coord(x, y);
            mineCoords.add(cord);
        }
        this.generateHints(mineCoords);
    }

    private void generateRandomEmtpyPlaces() {

    }

    private void generateHints(Set<Coord> mineCoords) {
        for (Coord mineCoord : mineCoords) {
            int[] xCoords = { mineCoord.x - 1, mineCoord.x, mineCoord.x + 1 };
            int[] yCoords = { mineCoord.y - 1, mineCoord.y, mineCoord.y + 1 };
            for (int xCoord : xCoords) {
                for (int yCoord : yCoords) {
                    boolean xIsValid = xCoord >= 0 && xCoord < this.width;
                    boolean yIsValid = yCoord >= 0 && yCoord < this.height;
                    if (xIsValid && yIsValid) {
                        if (xCoord == mineCoord.x && yCoord == mineCoord.y) {
                            field[yCoord][xCoord] = 9;
                        } else {
                            field[yCoord][xCoord] += 1;
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        String fieldStr = "\n";
        for (int[] row : field) {
            fieldStr += "   ";
            for (int i : row) {
                fieldStr += String.valueOf(i) + "   ";
            }
            fieldStr += "\n";
        }
        fieldStr += "\n";
        return fieldStr;
    }
}

class Coord {
    public int x;
    public int y;

    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        Coord otherCoord = (Coord) other;
        return otherCoord.x == this.x && otherCoord.y == this.y;
    }

    @Override
    public int hashCode() {
        return String.format("%dx%d", this.x, this.y).hashCode();
    }
}
