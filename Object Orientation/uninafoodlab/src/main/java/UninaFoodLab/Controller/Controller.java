package UninaFoodLab.Controller;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.mindrot.jbcrypt.BCrypt;

import com.formdev.flatlaf.FlatLightLaf;

import UninaFoodLab.Boundary.*;
import UninaFoodLab.DTO.*;
import UninaFoodLab.DAO.*;
import UninaFoodLab.DAO.Postgres.*;
import UninaFoodLab.Exceptions.*;

public class Controller
{
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private static Controller instance = null;
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
    	java.awt.EventQueue.invokeLater(() -> {
    		
    		try 
    		{
    	        UIManager.setLookAndFeel(new FlatLightLaf());
    	    } 
    		catch (UnsupportedLookAndFeelException e) 
    		{
    	        e.printStackTrace();
    	    }
    		
            new LoginFrame().setVisible(true);
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
    	currFrame.dispose();
        new LoginFrame().setVisible(true);
    }
    
    public void goToRegister(JFrame currFrame)
    {
    	currFrame.dispose();
        new RegisterFrame().setVisible(true);
    }
    
    public void goToHomepage(JFrame currFrame)
    {
    	currFrame.dispose();
        new HomepageFrame().setVisible(true);
    }

    
    
    // RegisterFrame
    public String hashPassword(char[] plainPassword)
    {
        String hashed = BCrypt.hashpw(new String(plainPassword), BCrypt.gensalt(11));
        Arrays.fill(plainPassword, ' ');
        return hashed;
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
        currFrame.enableButtons();
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
			LOGGER.log(Level.SEVERE, "Errore login DB", e);
			loginFailed(currFrame, "Errore di accesso al database.");
		}
		finally
	    {
	        Arrays.fill(pass, ' '); // It is recommended that the returned character array be cleared after use by setting each character to zero.
	    }
	}
	
	// HomepageFrame

	// CoursesFrame
	
	
	
}