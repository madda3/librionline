/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.idw.librionline.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author giacomolm
 */
public interface LibriOnLineDataLayer {

    /**
     * Inserisce un libro nella libreria, controllondo anticipatamente
     * se questo è presente già nella libreria, tramite il metodo bookIsThis(Libro).
     * È importante notare come gli autori vengono passati come una collezione 
     * dell'oggetto autore: gli utilizzatori della funzione devono prima creare
     * una collezione di autori relativi il libro e succesivamente passarlo alla
     * funzione che effettua l'inserimento del libro.
     * @param l Libro da inserire
     * @return true se il l'inserimento è stato effettuato in maniera corretta
     */
    boolean insertBook(String isbn, String titolo, String editore, String annopubbl, String recens, int id_lingua,String[] id_autori, String[] id_tag, int n_copie, int durata_max,int id_stato);
    
    
    /**
     * La funzione permette di modificare il libro che indichiamo con il parametro
     * isbn.
     * @param isbn del libro che vogliamo modificare
     */
     boolean modificaLibro(String isbn, String titolo, String editore, String annopubbl, String recens, int id_lingua,String[] id_autori, String[] id_tag);
    
    /**
     * La funzione permette di eliminare il libro che indichiamo con il parametro
     * isbn.
     * @param isbn del libro che vogliamo modificare
     */
     boolean eliminaLibro(String isbn);
 
     
    /**
     * Questo metodo verifica se una particolare username è già presente nel DB.
     * @param username String rappresentante l'username
     * @retur true se l'username inserito è già stato utilizzato da un altro utente
     */
    boolean isThisUsername(String username);
    
    /**
     * Questo metodo fornisce la funzionalità di login al sistema.
     * @param username e password necessari per l'autenticazione al sistema
     * @return User; se il logging va a buon fine viene restituito l'oggetto user
     * che si appena loggato, altrimenti restituisce null se c'è stato qualche 
     * problema nella procedura.
     */
    User login(String username, String password);
    
    /**
     * Funzione di ricerca avanzata: verifica se esiste un libro che corrisponde
     * a tutti quanti i parametri descritti.
     * @param titolo del libro che su vuole cercare
     * @param tag relativi al libro
     * @param autori che hanno scritto o contribuito alla realizzazione del libro
     * @param isbn del libro
     * @return Una lista di libri che corrispondono ai criteri inseriti
     */
     List<Libro> advancedSearch(String titolo, String tag, String autori, String isbn);
    
    /**
     * Questo metodo viene utilizzato nella fase di ricerca base: i visitatori
     * possono eseguire una ricerca rapida per trovare il libro desiderato. Abbiamo
     * assunto che questo tipo di ricerca si basi solo sul titolo del libro.
     * @param String titolo indicannte il titolo del libro che si vuole cercare
     * @return Collection<Libro> restituisce una collezione di libri, in quanto il titolo potrebbe
     * coincidere tra i diversi libri
     */
    List<Libro> simpleBookSearch(String titolo);
    
    /**
     * Il metodo prevede la registrazione degli utenti al sistema. Il metodo controlla
     * anticipatamente se esiste un altro utente iscritto con lo stesso username. Se ciò
     * accada, si interrompe la registrazione
     * @param username, password, nome, cognome, codfisc, indirizzo, citta, prov, cap
     * @return true se la registrazione è stata eseguita correttamente, false altrimenti
     */
    boolean insertUser(String username,String password,String email,String telefono,String nome,String cognome,String codfisc,String indirizzo,String citta,String prov,int cap,Gruppo gruppo);
    
    /**
     * Il metofo permette di modificare i dati relativi ad un utente, con riferimento
     * agli stessi parametri parametri per la creazione dell'utente stesso
     * @param id_user
     * @return true se la modifica va a buon fine
     */
    public boolean modificaUser(int id_user, String password,String email,String telefono,String nome,String cognome,String codfisc,String indirizzo,String citta,String prov,int cap,Gruppo gruppo);
    
