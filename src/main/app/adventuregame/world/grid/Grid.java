package adventuregame.world.grid;

import adventuregame.world.grid.Exceptions.IncompatibleSizeException;

public interface Grid<T> {

    public int getRown();
    public int getColn();
    public void set(Coordinate coordinate, T element) throws IncompatibleSizeException;
    public T get(Coordinate coordinate) throws IncompatibleSizeException;
    public T remove(Coordinate coordinate) throws IncompatibleSizeException;
    public boolean isCellEmpty(Coordinate coordinate) throws IncompatibleSizeException;
    public int getSize();
}
