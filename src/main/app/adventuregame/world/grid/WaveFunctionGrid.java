package adventuregame.world.grid;

import adventuregame.world.grid.Exceptions.IncompatibleSizeException;

import java.util.ArrayList;
import java.util.List;

public class WaveFunctionGrid<T> implements Grid<T>{

    private final List<List<T>> grid;
    private final List<List<Boolean>> visited;
    private final int rown;
    private final int coln;

    public WaveFunctionGrid(int x, int y, T element) {

        if (x > 0 && y > 0) {
            this.rown = y; this.coln = x;}
        else {
            this.rown = 1; this.coln = 1;}

        grid = new ArrayList<>(y);
        visited = new ArrayList<>(y);
        for (int i = 0; i < y; i++) {
            List<T> rowGrid = new ArrayList<>(x);
            List<Boolean> rowVisited = new ArrayList<>(x);
            for (int j = 0; j < x; j++) {
                rowGrid.add(element);
                rowVisited.add(false);
            }
            this.grid.add(rowGrid);
            this.visited.add(rowVisited);
        }
    }

    public WaveFunctionGrid(int x, int y) {
        if (x > 0 && y > 0) {
            this.rown = y; this.coln = x;}
        else {
            this.rown = 1; this.coln = 1;}

        grid = new ArrayList<>(y);
        visited = new ArrayList<>(y);
        for (int i = 0; i < y; i++) {
            List<T> rowGrid = new ArrayList<>(x);
            List<Boolean> rowVisited = new ArrayList<>(x);
            for (int j = 0; j < x; j++) {
                rowGrid.add(null);
                rowVisited.add(false);
            }
            this.grid.add(rowGrid);
            this.visited.add(rowVisited);
        }
    }

    @Override
    public int getRown() {
        return rown;
    }

    @Override
    public int getColn () {
        return coln;
    }

    public void setVisited(int x, int y, boolean visited) {
        this.visited.get(y).set(x, visited);
    }

    public void setVisited(Coordinate coordinate, boolean visited) throws IncompatibleSizeException {
        if (coordinate.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        this.setVisited(coordinate.getCoordinateAti(0), coordinate.getCoordinateAti(1), visited);
    }

    public void set(int x, int y, T element) {
        this.grid.get(y).set(x, element);
    }

    @Override
    public void set(Coordinate location, T element) throws IncompatibleSizeException {
        if (location.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        this.set(location.getCoordinateAti(0), location.getCoordinateAti(1), element);
    }

    public boolean getVisited(int x, int y) {
        return this.visited.get(y).get(x);
    }

    public boolean getVisited(Coordinate coordinate) throws IncompatibleSizeException {
        if (coordinate.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        return this.getVisited(coordinate.getCoordinateAti(0), coordinate.getCoordinateAti(1));
    }

    public T get(int x, int y) {
        return this.grid.get(y).get(x);

    }

    @Override
    public T get(Coordinate location) throws IncompatibleSizeException {
        if (location.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        return this.get(location.getCoordinateAti(0), location.getCoordinateAti(1));
    }

    public T remove(int x, int y) {
        T result = this.grid.get(y).get(x);
        this.grid.get(y).set(x, null);
        return result;
    }

    @Override
    public T remove(Coordinate location) throws IncompatibleSizeException {
        if (location.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        return this.remove(location.getCoordinateAti(0), location.getCoordinateAti(1));
    }

    public boolean isCellEmpty(int x, int y) {
        return this.get(x, y) == null;
    }

    @Override
    public boolean isCellEmpty(Coordinate coordinate) throws IncompatibleSizeException {
        if (coordinate.getSize() != 2)
            throw new IncompatibleSizeException("location coordinate length is not 2");

        return this.get(coordinate) == null;
    }

    @Override
    public int getSize() {
        return this.coln * this.rown;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.rown; i++) {
            if(i == 0) {
                result.append("[");
            } else {
                result.append(" ");
            }
            result.append(this.grid.get(i).toString());

            if (i != rown - 1) {
                result.append("\n");
            }
        }

        result.append("]");
        return result.toString();
    }
}
