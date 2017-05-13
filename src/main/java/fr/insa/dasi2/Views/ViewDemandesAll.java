package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Demande;

/**
 *
 */
public class ViewDemandesAll extends View {
 
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
            if (null != demandes) {
                demandes.sort(new Comparator<Demande>() {
                    @Override
                    public int compare(Demande o1, Demande o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");

                JsonArray jsonArray = new JsonArray();
                for (Demande demande : demandes) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", demande.getId());
                    jsonObject.addProperty("activity", demande.getAct().getDenomination());
                    jsonObject.addProperty("date", df.format(demande.getDate()));
                    jsonObject.addProperty("moment", momentToString(demande.getMoment()));
                    jsonObject.addProperty("payant", demande.getAct().getPayant());

                    jsonArray.add(jsonObject);
                }

                out.println(new Gson().toJson(jsonArray));
                response.setContentType("application/json");
            }
        }
    }
}
