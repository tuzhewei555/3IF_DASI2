package fr.insa.dasi2.Controllers;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class HomeController extends Controller {

    @Override
    public void route(URLInfo urlInfo, HttpServletRequest request) {
        home();
    }

    public void home() {
        setTitle("Collect'IF - Accueil");
        setView("/vues/home");
    }
}
