package adventuregame.world.maps;

import adventuregame.world.grid.Coordinate;
import adventuregame.world.grid.CoordinateGrid;
import adventuregame.world.grid.Exceptions.IncompatibleSizeException;
import adventuregame.world.locations.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridMap implements WorldMap {

    public final Map<String, Coordinate> directions = compass();
    private final CoordinateGrid<Location> m;
    private final Coordinate end;
    private Coordinate currentLocation;

    public GridMap(int mapL, int mapH, Coordinate start, Coordinate end) {
        m = new CoordinateGrid<>(mapL, mapH);
        currentLocation = start;
        this.end = end;
    }

    public void setLocation(Coordinate coordinate, Location location) {
        try {
            m.set(coordinate, location);
        } catch (IncompatibleSizeException e) {
            throw new RuntimeException("Error: Coordinate size mismatch in WorldMap, this is a 2D map", e);
        }
    }

    private Map<String, Coordinate> compass() {
        Map<String, Coordinate> directions = new HashMap<>();

        directions.put("NORTH", new Coordinate(List.of(0, 1)));
        directions.put("SOUTH", new Coordinate(List.of(0, -1)));
        directions.put("EAST", new Coordinate(List.of(1, 0)));
        directions.put("WEST", new Coordinate(List.of(-1, 0)));

        return directions;
    }

    @Override
    public Location getCurrentLocation() {
        Location location;
        try {
            location =  m.get(currentLocation);
        } catch (IncompatibleSizeException e) {
            throw new RuntimeException("Error: Coordinate size mismatch in WorldMap, this is a 2D map", e);
        }

        return location;
    }

    @Override
    public Location peak(String direction) {
        Location location;
        try {
            location = m.get(currentLocation.add(directions.get(direction)));
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (IncompatibleSizeException e) {
            throw new RuntimeException("Error: Coordinate size mismatch in WorldMap, this is a 2D map", e);
        }

        return location;
    }

    @Override
    public void move(String direction) {
        try {
            currentLocation = currentLocation.add(directions.get(direction));
        } catch (IncompatibleSizeException e) {
            throw new RuntimeException("Error: Coordinate size mismatch in WorldMap, this is a 2D map", e);
        }
    }

    @Override
    public List<String> validDirections() {
        List<String> validDirections = new ArrayList<>();
        for (String d : directions.keySet()) {
            if (peak(d) != null)
                validDirections.add(d);
        }

        return validDirections;
    }

    @Override
    public boolean atEnd() {
        return currentLocation.equals(end);
    }

    @Override
    public String toString() {
        return m.toString();
    }
}
