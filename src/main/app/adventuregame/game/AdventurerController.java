package adventuregame.game;

import adventuregame.world.Generators.Level;
import adventuregame.world.Generators.LevelGenerator;
import adventuregame.world.grid.Exceptions.IncompatibleSizeException;
import adventuregame.world.locations.Location;
import adventuregame.world.maps.WorldMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventurerController {

    private int levelSize = 6;
    private int level = 0;
    private Level selectedLevel = Level.CLASSIC;
    private WorldMap theMap;

    public List<String> levelOptions() {
        List<String> levelNames = new ArrayList<>(Level.values().length);

        for (Level l : Level.values()) {
            levelNames.add(l.name());
        }

        return levelNames;
    }

    public void createMap() {
        try {
            theMap = LevelGenerator.generate(levelSize, levelSize, selectedLevel.address);
        } catch (IOException e) {
            throw new RuntimeException("Error: Something went wrong when generating the map, IOException thrown", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: Something went wrong when generating the map, ClassNotFoundException thrown", e);
        } catch (IncompatibleSizeException e) {
            throw new RuntimeException("Error: Something went wrong when generating the map, IncompatibleSizeException thrown", e);
        }
    }

    public int setSelectedLevel(String selectedLevel) {
        Level level;
        try {
            level = Level.valueOf(selectedLevel);
        } catch (IllegalArgumentException e) {
            return -1;
        }

        this.selectedLevel = level;
        return 0;
    }

    public String getSelectedLevel() {
        return selectedLevel.name();
    }

    public String currentLocationIntroduction() {
        Location location = theMap.getCurrentLocation();
        return location.getDescription();
    }

    public List<String> validDirections() {
        return theMap.validDirections();
    }

    public List<Location> peakAll(List<String> allDirections) {
        List<Location> locationList = new ArrayList<>();

        for (String d : allDirections) {
            Location location = theMap.peak(d);
            if (location != null)
                locationList.add(location);
        }

        return locationList;
    }

    public void move(String direction) {
        theMap.move(direction);
    }

    public boolean atEnd() {
        return theMap.atEnd();
    }

    public void setNextLevelSize() {
        level+=1;
        this.levelSize = getLevelSize(level);
    }

    private int getLevelSize(int level) {
        return (int) (9*Math.log((1d/45d)*level)+20);
    }

    public String gameIntoInstructions() {
        String stringBuilder = "Hello ... you ... probably cannot see me, but do not be alarmed,  " +
                "it seems we've both found ourselves in quite the predicament. " +
                "Somehow somewhere we have found ourselves in another plane of existence!" +
                "I do have an idea of where we are though. It is a place of great magic and mysteries " +
                "Beings from all over the multiverse come here seeking knowledge, treasure and gold," +
                "However dark magic guards this place, it robs us of things we used to have. " +
                "To leave this place we have to travel through a vast interconnected network of smaller planes" +
                "Each smaller plain contains a portal that leads us to the next one." +
                "Our answer lies in one of these planes" +
                "I will be your eyes and hands. Direct me with commands of one or two words. Though, " +
                "I should warn you that I look at only the first five letter of each word, so you'll have to enter " +
                "valid commands to guide me properly. Once we step through that portal there is no escape until the end. " +
                "\n\nAre you ready to begin?\n";

        return stringBuilder;
    }
}
