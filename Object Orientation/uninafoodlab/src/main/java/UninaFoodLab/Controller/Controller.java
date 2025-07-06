package UninaFoodLab.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

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
    	if(!(currFrame instanceof HomepageFrame))
    	{
    		currFrame.dispose();
            new HomepageFrame().setVisible(true);
    	}
    }

    public void goToCourses(JFrame currFrame)
	{
    	if(!(currFrame instanceof CoursesFrame))
    	{
	    	currFrame.dispose();
	        new CoursesFrame().setVisible(true);	
    	}
    	else
    		((CoursesFrame) currFrame).resetView();
	}
    
    public void goToRecipes(JFrame currFrame)
	{
    	if(!(currFrame instanceof RecipesFrame))
    	{
	    	currFrame.dispose();
	        new RecipesFrame().setVisible(true);	
    	}
    	else
    		((RecipesFrame) currFrame).resetView();
	}
    
    public void goToReport(JFrame currFrame)
   	{
    	if(!(currFrame instanceof ReportFrame))
    	{
	       	currFrame.dispose();
	        new ReportFrame().setVisible(true);	
    	}
    	else
    		((ReportFrame) currFrame).resetView();
   	}
    
    public void goToProfile(JFrame currFrame)
	{
    	if(!(currFrame instanceof ProfileFrame))
    	{
	    	currFrame.dispose();
	        new ProfileFrame().setVisible(true);	
    	}
    	else
    		((ProfileFrame) currFrame).resetView();
	}
    
    public void logout(JFrame currFrame)
    {
        currFrame.dispose();
        loggedUser = null; // metto i riferimenti di tutto quello salvato attualmente nel controller a null
        new LoginFrame().setVisible(true);
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
    
    public void checkRegister (RegisterFrame currFrame, boolean partecipante, boolean chef, String nome, String cognome, LocalDate data, String luogo, String codFisc, String email, String username, char[] pass, Path path, String percorsoComposto, File selectedFile)
    {
    	try
    	{
    		tryGetUser(username);
			registerFailed(currFrame, "Username già utilizzato.");   		
    	}
    	catch(RecordNotFoundException e)
    	{
    		if(partecipante==true)
    		{
    			try
    			{
    				if(getPartecipanteDAO().getPartecipanteByCodEmail(codFisc, email))
    				{
    					registerFailed(currFrame, "Partecipante già registrato.");
    				}
    				else
    				{
    					try
    					{
    						getPartecipanteDAO().save(new Partecipante(username, nome, cognome, codFisc, data, luogo, email, hashPassword(pass), null, null));
    					}
    					catch(DAOException e1)
    					{
    						LOGGER.log(Level.SEVERE, "Errore registrazione DB", e);
    						registerFailed(currFrame, "Errore di accesso al database.");
    					}
    				}
    			}
    			catch(DAOException  e2)
    			{
    				LOGGER.log(Level.SEVERE, "Errore registrazione DB", e);
    				registerFailed(currFrame, "Errore di accesso al database.");
    			}
    		}
    		else
    		{
    			try
    			{
    				if(getChefDAO().getChefByCodEmail(codFisc, email))
    				{
    					registerFailed(currFrame, "Chef già registrato.");
    				}
    				else
    				{
    					try
    					{
    						getChefDAO().save(new Chef(username, nome, cognome, codFisc, data, luogo, email, hashPassword(pass), path.toString(), null, null));
    					}
    					catch(DAOException e1)
    					{
    						LOGGER.log(Level.SEVERE, "Errore registrazione DB", e);
    						registerFailed(currFrame, "Errore di accesso al database.");
    					}	
    				}
    				
    				try {
		        		
		        		File cartellaComposta = new File(percorsoComposto);
		        				
		                if (!cartellaComposta.exists()) {
		                    if (cartellaComposta.mkdirs()) {
		                        System.out.println("Cartella composta creata con successo: " + percorsoComposto);
		                    } else {
		                        System.out.println("Impossibile creare la cartella composta.");
		                    }
		                } else {
		                    System.out.println("La cartella composta esiste già: " + percorsoComposto);
		                }
		                Files.copy(selectedFile.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
		                System.out.println("File salvato con successo in: " + path);
		            } catch (IOException e1) {
		                System.err.println("Errore durante il salvataggio del file: " + e1.getMessage());
		            }
    			}
    			catch(ChefNotFoundException e3)
    			{
    				LOGGER.log(Level.SEVERE, "Errore registrazione DB", e);
    				registerFailed(currFrame, "Errore di accesso al database.");
    			}
    			
    		}
    	}
    }
    
    private void registerFailed(RegisterFrame currFrame, String message) 
    {
    	LOGGER.log(Level.WARNING, "Tentativo di registrazione fallito: {0}", message);
        currFrame.showError(message);
        currFrame.enableButtons();
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