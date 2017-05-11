package fr.insa.dasi2.Controllers;

/**
 *
 */
public class UsersController extends Controller {

    @Override
    public void route(URLInfo urlInfo) {
        // Exécute la bonne action
        String action = urlInfo.getFixed(0);
        if (null == action || "".equals(action)) {
            account();
        } else if ("login".equals(action)) {
            login();
        } else if ("signup".equals(action)) {
            signup();
        }
    }

    public void account() {
        setTitle("Collect'IF - Mon compte");
        setView("/vues/users/account.jsp");
    }

    public void login() {
        setTitle("Collect'IF - Connexion");
        setView("/vues/users/login.jsp");
        
        
    }

    public void signup() {
        setTitle("Collect'IF - Inscription");
        setView("/vues/users/signup.jsp");
    }

    public void registration() {
        setTitle("Collect'IF - Inscription");
        setView("/vues/users/registration");
    }
}
