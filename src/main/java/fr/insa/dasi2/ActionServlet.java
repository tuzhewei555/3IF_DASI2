package fr.insa.dasi2;

import dao.JpaUtil;
import fr.insa.dasi2.Actions.*;
import fr.insa.dasi2.Views.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy();
        JpaUtil.destroy();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String strAction = request.getParameter("action");
        if (null == strAction) {
            return;
        }

        // Récupère l'action et la vue
        Action action = null;
        View view = new ViewEmpty();
        switch (strAction) {
            case "login":
                action = new ActionLogin();
                view = new ViewAdherent();
                break;
            case "logout":
                action = new ActionLogout();
                break;
            case "signup":
                action = new ActionSignup();
                view = new ViewAdherent();
                break;
            case "activites_all":
                action = new ActionActivitesAll();
                view = new ViewActivitesAll();
                break;
            case "nouvelle_demande":
                action = new ActionNouvelleDemande();
                view = new ViewNouvelleDemande();
                break;
            case "mes_demandes":
                action = new ActionMesDemandes();
                view = new ViewDemandesAll();
                break;
            case "evenements_all":
                action = new ActionAdminEvenementsAll();
                view = new ViewEvenementsAll();
                break;
            case "definir_paf":
                action = new ActionAdminDefinirPaf();
                view = new ViewString();
                break;
            case "definir_lieu":
                action = new ActionAdminDefinirLieu();
                view = new ViewString();
                break;
            case "lieux_all":
                action = new ActionLieuxAll();
                view = new ViewLieuxAll();
                break;
        }
        if (null != action) {
            action.process(request);
        }
        view.process(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
