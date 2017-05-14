package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class ViewLieuxAll extends View {
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Object obj = request.getAttribute("lieux");
            if (null != obj) {
                out.println(new Gson().toJson(obj));
                response.setContentType("application/json");
            }
        }
    }

}
