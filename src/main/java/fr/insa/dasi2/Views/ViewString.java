package fr.insa.dasi2.Views;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class ViewString extends View {
    
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            String str = (String) request.getAttribute("string");
            if (null != str) {
                out.println(str);
                response.setContentType("text/plain");
            }
        }
    }

}
