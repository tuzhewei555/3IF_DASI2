package fr.insa.dasi2.Views;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Adherent;

/**
 *
 */
public class ViewAdherent extends View {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            // Récupère l'adhérent
            Adherent adherent = null;
            if (null != request.getSession(false)) {
                adherent = (Adherent) request.getSession(false).getAttribute("adherent");
            }

            // Renvoi l'adhérent
            JsonObject jsonResponse = null;
            if (null != adherent) {
                jsonResponse = new JsonObject();
                jsonResponse.addProperty("id", adherent.getId());
                jsonResponse.addProperty("nom", adherent.getNom());
                jsonResponse.addProperty("prenom", adherent.getPrenom());
                jsonResponse.addProperty("mail", adherent.getMail());
            }
            out.println(jsonResponse);
            response.setContentType("application/json");
        }
    }

}
