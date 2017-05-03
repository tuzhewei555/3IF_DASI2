package fr.insa.dasi2;

import dao.JpaUtil;
import fr.insa.dasi2.Controllers.ActivitesController;
import fr.insa.dasi2.Controllers.Controller;
import fr.insa.dasi2.Controllers.HomeController;
import fr.insa.dasi2.Controllers.URLInfo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JpaUtil.init();
        PrintWriter out = response.getWriter();
        try {
            // Crée l'objet URLInfo
            URLInfo urlInfo = new URLInfo(request);

            // Extrait le nom du controleur
            Controller controller = null;
            String controllerName = urlInfo.getFixed(0);
            if (null == controllerName) {
                controllerName = "";
            }

            // Appelle le controleur
            switch (controllerName) {
                case "":
                    // TODO: accueil
                    out.println("accueil");
                    controller = new HomeController();
                    break;
                case "activites":
                    controller = new ActivitesController();
                    break;
                default:
                    String message = "Contrôleur inconnu.<br/>";
                    message += request.getContextPath() + "<br/>";
                    message += request.getPathInfo() + "<br/>";
                    message += request.getPathTranslated() + "<br/>";
                    message += request.getQueryString() + "<br/>";
                    message += request.getRequestURI() + "<br/>";
                    message += request.getServletPath() + "<br/>";

                    HtmlHelper.GenerateErrorPage("Servlet ActionServlet", message, out);
                    break;
            }
            if (null != controller) {
                RequestDispatcher rd = controller.execute(urlInfo, request);
                response.setContentType(urlInfo.getContentType());
                if (null != rd) {
                    rd.forward(request, response);
                } else {
                    out.println(request.getAttribute("data"));
                }
            }
        } catch (Exception e) {
            out.print("Erreur :<br/>");
            out.print(e.getMessage());
        } finally {
            out.close();
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
