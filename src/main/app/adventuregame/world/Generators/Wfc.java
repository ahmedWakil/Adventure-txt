package adventuregame.world.Generators;

import adventuregame.json.Exceptions.InvalidJsonFileAddress;
import adventuregame.json.PrototypeLoader;
import adventuregame.world.grid.Coordinate;
import adventuregame.world.grid.Exceptions.IncompatibleSizeException;
import adventuregame.world.grid.Grid;
import adventuregame.world.grid.WaveFunctionGrid;
import adventuregame.world.locations.Prototype;

import java.io.IOException;
import java.util.*;

public class Wfc {

    public static Grid<List<String>> waveFunctionCollapse(int mapL, int mapH, String prototypeDataURL) throws IOException, ClassNotFoundException, IncompatibleSizeException {
        // load prototype data
        List<Prototype> prototypeList;
        try {
            prototypeList  = PrototypeLoader.getPrototypeList(prototypeDataURL);
        } catch (InvalidJsonFileAddress e) {
            throw new RuntimeException("Error: Json file address is invalid", e);
        }
        List<String> prototypeIds = PrototypeLoader.getListOfPrototypeIds(prototypeList);
        Map<String, Prototype> prototypeMap = PrototypeLoader.getPrototypeMap(prototypeList);

        // make a grid, fill with all possible prototype ids
        WaveFunctionGrid<List<String>> m = initialize(mapL, mapH, prototypeIds);

        // make an initial cell to collapse at the beginning
        List<Coordinate> lowEntropyCells = new ArrayList<>();
        lowEntropyCells.add(new Coordinate(List.of(randomInteger(0, mapL), randomInteger(0, mapH))));

        // loop that brakes when the wave has been completely collapsed, condition
        // to check: every single cell has does not contain one element
        boolean collapsed = false;
        while (!collapsed) {
            // 1. take the cell with the lowest entropy, pick at random if they are equal
            Coordinate coordinateCollapsed = lowEntropyCells.get(randomInteger(0, lowEntropyCells.size()));
            // 2. collapse the cell reducing the cell to just have one prototype
            List<String> currentCellPrototypeIds = m.get(coordinateCollapsed);
            String selection = currentCellPrototypeIds.get(randomInteger(0, currentCellPrototypeIds.size()));
            currentCellPrototypeIds.retainAll(List.of(selection));
            // 3. propagate the consequences of the selection
            propagate(m, prototypeMap, coordinateCollapsed);
            // 4. Find the lowest entropy cells, if they are tie add it to the list of low entropy cells list
            //    if this function returns an empty list that means all the cells have been collapsed
            lowEntropyCells = getLowEntropyCells(m);
            if (lowEntropyCells.size() == 0)
                collapsed = true;
        }

        return m;
    }

    private static WaveFunctionGrid<List<String>> initialize(int mapL, int mapH, List<String> prototypeIds) {
        WaveFunctionGrid<List<String>> m = new WaveFunctionGrid<>(mapL, mapH);

        for (int i = 0; i < m.getColn(); i++) {
            for (int j = 0; j < m.getRown(); j++) {
                List<String> element = new ArrayList<>(prototypeIds);
                m.set(i, j, new ArrayList<>(element));
            }
        }

        return m;
    }

    private static void propagate(WaveFunctionGrid<List<String>> m, Map<String, Prototype> prototypeMap, Coordinate coordinateCollapsed) throws IncompatibleSizeException {
        // create a stack, and insert the cell that has been collapsed
        Stack<Coordinate> toConstrict = new Stack<>();
        toConstrict.push(coordinateCollapsed);

        // Loop while there is cells to visit in the stack
        while (!toConstrict.isEmpty()) {
            // get the current cell's coordinate, and all the possible prototypes in this cell
            Coordinate currentCoordinate = toConstrict.pop();
            List<Prototype> currentCellPrototypes = getCurrentCellPrototypes(currentCoordinate, m, prototypeMap);
            // set the current cell to visited
            m.setVisited(currentCoordinate, true);

            for (Coordinate d : validDirections(currentCoordinate, m)) {
                // get neighbouring coordinates
                Coordinate neighbouringCoordinate = Coordinate.add(currentCoordinate, d);

                // get all the possible neighbours in that direction
                List<String> possibleNeighbours = getPossibleNeighbours(currentCellPrototypes, d);

                // constrict the cell to the new possible neighbours
                List<String> constrictedIds = constrict(m.get(neighbouringCoordinate), neighbouringCoordinate, possibleNeighbours);
                if (constrictedIds.size() != 0)
                    m.set(neighbouringCoordinate, constrictedIds);

                // we add the neighbouring coordinate to the stack to also resolve those cells as well
                if (!toConstrict.contains(neighbouringCoordinate))
                    toConstrict.push(neighbouringCoordinate);
            }
        }
    }

