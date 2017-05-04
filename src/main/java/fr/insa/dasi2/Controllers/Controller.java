package fr.insa.dasi2.Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public abstract class Controller {

    protected URLInfo urlInfo;
    protected HttpServletRequest request;
    protected String layout;
    protected String view;
    protected String title;

    public final String DEFAULT_TITLE = "Title";
    public final String DEFAULT_LAYOUT = "/vues/layouts/default";

    public RequestDispatcher execute(URLInfo urlInfo, HttpServletRequest request) {
        this.urlInfo = urlInfo;
        this.request = request;
        this.title = DEFAULT_TITLE;
        this.layout = DEFAULT_LAYOUT;

        this.route(urlInfo, request);

        if (isJson()) {
            return null;
        } else {
            request.setAttribute("title", this.title);
            request.setAttribute("view", this.view);
            request.setAttribute("layout", this.layout);
            return (null != this.layout) ? request.getRequestDispatcher(this.layout) : null;
        }
    }

    protected abstract void route(URLInfo urlInfo, HttpServletRequest request);

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
}
