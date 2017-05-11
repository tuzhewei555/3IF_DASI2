package fr.insa.dasi2.Controllers;

import metier.modele.Adherent;
import static metier.service.ServiceMetier.inscription;

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
        if (null != this.request.getParameter("last_name")
                && null != this.request.getParameter("first_name")
                && null != this.request.getParameter("address")
                && null != this.request.getParameter("email1")
                && null != this.request.getParameter("email2")) {
            // Récupère les paramètres
            String lastName = this.request.getParameter("last_name");
            String firstName = this.request.getParameter("first_name");
            String address = this.request.getParameter("address");
            String email1 = this.request.getParameter("email1");
            String email2 = this.request.getParameter("email2");

            // Essaie d'inscrire
            Adherent adherent = inscription(lastName, firstName, address, email1, email2);
            
            // Vérifie l'inscription
            if (null == adherent) {
                setTitle("Collect'IF - Inscription");
                setView("/vues/users/signup.jsp");
                setError("Une erreur est survenue lors de l'inscription.");
            } else {
                setTitle("Collect'IF - Connexion");
                setView("/vues/users/login.jsp");
                setSuccess("Vous êtes maintenant inscrit sur le site !");
            }

        } else {
            setTitle("Collect'IF - Inscription");
            setView("/vues/users/signup.jsp");
        }
    }

    public void registration() {
        setTitle("Collect'IF - Inscription");
        setView("/vues/users/registration");
    }
}
