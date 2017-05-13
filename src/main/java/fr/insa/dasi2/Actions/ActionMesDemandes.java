package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionMesDemandes extends Action {

    @Override
    public void process(HttpServletRequest request) {
        Adherent adherent = getAdherent(request);
        if (null != adherent) {
            request.setAttribute("demandes", ServiceMetier.affichageListeDemandes(adherent));
        } else {
            request.setAttribute("demandes", null);
        }
    }

}
