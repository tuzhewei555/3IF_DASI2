package fr.insa.dasi2.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class URLInfo {

    private String type;
    private ArrayList<String> fixed;
    private final HttpServletRequest request;

    public URLInfo(HttpServletRequest request) {
        this.request = request;
        this.type = "html"; // défaut
        this.fixed = new ArrayList<>();

        // Récupère le chemin relatif
        String pathInfo = request.getPathInfo();
        if (null != pathInfo && pathInfo.length() > 0) {
            if (pathInfo.charAt(0) == '/') {
                pathInfo = pathInfo.substring(1);
            }
            
            // Extrait les paramètres positionels
            for (String param : pathInfo.split("/")) {
                // Regarde s'il contient le type
                if ("html".equals(param) || "json".equals(param)) {
                    this.type = param;
                } else {
                    this.fixed.add(param);
                }
            }
        }

        // Enregistre le type dans la requête
        request.setAttribute("urlInfo", this);
        request.setAttribute("type", this.type);
    }

    public String getType() {
        return type;
    }

    public String getNamed(String name) {
        return request.getParameter(name);
    }

    public String getFixed(int i) {
        if (i < 0 || i >= fixed.size()) {
            return null;
        }
        return fixed.get(i);
    }

    public static boolean isInteger(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public String getContentType() {
        if ("json".equals(this.type)) {
            return "application/json;charset=UTF-8";
        } else {
            return "text/html;charset=UTF-8";
        }
    }
}
