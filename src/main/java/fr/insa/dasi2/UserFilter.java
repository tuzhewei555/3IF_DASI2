package fr.insa.dasi2;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 */
public class UserFilter implements Filter {

    private final boolean DEBUG = false;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        log("-----------------------");
        log(request.getRequestURI());

        String[] allowedPages = {
            request.getContextPath() + "/",
            request.getContextPath() + "/login.html",
            request.getContextPath() + "/signup.html",
            request.getContextPath() + "/admin_dashboard.html",
            request.getContextPath() + "/admin_paf.html",
            request.getContextPath() + "/admin_lieu.html",
            request.getContextPath() + "/admin_maps.html",
        };
        String[] allowedStart = {
            request.getContextPath() + "/ActionServlet",
            request.getContextPath() + "/css/",
            request.getContextPath() + "/js/",
            request.getContextPath() + "/fonts/",
            request.getContextPath() + "/images/",
        };

        boolean loggedIn = session != null && session.getAttribute("adherent") != null;
        boolean allowed = false;
        String requestedURI = request.getRequestURI();
        for (String s : allowedPages) {
            if (requestedURI.equals(s)) {
                log("Allowed at: " + s);
                allowed = true;
                break;
            }
        }
        for (String s : allowedStart) {
            if (requestedURI.startsWith(s)) {
                log("Allowed on: " + s);
                allowed = true;
                break;
            }
        }

        log("loggedIn: " + loggedIn);
        log("allowed: " + allowed);
        log("-----------------------");

        if (loggedIn || allowed) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(allowedPages[0]);
        }
    }

    @Override
    public void destroy() {
    }
    
    private void log(String msg) {
        if (DEBUG) {
            System.out.println(msg);
        }
    }
}
