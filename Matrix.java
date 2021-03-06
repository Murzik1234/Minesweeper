package sweeper;

class Matrix {
    private Box [] [] field;

    Matrix (Box defaultBox) {
        field = new Box [Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords())
            field[coord.x] [coord.y] = defaultBox;
    }

    Box get (Coord coord) {
        if (Ranges.inRange (coord))
            return field[coord.x] [coord.y];
        return null;
    }

    void set (Coord coord, Box box) {
        if (Ranges.inRange (coord))
            field[coord.x] [coord.y] = box;

    }

}
