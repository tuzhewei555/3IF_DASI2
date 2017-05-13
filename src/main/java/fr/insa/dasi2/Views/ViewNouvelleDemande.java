package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Demande;
import metier.modele.Demande.Moment;

/**
 *
 */
public class ViewNouvelleDemande extends View {
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            Demande demande = (Demande) request.getAttribute("demande");
            if (null != demande) {
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");
                String dateStr = df.format(demande.getDate());
                
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("denomination", demande.getAct().getDenomination());
                jsonObject.addProperty("date", dateStr);
                jsonObject.addProperty("moment", momentToString(demande.getMoment()));
                out.println(new Gson().toJson(jsonObject));
                response.setContentType("application/json");
            }
        }
    }
    
    private String momentToString(Moment moment) {
        if (moment == Moment.apresmidi) {
            return "après-midi";
        }
        return moment.toString();
    }

}
