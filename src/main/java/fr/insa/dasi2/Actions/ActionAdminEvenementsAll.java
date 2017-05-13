package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionAdminEvenementsAll extends Action {
    @Override
    public void process(HttpServletRequest request) {
        request.setAttribute("evenements", ServiceMetier.affichageListeEvenements());
        System.out.println("Liste des évènements :");
        System.out.println(ServiceMetier.affichageListeEvenements());
        System.out.println("fin de la liste.");
    }
}
