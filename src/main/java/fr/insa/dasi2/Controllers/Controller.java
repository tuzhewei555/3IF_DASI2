package fr.insa.dasi2.Controllers;

import dao.JpaUtil;
import fr.insa.dasi2.HtmlHelper;
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
public abstract class Controller extends HttpServlet {

    protected URLInfo urlInfo;
    protected HttpServletRequest request;
    protected String layout;
    protected String view;
    protected String title;

    public static final String DEFAULT_TITLE = "Title";
    public static final String DEFAULT_LAYOUT = "/vues/layouts/default.jsp";
    public static final String DEFAULT_VIEW = "/vues/errors/404.jsp";

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JpaUtil.init();
        try {
            this.urlInfo = new URLInfo(request);
            this.request = request;
            this.title = DEFAULT_TITLE;
            this.layout = DEFAULT_LAYOUT;
            this.view = DEFAULT_VIEW;

            this.route(urlInfo);
            response.setContentType(urlInfo.getContentType());

            if ((isJson() && null != request.getAttribute("data")) || null == this.layout) {
                try (PrintWriter out = response.getWriter()) {
                    out.println(request.getAttribute("data"));
                }
            } else {
                System.err.println("layout=" + this.layout);
                System.err.println("view=" + this.view);
                
                request.setAttribute("title", this.title);
                request.setAttribute("view", this.view);
                request.setAttribute("layout", this.layout);
                RequestDispatcher rd = request.getRequestDispatcher(this.layout);
                rd.forward(request, response);
            }
        } finally {
            JpaUtil.destroy();
        }
    }

    protected abstract void route(URLInfo urlInfo);

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setView(String view) {
        this.view = view;
    }

    protected void setLayout(String layout) {
        this.layout = layout;
    }

    protected boolean isJson() {
        return "json".equals(urlInfo.getType());
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
        // TODO: Description
        return "Short description";
    }// </editor-fold>
}
