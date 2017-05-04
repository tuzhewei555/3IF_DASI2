package fr.insa.dasi2;

import dao.JpaUtil;
import fr.insa.dasi2.Controllers.ActivitesController;
import fr.insa.dasi2.Controllers.Controller;
import fr.insa.dasi2.Controllers.HomeController;
import fr.insa.dasi2.Controllers.URLInfo;
import fr.insa.dasi2.Controllers.UsersController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cet Servlet s'occupe de rediriger les requêtes vers les bons controleurs,
 * puis vers la bonne vue ou en tant que données brutes.
 */
public class RouterServlet extends HttpServlet {

    /**
     * Redirige les requêtes reçues vers le bon controleur, puis suivant les
     * indications du controleur redirige vers la vue ou en données brutes.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JpaUtil.init();
        try (PrintWriter out = response.getWriter()) {
            // Crée l'objet URLInfo
            URLInfo urlInfo = new URLInfo(request);

            // Extrait le nom du controleur
            String controllerName = urlInfo.getFixed(0);
            if (null == controllerName) {
                controllerName = "";
            }

            // Route la requête vers le bon controleur
            Controller controller;
            switch (controllerName) {
                case "":
                    controller = new HomeController();
                    break;
                case "users":
                    controller = new UsersController();
                    break;
                case "activites":
                    controller = new ActivitesController();
                    break;
                default:
                    // Erreur 404, crée un contrôleur vide
                    controller = new Controller() {
                        @Override
                        protected void route(URLInfo urlInfo, HttpServletRequest request) {
                            setTitle("Erreur 404");
                        }
                    };
                    break;
            }

            // Lance le controleur et récupère le dispatcher
            RequestDispatcher rd = controller.execute(urlInfo, request);
            response.setContentType(urlInfo.getContentType());

            // Si un dispatcher a été renvoyé, on l'appelle
            if (null != rd) {
                rd.forward(request, response);
            } // Sinon on traîte envoi les données brutes
            else if (null != request.getAttribute("data")) {
                out.println(request.getAttribute("data"));
            } // S'il n'y a pas de données, il s'agit d'une erreur inconnue
            else {
                String message = "Erreur 500 - Aucune action n'a été effectuée :<br/>";
                message += request.getContextPath() + "<br/>";
                message += request.getPathInfo() + "<br/>";
                message += request.getQueryString() + "<br/>";
                message += request.getRequestURI() + "<br/>";
                message += request.getServletPath() + "<br/>";
                HtmlHelper.GenerateErrorPage("Erreur 500", message, out);
            }
        } finally {
            JpaUtil.destroy();
        }
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
