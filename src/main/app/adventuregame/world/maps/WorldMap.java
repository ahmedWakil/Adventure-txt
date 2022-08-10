package adventuregame.world.maps;

import adventuregame.world.locations.Location;

import java.util.List;

public interface WorldMap {

    public Location getCurrentLocation();
    public Location peak(String direction);
    public void move(String direction);
    public List<String> validDirections();
    public boolean atEnd();
}
