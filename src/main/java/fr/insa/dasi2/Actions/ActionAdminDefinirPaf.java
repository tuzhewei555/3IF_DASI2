package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Evenement;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionAdminDefinirPaf extends Action {

    @Override
    public void process(HttpServletRequest request) {

        // Récupère les paramètres
        Long id;
        Integer paf;
        try {
            id = Long.parseLong(request.getParameter("id"));
            paf = Integer.parseInt(request.getParameter("paf"));
        } catch (NullPointerException | NumberFormatException e) {
            return;
        }

        // Essaie de créer la demande
        Evenement event = ServiceMetier.choisirPaf(paf, id);

        // Construit le message
        String msg = paf + "&nbsp;&euro;.";
        if (null != event) {
            msg += " L'évènement est complet et a été créé !";
        }
        request.setAttribute("string", msg);
    }

}
