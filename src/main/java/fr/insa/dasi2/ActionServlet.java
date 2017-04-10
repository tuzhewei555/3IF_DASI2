package fr.insa.dasi2;

import com.google.gson.Gson;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Activite;
import metier.service.ServiceMetier;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String todo = request.getParameter("todo");
            switch (todo) {
                case "listAll":
                    List<Activite> acts = ServiceMetier.affichageListeActivites();
                    /*if (null == acts) {
                        out.println("null");
                        return;
                    }
                    for (Activite act : acts) {
                        out.println(" - " + act);
                        out.println("<br>");
                    }*/
                    Gson r = new Gson();
                    out.println(r.toJson(acts));
                    break;
                default:
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet ActionServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.print("<p>");
                    out.println("Action inconnue.");
                    out.print("</p>");
                    out.println("</body>");
                    out.println("</html>");
                    break;
            }
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
