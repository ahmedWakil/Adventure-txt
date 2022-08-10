package adventuregame.world.Generators;

import adventuregame.json.Exceptions.InvalidJsonFileAddress;
import adventuregame.world.grid.Coordinate;
import adventuregame.world.grid.Exceptions.IncompatibleSizeException;
import adventuregame.world.grid.Grid;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


public class WfcTest {


    @org.junit.jupiter.api.Test
    void WaveFunctionCollapseTestOne() throws IOException, ClassNotFoundException, IncompatibleSizeException, InvalidJsonFileAddress {
        System.out.println("WaveFunctionCollapse TEST ONE: \n");
        Grid<List<String>> grid = Wfc.waveFunctionCollapse(9, 9, "/Data/basic_prototypes.json");
        for (int i = 0; i < grid.getColn(); i++) {
            for (int j = 0; j < grid.getRown(); j++) {
                if (grid.get(new Coordinate(List.of(i, j))).size() > 1)
                    fail("Wave function is not collapsed! There should be only one prototype left in each cell");
                if (grid.get(new Coordinate(List.of(i, j))).size() == 0)
                    fail("Wave function grid has an empty cell!");
            }
        }

        System.out.println(grid);
        System.out.println("\n...Passed\n");
    }

    @org.junit.jupiter.api.Test
    void WaveFunctionCollapseTestTwo() throws IOException, ClassNotFoundException, IncompatibleSizeException, InvalidJsonFileAddress {
        System.out.println("WaveFunctionCollapse TEST TWO: \n");
        Grid<List<String>> grid = Wfc.waveFunctionCollapse(1, 1, "/Data/basic_prototypes.json");
        for (int i = 0; i < grid.getColn(); i++) {
            for (int j = 0; j < grid.getRown(); j++) {
                if (grid.get(new Coordinate(List.of(i, j))).size() > 1)
                    fail("Wave function is not collapsed! There should be only one prototype left in each cell");
                if (grid.get(new Coordinate(List.of(i, j))).size() == 0)
                    fail("Wave function grid has an empty cell!");
            }
        }

        System.out.println(grid);
        System.out.println("\n...Passed\n");
    }
}
