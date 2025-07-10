package UninaFoodLab.Controller;

import java.util.*;
import java.util.List;
import java.util.logging.*;
import javax.swing.*;
import org.mindrot.jbcrypt.*;
import com.formdev.flatlaf.*;
import UninaFoodLab.Boundary.*;
import UninaFoodLab.DTO.*;
import UninaFoodLab.DAO.Postgres.*;
import UninaFoodLab.Exceptions.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;

public class Controller
{
	/** Logger per tracciamento eventi e errori */
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	private static final String ERR_CF_EXISTING = "Esiste già un account associato a questo codice fiscale.";
	private static final String ERR_EMAIL_EXISTING = "Esiste già un account associato a questa email";
	
	/** Istanza Singleton */
    private static Controller instance = null;
    
    /** Utente attualmente autenticato */
    private Utente loggedUser;
    
    /** DAO per le varie entità */
    private AdesioneDAO_Postgres adesioneDAO;
    private ArgomentoDAO_Postgres argomentoDAO;
    private ChefDAO_Postgres chefDAO;
    private CorsoDAO_Postgres corsoDAO;
    private IngredienteDAO_Postgres ingredienteDAO;
    private PartecipanteDAO_Postgres partecipanteDAO;
    private ReportMensileDAO_Postgres reportDAO;
    private RicettaDAO_Postgres ricettaDAO;
    private SessioneOnlineDAO_Postgres sessioneOnlineDAO;
    private SessionePraticaDAO_Postgres sessionePraticaDAO;
    private UtilizzoDAO_Postgres utilizzoDAO;
    
    /** Costruttore privato per pattern Singleton */
    private Controller() {}

