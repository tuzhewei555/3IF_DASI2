package fr.insa.dasi2.Controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public abstract class Controller {

    protected URLInfo urlInfo;
    protected HttpServletRequest request;
    protected String dispatcherName;

    public RequestDispatcher execute(URLInfo urlInfo, HttpServletRequest request) {
        this.urlInfo = urlInfo;
        this.request = request;
        this.dispatcherName = null;

        this.route(urlInfo, request);

        return (null != this.dispatcherName) ? request.getRequestDispatcher(this.dispatcherName) : null;
    }
    
    protected abstract void route(URLInfo urlInfo, HttpServletRequest request);
    
    protected void dispatchTo(String where) {
        this.dispatcherName = where;
    }
    
}
