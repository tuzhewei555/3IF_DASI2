package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Evenement;
import metier.modele.Evenement_P;

/**
 *
 */
public class ViewEvenementsAll extends View {
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<Evenement> evenements = (List<Evenement>) request.getAttribute("evenements");
            if (null != evenements) {
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");
                
                JsonArray jsonArray = new JsonArray();
                for (Evenement evenement : evenements) {
                    String lieu = (null == evenement.getLieu()) ? null : evenement.getLieu().getDenomination();
                    String paf = "";
                    if (evenement instanceof Evenement_P) {
                        paf = ((Integer) ((Evenement_P) evenement).getPaf()).toString();
                    }
                    
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", evenement.getId());
                    jsonObject.addProperty("activity", evenement.getAct().getDenomination());
                    jsonObject.addProperty("lieu", lieu);
                    jsonObject.addProperty("date", df.format(evenement.getDate()));
                    jsonObject.addProperty("moment", momentToString(evenement.getMoment()));
                    jsonObject.addProperty("payant", evenement.getAct().getPayant());
                    jsonObject.addProperty("paf", paf);
                    
                    jsonArray.add(jsonObject);
                }
                
                out.println(new Gson().toJson(jsonArray));
                response.setContentType("application/json");
            }
        }
    }
}
