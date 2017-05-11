package fr.insa.dasi2.Actions;

import dao.JpaUtil;
import javax.servlet.http.HttpServletRequest;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionActivitesAll extends Action {

    @Override
    public void process(HttpServletRequest request) {
        System.err.println("1");
        JpaUtil.init();
        try {
            request.setAttribute("activites", ServiceMetier.affichageListeActivites());
        } finally {
            JpaUtil.destroy();
        }
        System.err.println("2");
    }
}