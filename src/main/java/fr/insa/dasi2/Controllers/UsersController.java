package fr.insa.dasi2.Controllers;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Activite;
import metier.service.ServiceMetier;

/**
 *
 */
public class UsersController extends Controller {

    @Override
    public void route(URLInfo urlInfo, HttpServletRequest request) {
        // Exécute la bonne action
        String action = urlInfo.getFixed(1);
        if (null == action || "".equals(action)) {
            account();
        } else if ("login".equals(action)) {
            
        } else if ("registration".equals(action)) {
            registration();
        }
    }

    public void account() {
        setTitle("Collect'IF - Log In");
        setView("/vues/users/account");
    }

    public void registration() {
        setTitle("Collect'IF - Inscription");
        setView("/vues/users/registration");
    }
}
