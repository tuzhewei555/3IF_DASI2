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
        if (isJson()) {
            request.setAttribute("data", new Gson().toJson(ServiceMetier.affichageListeActivites()));
        } else {
            setTitle("Collect'IF - Liste des activités");
            setView("/vues/activites/all");
        }
    }

    public void id() {
        int id = Integer.parseInt(urlInfo.getFixed(1));
        if (isJson()) {
            List<Activite> activites = ServiceMetier.affichageListeActivites();
            request.setAttribute("data", new Gson().toJson(activites.get(id)));
        } else {
            setTitle("Collect'IF - Activité");
            request.setAttribute("id", id);
            setView("/vues/activites/id");
        }
    }
}
