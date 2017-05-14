package fr.insa.dasi2.Actions;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.modele.Adherent_;
import metier.service.ServiceMetier;
import metier.service.ServiceTechnique;

/**
 *
 */
public class ActionSignup extends Action {

    @Override
    public void process(HttpServletRequest request) {

        // Récupère les paramètres
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String address = request.getParameter("address");
        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");

        // Si un des paramètres est nul, on ne fait rien
        if (null == lastName || null == firstName || null == address || null == email1 || null == email2) {
            request.setAttribute("adherent", null);
        }

        // Si il y a quelqu'un de connecté, on le déconnecte
        Adherent adherent = getAdherent(request);
        if (null != adherent) {
            ServiceMetier.deconnecter(adherent);
        }
        request.getSession().invalidate();

        // Essaie d'inscrire
        adherent = ServiceMetier.inscription(lastName, firstName, address, email1, email2);

        // Ajoute l'adhérent à la session
        if (null != adherent) {
            request.getSession(true).setAttribute("adherent", adherent);
        }

        // Mets l'adhérent dans la requête
        request.setAttribute("adherent", adherent);

        // PAS A NOUS DE LE FAIRE NORMALEMENT
        // Envoi le mail si l'évènement est créé
        if (null != adherent) {
            System.out.println(ServiceTechnique.envoieMailInscriptionSucces(adherent));
        } else {
            System.out.println(ServiceTechnique.envoieMailInscriptionEchec(email1, firstName));
        }
    }

}
