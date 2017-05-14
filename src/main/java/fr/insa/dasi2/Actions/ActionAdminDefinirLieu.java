package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Evenement;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionAdminDefinirLieu extends Action {

    @Override
    public void process(HttpServletRequest request) {

        // Récupère les paramètres
        Long id;
        Long idLieu;
        try {
            id = Long.parseLong(request.getParameter("id"));
            idLieu = Long.parseLong(request.getParameter("lieu"));
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("fail");
            return;
        }

        // Essaie de créer la demande
        Evenement event = ServiceMetier.choisirLieu(idLieu, id);

        // Construit le message
        String msg = "";
        if (null != event) {
            msg += " L'évènement est complet et a été créé !";
        }
        request.setAttribute("string", msg);
    }

}
