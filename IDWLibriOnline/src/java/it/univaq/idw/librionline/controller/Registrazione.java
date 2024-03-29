/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.controller;

import it.univaq.idw.librionline.framework.util.Md5;
import it.univaq.idw.librionline.framework.util.SecurityLayer;
import it.univaq.idw.librionline.framework.util.TemplateResult;
import it.univaq.idw.librionline.model.Gruppo;
import it.univaq.idw.librionline.model.LibriOnLineDataLayer;
import it.univaq.idw.librionline.model.impl.LibriOnLineDataLayerMysqlImpl;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zilfio
 */

public class Registrazione extends HttpServlet {
    
    private boolean analizza_form_registrazione(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        
        //prendo in post tutti i campi del form_registrazione
        String user = request.getParameter("registrazione_username");
        String pass = Md5.md5(request.getParameter("registrazione_password"));
        String email = request.getParameter("registrazione_email");
        String tel = request.getParameter("registrazione_telefono");
        
        String nome = request.getParameter("registrazione_nome");
        String cognome = request.getParameter("registrazione_cognome");
        String codicefiscale = request.getParameter("registrazione_codicefiscale");
        String indirizzo = request.getParameter("registrazione_indirizzo");
        String citta = request.getParameter("registrazione_citta");
        String provincia = request.getParameter("registrazione_provincia");
        String cap = request.getParameter("registrazione_cap");
        String gruppo = request.getParameter("registrazione_gruppo");
        
        if(user.equals("") || pass.equals("") || email.equals("") || tel.equals("") || nome.equals("") || cognome.equals("") || codicefiscale.equals("") || indirizzo.equals("") || citta.equals("") || provincia.equals("") || cap.equals("") || gruppo.equals("")){
            return false;
        }
        
        int cap2 = Integer.parseInt(cap); 
        int gruppo2 = Integer.parseInt(gruppo); 
        
        //Questo è l'oggetto che devi dichiarare per qualsiasi interazione con il DB
        LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();
 
        
        //Ottengo il gruppo che voglio inserire
        Gruppo g = dl.getGruppo(gruppo2);
        
        //Eseguiamo la registrazione
        if(dl.insertUser(user, pass, email, tel, nome, cognome, codicefiscale, indirizzo, citta, provincia, cap2, g)){
            return true;
        }
        return false;
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        
        TemplateResult res = new TemplateResult(getServletContext());
        HttpSession session = SecurityLayer.checkSession(request);
                
        if(session != null){
            request.setAttribute("stato_log", "Logout");

            LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();

            if(dl.isAdmin((String)session.getAttribute("username"))){
                request.setAttribute("bibliotecario",true);
                request.setAttribute("tipologia_utente","Bibliotecario");
                
                String registrazione = request.getParameter("Registrazione");
                List<Gruppo> gruppi = dl.getAllGruppi();
        
                if(registrazione == null){
                    request.setAttribute("title","Registrazione");
                    request.setAttribute("gruppi",gruppi);
                    request.setAttribute("navigazione","<a href='Home'>Homepage</a>");
                    res.activate("form_registrazione.ftl.html", request, response);
                }
                else{
                    if(analizza_form_registrazione(request,response)){
                        request.setAttribute("title","Registrazione");
                        request.setAttribute("gruppi",gruppi);
                        request.setAttribute("messaggio","Registrazione effettuata con successo!");
                        request.setAttribute("navigazione","<a href='Home'>Homepage</a>");
                        res.activate("form_registrazione.ftl.html", request, response);
                    }
                    else{
                        request.setAttribute("title","Registrazione");
                        request.setAttribute("gruppi",gruppi);
                        request.setAttribute("messaggio","Registrazione fallita! Si prega di compilare bene i campi sottostanti!");
                        request.setAttribute("navigazione","<a href='Home'>Homepage</a>");
                        res.activate("form_registrazione.ftl.html", request, response);
                    }
                }  
            }
            else{
                request.setAttribute("bibliotecario",false);
                request.setAttribute("tipologia_utente","Utente");
                response.sendRedirect("Home");
            }
        }
        else{
            response.sendRedirect("Home");
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
        }
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
