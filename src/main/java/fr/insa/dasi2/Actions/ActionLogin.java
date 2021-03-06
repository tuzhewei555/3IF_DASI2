package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionLogin extends Action {

    @Override
    public void process(HttpServletRequest request) {

        // Récupère les paramètres
        String email = request.getParameter("email");
        String strId = request.getParameter("identifiant");

        // Si un des deux est nul, on ne fait rien
        if (null == email || null == strId) {
            request.setAttribute("adherent", null);
            return;
        }

        // Récupère l'id
        Long id;
        try {
            id = Long.parseLong(strId);
        } catch (NumberFormatException e) {
            request.setAttribute("adherent", null);
            return;
        }
        
        // Si il y a quelqu'un de connecté, on le déconnecte
        Adherent adherent = getAdherent(request);
        if (null != adherent) {
            ServiceMetier.deconnecter(adherent);
        }
        request.getSession().invalidate();

        // Récupère l'adhérent
        adherent = ServiceMetier.connecter(email, id);

        // Ajoute l'adhérent à la session
        if (null != adherent) {
            request.getSession(true).setAttribute("adherent", adherent);
        }

        // Mets l'adhérent dans la requête
        request.setAttribute("adherent", adherent);
    }

}
