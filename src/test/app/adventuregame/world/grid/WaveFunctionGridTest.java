package adventuregame.world.grid;

import adventuregame.json.Exceptions.InvalidJsonFileAddress;
import adventuregame.json.PrototypeLoader;
import adventuregame.world.locations.Prototype;

import java.io.IOException;
import java.util.List;

public class WaveFunctionGridTest {

    @org.junit.jupiter.api.Test
    void setTestOne() {
        System.out.println("set method TEST ONE:\n");
        WaveFunctionGrid<Integer> grid = new WaveFunctionGrid<>(3, 3, 12);
        grid.set(0, 2, 6);
        System.out.println(grid);
    }

    @org.junit.jupiter.api.Test
    void setTestTwo() throws IOException, ClassNotFoundException, InvalidJsonFileAddress {
        System.out.println("set method TEST TWO:\n");
        List<Prototype> prototypeList = PrototypeLoader.getPrototypeList("/Data/basic_prototypes.json");
        List<String> prototypesIds = PrototypeLoader.getListOfPrototypeIds(prototypeList);
        WaveFunctionGrid<List<String>> grid = new WaveFunctionGrid<>(3, 3, prototypesIds);
        System.out.println("Before set called\n" + grid);
        grid.set(2, 1, List.of("1"));
        System.out.println("After set called\n" + grid);

    }

    @org.junit.jupiter.api.Test
    void toStringTestOne() {
        WaveFunctionGrid<Integer> grid = new WaveFunctionGrid<>(3, 3, 12);
        System.out.println(grid);
    }
}
