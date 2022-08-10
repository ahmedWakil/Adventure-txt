package adventuregame.json;

import adventuregame.world.locations.Prototype;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class PrototypeDeserializer extends StdDeserializer<Prototype> {

    public PrototypeDeserializer() {
        this(null);
    }

    public PrototypeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Prototype deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String id = "";
        String name = "";
        List<String> north = new ArrayList<>();
        List<String> south = new ArrayList<>();
        List<String> east = new ArrayList<>();
        List<String> west = new ArrayList<>();

        Iterator<String> keyIterator = node.fieldNames();
        List<String> keyList = new ArrayList<>();
        keyIterator.forEachRemaining(keyList::add);

        for (String key : keyList) {
            switch (key) {
                case "id":
                    id = node.get(key).asText();
                    break;
                case "name":
                    name = node.get(key).asText();
                    break;
                case "north":
                    for (JsonNode valueNode : node.get(key)) {
                        north.add(valueNode.asText());
                    }
                    break;
                case "south":
                    for (JsonNode valueNode : node.get(key)) {
                        south.add(valueNode.asText());
                    }
                    break;
                case "east":
                    for (JsonNode valueNode : node.get(key)) {
                        east.add(valueNode.asText());
                    }
                    break;
                case "west":
                    for (JsonNode valueNode : node.get(key)) {
                        west.add(valueNode.asText());
                    }
                    break;
            }
        }
        return new Prototype(id, name, north, south, east, west);
    }
}
