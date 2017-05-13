/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.dasi2;

import dao.JpaUtil;
import fr.insa.dasi2.Actions.Action;
import fr.insa.dasi2.Actions.ActionActivitesAll;
import fr.insa.dasi2.Actions.ActionAdminEvenement;
import fr.insa.dasi2.Actions.ActionLogin;
import fr.insa.dasi2.Actions.ActionLogout;
import fr.insa.dasi2.Actions.ActionNouvelleDemande;
import fr.insa.dasi2.Actions.ActionSignup;
import fr.insa.dasi2.Views.View;
import fr.insa.dasi2.Views.ViewToJson;
import fr.insa.dasi2.Views.ViewEmpty;
import fr.insa.dasi2.Views.ViewAdherent;
import fr.insa.dasi2.Views.ViewToJsonEvenement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author totorigolo
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
        String todo = request.getParameter("action");
        if (null == todo) {
            return;
        }

        // TODO: Créer un filtre
        // Récupère l'action et la vue
        Action action = null;
        View view = new ViewEmpty();
        switch (todo) {
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
                view = new ViewToJson();
                break;
            case "nouvelleDemande":
                action = new ActionNouvelleDemande();
                view = new ViewAdherent();
                break;
            case "evenements":
                action = new ActionAdminEvenement(); 
                view = new ViewToJsonEvenement();             
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
