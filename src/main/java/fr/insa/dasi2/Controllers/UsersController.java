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
}
