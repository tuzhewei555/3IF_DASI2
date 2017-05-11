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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String[] allowedPages = {
            request.getContextPath() + "/login.html",
            request.getContextPath() + "/signup.html",
            request.getContextPath() + "/",
            request.getContextPath() + "/ActionServlet",
            request.getContextPath() + "/main.css"
        };
        
        boolean loggedIn = session != null && session.getAttribute("adherent") != null;
        boolean allowed = false;
        String requestedURI = request.getRequestURI();
        for (String s : allowedPages) {
            System.out.println("Checking: " + requestedURI + "==" + s);
            if (requestedURI.equals(s)) {
                allowed = true;
                System.out.println("Allowed on page: "+ requestedURI);
                break;
            }
        }

        System.out.println("loggedIn: " + loggedIn);

        if (loggedIn || allowed) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(allowedPages[0]);
        }
    }

    @Override
    public void destroy() {
    }
}
