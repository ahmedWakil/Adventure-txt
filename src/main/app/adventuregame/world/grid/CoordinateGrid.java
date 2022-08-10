package adventuregame.world.grid;

import adventuregame.world.grid.Exceptions.IncompatibleSizeException;

import java.util.ArrayList;
import java.util.List;

public class CoordinateGrid<T> implements Grid<T>{

    private final List<List<T>> grid;
    private final int rown;
    private final int coln;

    private List<Coordinate> visitedLocationsList = new ArrayList<>();

    public CoordinateGrid(int x, int y) {
        if (x > 0 && y > 0) {
            this.rown = y; this.coln = x;}
        else {
            this.rown = 1; this.coln = 1;}

        grid = new ArrayList<>(y);
        for (int i = 0; i < y; i++) {
            List<T> rowGrid = new ArrayList<>(x);
            for (int j = 0; j < x; j++) {
                rowGrid.add(null);
            }
            this.grid.add(rowGrid);
        }
    }
    @Override
    public int getRown() {
        return rown;
    }

    @Override
    public int getColn() {
        return coln;
    }

    @Override
    public void set(Coordinate coordinate, T element) throws IncompatibleSizeException {
        if (coordinate.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        grid.get(coordinate.getCoordinateAti(1)).set(coordinate.getCoordinateAti(0), element);
    }

    @Override
    public T get(Coordinate coordinate) throws IncompatibleSizeException {
        if (coordinate.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        return grid.get(coordinate.getCoordinateAti(1)).get(coordinate.getCoordinateAti(0));
    }

    @Override
    public T remove(Coordinate coordinate) throws IncompatibleSizeException {
        if (coordinate.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        T element = get(coordinate);
        set(coordinate, null);
        return element;
    }

    @Override
    public boolean isCellEmpty(Coordinate coordinate) throws IncompatibleSizeException {
        return false;
    }

    @Override
    public int getSize() {
        return rown*coln;
    }
}
