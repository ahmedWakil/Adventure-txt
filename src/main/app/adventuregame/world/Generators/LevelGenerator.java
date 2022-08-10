package adventuregame.world.Generators;

import adventuregame.json.Exceptions.InvalidJsonFileAddress;
import adventuregame.json.PrototypeLoader;
import adventuregame.world.grid.Coordinate;
import adventuregame.world.grid.Exceptions.IncompatibleSizeException;
import adventuregame.world.grid.Grid;
import adventuregame.world.locations.Location;
import adventuregame.world.locations.Prototype;
import adventuregame.world.maps.GridMap;
import adventuregame.world.maps.WorldMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LevelGenerator {

    public static WorldMap generate(int L, int H, String levelUrl) throws IOException, ClassNotFoundException, IncompatibleSizeException{
        List<Prototype> prototypeList;
        try {
            prototypeList  = PrototypeLoader.getPrototypeList(levelUrl);
        } catch (InvalidJsonFileAddress e) {
            throw new RuntimeException("Error: Json file address is invalid", e);
        }
        Map<String, Prototype> prototypeMap = PrototypeLoader.getPrototypeMap(prototypeList);

        Grid<List<String>> worldGrid = Wfc.waveFunctionCollapse(L, H, levelUrl);

        Coordinate start = new Coordinate(List.of(0, 0));
        Coordinate end = new Coordinate(List.of(Wfc.randomInteger(L/2, L), Wfc.randomInteger(H/2, H)));
        GridMap finalMap = new GridMap(worldGrid.getColn(), worldGrid.getRown(), start, end);

        for (int i = 0; i < worldGrid.getColn(); i++) {
            for (int j = 0; j < worldGrid.getRown(); j++) {
                Coordinate current = new Coordinate(List.of(i, j));
                String id = worldGrid.get(current).get(0);
                Prototype prototype = prototypeMap.get(id);
                String name = prototype.getName();
                Location location = new Location(id, name, "You have found yourself at a(n) " + name);
                finalMap.setLocation(current, location);
            }
        }

        return finalMap;
    }
}
