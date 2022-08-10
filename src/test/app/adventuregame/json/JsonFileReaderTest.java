package adventuregame.json;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFileReaderTest {

    private final String jsonStringOne = "[\n" +
            "  {\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"location1\",\n" +
            "    \"north\": [\"one\", \"two\", \"three\"],\n" +
            "    \"south\": [\"two\", \"three\"],\n" +
            "    \"east\": [\"three\"],\n" +
            "    \"west\": [\"four\", \"five\", \"six\", \"Seven\"]\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"2\",\n" +
            "    \"name\": \"location2\",\n" +
            "    \"north\": [\"one\"],\n" +
            "    \"south\": [\"two\"],\n" +
            "    \"east\": [\"three\"],\n" +
            "    \"west\": [\"four\"]\n" +
            "  }\n" +
            "]";
    private final String jsonStringTwo = "[\n" +
            "  {\n" +
            "    \"authorName\": \"testName1\",\n" +
            "    \"books\": [\n" +
            "      {\n" +
            "        \"title\": \"title1\",\n" +
            "        \"inPrint\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"title\": \"title2\",\n" +
            "        \"inPrint\": false\n" +
            "      }\n" +
            "      ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"authorName\": \"testName2\",\n" +
            "    \"books\": [\n" +
            "      {\n" +
            "        \"title\": \"title3\",\n" +
            "        \"inPrint\": true\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    @org.junit.jupiter.api.Test
    void readJsonFileTestOne() throws IOException {
        System.out.println("readJsonFile TEST ONE:\n");
        JsonFileReader jsonFileReader = new JsonFileReader();
        int result = jsonFileReader.readJsonFile("/Data/prototypes_test.json");
        assertEquals(0, result);
        System.out.println("FILE READ:\n" + jsonFileReader.getJsonString());

        String actual = jsonFileReader.getJsonString().replaceAll(System.getProperty("line.separator").toString(), "\n");
        assertEquals(jsonStringOne, actual);
        System.out.println("...Passed");
    }

    @org.junit.jupiter.api.Test
    void readJsonFileTestTwo() throws IOException {
        System.out.println("\nreadJsonFile TEST TWO:\n");
        JsonFileReader jsonFileReader = new JsonFileReader();
        int result = jsonFileReader.readJsonFile("/Data/test.json");
        assertEquals(0, result);
        System.out.println("FILE READ:\n" + jsonFileReader.getJsonString());

        String actual = jsonFileReader.getJsonString().replaceAll(System.getProperty("line.separator").toString(), "\n");
        assertEquals(jsonStringTwo, actual);
        System.out.println("...Passed");
    }

    @org.junit.jupiter.api.Test
    void readJsonFileTestThree() throws IOException {
        System.out.println("\nreadJsonFile TEST THREE:\n");
        JsonFileReader jsonFileReader = new JsonFileReader();
        int result = jsonFileReader.readJsonFile("/Data/empty.json");
        assertEquals(-1, result);
        System.out.println("...Passed");
    }
}