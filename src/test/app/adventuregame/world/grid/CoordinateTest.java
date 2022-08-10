package adventuregame.world.grid;

import adventuregame.world.grid.Exceptions.IncompatibleSizeException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTest {

    @org.junit.jupiter.api.Test
    void addTestOne() throws IncompatibleSizeException {
        System.out.println("Method add TEST ONE:\n");
        Coordinate coordinate1 = new Coordinate(List.of(-3, 0));
        Coordinate coordinate2 = new Coordinate(List.of(7, 1));
        System.out.println("a: " + coordinate1);
        System.out.println("b: " + coordinate2);

        Coordinate actual = Coordinate.add(coordinate1, coordinate2);
        assertEquals(new Coordinate(List.of(4, 1)), actual);

        System.out.println("\na + b: " + actual);
        System.out.println("...passed");
    }

    @org.junit.jupiter.api.Test
    void addTestTwo() throws IncompatibleSizeException {
        System.out.println("Method add TEST TWO\n");
        Coordinate coordinate1 = new Coordinate(List.of(-3, 0, -1, 6, 3, 1));
        Coordinate coordinate2 = new Coordinate(List.of(7, 1, 0, 0, -4, 5));
        System.out.println("a: " + coordinate1);
        System.out.println("b: " + coordinate2);

        Coordinate actual = Coordinate.add(coordinate1, coordinate2);
        assertEquals(new Coordinate(List.of(4, 1, -1, 6, -1, 6)), actual);

        System.out.println("\na + b: " + actual);
        System.out.println("...passed");
    }
}
