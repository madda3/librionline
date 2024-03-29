/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.test;
import it.univaq.idw.librionline.model.Autore;
import it.univaq.idw.librionline.model.LibriOnLineDataLayer;
import it.univaq.idw.librionline.model.Libro;
import it.univaq.idw.librionline.model.impl.AutoreMysqlImpl;
import it.univaq.idw.librionline.model.impl.LibriOnLineDataLayerMysqlImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import it.univaq.idw.librionline.model.impl.LibroMysqlImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.eclipse.persistence.indirection.IndirectList;

/**
 *
 * @author giacomolm
 */
public class EsempioInserimento {
    
    /**
     * Ciao
     * @param args Prova 5
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("IDWLibriOnlinePU");
        EntityManager manager = factory.createEntityManager();
        Libro l = new LibroMysqlImpl("90", "Giuliano Pisapia");
        l.setEditore("Mondadori");
        l.setAnnoPubblicazione(new Integer(1928));
        l.setRecensione("Il libro racconta la storia dal 1928 ad oggi");
        //l'inserimento della lingua è delicata, per via di un'altra 
        manager.getTransaction().begin();
        Libro b = manager.find(LibroMysqlImpl.class, "90");
        Collection<AutoreMysqlImpl> ca =  manager.find(LibroMysqlImpl.class, "90").getAutoreMysqlImplCollection();
        
        //LibroMysqlImpl rl = manager.getReference(LibroMysqlImpl.class, "90");
        manager.getTransaction().commit();
        for ( Iterator i = ca.iterator(); i.hasNext(); ) {
            Autore element = (Autore) i.next();
            System.out.println( "Il nome dell'autore del libro "+b.getTitolo()+" è " + element.getNome() );
        }*/
        //Collection<Libro> lc = get
        //System.out.println(ca[0]);
        //Libro l = new LibroMysqlImpl("910", "Giorgio Forattini");
        //LibriOnLineDataLayerMysqlImpl dl = new LibriOnLineDataLayerMysqlImpl();
        //System.out.println(dl.insertBook(l));
        LibriOnLineDataLayer dl = new LibriOnLineDataLayerMysqlImpl();
        System.out.println(dl.insertTag("botanica"));
        System.out.println(dl.insertLingua("inglese"));
        System.out.println(dl.insertAutore("D'orazio", "Silvio"));
        System.out.println(dl.chiudiPrestito(4));
        //System.out.println(dl.insertStato("Ciao"));
        Libro l = dl.searchByIsbn("9788871923031");
        dl.insertVolume(l, 655, 1);
        String[] id_autori = {"4","5","6"};
        String[] id_tag = {"1","2","3"};
        dl.insertBook("1289892112", "Test", "Test", "2006", "qwerty", 1, id_autori, id_tag, 3, 30, 1);
        System.out.println(dl.modificaLibro("9788861142879", "Test", "Test", "2006", "azerty", 1, id_autori, id_tag));
        dl.insertLingua("Russo");
        System.out.println(dl.modificaLingua(3, "Svedese"));
        //dl.eliminaLingua(4);
        //dl.insertAutore("Volo", "Fabio");
        //dl.modificaAutore(17, "Volo", "Fabio Maria");
        dl.eliminaAutore(16);
        dl.modificaTag(7,"Filmo");
        dl.eliminaTag(7);
        //dl.eliminaTag(7);
        //dl.insertVolume(dl.searchByIsbn("9788871923031"), 40, 2);
        //dl.modificaVolume(111, 41, 1);
        //dl.eliminaVolume(111);
        System.out.println(dl.modificaStato(11, "Zilfio"));
        System.out.println(dl.eliminaLibro("9788850321001"));
        System.out.println(dl.insertCopiaElettronica(dl.searchByIsbn("1232121213133"),"pdf"));
    }
}
