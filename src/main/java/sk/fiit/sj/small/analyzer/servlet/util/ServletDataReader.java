package sk.fiit.sj.small.analyzer.servlet.util;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import sk.fiit.sj.small.analyzer.exception.SmallAnalyzerException;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class ServletDataReader {

    public static JsonObject getJsonData(HttpServletRequest request) throws SmallAnalyzerException {
        try {
            JsonReader jsonReader = Json.createReader(request.getReader());
            JsonObject jsonObject = jsonReader.readObject();
            return jsonObject;
        } catch (IOException ex) {
            throw new SmallAnalyzerException("Error parsing request data: Invalid Json", ex);
        }
    }
}
