package sk.fiit.sj.small.analyzer.servlet;

import sk.fiit.sj.small.analyzer.exception.SmallAnalyzerException;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sk.fiit.sj.small.analyzer.Analyzer;
import sk.fiit.sj.small.analyzer.ValidationResult;
import sk.fiit.sj.small.analyzer.impl.SmallAnalyzer;
import sk.fiit.sj.small.analyzer.servlet.util.ServletDataReader;

/**
 *
 * @author Andrej Mlyncar <a.mlyncar@gmail.com>
 */
public class AnalyzeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JsonObject object = ServletDataReader.getJsonData(request);
            System.out.println(object.getString("text"));
            Analyzer analyzer = new SmallAnalyzer();
            ValidationResult result = analyzer.validateInput(object.getString("text"));
            response.setStatus(200);
            if (result.hasCorrections()) {
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                for (String correction : result.getCorrections()) {
                    jsonArrayBuilder.add(correction);
                }
                response.getWriter().write(Json.createObjectBuilder().add("status", "success").add("corrections", jsonArrayBuilder.build()).add("output", result.getOutput()).build().toString());
            } else {
                response.getWriter().write(Json.createObjectBuilder().add("status", "success").add("output", result.getOutput()).build().toString());
            }
        } catch (SmallAnalyzerException ex) {
            response.getWriter().write(Json.createObjectBuilder().add("status", "failed").add("error", ex.getMessage()).add("output", ex.getValidationOutput()).build().toString());
        }
    }

    @Override
    public void destroy() {
    }
}
