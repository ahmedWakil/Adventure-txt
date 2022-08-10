package adventuregame.json;

import adventuregame.json.Exceptions.InvalidJsonFileAddress;
import adventuregame.world.locations.Prototype;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrototypeLoader {

    private static final JsonFileReader jsonFileReader = initialize();

    private static JsonFileReader initialize() {
        Json.registerDeserializer(Prototype.class, new PrototypeDeserializer());
        return new JsonFileReader();
    }

    public static List<Prototype> getPrototypeList(String jsonFileURl) throws IOException, ClassNotFoundException, InvalidJsonFileAddress {
        int success = jsonFileReader.readJsonFile(jsonFileURl);
        if (success == -1) {
            System.out.println("Json file given is empty...");
            return null;
        }

        if (success == -2) {
            System.out.println("Invalid URL, or Json file does not exist");
            throw new InvalidJsonFileAddress("Invalid URL, or Json file does not exist");
        }

        JsonNode node = Json.parse(jsonFileReader.getJsonString());
        return Json.parseJsonArray(node, Prototype.class);
    }

    public static Map<String, Prototype> getPrototypeMap(List<Prototype> prototypeList) {
        Map<String, Prototype> prototypeMap = new HashMap<>();
        for (Prototype prototype : prototypeList) {
            prototypeMap.put(prototype.getId(), prototype);
        }
        return prototypeMap;
    }

    public static List<String> getListOfPrototypeIds(List<Prototype> prototypeList) {

        List<String> listOfIds = new ArrayList<>();
        for (Prototype prototype : prototypeList) {
            listOfIds.add(prototype.getId());
        }
        return listOfIds;
    }
}
