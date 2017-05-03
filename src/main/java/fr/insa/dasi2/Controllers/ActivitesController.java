package fr.insa.dasi2.Controllers;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Activite;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActivitesController extends Controller {

    @Override
    public void route(URLInfo urlInfo, HttpServletRequest request) {
        // Exécute la bonne action
        String action = urlInfo.getFixed(1);
        if (null == action || "".equals(action) || "all".equals(action)) {
            listAll();
        } else if (URLInfo.isInteger(action)) {
            id();
        }
    }

    public void listAll() {
        if ("json".equals(urlInfo.getType())) {
            request.setAttribute("data", new Gson().toJson(ServiceMetier.affichageListeActivites()));
        } else {
            dispatchTo("/vues/activites/all");
        }
    }

    public void id() {
        
        System.err.println("1");
        
        int id = Integer.parseInt(urlInfo.getFixed(1));
        if ("json".equals(urlInfo.getType())) {
            System.err.println("2");
            
            List<Activite> activites = ServiceMetier.affichageListeActivites();
            System.err.println("Activités :");
            System.err.println(activites);
            
            request.setAttribute("data", new Gson().toJson(activites.get(id)));
        } else {
            System.err.println("3");
            
            request.setAttribute("id", id);
            dispatchTo("/vues/activites/id");
        }
    }
}
