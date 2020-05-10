import java.util.*;
//       ~ 1 2 2 3 5 ~      _
//       ~ 3 2 3 4 4 ~      _
//       ~ 2 4 5 3 1 ~      _
//       ~ 6 7 1 4 5 ~      _
//      __ 5 1 1 2 4 ~      _
//        |~ ~ ~ ~ ~ Atlantic

class ContinentalDivide {
    static int[][] landArea = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 },
            { 5, 1, 1, 2, 4 } };

    public static void main(String[] args) {
        for (int i = 0; i < landArea.length; i++) {
            for (int j = 0; j < landArea[i].length; j++) {
                Set<Coord> processedCoords = new HashSet<Coord>();
                Oceans oceans = findOceans(new Coord(i, j), processedCoords);
                if (oceans == Oceans.Both) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }
    }

    static Oceans findOceans(Coord coord, Set<Coord> processedCoords) {
        if (processedCoords.contains(coord)) {
            return Oceans.None;
        }
        processedCoords.add(coord);

        int x = coord.x;
        int y = coord.y;
        boolean hasAtlantic = false;
        boolean hasPacific = false;
        if (x <= 0) {
            hasPacific = true;
        }
        if (x >= landArea.length - 1) {
            hasAtlantic = true;
        }
        if (y <= 0) {
            hasPacific = true;
        }
        if (y >= landArea[0].length - 1) {
            hasAtlantic = true;
        }

        Coord[] neighbours = { new Coord(x - 1, y), new Coord(x + 1, y), new Coord(x, y - 1), new Coord(x, y + 1) };
        for (Coord aCoord : neighbours) {
            if (aCoord.x >= 0 && aCoord.x < landArea.length && aCoord.y >= 0 && aCoord.y < landArea[0].length
                    && landArea[aCoord.x][aCoord.y] <= landArea[x][y]) {
                Oceans oceans = findOceans(aCoord, processedCoords);
                switch (oceans) {
                case Both:
                    hasAtlantic = true;
                    hasPacific = true;
                    break;
                case Atlantic:
                    hasAtlantic = true;
                    break;
                case Pacific:
                    hasPacific = true;
                    break;
                case None:
                    break;
                }
            }
        }

        if (hasAtlantic && hasPacific) {
            return Oceans.Both;
        } else if (hasAtlantic) {
            return Oceans.Atlantic;
        } else if (hasPacific) {
            return Oceans.Pacific;
        }
        return Oceans.None;
    }
}

class Coord {
    int x;
    int y;

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

enum Oceans {
    Pacific, Atlantic, Both, None
}