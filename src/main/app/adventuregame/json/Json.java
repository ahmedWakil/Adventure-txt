package adventuregame.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Json {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static <T> void registerDeserializer(Class<T> c, JsonDeserializer<? extends T> deserializer){
        SimpleModule module = new SimpleModule();
        module.addDeserializer(c, deserializer);
        objectMapper.registerModule(module);
    }

    public static JsonNode parse(String source) throws JsonProcessingException {
        return objectMapper.readTree(source);
    }

    public static <A> A fromJason(JsonNode node, Class<A> c) throws JsonProcessingException {
        // check that node is an object
        return objectMapper.treeToValue(node, c);
    }

    public static <A> List<A> parseJsonArray(JsonNode node, Class<A> classOnWhichArrayIsDefined) throws IOException, ClassNotFoundException {
        // TO DO: here check that node is an array
        Class<A[]> arrayClass = (Class<A[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
        A[] objects = objectMapper.treeToValue(node, arrayClass);
        return Arrays.asList(objects);
    }

    public static JsonNode toJson(Object a) {
        return objectMapper.valueToTree(a);
    }

    public static String generateString(JsonNode node, boolean format) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (format)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(node);
    }
}
