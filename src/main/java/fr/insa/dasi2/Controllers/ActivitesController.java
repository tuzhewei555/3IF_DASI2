package fr.insa.dasi2.Controllers;

import com.google.gson.Gson;
import java.util.List;
import metier.modele.Activite;
import metier.service.ServiceMetier;

/**
 * 
 */
public class ActivitesController extends Controller {

    @Override
    public void route(URLInfo urlInfo) {
        // Exécute la bonne action
        String action = urlInfo.getFixed(0);
        if (null == action || "".equals(action) || "all".equals(action)) {
            listAll();
        } else if (URLInfo.isInteger(action)) {
            id(Integer.parseInt(action));
        }
    }

    public void listAll() {
        if (isJson()) {
            request.setAttribute("data", new Gson().toJson(ServiceMetier.affichageListeActivites()));
        } else {
            setTitle("Collect'IF - Liste des activités");
            setView("/vues/activites/all.jsp");
        }
    }

    public void id(int id) {
        if (isJson()) {
            List<Activite> activites = ServiceMetier.affichageListeActivites();
            for (Activite activite : activites) {
                if (activite.getId() == id) {
                    request.setAttribute("data", new Gson().toJson(activite));
                    return;
                }
            }
        } else {
            setTitle("Collect'IF - Activité");
            request.setAttribute("id", id);
            setView("/vues/activites/id.jsp");
        }
    }
}
