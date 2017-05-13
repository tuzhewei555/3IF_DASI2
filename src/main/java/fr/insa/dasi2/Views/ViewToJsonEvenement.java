package fr.insa.dasi2.Views;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Evenement;

/**
 *
 */
public class ViewToJsonEvenement extends View {
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            List<Evenement> evenements = (List<Evenement>) request.getAttribute("evenements");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            if (null != evenements) {
                
                printListeEvenements(out,evenements);
                System.out.println(evenements); 
                //out.println(new Gson().toJson(evenements));
                response.setContentType("application/json");
            }
        }
    }
    public static void printListeEvenements(PrintWriter out, List<Evenement> evenements){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonListe = new JsonArray();
        for(Evenement e: evenements){
            JsonObject jsonEvenement = new JsonObject();
            jsonEvenement.addProperty("date", e.getDate().toString());
            jsonEvenement.addProperty("activity", e.getAct().getDenomination());
            jsonListe.add(jsonEvenement);
        }
        JsonObject container =new JsonObject();
        container.add("evenements",jsonListe);
        System.out.println(gson.toJson(container)); 
        out.println(gson.toJson(container));
    }

}