    /**
     * Il metodo permette di rimuovere un utente a partire dal suo Id
     * @param id_user dell'utente che si vuole rimuovere
     * @return true se la rimozione è stata fatta in maniera corretta
     */
    boolean removeUser(int id_user);
    
    /**
     * Il metodo permette di restituire la lista di gruppi che non appartengono
     * all'utente indicato dal parametro.
     * @param id_user dell'utente per il quale vogliamo ottentere i gruppi di non appartenenza
     * @return List di gruppi non appartenenti all'utente.
     */
    List<Gruppo> getNotGruppo(int id_user);
    
    /**
     * Questa funzione restituisce il gruppo relativo un particolare tipo. Ad esempio
     * se presente nella entità gruppo un tipo Amministrazione, passando questa stringa
     * come parametro viene restituito l'oggetto gruppo riferito.
     * @param id del tipo del gruppo che si vuole ricevere
     * @return gruppo di appartenza del tipo
     */
    Gruppo getGruppo(int gruppo);
    
    /**
     * Ricerca per tag: la ricerca consiste nella individuazione dei libri che
     * appartengono a quell'insieme di tag. Si procede con la ricerca dei libri 
     * associati al primo tag della lista; poi si verifica se questi appartengono
     * anche a quello successivo, raffinando progressivamente la lista.
     * @param String indicante la lista di tag separati da uno spazio
     * @return List di libri appartenente a quell'insieme di tag
    */
    List<Libro> searchByTags(String tc);
    
    /**
     * Il metodo ricerca un libro in base al suo isbn. Restituisce il libro, se viene
     * trovato, altrimenti torna null.
     * @param isbn Stringa indicante l'isbn
     * @return Libro, se esiste, relativo all'isbn indicato; altrimenti restituisce null
     */
    Libro searchByIsbn(String isbn);
    
    /**
     * Ricerca i libri associati ad una lista di autori passati in input per 
     * mezzo del parametro autori.
     * @param autori
     * @return Lista indicante l'insieme dei libri scritti dagli autori indicati
     */
    List<Libro> searchByAutori(String autori);
    
      /**
     * Questa funziona si dimostra essere necessaria in quanto potrebbero esistere
     * due o più autori con lo stesso nome: in questo modo possiamo accedere alla
     * lista dei libri scritti dall'autore desiderato tramite il suo id in modo tale da evitare
     * delle inesattezze
     * @param id dell'autore
     * @return List<Libro> scritti dall'autore rappresento da quell'id
     */
     List<Libro> searchLibriAutoriById(int id);
     
         /**
     * Questo tipo di ricerca fa riferimento esclusivamente al titolo del libro:
     * vengono considerati tutti i titoli che matchano con il titolo completo o 
     * parte di esso.
     * @param String titolo rappresentante il titolo completo o parte di esso
     * @return List di libri rappresentante il risultato della ricerca
     */
    List<Libro> searchByTitle(String titolo);
    
        /**
     * Il metodo ha il compito di cercare un insieme di un certo numero di libri,
     * in questo caso ne assumiamo 10, che sono stati aggiunti più di recente nella
     * biblioteca
     * @return Lista di libri aggiunta di recente
     */
    List<Libro> getLastAdded();
    
        /**
     * Restituisce il numero di copie, quindi il numero dei volumi, appartententi alla
     * libreria relativi all'isbn passato come parametro
     * @param isbn
     * @return int indicante il numero delle copie che appartengono alla libreria
     */
    int getNumeroCopie(String isbn);
    
        /**
     * Il metodo ha come compito quello di selezionare l'insieme di libri che sono
     * stati maggiormente prestati. L'insieme è stato impostato ad una cardinalità
     * si 10 elementi. 
     * @return lista dei libri maggiormente prestati
     */
     List<Libro> getMostProvided();
    
