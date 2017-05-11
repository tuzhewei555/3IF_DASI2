package fr.insa.dasi2.Views;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public abstract class View {

    public abstract void process(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
