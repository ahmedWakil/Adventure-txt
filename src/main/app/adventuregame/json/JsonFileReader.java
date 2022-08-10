package adventuregame.json;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonFileReader {

    private String jsonString = "";

    public String getJsonString() {
        return jsonString;
    }

    public int readJsonFile(String fileAddress) throws IOException {
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder output = new StringBuilder();

        Reader reader;
        try {
            reader = new InputStreamReader(this.getClass().getResourceAsStream(fileAddress));
        } catch (NullPointerException e) {
            return -2;
        }

        int numberRead;
        if (reader.ready()) {
            while ((numberRead = reader.read(buffer, 0, bufferSize)) > 0) {
                output.append(buffer, 0, numberRead);
            }
        } else {
            return -1;
        }

        reader.close();
        jsonString = output.toString();
        return 0;
    }
}
