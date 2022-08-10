package adventuregame.world.locations;

import testclasses.Author;
import testclasses.Books;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrototypeTest {

    private final String[] north1 = {"one", "two", "three"};
    private final String[] south1 = {"two", "three"};
    private final String[] east1 = {"three"};
    private final String[] west1 = {"four", "five", "six", "Seven"};

    private final String[] north2 = {"one"};
    private final String[] south2 = {"two"};
    private final String[] east2 = {"three"};
    private final String[] west2 = {"four"};

    private final Prototype prototype1 = new Prototype("1", "location1", new ArrayList<>(Arrays.asList(north1)),
                                                                        new ArrayList<>(Arrays.asList(south1)),
                                                                        new ArrayList<>(Arrays.asList(east1)),
                                                                        new ArrayList<>(Arrays.asList(west1)));

    private final Prototype prototype2 = new Prototype("2", "location2", new ArrayList<>(Arrays.asList(north2)),
                                                                        new ArrayList<>(Arrays.asList(south2)),
                                                                        new ArrayList<>(Arrays.asList(east2)),
                                                                        new ArrayList<>(Arrays.asList(west2)));

    @org.junit.jupiter.api.Test
    void equalsTestOne() {
        System.out.println("Prototype equals TEST ONE:\n");
        assertNotEquals(prototype1, prototype2);
        System.out.println("...Passed");
    }

    @org.junit.jupiter.api.Test
    void equalsTestTwo() {
        System.out.println("Prototype equals TEST TWO:\n");
        Prototype prototypeTest = new Prototype("1", "location1", new ArrayList<>(Arrays.asList(north1)),
                                                                        new ArrayList<>(Arrays.asList(south1)),
                                                                        new ArrayList<>(Arrays.asList(east1)),
                                                                        new ArrayList<>(Arrays.asList(west1)));

        assertEquals(prototype1, prototypeTest);
        System.out.println("...Passed");
    }

    @org.junit.jupiter.api.Test
    void equalsTestThree(){
        System.out.println("Prototype equals TEST THREE:\n");
        Books book1 = new Books();
        book1.setTitle("Number one book");
        book1.setInPrint(false);

        Books book2 = new Books();
        book2.setTitle("Number two book");
        book2.setInPrint(true);

        List<Books> booksList = new ArrayList<>();
        booksList.add(book1);
        booksList.add(book2);

        Author testThreeClass = new Author();
        testThreeClass.setAuthorName("bob");
        testThreeClass.setBooks(booksList);

        assertFalse(prototype1.equals(testThreeClass));
        System.out.println("...Passed");
    }
}
