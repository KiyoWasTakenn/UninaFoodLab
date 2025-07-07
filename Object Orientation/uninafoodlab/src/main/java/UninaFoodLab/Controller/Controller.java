package UninaFoodLab.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.mindrot.jbcrypt.BCrypt;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import UninaFoodLab.Boundary.*;
import UninaFoodLab.DTO.*;
import UninaFoodLab.DAO.*;
import UninaFoodLab.DAO.Postgres.*;
import UninaFoodLab.Exceptions.*;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class Controller
{
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	private static final String ERR_CF_EXISTING = "Esiste già un account associato a questo codice fiscale.";
	private static final String ERR_EMAIL_EXISTING = "Esiste già un account associato a questa email";
	
    private static Controller instance = null;
    
    // Variabili di Sessione
    private Utente loggedUser;
    
    private AdesioneDAO_Postgres adesioneDAO;
    private ArgomentoDAO_Postgres argomentoDAO;
    private ChefDAO_Postgres chefDAO;
    private CorsoDAO_Postgres corsoDAO;
    private IngredienteDAO_Postgres ingredienteDAO;
    private PartecipanteDAO_Postgres partecipanteDAO;
    private RicettaDAO_Postgres ricettaDAO;
    private SessioneOnlineDAO_Postgres sessioneOnlineDAO;
    private SessionePraticaDAO_Postgres sessionePraticaDAO;
    private UtilizzoDAO_Postgres utilizzoDAO;
    
    private Controller() {}

    public static Controller getController()
    {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                System.out.println("FlatLaf applicato correttamente");
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            System.out.println("Provo a creare LoginFrame");
            try {
                new LoginFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        });
    }

    public Utente getLoggedUser()
    {
        return loggedUser;
    }
    
    public boolean isChefLogged()
    {
        return loggedUser instanceof Chef;
    }

    public boolean isPartecipanteLogged() 
    {
        return loggedUser instanceof Partecipante;
    }

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
    
    // Navigation methods
  
    public void goToLogin(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
        {
            currFrame.dispose();
            new LoginFrame().setVisible(true);
        });
    }

    public void goToRegister(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
        {
            currFrame.dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    public void goToHomepage(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
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

    public void goToCourses(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
        {
            if(!(currFrame instanceof CoursesFrame)) 
            {
                currFrame.dispose();
                new CoursesFrame().setVisible(true);
            }
            else
                ((CoursesFrame) currFrame).resetView();
        });
    }

    public void goToRecipes(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
        {
            if(!(currFrame instanceof RecipesFrame))
            {
                currFrame.dispose();
                new RecipesFrame().setVisible(true);
            }
            //else
            	//((RecipesFrame) currFrame).resetView();
        });
    }

    public void goToReport(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
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

    public void goToProfile(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() -> 
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

    public void logout(JFrame currFrame)
    {
        SwingUtilities.invokeLater(() ->
        {
            currFrame.dispose();
            loggedUser = null;
            new LoginFrame().setVisible(true);
        });
    }

    public void toggleDarkMode(JFrame currFrame)
    {
    	if(UIManager.getLookAndFeel() instanceof FlatLightLaf)
    		FlatDarkLaf.setup();
    	else
    		FlatLightLaf.setup();
    		
    	SwingUtilities.updateComponentTreeUI(currFrame);
    }
    
    // RegisterFrame
    public String hashPassword(char[] plainPassword)
    {
        String hashed = BCrypt.hashpw(new String(plainPassword), BCrypt.gensalt(11));
        Arrays.fill(plainPassword, ' ');
        return hashed;
    }
    
    private void registerSuccess(RegisterFrame currFrame, String username) 
    {
		LOGGER.log(Level.INFO, "Registrazione riuscita per utente: {0}", username);
    	goToLogin(currFrame);
    }
	
    private void registerFailed(RegisterFrame currFrame, String message) 
    {
    	LOGGER.log(Level.WARNING, "Tentativo di registrazione fallito: {0}", message);
        currFrame.showError(message);
    }
    
    private void registerPartecipante(RegisterFrame currFrame, String username, String nome, String cognome, String codFisc, 
    								  LocalDate data, String luogo, String email, char[] pass) throws DAOException
    {
        if(getPartecipanteDAO().getPartecipanteByCodiceFiscale(codFisc))
            registerFailed(currFrame, ERR_CF_EXISTING);
        else if(getPartecipanteDAO().getPartecipanteByEmail(email))
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
    
    private void registerChef(RegisterFrame currFrame, String username, String nome, String cognome, String codFisc, LocalDate data, 
    		 				  String luogo, String email, char[] pass, File selectedFile) throws DAOException, IOException
    {
        if(getChefDAO().getChefByCodiceFiscale(codFisc))
            registerFailed(currFrame, ERR_CF_EXISTING);
        else if(getChefDAO().getChefByEmail(email))
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
    
    // LoginFrame
    private boolean checkPassword(String hashedPassword, char[] inputPassword)
    {
        boolean match = BCrypt.checkpw(new String(inputPassword), hashedPassword);
        Arrays.fill(inputPassword, ' ');
        return match;
    }
    
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

    private void loginSuccess(LoginFrame currFrame, Utente currUser)
    {
    	LOGGER.log(Level.INFO, "Login riuscito per utente: {0}", currUser.getUsername());
    	this.loggedUser = currUser;
    	goToHomepage(currFrame);
    }
    
    private void loginFailed(LoginFrame currFrame, String message) 
    {
        LOGGER.log(Level.WARNING, "Tentativo di Login fallito: {0}", message);
        currFrame.showError(message);
    }
    
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

	// HomepageFrame

	// CoursesFrame
	
	// ReportFrame
	public void openMonthlyReport(JFrame parent)
	{
	    ReportFrame report = new ReportFrame();
	    report.setReportData();
	    report.setVisible(true);


	}

	
}