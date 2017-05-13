package fr.insa.dasi2.Actions;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.modele.Demande;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionNouvelleDemande extends Action {

    @Override
    public void process(HttpServletRequest request) {

        // Récupère les paramètres
        String activity = request.getParameter("activity");
        String date = request.getParameter("date");
        String moment = request.getParameter("moment");

        // Si un des deux est nul, on ne fait rien
        if (null == activity || null == date || null == moment) {
            return;
        }

        // Si pas connecté, on ne fait rien
        // TODO: Faire un helper / config
        Adherent adherent = getAdherent(request);
        if (null == adherent) {
            System.out.println("Non connecté.");
            return;
        }

        // Essaie de créer la demande
        System.out.println("Date: " + new Date(date));
        Demande demande = ServiceMetier.creerDemande(adherent, activity, new Date(date), moment);

        // Mets la demande dans la requête
        request.setAttribute("demande", demande);
    }

}
