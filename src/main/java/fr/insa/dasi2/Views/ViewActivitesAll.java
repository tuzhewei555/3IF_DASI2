package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
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
                activites.sort(new Comparator<Activite>() {
                    @Override
                    public int compare(Activite o1, Activite o2) {
                        return o1.getDenomination().compareTo(o2.getDenomination());
                    }
                });
                
                out.println(new Gson().toJson(activites));
                response.setContentType("application/json");
            }
        }
    }

}
