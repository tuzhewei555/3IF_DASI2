package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Activite;

/**
 *
 */
public class ViewActivitesAll extends View {
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<Activite> activites = (List<Activite>) request.getAttribute("activites");
            if (null != activites) {
                out.println(new Gson().toJson(activites));
                response.setContentType("application/json");
            }
        }
    }

}
