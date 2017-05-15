package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Adherent;

/**
 *
 */
public abstract class Action {

    public abstract void process(HttpServletRequest request);

    public Adherent getAdherent(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return null;
        }
        return (Adherent) session.getAttribute("adherent");
    }

    public boolean loggedIn(HttpServletRequest request) {
        return null != getAdherent(request);
    }
    
}