        /**
     * il metodo si occupa di ricerca qual'è l'id del gruppo che appartiene ad
     * un determina utente
     * @param un String username
     * @return intero indicante l'id del gruppo
     */
    int getGruppoByUsername(String un);
    
    /**
     * Verifico se l'username passato come parametro appartiene al gruppo 
     * amministratore
     * @param un stringa username
     * @return true se lo user appartiene all'amministrazione
     */
     boolean isAdmin(String un);
    
        /**
     * Restituisco il numero delle copie di un libro disponibili al prestito, cioè
     * il numero delle copiè che per quel determinato momento possono essere prestate
     * @param isbn indicante quello del libro
     * @return int indicante il numero delle copie disponibili
     */
    int getNumeroCopieDisponibili(String isbn);
        /**
     * Restituisco la data di restituzione del volume più vicina, in modo tale 
     * da informare chi di interesse quando potrà essere reperibile un determinato
     * volume
     * @param  del libro interessato
     * @return data indicante la restituzione più prossima della prima copia
     */
    Date getProssimoData(String isbn);
        /**
     * Il metodo si occupa di restituire la lista di libri presi in prestito da parte di un determinato
     * utente.
     * @param username dell'utente di cui si vogliono conoscere il libri da lui detenuti
     * @return lista di libri non ancora restituiti 
     */
    List<Prestito> getPrestitiAttivi(String username);
         /**
     * Il metodo restituisce la lista dei libri presi in prestito in passato da un
     * utente, e quindi già restituiti. Nel caso l'utente non abbia preso in prestito 
     * alcun libro, il metodo restituisce alcuna lista referenziata (NULL).
     * @param username dell'utente di cui si vogliono conoscere il libri presi in prestito
     * @return lista di libri presi in prestito e già restituiti
     */
    List<Prestito> getPrestitiPassati(String username);
    /**
     * Restituisce la lista di tutti i libri presenti nel DB, inclusi quelli
     * prestati.
     * @return Lista di libri appartenenti alla libreria
     */
    public List<Libro> libriTotale();
    /**
     * Il metodo si occupa di restituire l'insieme dei prestiti attivi legati 
     * a ciascun volume di un libro. 
     * @param isbn del libro
     * @return  Lista di prestiti legati a quel libro
     */
    List<Prestito> prestitiAttiviLibro(String isbn);
    /**
     * Il metodo si occupa di restituire l'insieme dei prestiti passati legati 
     * a ciascun volume di un libro. 
     * @param isbn del libro
     * @return  Lista di prestiti legati a quel libro
     */
    List<Prestito> prestitiPassiviLibro(String isbn);
    
    /**
     * Il metodo ha il compito di esaminare quali sono i prestiti che sono
     * scaduti, in modo tale da poter essere avvisati da un bibliotecario
     * @return 
     */
    List<Prestito> getPrestitiScaduti();
    /**
     * Il metodo restituisce l'insieme di tutti gli utenti iscritti alla libreria
     * @return Lista di tutti gli utenti registrati
     */
    List<User> allUser();
    
    /**
     * Il metodo analizza quali sono i volumi disponibili per il libro indicato
     * nell'isbn e procede con la restituzione dell'insieme di questi
     * @param isbn indicante il libro di cui si vogliono analizzare i volumi
     * @return Lista di volumi disponibili
     */
    List<Volume> getVolumiDisponibili(String isbn);
    /**
     * Il metodo registra nella base di dati il prestito eseguito da un
     * bibliotecario, in relazione ai parametri passati
     * @param isbn del libro che vogliamo prestare
     * @param id_vol della capia fisica che rilasciamo all'utente
     * @param id_user che vuole prendere in prestito il libro indicato
     * @param durata_max del prestito
     * @return true se il prestito va a buon fine
     */
    boolean registraPrestito(String isbn, int id_vol, int id_user);
    /**
     * Il metodo restituisce l'oggetto user in modo tale da poter fornire delle 
     * informazioni utili su di esso.
     * @param id_user dell'utente sul quale vogliamo reperire informazioni
     * @return oggetto User relativo quell'id
     */
    User getUser(int id_user);
    /**
     * Il metodo ha come compito quello di cercare tutti i volumi
     * che sono stati prestati agli utenti della libreria e non 
     * ancora restituiti
     * @return lista di tutti i prestiti in corso
     */
    List<Prestito> getAllActivePrestiti();
    
