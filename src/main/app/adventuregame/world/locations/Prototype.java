package adventuregame.world.locations;

import java.util.List;

public class Prototype {
    private String id;
    private String name;
    private List<String> north;
    private List<String> south;
    private List<String> east;
    private List<String> west;

    public Prototype(String id, String name, List<String> north,
                                             List<String> south,
                                             List<String> east,
                                             List<String> west) {
        this.id = id;
        this.name = name;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getNorth() {
        return north;
    }

    public List<String> getSouth() {
        return south;
    }

    public List<String> getEast() {
        return east;
    }

    public List<String> getWest() {
        return west;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNorth(List<String> north) {
        this.north = north;
    }

    public void setSouth(List<String> south) {
        this.south = south;
    }

    public void setEast(List<String> east) {
        this.east = east;
    }

    public void setWest(List<String> west) {
        this.west = west;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(this.getClass().getName().equals(obj.getClass().getName()))) {
            return false;
        }

        Prototype prototypeTest = (Prototype) obj;

        boolean id = this.id.equals(prototypeTest.getId());
        boolean name = this.name.equals(prototypeTest.getName());
        boolean north = this.north.equals(prototypeTest.getNorth());
        boolean south = this.south.equals(prototypeTest.getSouth());
        boolean east = this.east.equals(prototypeTest.getEast());
        boolean west = this.west.equals(prototypeTest.getWest());

        return id && name && north && south && east && west;
    }

    @Override
    public String toString() {
        String result = "";
        result += "id: " + id;
        result += "\n" + "name: " + name;
        result += "\n" + "North -> " + north.toString();
        result += "\n" + "South -> " + south.toString();
        result += "\n" + "East -> " + east.toString();
        result += "\n" + "West -> " + west.toString();
        return result;
    }
}
