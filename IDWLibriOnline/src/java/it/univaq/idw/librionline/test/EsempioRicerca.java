/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.test;

import it.univaq.idw.librionline.model.LibriOnLineDataLayer;
import it.univaq.idw.librionline.model.Libro;
import it.univaq.idw.librionline.model.impl.LibriOnLineDataLayerMysqlImpl;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author giacomolm
 */
public class EsempioRicerca {
    public static void main(String[] args){
        
        //Questo è l'oggetto che devi dichiarare per qualsiasi interazione con il DB
        LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();
        //Inserisci un libro a mano sul DB e inserisci qui sotto il suo titolo
        String l = "Sviluppare siti con gli standard";
        //Questa è la funzione da richiamare per la ricerca base
        //Attenzion! restituisco una collezione di libri! Perchè più libri potrebbero avere lo stesso titolo
        Collection<Libro> bc = dl.simpleBookSearch(l);
        System.out.println(bc.isEmpty());
        
        //Se le collezioni potrebbero essere un problema fammi sapere!
        for ( Iterator i = bc.iterator(); i.hasNext(); ) {
            Libro element = (Libro) i.next();
            System.out.println( "Codice ISBN "+element.getIsbn());
        }

        //ricerca per isbn
        Libro li = dl.searchByIsbn("9788850329649");
        System.out.println( "Titolo "+li.getTitolo());
        Collection<Libro> list = dl.searchByTags("programmazione ");
        for ( Iterator i = list.iterator(); i.hasNext(); ) {
            Libro element = (Libro) i.next();
            System.out.println( "Titolo Tag "+element.getTitolo());
        }
    }
}