    /**
     * Restituisce l'unica istanza di Controller (Singleton).
     * @return istanza del Controller
     */
    public static Controller getController()
    {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * Entry point dell'applicazione. Inizializza il Look&Feel e apre il LoginFrame.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> 
        {
            try 
            {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } 
            catch(UnsupportedLookAndFeelException e) 
            {
                e.printStackTrace();
            }

            new LoginFrame().setVisible(true);  
        });
    }

    /** @return Utente attualmente autenticato */
    public Utente getLoggedUser()
    {
        return loggedUser;
    }
    
    /** @return true se l'utente loggato è uno Chef */
    public boolean isChefLogged()
    {
        return loggedUser instanceof Chef;
    }

    /** @return true se l'utente loggato è un Partecipante */
    public boolean isPartecipanteLogged() 
    {
        return loggedUser instanceof Partecipante;
    }

    /** Metodi getter Lazy-loaded per ogni DAO */
    public AdesioneDAO_Postgres getAdesioneDAO() 
    {
        if(adesioneDAO == null)
        	adesioneDAO = new AdesioneDAO_Postgres();

        return adesioneDAO;
    }
    
    public ArgomentoDAO_Postgres getArgomentoDAO() 
    {
        if(argomentoDAO == null)
        	argomentoDAO = new ArgomentoDAO_Postgres();

        return argomentoDAO;
    }
    
    public ChefDAO_Postgres getChefDAO() 
    {
        if(chefDAO == null)
        	chefDAO = new ChefDAO_Postgres();

        return chefDAO;
    }
    
    public CorsoDAO_Postgres getCorsoDAO() 
    {
        if(corsoDAO == null)
        	corsoDAO = new CorsoDAO_Postgres();

        return corsoDAO;
    }
    
    public IngredienteDAO_Postgres getIngredienteDAO() 
    {
        if(ingredienteDAO == null)
        	ingredienteDAO = new IngredienteDAO_Postgres();

        return ingredienteDAO;
    }
    
    public PartecipanteDAO_Postgres getPartecipanteDAO() 
    {
        if(partecipanteDAO == null)
        	partecipanteDAO = new PartecipanteDAO_Postgres();

        return partecipanteDAO;
    }

    public ReportMensileDAO_Postgres getReportDAO() 
    {
        if (reportDAO == null)
            reportDAO = new ReportMensileDAO_Postgres();
        return reportDAO;
    }

    public RicettaDAO_Postgres getRicettaDAO()
    {
        if (ricettaDAO == null)
            ricettaDAO = new RicettaDAO_Postgres();
        
        return ricettaDAO;
    }
    
    public SessioneOnlineDAO_Postgres getSessioneOnlineDAO()
    {
        if (sessioneOnlineDAO == null)
            sessioneOnlineDAO = new SessioneOnlineDAO_Postgres();
        
        return sessioneOnlineDAO;
    }

    public SessionePraticaDAO_Postgres getSessionePraticaDAO() 
    {
        if (sessionePraticaDAO == null)
            sessionePraticaDAO = new SessionePraticaDAO_Postgres();
        
        return sessionePraticaDAO;
    }
    
    public UtilizzoDAO_Postgres getUtilizzoDAO()
    {
        if (utilizzoDAO == null)
            utilizzoDAO = new UtilizzoDAO_Postgres();
        
        return utilizzoDAO;
    }
    
    /**
     *  --------------------------------------------
     *   	    Metodi di Navigazione UI
     *   @param currFrame frame attuale da chiudere   
     *  --------------------------------------------
	*/
    
    /**
     * Naviga alla schermata di login.
     */
    public void goToLogin(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            currFrame.dispose();
            new LoginFrame().setVisible(true);
        });
    }
    
    /**
     * Naviga alla schermata di registrazione.
     */
    public void goToRegister(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            currFrame.dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    /**
     * Naviga alla homepage.
     */
    public void goToHomepage(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            if(!(currFrame instanceof HomepageFrame)) 
            {
                currFrame.dispose();
                new HomepageFrame().setVisible(true);
            }
            //else
            	//((HomepageFrame) currFrame).resetView();
        });
    }

    /**
     * Naviga alla schermata dei corsi.
     */
    public void goToMyCourses(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            if(!(currFrame instanceof MyCoursesFrame)) 
            {
                currFrame.dispose();
                new MyCoursesFrame().setVisible(true);
            }
            else
                ((MyCoursesFrame) currFrame).resetView();
        });
    }

    /**
     * Naviga alla schermata delle ricette personali dello Chef.
     */
    public void goToMyRecipes(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            if(!(currFrame instanceof MyRecipesFrame))
            {
                currFrame.dispose();
                new MyRecipesFrame().setVisible(true);
            }
            //else
            	//((RecipesFrame) currFrame).resetView();
        });
    }
     
    /**
     * Naviga alla schermata del report mensile dello Chef.
     */
    public void goToReport(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            if(!(currFrame instanceof ReportFrame)) 
            {
                currFrame.dispose();
                new ReportFrame().setVisible(true);
            } 
            // else
            	//((ReportFrame) currFrame).resetView();
        });
    }

    /**
     * Naviga alla schermata del profilo personale.
     */
    public void goToProfile(JFrame currFrame)
    {
    	EventQueue.invokeLater(() -> 
        {
            if(!(currFrame instanceof ProfileFrame)) 
            {
                currFrame.dispose();
                new ProfileFrame().setVisible(true);
            } 
            //else 
            	//((ProfileFrame) currFrame).resetView();
        });
    }

    /**
     * Effettua il logout dell'utente e torna alla schermata di login.
     */
    public void logout(JFrame currFrame)
    {
        EventQueue.invokeLater(() ->
        {
            currFrame.dispose();
            loggedUser = null;
            new LoginFrame().setVisible(true);
        });
    }

    /**
     * Alterna tra modalità chiara e scura per l'interfaccia grafica.
     * @param currFrame frame attivo da aggiornare
     */
    public void toggleDarkMode(JFrame currFrame)
    {
    	if(UIManager.getLookAndFeel() instanceof FlatLightLaf)
    		FlatDarkLaf.setup();
    	else
    		FlatLightLaf.setup();
    		
    	SwingUtilities.updateComponentTreeUI(currFrame);
    }
    
    /**
     *  -------------------------
     * 
     *   	 RegisterFrame
     *   
     *  -------------------------
	*/
    
    /**
     * Cifra la password in modo sicuro usando BCrypt.
     * @param plainPassword array di caratteri della password in chiaro
     * @return stringa con hash cifrato
     */
    public String hashPassword(char[] plainPassword)
    {
        String hashed = BCrypt.hashpw(new String(plainPassword), BCrypt.gensalt(11));
        Arrays.fill(plainPassword, ' ');
        return hashed;
    }
    
    /**
     * Notifica il successo della registrazione loggando l'evento e tornando alla schermata di login per l'autenticazione.
     * 
     * @param currFrame il frame di registrazione da chiudere
     * @param username l'username dell'utente appena registrato
     */
    private void registerSuccess(RegisterFrame currFrame, String username) 
    {
		LOGGER.log(Level.INFO, "Registrazione riuscita per utente: {0}", username);
    	goToLogin(currFrame);
    }
	
    /**
     * Notifica il fallimento della registrazione loggando l'errore e mostrando un messaggio all'utente.
     * 
     * @param currFrame il frame di registrazione
     * @param message messaggio di errore da mostrare
     */
    private void registerFailed(RegisterFrame currFrame, String message) 
    {
    	LOGGER.log(Level.WARNING, "Tentativo di registrazione fallito: {0}", message);
        currFrame.showError(message);
    }
    
    
    /**
     * Registra un nuovo partecipante dopo aver verificato che:
     * <ul>
     *   <li>non esista già un account con lo stesso codice fiscale</li>
     *   <li>non esista già un account con la stessa email</li>
     *   <li>non esista già un utente con lo stesso username</li>
     * </ul>
     * Se tutte le verifiche hanno esito positivo, il partecipante viene salvato nel database
     * e l’utente viene reindirizzato alla schermata di login.
     *
     * @param currFrame il frame di registrazione attuale
     * @param username lo username scelto
     * @param nome il nome del partecipante
     * @param cognome il cognome del partecipante
     * @param codFisc il codice fiscale
     * @param data la data di nascita
     * @param luogo il luogo di nascita
     * @param email l'indirizzo email
     * @param pass la password in chiaro (verrà hashata e svuotata)
     * @throws DAOException se si verifica un errore durante l'accesso al database
     */
    private void registerPartecipante(RegisterFrame currFrame, String username, String nome, String cognome, String codFisc, 
    								  LocalDate data, String luogo, String email, char[] pass) throws DAOException
    {
        if(getPartecipanteDAO().existsPartecipanteByCodiceFiscale(codFisc))
            registerFailed(currFrame, ERR_CF_EXISTING);
        else if(getPartecipanteDAO().existsPartecipanteByEmail(email))
            registerFailed(currFrame, ERR_EMAIL_EXISTING);
        else
        {
        	try
            {
                tryGetUser(username);
                registerFailed(currFrame, "Username già utilizzato.");
            }
        	catch(RecordNotFoundException e)
        	{
        		Partecipante p = new Partecipante(username, nome, cognome, codFisc, data, luogo, email, hashPassword(pass), null, null);
                getPartecipanteDAO().save(p);
                registerSuccess(currFrame, username);
        	}
        }
    }
    
    /**
     * Registra un nuovo chef dopo aver verificato che:
     * <ul>
     *   <li>non esista già un account con lo stesso codice fiscale</li>
     *   <li>non esista già un account con la stessa email</li>
     *   <li>non esista già un utente con lo stesso username</li>
     * </ul>
     * Se tutte le verifiche hanno esito positivo:
     * <ul>
     *   <li>il curriculum viene salvato nella cartella {@code resources/<username>/Curriculum/}</li>
     *   <li>lo chef viene inserito nel database</li>
     *   <li>l’utente viene reindirizzato alla schermata di login</li>
     * </ul>
     *
     * @param currFrame il frame di registrazione attuale
     * @param username lo username scelto
     * @param nome il nome dello chef
     * @param cognome il cognome dello chef
     * @param codFisc il codice fiscale
     * @param data la data di nascita
     * @param luogo il luogo di nascita
     * @param email l'indirizzo email
     * @param pass la password in chiaro (verrà hashata e svuotata)
     * @param selectedFile il file PDF del curriculum da salvare
     * @throws DAOException se si verifica un errore durante l’accesso al database
     * @throws IOException se si verifica un errore durante il salvataggio del curriculum
     */
    private void registerChef(RegisterFrame currFrame, String username, String nome, String cognome, String codFisc, LocalDate data, 
    		 				  String luogo, String email, char[] pass, File selectedFile) throws DAOException, IOException
    {
        if(getChefDAO().existsChefByCodiceFiscale(codFisc))
            registerFailed(currFrame, ERR_CF_EXISTING);
        else if(getChefDAO().existsChefByEmail(email))
            registerFailed(currFrame, ERR_EMAIL_EXISTING);
        else
        {
        	try
            {
                tryGetUser(username);
                registerFailed(currFrame, "Username già utilizzato.");
            }
            catch(RecordNotFoundException e)
            {
                String curriculumPath = saveCurriculumFile(username, selectedFile);
                Chef c = new Chef(username, nome, cognome, codFisc, data, luogo, email, hashPassword(pass), curriculumPath, null, null);
                getChefDAO().save(c);
                registerSuccess(currFrame, username);
            }
        }
    }
    
    /**
     * Salva il file PDF del curriculum per uno Chef in una directory locale del progetto.
     * Sovrascrive eventuali file esistenti con lo stesso nome.
     *
     * @param username lo username dell’utente
     * @param selectedFile file PDF selezionato da salvare
     * @return percorso relativo del file salvato, da memorizzare nel database
     * @throws IOException in caso di errori durante la scrittura
     */
    private String saveCurriculumFile(String username, File selectedFile) throws IOException
    {
        String relativePath = "resources" + File.separator + username + File.separator + "Curriculum";
        String fullPathString = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + relativePath;

        Path destinationDir = Paths.get(fullPathString);
        Files.createDirectories(destinationDir); 

        Path destinationPath = destinationDir.resolve(selectedFile.getName());
        Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        LOGGER.log(Level.INFO, "File salvato con successo in: {0}", destinationPath);
        return relativePath + File.separator + selectedFile.getName();
    }
    
    /**
     * Gestisce la registrazione di un nuovo utente, determinando dinamicamente se registrarlo come Chef o Partecipante.
     * Effettua tutte le validazioni di unicità (username, email, codice fiscale) e salva i dati nel database.
     * <p>
     * In caso di Chef, salva anche il curriculum su disco prima dell'inserimento.
     * Mostra messaggi d'errore in caso di dati duplicati, problemi di I/O o errori sul database.
     *
     * @param currFrame il frame attivo da chiudere dopo la registrazione
     * @param isPartecipante true se l'utente è un partecipante, false se è uno chef
     * @param nome nome dell’utente
     * @param cognome cognome dell’utente
     * @param data data di nascita
     * @param luogo luogo di nascita
     * @param codFisc codice fiscale (univoco)
     * @param email email dell’utente
     * @param username username scelto
     * @param pass password dell’utente (in chiaro, verrà subito hashata)
     * @param selectedFile curriculum in PDF, obbligatorio solo per Chef (null per Partecipanti)
     */
    public void checkRegister(RegisterFrame currFrame, boolean isPartecipante, String nome,
            String cognome, LocalDate data, String luogo, String codFisc, String email,
            String username, char[] pass, File selectedFile)
    {
		try
		{
			if(isPartecipante)
				registerPartecipante(currFrame, username, nome, cognome, codFisc, data, luogo, email, pass);
			else
				registerChef(currFrame, username, nome, cognome, codFisc, data, luogo, email, pass, selectedFile);
		}
		catch(DAOException e)
		{
			LOGGER.log(Level.SEVERE, "Errore registrazione DB", e);
			registerFailed(currFrame, "Errore di accesso al database.");
		}
		catch(IOException e)
		{
			LOGGER.log(Level.SEVERE, "Errore salvataggio file", e);
			registerFailed(currFrame, "Errore di salvataggio file.");
		}
    }
    
    /**
     *  -------------------------
     * 
     *   	  LoginFrame
     *   
     *  -------------------------
	*/

    /**
     * Verifica se la password fornita corrisponde all'hash salvato.
     * @param hashedPassword hash della password da DB
     * @param inputPassword password inserita da GUI
     * @return true se corrispondono
     */
    private boolean checkPassword(String hashedPassword, char[] inputPassword)
    {
        boolean match = BCrypt.checkpw(new String(inputPassword), hashedPassword);
        Arrays.fill(inputPassword, ' ');
        return match;
    }
    
    /**
     * Recupera l'utente associato allo username fornito, cercando prima tra i partecipanti,
     * poi tra gli chef. Usato sia per la validazione in fase di login che in fase di registrazione.
     *
     * @param username username da cercare
     * @return istanza di {@link Utente} (può essere {@link Chef} o {@link Partecipante})
     * @throws DAOException se si verifica un errore durante l'accesso al database
     * @throws RecordNotFoundException se non esiste nessun utente con lo username fornito
     */
    private Utente tryGetUser(String username) throws DAOException, RecordNotFoundException
    {
        try 
        {
            return getPartecipanteDAO().getPartecipanteByUsername(username);
        } 
        catch (PartecipanteNotFoundException e1)
        {
        	try
        	{
        		return getChefDAO().getChefByUsername(username);
        	}
            catch(ChefNotFoundException e2)
        	{
            	throw new RecordNotFoundException("Utente non trovato");
        	}
        }
    }

    /**
     * Gestisce il successo del login: aggiorna lo stato e naviga alla homepage.
     *
     * @param currFrame il frame di login da chiudere
     * @param currUser l'utente autenticato
     */
    private void loginSuccess(LoginFrame currFrame, Utente currUser)
    {
    	LOGGER.log(Level.INFO, "Login riuscito per utente: {0}", currUser.getUsername());
    	this.loggedUser = currUser;
    	goToHomepage(currFrame);
    }
    
    /**
     * Gestisce il fallimento del login: mostra errore e logga l'evento.
     *
     * @param currFrame il frame di login
     * @param message messaggio di errore da mostrare
     */
    private void loginFailed(LoginFrame currFrame, String message) 
    {
        LOGGER.log(Level.WARNING, "Tentativo di Login fallito: {0}", message);
        currFrame.showError(message);
    }
    
    /**
     * Controlla se le credenziali inserite sono corrette.
     * In caso positivo, effettua login e naviga alla homepage.
     *
     * @param currFrame il frame di login corrente
     * @param username username inserito
     * @param pass password inserita
     */
	public void checkLogin(LoginFrame currFrame, String username, char[] pass)
	{
		try
		{
			Utente user = tryGetUser(username);
			
			if(checkPassword(user.getHashPassword(), pass))
				loginSuccess(currFrame, user);
			else
				loginFailed(currFrame, "Username o password errati.");			
		}
		catch(RecordNotFoundException e) 
		{
			loginFailed(currFrame, "Username o password errati."); // Evitiamo User Enumeration
		}
		catch(DAOException e)
		{
			LOGGER.log(Level.SEVERE, "Errore durante login nel DB: " + e.getMessage(), e);
			loginFailed(currFrame, "Errore di accesso al database.");
		}
		finally
	    {
	        Arrays.fill(pass, ' '); // It is recommended that the returned character array be cleared after use by setting each character to zero.
	    }
	}

	/**
     *  -------------------------
     * 
     *   	  HomepageFrame
     *   
     *  -------------------------
	*/

	/**
     *  -------------------------
     * 
     *   	  MyCoursesFrame
     *   	  CreateCourseDialog
     *   	  DetailedCourseFrame
     *   
     *  -------------------------
	*/
	public List<Argomento> loadArgomenti()
	{
		try
		{
			return getArgomentoDAO().getAllArgomenti();
		} 
		catch (DAOException e)
		{
			LOGGER.log(Level.SEVERE, "Errore loadArgomenti da DB", e);
		}
		
		return null;
	}
	
	public List<Ricetta> loadRicette()
	{
		try
		{
			return getRicettaDAO().getRicetteByIdChef(loggedUser.getId());
		} 
		catch (DAOException e)
		{
			LOGGER.log(Level.SEVERE, "Errore loadRicette da DB", e);
		}
		
		return null;
	}
	
	
	/**
     *  -------------------------
     * 
     *   	  MyRecipesFrame
     *   
     *  -------------------------
	*/
	// CreateRecipesDialog
	// DetailedRecipeFrame
	
	
	
	
	
	/**
     *  -------------------------
     * 
     *   	   ReportFrame
     *   
     *  -------------------------
	*/
	
	/**
	 * Carica e apre il report mensile dello Chef autenticato.
	 * @param parent frame genitore da cui si accede
	 */
	public void openMonthlyReport(JFrame parent)
	{
		EventQueue.invokeLater(() -> 
        {
        	try
        	{
        		ReportMensile report = getReportDAO().getMonthlyReportByIdChef(loggedUser.getId());
        		ReportFrame rFrame = new ReportFrame();
        	    rFrame.setReportData(report.getTotCorsi(), report.getTotOnline(), report.getTotPratiche(),
    					 			 report.getMinRicette(), report.getMaxRicette(), report.getAvgRicette());
        	    rFrame.setVisible(true);
        	}
        	catch(DAOException e)
        	{
        		JOptionPane.showMessageDialog(parent, "Errore durante il caricamento del report.", "Errore", JOptionPane.ERROR_MESSAGE);
        		LOGGER.log(Level.SEVERE, "Errore durante il report nel DB: " + e.getMessage(), e);
        	}
        });
	}
	
	/**
     *  -------------------------
     * 
     *   	  ProfileFrame
     *   
     *  -------------------------
	*/
	
	public void checkNewPassword(ChangePasswordDialog currFrame, char[] oldPass, char[] newPass)
	{
		try
		{
			Utente user = getLoggedUser();
			if(checkPassword(user.getHashPassword(), oldPass))
			{
				//newPassSuccess(currFrame, );
				//aggiornaPassword(currFrame, user, newPass);
			}
				
			/*else
				loginFailed(currFrame, "Username o password errati.");*/			
		}
		catch(RecordNotFoundException e) 
		{
			//loginFailed(currFrame, "Username o password errati."); // Evitiamo User Enumeration
		}
		catch(DAOException e)
		{
			LOGGER.log(Level.SEVERE, "Errore durante login nel DB: " + e.getMessage(), e);
			//loginFailed(currFrame, "Errore di accesso al database.");
		}
		finally
	    {
	        //Arrays.fill(pass, ' '); // It is recommended that the returned character array be cleared after use by setting each character to zero.
	    }
	}
	
	private void aggiornaPassword(RegisterFrame currFrame, Utente user, char[] newPass)
	{
		if (isChefLogged())
		{
			
		}
	}
	/*private void registerSuccess(RegisterFrame currFrame, String username) 
    {
		LOGGER.log(Level.INFO, "Registrazione riuscita per utente: {0}", username);
    	goToLogin(currFrame);
    }*/
	
    /**
     * Notifica il fallimento della registrazione loggando l'errore e mostrando un messaggio all'utente.
     * 
     * @param currFrame il frame di registrazione
     * @param message messaggio di errore da mostrare
     */
    /*private void registerFailed(RegisterFrame currFrame, String message) 
    {
    	LOGGER.log(Level.WARNING, "Tentativo di registrazione fallito: {0}", message);
        currFrame.showError(message);
    }*/
	
}