    /**
     * Questo metodo verifica se un tag è gia presente nella lista dei tag della
     * libreria: risulta indispensabile quando si vuole inserire un nuovo tag.
     * @param tag stringa indicante il tag che si vuole inserire
     * @return true se il tag è già presente
     */
    boolean tagIsThis(String tag);
    
    /**
     * Il metodo permette l'aggiunta di un nuovo tag nel database, nel caso
     * la libreria non abbia ancora incluso una particolare categoria di libri
     * @param tag stringa indicante il tag
     * @return true se l'inserimento è andato a buon fine
     */
    boolean insertTag(String tag);

    /**
     * Il metodo permette la modifica di un tag nel database.
     * @param id_tag indicante l'id del tag che vogliamo modificare
     * @param tag stringa indicante il tag
     * @return true se la modifica è andata a buon fine
     */
    boolean modificaTag(int id_tag,String tag);
    /**
     * Il metodo permette la rimozione di un tag nel database.
     * @param id_tag indicante l'id del tag che vogliamo eliminare
     * @return true se la rimozione è andata a buon fine
     */
     boolean eliminaTag(int id_tag);
     
    /**
     * Il metodo permette di prelevare tutti quanti i tag presenti nel database
     * in modo tale da poter essere visualizzati agli utenti che hanno bisogno
     * di manipolarli
     * @return Lista di tag presenti nel database
     */
    List<Tag> getAllTag();
    
    /**
     * Il metodo permette di prelevare tutti quanti gli autori presenti nel database
     * in modo tale da poter essere visualizzati agli utenti che hanno bisogno
     * di manipolarli
     * @return Lista di autori presenti nel database
     */
    List<Autore> getAllAutori();
    
    /**
     * Il metodo permette di prelevare tutte quante le lingue presenti nel database
     * in modo tale da poter essere visualizzati agli utenti che hanno bisogno
     * di manipolarli
     * @return Lista di Lingua presenti nel database
     */
    public List<Lingua> getAllLingua();
    /**
     * Verifica se la lingua passata come parametro è presente nella tabella lingua
     * @param lingua di cui vogliamo controllare la presenza
     * @return true se la lingua è gia presente
     */
    boolean linguaIsThis(String lingua);
    /**
     * Inserisce una nuova lingua nella base di dati, se questa non esiste
     * @param lingua che vogliamo inserire
     * @return true se l'inserimento è andato a buon fine
     */
    boolean insertLingua(String lingua);
    /**
     * Il metodo permette di modificare una lingua, indicando il suo id
     * @param id_lingua
     * @param lingua
     * @return true se la modifica è stata effettuata corretramente
     */
    boolean modificaLingua(int id_lingua, String lingua);
    /**
     * Il metodo permette di eliminare una lingua, indicando il suo id
     * @param id_lingua
     * @return true se la sua eliminazione è stata effettuata correttamente
     */
    boolean eliminaLingua(int id_lingua);
    /**
     * Il metodo permette l'aggiunta di un nuovo tag nel database, nel caso
     * la libreria non abbia ancora incluso una particolare categoria di libri
     * @param tag stringa indicante il tag
     * @return true se l'inserimento è andato a buon fine
     */
    boolean insertAutore(String cognome,String nome);
    
