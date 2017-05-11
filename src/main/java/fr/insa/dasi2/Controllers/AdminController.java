package fr.insa.dasi2.Controllers;

/**
 *
 */
public class AdminController extends Controller {

    @Override
    public void route(URLInfo urlInfo) {
        // Exécute la bonne action
        String action = urlInfo.getFixed(1);
        if (null == action || "".equals(action)) {
            account();
        } else if ("login".equals(action)) {
            
        }
    }

    public void account() {
        setTitle("Collect'IF - Admin - Mon compte");
        setView("/vues/admin/account.jsp");
    }
}
