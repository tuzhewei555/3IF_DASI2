package fr.insa.dasi2.Views;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Demande;

/**
 *
 */
public abstract class View {

    public abstract void process(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String momentToString(Demande.Moment moment) {
        if (moment == Demande.Moment.apresmidi) {
            return "après-midi";
        }
        return moment.toString();
    }

}
