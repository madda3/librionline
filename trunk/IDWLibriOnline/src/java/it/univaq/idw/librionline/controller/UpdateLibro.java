/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.controller;

import it.univaq.idw.librionline.framework.util.SecurityLayer;
import it.univaq.idw.librionline.framework.util.TemplateResult;
import it.univaq.idw.librionline.model.Autore;
import it.univaq.idw.librionline.model.LibriOnLineDataLayer;
import it.univaq.idw.librionline.model.Libro;
import it.univaq.idw.librionline.model.Tag;
import it.univaq.idw.librionline.model.impl.LibriOnLineDataLayerMysqlImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zilfio
 */
public class UpdateLibro extends HttpServlet {

    private boolean analizza_form_libro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String isbn = request.getParameter("updatebook_isbn");
        String titolo = request.getParameter("updatebook_titolo");
        String editore = request.getParameter("updatebook_editore");
        String annoPubblicazione = request.getParameter("updatebook_annopubblicazione");
        String recensione = request.getParameter("updatebook_recensione");
        String lingua = request.getParameter("updatebook_lingua");
        String[] autore = request.getParameterValues("updatebook_autore");
        String[] tag = request.getParameterValues("updatebook_tag");
        
        if((isbn == null || isbn.isEmpty()) || (titolo == null || titolo.isEmpty()) || (editore == null || editore.isEmpty()) || (annoPubblicazione == null || annoPubblicazione.isEmpty())|| (autore == null) || (tag == null)){
            return false;
        }
        else{
            return true;
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
        LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();
        
        if(session != null){
            request.setAttribute("stato_log", "Logout");

            if(dl.isAdmin((String)session.getAttribute("username"))){
                request.setAttribute("bibliotecario",true);
                request.setAttribute("tipologia_utente","Bibliotecario");
                
                String update = request.getParameter("Modifica Libro");
                
                if(update == null){
                    String isbn = request.getParameter("id");
                    Libro libro = dl.searchByIsbn(isbn);
                    Collection<Autore> autori = libro.getAutoreCollection();
                    Collection<Autore> autorinotautori = dl.notAuthor(isbn);
                    Collection<Tag> tags = libro.getTagCollection();
                    Collection<Tag> tagsnottags = dl.notAtag(isbn);
                    
                    request.setAttribute("title", "Modifica libro");
                    request.setAttribute("libro", libro);
                    request.setAttribute("autori", autori);
                    request.setAttribute("autorinotautori", autorinotautori);
                    request.setAttribute("tags", tags);
                    request.setAttribute("tagsnottags", tagsnottags);
                    res.activate("backoffice_updatelibro.ftl.html", request, response);
                }
                
                else if(update.equals("Modifica Libro")){
                    String isbn = request.getParameter("updatebook_isbn");
                    String titolo = request.getParameter("updatebook_titolo");
                    String editore = request.getParameter("updatebook_editore");
                    String annoPubblicazione = request.getParameter("updatebook_annopubblicazione");
                    String recensione = request.getParameter("updatebook_recensione");
                    String lingua = request.getParameter("updatebook_lingua");
                    String[] autore = request.getParameterValues("updatebook_autore");
                    String[] tag = request.getParameterValues("updatebook_tag");
                    int id_lingua = Integer.parseInt(lingua);
                    boolean result = analizza_form_libro(request, response);
                    
                    if(result){
                        dl.modificaLibro(isbn, titolo, editore, lingua, recensione, id_lingua, autore, tag);
                        Libro object_libro = dl.searchByIsbn(isbn);
                        request.setAttribute("title", "Modifica libro");
                        request.setAttribute("messaggio", "Libro modificato correttamente");
                        request.setAttribute("libro", object_libro);
                        res.activate("backoffice_updatelibro.ftl.html", request, response);
                    }
                    else{
                        Libro object_libro = dl.searchByIsbn(isbn);
                        request.setAttribute("title", "Modifica libro");
                        request.setAttribute("messaggio", "Update libro Fallito");
                        request.setAttribute("libro", object_libro);
                        res.activate("backoffice_updatelibro.ftl.html", request, response);
                    }
                }
                else{
                    String isbn = request.getParameter("updatelibro_isbn");
                    
                    dl.eliminaLibro(isbn);
                    
                    response.sendRedirect("VisualizzaLibri");
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