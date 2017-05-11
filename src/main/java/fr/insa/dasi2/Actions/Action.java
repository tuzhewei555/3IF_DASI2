package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public abstract class Action {

    public abstract void process(HttpServletRequest request);
}
