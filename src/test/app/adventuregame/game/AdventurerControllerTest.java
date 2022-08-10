package adventuregame.game;

import adventuregame.world.Generators.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventurerControllerTest {

    List<Level> listOfLevels = getAllLevels();
    List<String> listOfLevelNames = getAllLevelNames();

    private List<String> getAllLevelNames() {
        List<String> levelNames = new ArrayList<>(listOfLevels.size());

        for (Level l : listOfLevels) {
            levelNames.add(l.name());
        }

        return levelNames;
    }

    private List<Level> getAllLevels() {
        List<Level> levelList = new ArrayList<>();
        Collections.addAll(levelList, Level.values());
        return levelList;
    }

    @org.junit.jupiter.api.Test
    void levelOptionsTestOne() {
        System.out.println("levelOptions TEST ONE: \n");
        AdventurerController controller = new AdventurerController();

        List<String> levelList = controller.levelOptions();

        assertEquals(listOfLevelNames, levelList);

        System.out.println("Level list: " + levelList + "\n");
        System.out.println("...Passed\n");
    }

    @org.junit.jupiter.api.Test
    void setLevelTestOne() {
        System.out.println("setLevel TEST ONE: \n");
        AdventurerController controller = new AdventurerController();

        int result = controller.setSelectedLevel("CLASSIC");

        assertEquals(0, result);

        System.out.println("...Passed\n");
    }

    @org.junit.jupiter.api.Test
    void setLevelTestTwo() {
        System.out.println("setLevel TEST TWO: \n");
        AdventurerController controller = new AdventurerController();

        int result = controller.setSelectedLevel("FAKE");

        assertEquals(-1, result);

        System.out.println("...Passed\n");
    }
}
