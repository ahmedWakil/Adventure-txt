package adventuregame.json;

import adventuregame.json.Exceptions.InvalidJsonFileAddress;
import adventuregame.world.locations.Prototype;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PrototypeLoaderTest {

    private final String prototypeTestSourceOne = "[\n" +
            "  {\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"location1\",\n" +
            "    \"north\": [\"one\", \"two\", \"three\"],\n" +
            "    \"south\": [\"two\", \"three\"],\n" +
            "    \"east\": [\"three\"],\n" +
            "    \"west\": [\"four\", \"five\", \"six\", \"Seven\"]\n" +
            "  },\n" +
            "\n" +
            "  {\n" +
            "    \"id\": \"2\",\n" +
            "    \"name\": \"location2\",\n" +
            "    \"north\": [\"one\"],\n" +
            "    \"south\": [\"two\"],\n" +
            "    \"east\": [\"three\"],\n" +
            "    \"west\": [\"four\"]\n" +
            "  }\n" +
            "]";

    private final String prototypeTestSourceTwo = "{\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"location1\",\n" +
            "    \"north\": [\"one\", \"two\", \"three\"],\n" +
            "    \"south\": [\"two\", \"three\"],\n" +
            "    \"east\": [\"three\"],\n" +
            "    \"west\": [\"four\", \"five\", \"six\", \"Seven\"]\n" +
            "  }";

    private final String[] north1 = {"one", "two", "three"};
    private final String[] south1 = {"two", "three"};
    private final String[] east1 = {"three"};
    private final String[] west1 = {"four", "five", "six", "Seven"};

    private final String[] north2 = {"one"};
    private final String[] south2 = {"two"};
    private final String[] east2 = {"three"};
    private final String[] west2 = {"four"};

    private final Prototype prototype1 = new Prototype("1", "location1", new ArrayList<String>(Arrays.asList(north1)),
                                                                new ArrayList<String>(Arrays.asList(south1)),
                                                                new ArrayList<String>(Arrays.asList(east1)),
                                                                new ArrayList<String>(Arrays.asList(west1)));

    private final Prototype prototype2 = new Prototype("2", "location2", new ArrayList<String>(Arrays.asList(north2)),
                                                                new ArrayList<String>(Arrays.asList(south2)),
                                                                new ArrayList<String>(Arrays.asList(east2)),
                                                                new ArrayList<String>(Arrays.asList(west2)));



    @org.junit.jupiter.api.Test
    void prototypeJasonFormatTest() throws IOException, ClassNotFoundException {
        System.out.println("PROTOTYPE JSON FORMAT TEST:\n");

        JsonNode node = Json.parse(prototypeTestSourceTwo);
        String id = node.get("id").asText();
        String name = node.get("name").asText();

        List<String> north;
        List<String> south;
        List<String> east;
        List<String> west;

        north = Json.parseJsonArray(node.get("north"), String.class);
        south = Json.parseJsonArray(node.get("south"), String.class);
        east = Json.parseJsonArray(node.get("east"), String.class);
        west = Json.parseJsonArray(node.get("west"), String.class);

        Prototype prototype = new Prototype(id, name, new ArrayList<>(north),
                                                      new ArrayList<>(south),
                                                      new ArrayList<>(east),
                                                      new ArrayList<>(west));
        System.out.println(prototype);
    }

    @org.junit.jupiter.api.Test
    void prototypeTestOne() throws JsonProcessingException {
        System.out.println("\nPROTOTYPE DESERIALIZER TEST ONE:\n");

        Json.registerDeserializer(Prototype.class, new PrototypeDeserializer());
        JsonNode node = Json.parse(prototypeTestSourceTwo);
        Prototype prototype = Json.fromJason(node, Prototype.class);

        System.out.println(prototype);
    }

    @org.junit.jupiter.api.Test
    void prototypeTestTwo() throws IOException, ClassNotFoundException {
        System.out.println("\nPROTOTYPE DESERIALIZER TEST TWO:\n");

        Json.registerDeserializer(Prototype.class, new PrototypeDeserializer());
        JsonNode node = Json.parse(prototypeTestSourceOne);
        List<Prototype> prototypes = Json.parseJsonArray(node, Prototype.class);

        System.out.println(prototypes);
    }

    @org.junit.jupiter.api.Test
    void getPrototypeListTestOne() throws IOException, ClassNotFoundException, InvalidJsonFileAddress {
        System.out.println("\ngetPrototypeList TEST ONE:\n");

        List<Prototype> prototypeListActual = PrototypeLoader.getPrototypeList("/Data/prototypes_test.json");
        if (prototypeListActual == null) {
            fail("Failed to load a valid Json file ....Test incomplete");
        }

        List<Prototype> prototypeListExpected = new ArrayList<>();
        prototypeListExpected.add(prototype1);
        prototypeListExpected.add(prototype2);

        assertEquals(prototypeListExpected, prototypeListActual);
        System.out.println("...Passed");
    }

    @org.junit.jupiter.api.Test
    void getPrototypeMapTestOne() throws IOException, ClassNotFoundException, InvalidJsonFileAddress {
        System.out.println("\ngetPrototypeMap TEST ONE:\n");

        List<Prototype> prototypeList = PrototypeLoader.getPrototypeList("/Data/prototypes_test.json");
        if (prototypeList == null) {
            fail("Failed to load a valid Json file ....Test incomplete");
        }

        Map<String, Prototype> prototypeMapActual = PrototypeLoader.getPrototypeMap(prototypeList);
        Map<String, Prototype> prototypeMapExpected = new HashMap<>();
        prototypeMapExpected.put(prototype1.getId(), prototype1);
        prototypeMapExpected.put(prototype2.getId(), prototype2);

        assertEquals(prototypeMapExpected, prototypeMapActual);
        System.out.println("...Passed");
    }

    @org.junit.jupiter.api.Test
    void getListOfPrototypeIdsTestOne() throws IOException, ClassNotFoundException, InvalidJsonFileAddress {
        System.out.println("\ngetListOfPrototypeIds TEST ONE:\n");
        List<Prototype> prototypeList = PrototypeLoader.getPrototypeList("/Data/prototypes_test.json");
        if (prototypeList == null) {
            fail("Failed to load a valid Json file ....Test incomplete");
        }

        List<String> prototypeIdsActual = PrototypeLoader.getListOfPrototypeIds(prototypeList);
        List<String> prototypeIdsExpected = new ArrayList<>();
        prototypeIdsExpected.add(prototype1.getId());
        prototypeIdsExpected.add(prototype2.getId());

        assertEquals(prototypeIdsExpected, prototypeIdsActual);
        System.out.println("...Passed");
    }
}