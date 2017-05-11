package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionActivitesAll extends Action {

    @Override
    public void process(HttpServletRequest request) {
        request.setAttribute("activites", ServiceMetier.affichageListeActivites());
    }

}
