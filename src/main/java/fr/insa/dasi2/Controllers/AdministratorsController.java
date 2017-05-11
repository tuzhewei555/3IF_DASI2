package fr.insa.dasi2.Controllers;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Activite;
import metier.service.ServiceMetier;

/**
 *
 */
public class AdministratorsController extends Controller {

    @Override
    public void route(URLInfo urlInfo, HttpServletRequest request) {
        // Exécute la bonne action
        String action = urlInfo.getFixed(1);
        if (null == action || "".equals(action)) {
            account();
        } else if ("login".equals(action)) {
            
        }
    }

    public void account() {
        setTitle("Collect'IF - Admin - Mon compte");
        setView("/vues/administrators/account");
    }
}
