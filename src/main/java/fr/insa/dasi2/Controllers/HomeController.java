package fr.insa.dasi2.Controllers;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import metier.service.ServiceMetier;

/**
 *
 */
public class HomeController extends Controller {

    @Override
    public void route(URLInfo urlInfo, HttpServletRequest request) {
        home();
    }

    public void home() {
        if ("json".equals(urlInfo.getType())) {
            request.setAttribute("data", new Gson().toJson(ServiceMetier.affichageListeActivites()));
        } else {
            dispatchTo("/vues/home");
        }
    }
}
