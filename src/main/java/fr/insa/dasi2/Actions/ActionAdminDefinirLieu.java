package fr.insa.dasi2.Actions;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.modele.Evenement;
import metier.service.ServiceMetier;
import metier.service.ServiceTechnique;

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
            return;
        }
        System.out.println("id: " + id);
        System.out.println("idLieu: " + idLieu);

        // Essaie de créer la demande
        Evenement event = ServiceMetier.choisirLieu(idLieu, id);

        // Construit le message
        String msg = "";
        if (null != event) {
            msg += " L'évènement est complet et a été créé !";
        }
        request.setAttribute("string", msg);

        // PAS A NOUS DE LE FAIRE NORMALEMENT
        // Envoi le mail si l'évènement est créé
        if (null != event) {
            List<Adherent> adherents = event.getListAdherent();
            for (int i = 0; i < adherents.size(); i++) {
                System.out.println(ServiceTechnique.envoieMailEvenement(event, adherents.get(i)));
            }
        }
    }

}
