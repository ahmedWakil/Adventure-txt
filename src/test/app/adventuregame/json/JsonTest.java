package adventuregame.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import testclasses.Author;
import testclasses.Books;
import testclasses.Person;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonTest {

    private final String testSourceOne = "{\n" +
            "  \"name\" : \"John\",\n" +
            "  \"age\" : 21\n" +
            "}";

    private final String authorTestSourceOne = "{\n" +
            "  \"authorName\": \"testName\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"inPrint\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"inPrint\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private final String authorTestSourceTwo = "[\n" +
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
    void parseTest1() throws JsonProcessingException {
        JsonNode node = Json.parse(testSourceOne);
        assertEquals("John", node.get("name").asText());
    }

    @org.junit.jupiter.api.Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = Json.parse(testSourceOne);
        Person person = Json.fromJason(node, Person.class);
        assertEquals("John", person.getName());
        assertEquals(21, person.getAge());
    }

    @org.junit.jupiter.api.Test
    void toJson() {
        Person person = new Person();
        person.setAge(21);
        person.setName("John");

        JsonNode node = Json.toJson(person);
        assertEquals("John", node.get("name").asText());
        assertEquals(21, node.get("age").asInt());
    }

    @org.junit.jupiter.api.Test
    void generateStringTrue() throws JsonProcessingException {
        Person person = new Person();
        person.setAge(21);
        person.setName("John");

        JsonNode node = Json.toJson(person);
        String test = Json.generateString(node, true);
        String expected = "{\n" +
                "  \"name\" : \"John\",\n" +
                "  \"age\" : 21\n" +
                "}";

        if (!("John".equals(node.get("name").asText())) || !(21 == node.get("age").asInt())) {
            fail("\nExpected: " + "\n" + expected + "\n" +
                    "Found: " + "\n" + test);
        }
    }

    @org.junit.jupiter.api.Test
    void generateStringFalse() throws JsonProcessingException {
        Person person = new Person();
        person.setAge(21);
        person.setName("John");

        JsonNode node = Json.toJson(person);

        String test = Json.generateString(node, false);
        assertEquals("{\"name\":\"John\",\"age\":21}", test);
    }

    @org.junit.jupiter.api.Test
    void authorTest1() throws JsonProcessingException {
        JsonNode node = Json.parse(authorTestSourceOne);
        Author author = Json.fromJason(node, Author.class);

        assertEquals("testName", author.getAuthorName());
    }

    @org.junit.jupiter.api.Test
    void authorTest2() throws JsonProcessingException {
        JsonNode node = Json.parse(authorTestSourceOne);
        Author author = Json.fromJason(node, Author.class);
        List<Books> books = author.getBooks();

        for (int i = 0; i < books.size(); i++) {
            assertEquals("title" + (i+1), books.get(i).getTitle());
        }
    }

    @org.junit.jupiter.api.Test
    void parseJsonArrayTestOne() throws IOException, ClassNotFoundException {
        JsonNode node = Json.parse(authorTestSourceTwo);
        List<Author> authors = Json.parseJsonArray(node, Author.class);
        System.out.println(authors);
    }
}