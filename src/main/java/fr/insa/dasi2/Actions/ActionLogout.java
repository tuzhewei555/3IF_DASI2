package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionLogout extends Action {

    @Override
    public void process(HttpServletRequest request) {

        // Récupère l'adhérent
        Adherent adherent = getAdherent(request);

        // S'il n'y a pas d'adhérent dans la session, on n'a pas à déconnecter
        if (null != adherent) {
            // Déconnecte l'adhérent
            ServiceMetier.deconnecter(adherent);
        }

        // Supprime la session
        request.getSession().invalidate();
    }

}