        /**
     * Il metodo permette la modifica di un autore nel database.
     * @param id_autore 
     * @param cognome dell'autore da inserire
     * @param nome nome dell'autore da inserire
     * @return true se la modifica è andata a buon fine
     */
    boolean modificaAutore(int id_autore,String cognome,String nome);   

    /**
     * Il metodo permette di eliminare un autore presente nel database.
     * @param id_autore 
     * @return true se la cancellazione è andata a buon fine
     */
    boolean eliminaAutore(int id_autore);
    
    /**
     * Controllo se l'autore è già presente nel database, attraverso il suo nome
     * e cognome
     * @param cognome dell'autore che vogliamo inserire
     * @param nome dell'autore che vogliamo inserire
     * @return true se un autore con quei dati è già presente
     */
    boolean autoreIsThis(String cognome, String nome);
    
    /**
     * Il metodo restituisce un oggetto Autore a partire dal suo ID
     * @param id_autore che vogliamo cercare
     * @return Autore relativo a quell'id, se esiste, altrimenti null
     */
    Autore getAutore(int id_autore);
    
    /**
     * Il metodo restituisce un oggetto Tag a partire dal suo ID
     * @param id_tag
     * @return Tag relativo quell'id se esiste, altrimenti null
     */
    Tag getTag(int id_tag);
    /**
     * Il metodo restituisce un oggetto Lingua a partire dal suo ID
     * @param id_lingua
     * @return Lingua relativo quell'id se esiste, altrimenti null
     */
    Lingua getLingua(int id_lingua);
    
    /**
     * Il metodo restituisce l'oggetto user in modo tale da poter fornire delle 
     * informazioni utili su di esso.
     * @param username dell'utente, o parte di esso
     * @return list di oggetti User relativi quell'username
     */
    List<User> getUsers(String username);
    /**
     * Il metodo permette di chiudere un prestito effettuato in precedenza da un
     * utente.
     * @param id_prestito che si vuole chiudere
     * @return true se la chiusura è stata eseguita correttamente
     */
    boolean chiudiPrestito(int id_prestito);
    
    /**
     * Il metodo fornisce tutti quanti gli stati presenti nel sistema. Ricordiamo
     * che per stato indichiamo le condizioni fisiche di un volume
     * @return Lista di stato
     */
    List<Stato> getAllStato();
    
    /**
     * Il metodo provvede all'inserimento di un nuovo stato, cioè di una nuova
     * condizione fisica del libro
     * @param stato Stringa indicante la nuova condizione fisica
     * @return true se l'inserimento è stato eseguito in maniera corretta
     */
    boolean insertStato(String stato);
    
    /**
     * Il metodo provvede alla modifica di uno stato, cioè alla modifica di una
     * condizione fisica del libro
     * @param id_stato 
     * @param stato, cioè la stringa indicante il nuovo stato
     * @return true se la modifica è avvenuta correttamente
     */
    boolean modificaStato(int id_stato,String stato);

    /**
     * Il metodo provvede alla rimozione di uno stato.
     * @param id_stato 
     * @return true se la cancellazione è avvenuta correttamente
     */
    boolean eliminaStato(int id_stato);
    
    /**
     * Il metodo permette l'inserimento di un volume a partire da un libro 
     * @param l Libro al quale vogliamo inserire i volumi
     * @param durata_max indica la durata di default del prestito
     * @param id_stato stato dei volumi che vogliamo inserire
     * @return true se l'aggiunta del volume viene fatta in maniera corretta
     */
    boolean insertVolume(Libro l, int durata_max,int id_stato);
    
    /**
     * Il metodo provvede a restituire uno stato, inteso come oggetto a partire 
     * dal suo id.
     * @param id dello stato che si vuole cercare
     * @return Stato se ce n'è uno con quell'id, altrimenti null
     */
    
