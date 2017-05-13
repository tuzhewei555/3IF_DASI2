/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.dasi2.Actions;

import javax.servlet.http.HttpServletRequest;
import metier.service.ServiceMetier;

/**
 *
 * @author Zhewei
 */
public class ActionAdminEvenement extends Action {
    @Override
    public void process(HttpServletRequest request) {
        request.setAttribute("evenements", ServiceMetier.affichageListeEvenements());
        
    }
}
