package sk.fiit.sj.small.analyzer.servlet;

import sk.fiit.sj.small.analyzer.exception.SmallAnalyzerException;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            response.setStatus(200);
            response.getWriter().write(Json.createObjectBuilder().add("status", "success").build().toString());      
        } catch (SmallAnalyzerException ex) {
            response.setStatus(500);
            response.getWriter().write(Json.createObjectBuilder().add("status", "failed").add("error", ex.getMessage()).build().toString());
        }
    }

    @Override
    public void destroy() {
    }
}