    /**
     * Il metodo permette la modifica di un volume relativo un libro 
     * @param id_volume
     * @param durata_max indica la durata di default del prestito
     * @param id_stato stato dei volumi che vogliamo inserire
     * @return true se la modifica dei volumi viene fatta in maniera corretta
     */
    boolean modificaVolume(int id_volume, int durata_max,int id_stato);   
   
    /**
     * Il metodo permette la rimozione di un volume relativo un libro 
     * @param id_volume
     * @return true se la rimozione del volume viene fatta in maniera corretta
     */
    boolean eliminaVolume(int id_volume);
    
    /**
     * Il metodo restituisce un volume, se presente, a partire dal suo id
     * @param id_volume che vogliamo trovare
     * @return Volume se presente , altrimenti null
     */
    Volume getVolume(int id_volume);
    
    /**
     * Restituisce l'oggetto Stato a partire dal suo id
     * @param id dello stato che si vuole reperire
     * @return Stato se estite, altrimenti null
     */
    Stato getStato(int id);
    
    /**
     * Il metodo consiste nel verificare se un autore ha scritto il libro indicato
     * dall'id
     * @param id_libro
     * @param id_autore
     * @return "selected" se appartiene, altrimenti una stringa vuota
     */
    List<Autore> notAuthor(String isbn_libro);
    
        
    /**
     * Il metodo consiste nel verificare se un tag appartiene al libro indicato
     * dall'isbn, e restituire la lista dei tag che non appartengono al libro
     * @param isbn_libro
     * @return la lista dei tag che non appartengono al libro
     */
    List<Tag> notAtag(String isbn_libro);
    
    /**
     * Il metodo provvede l'inserimento della copia elettronica relativo un libro
     * @param l libro al quale vogliamo aggiungere una copia elettronica
     * @param mime tipo del file che stiamo inserendo
     * @return true se l'inserimento va a buon fine
     */
    boolean insertCopiaElettronica(Libro l, String mime);
    /**
     * Il metodo provvede la modifica della copia elettronica relativo un libro
     * @param id_copia 
     * @param mime tipo del file che stiamo modificando
     * @return true se l'inserimento va a buon fine
     */
    boolean modificaCopiaElettronica(int id_copia, String mime);
    
    /**
     * Il metodo provvede alla rimozione della copia elettronica relativa un libro
     * @param id_copia 
     * @return true se l'eliminazione va a buon fine
     */
    boolean eliminaCopiaElettronica(int id_copia);
    
    /**
     * Ritorna a copia elettronica indicata da quell'id
     * @param id_copia della copia che vogliamo recuperare
     * @return CopiaElettronica desiderata altrimenti null se non esiste
     */
    Copiaelettronica getCopiaElettronica(int id_copia);
        
    /**
     * Il metodo permette di visualizzare tutti quanti gli stati di un volume,
     * ad eccezione di quello del volume di riferimento.
     * @param id_volume per il quale 
     * @return Lista di stato escluso quello del volume riferito
     */
    List<Stato> notStati(int id_volume);
    
    /**
     * Il metodo si occupa di individuare i libri che devono essere riconsegnati
     * a breve (entro max 7 giorni), e include anche i libri la cui data di 
     * riconsegna è scaduta. 
     * @param username dell'utente di cui si vogliono conoscere i prestiti da riconsegnare a breve
     * @return List<Prestito> di libri da riconsegnare a breve
     */
    List<Prestito> getPrestitiABreve(String username);
    
    /**
     * Il metodo permette di verificare se una copia elettronica di un determinato
     * formato è già presente nell'insieme di copie elettroniche.
     * @param isbn del libro per il quale vogliamo esaminare le copie elettronico
     * @param type della copia elettronica che vogliamo verificare
     * @return true se la copia è già presente in quel formato
     */
    boolean existCopiaElettronica(String isbn, String type);
    
    /**
     * Il metodo premette di individuare tutti i gruppi presenti nel database
     * @return List di tutti i gruppi presenti
     */
    List<Gruppo> getAllGruppi();
}
