/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.controller;

import it.univaq.idw.librionline.framework.util.SecurityLayer;
import it.univaq.idw.librionline.framework.util.TemplateResult;
import it.univaq.idw.librionline.model.LibriOnLineDataLayer;
import it.univaq.idw.librionline.model.impl.LibriOnLineDataLayerMysqlImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zilfio
 */
public class InserisciLingua extends HttpServlet {
    
    private boolean analizza_form_lingua(HttpServletRequest request, HttpServletResponse response) {
      
        String lingua = request.getParameter("insertlanguage_language");
        
        if(lingua == null || lingua.isEmpty()){
            return false;
        }
        
        LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();

        if(dl.insertLingua(lingua)){
            return true;
        }
        else{
            return false;
        }
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TemplateResult res = new TemplateResult(getServletContext());
        HttpSession session = SecurityLayer.checkSession(request);
        
        if(session != null){
            request.setAttribute("stato_log", "Logout");

            LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();

            if(dl.isAdmin((String)session.getAttribute("username"))){
                request.setAttribute("bibliotecario",true);
                request.setAttribute("tipologia_utente","Bibliotecario");
                
                String insert_language = request.getParameter("Inserisci Lingua");
                
                if(insert_language == null){
                    request.setAttribute("title","Inserisci Lingua");
                    res.activate("backoffice_inseriscilingua.ftl.html", request, response);
                }
                else{
                    boolean result = analizza_form_lingua(request,response);
                    if(result){
                        request.setAttribute("title","Inserisci Lingua");
                        request.setAttribute("messaggio","La Lingua è stata inserita correttamente!");
                        res.activate("backoffice_inseriscilingua.ftl.html", request, response);
                    }
                    else{
                        request.setAttribute("title","Inserisci Lingua");
                        request.setAttribute("messaggio","Inserimento Lingua fallito: Si prega di compilare bene i campi sottostanti!");
                        res.activate("backoffice_inseriscilingua.ftl.html", request, response);
                    }
                    
                }
            }
            else{
                request.setAttribute("bibliotecario",false);
                request.setAttribute("tipologia_utente","Utente");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
