package fr.insa.dasi2.Actions;

import dao.JpaUtil;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Adherent;
import metier.service.ServiceMetier;

/**
 *
 */
public class ActionLogin extends Action {

    @Override
    public void process(HttpServletRequest request) {
        
        // Récupère les paramètres
        String email = request.getParameter("email");
        String strId = request.getParameter("identifiant");
        
        // Si un des deux est nul, on ne fait rien
        if (null == email || null == strId) {
            return;
        }
        
        // Récupère l'id
        Long id = Long.getLong(strId);
        if (null == id) {
            return;
        }
        
        JpaUtil.init();
        try {
            // Récupère l'adhérent
            Adherent adherent = ServiceMetier.connecter(email, id);
        } finally {
            JpaUtil.destroy();
        }
    }
    
}
