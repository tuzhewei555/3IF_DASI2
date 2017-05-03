package fr.insa.dasi2;

import java.io.PrintWriter;

/**
 *
 */
public class HtmlHelper {

    public static void GenerateErrorPage(String title, String message, PrintWriter out) {
        System.out.println(title + " **> " + message);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.print("  <p>" + message + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