    private static List<String> constrict(List<String> cellIds, Coordinate neighbouringCoordinate, List<String> possibleNeighbours) throws IncompatibleSizeException {
        List<String> neighbourList = new ArrayList<>(cellIds);
        neighbourList.removeIf(current -> !possibleNeighbours.contains(current));
        return neighbourList;
    }

    private static List<Coordinate> validDirections(Coordinate coordinate, WaveFunctionGrid<List<String>> m) throws IncompatibleSizeException {
        List<Coordinate> validDirections = new ArrayList<>();
        // north
        validDirections.add(new Coordinate(List.of(0, 1)));
        // south
        validDirections.add(new Coordinate((List.of(0, -1))));
        // east
        validDirections.add(new Coordinate(List.of(1, 0)));
        // west
        validDirections.add(new Coordinate(List.of(-1, 0)));

        for (Iterator<Coordinate> iterator = validDirections.iterator(); iterator.hasNext();) {
            Coordinate other = Coordinate.add(coordinate, iterator.next());
            int x = other.getCoordinateAti(0);
            int y = other.getCoordinateAti(1);
            boolean validX = 0 <= x && x < m.getColn();
            boolean validY = 0 <= y && y < m.getRown();

            if (!validX || !validY) {
                iterator.remove();
                continue;
            }

            boolean visited = m.getVisited(other.getCoordinateAti(0), other.getCoordinateAti(1));
            if (visited)
                iterator.remove();
        }
        return validDirections;
    }

    private static List<Prototype> getCurrentCellPrototypes(Coordinate currentCell, WaveFunctionGrid<List<String>> m, Map<String, Prototype> prototypeMap) throws IncompatibleSizeException {
        List<String> prototypeIds = m.get(currentCell);
        List<Prototype> prototypes = new ArrayList<>();

        for (String id : prototypeIds) {
            prototypes.add(prototypeMap.get(id));
        }

        return prototypes;
    }

    private static List<String> getPossibleNeighbours(List<Prototype> possiblePrototypes, Coordinate direction) {
        int x = direction.getCoordinateAti(0);
        int y = direction.getCoordinateAti(1);
        Set<String> possibleNeighbours = new HashSet<>();

        for (Prototype prototype : possiblePrototypes) {
            if (x == 0 && y == 1)
                possibleNeighbours.addAll(prototype.getNorth());

            if (x == 0 && y == -1)
                possibleNeighbours.addAll(prototype.getSouth());

            if (x == 1 && y == 0)
                possibleNeighbours.addAll(prototype.getEast());

            if (x == -1 && y == 0)
                possibleNeighbours.addAll(prototype.getWest());
        }

        return new ArrayList<>(possibleNeighbours);
    }

    private static List<Coordinate> getLowEntropyCells(WaveFunctionGrid<List<String>> m) {
        int minEntropy = Integer.MAX_VALUE;
        List<Coordinate> lowEntropyCells = new ArrayList<>();

        // for loop to go through each cell on the grid
        for (int x = 0; x < m.getColn(); x++) {
            for (int y = 0; y < m.getRown(); y++) {
                m.setVisited(x, y, false);
                List<String> currentCell = m.get(x, y);

                // if the current cell has a size of one that means that cell has already been collapsed
                // if the current cell has lower than the previous
                if (currentCell.size() < minEntropy && currentCell.size() > 1) {
                    lowEntropyCells.clear();
                    lowEntropyCells.add(new Coordinate(List.of(x, y)));
                    minEntropy = currentCell.size();
                // add cells with the same entropy as the current lowest minimum entropy
                } else if (currentCell.size() == minEntropy)
                    lowEntropyCells.add(new Coordinate(List.of(x, y)));
            }
        }
        return lowEntropyCells;
    }

    public static int randomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
