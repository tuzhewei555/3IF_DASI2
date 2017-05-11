package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;

/**
 *
 */
public abstract class Action {

    public abstract void process(HttpServletRequest request);

    public Adherent getAdherent(HttpServletRequest request) {
        if (null == request.getSession(false)) {
            return null;
        }
        return (Adherent) request.getAttribute("adherent");
    }

}
