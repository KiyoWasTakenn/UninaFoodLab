package UninaFoodLab.Boundary;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import com.formdev.flatlaf.FlatLightLaf;

import UninaFoodLab.Controller.Controller;
import net.miginfocom.swing.MigLayout;

/**
 * {@code CoursesFrame} rappresenta la finestra principale della sezione "I miei corsi"
 * <p>
 * La finestra è costruita utilizzando Swing, con layout gestiti tramite MigLayout.
 * Presenta un'interfaccia utente con supporto per:
 * <ul>
 *   <li>Header con logo, barra di ricerca, filtro, hamburger menu e profilo utente</li>
 *   <li>Sidebar navigabile per le sezioni principali (Homepage, Corsi, Ricette, Report)</li>
 *   <li>Dropdown del profilo con opzioni di visualizzazione e logout</li>
 *   <li>Gestione eventi centralizzata per navigazione, ridimensionamento e usabilità</li>
 * </ul>
 * <p>
 * I componenti `ProfileDropdownPanel` e `SidebarPanel` sono aggiunti dinamicamente 
 * al `JLayeredPane` per essere visualizzati sopra il contenuto principale.
 * <p>
 * La classe gestisce correttamente eventi AWT globali, listener di interazione e
 * rimozione sicura degli stessi per evitare memory leak.
 *
 * @see SidebarPanel
 * @see ProfileDropdownPanel
 * @see Controller
 */

public class MyCoursesFrame extends JXFrame 
{

    private static final long serialVersionUID = 1L;

    private JXPanel rootPanel,  mainContentPanel;
    private HeaderPanel header;
 
    // Listeners
    

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                new MyCoursesFrame().setVisible(true);
    
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Costruttore della finestra {@code CoursesFrame}.
     * Inizializza dimensioni, layout, componenti grafici e listener.
     * La finestra viene massimizzata e resa visibile.
     */  
    public MyCoursesFrame()
    {
        setTitle("UninaFoodLab - I miei corsi");
        setMinimumSize(new Dimension(700, 600));
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setExtendedState(JXFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        initComponents();
        initListeners(); 
        setVisible(true);
    }
   
 

    private void initComponents()
    {
    	// Icona finestra
        ImageIcon windowLogo = new ImageIcon(getClass().getResource("/logo_finestra.png"));
        setIconImage(windowLogo.getImage());

        // Root panel
        rootPanel = new JXPanel(new MigLayout("fill, insets 0", "[grow]", "[][grow]"));
        rootPanel.setBackground(new Color(0xFAFAFA));
        setContentPane(rootPanel);

        // Header riutilizzabile
        header = new HeaderPanel(this, getLayeredPane());
        rootPanel.add(header, "dock north");

        // Pannello principale dei contenuti
        mainContentPanel = new JXPanel(new MigLayout("fill", "[grow]", "[grow]"));
        rootPanel.add(mainContentPanel, "grow, wrap");

        // Esempio: apertura dialog di creazione corso
        CreateCourseDialog dialog = new CreateCourseDialog(this);
        dialog.setVisible(false);

    }
 
 
    
    private void initListeners()
    {

    }
    
    
    private void disposeListeners() 
    {
        header.disposeListeners();
    }
 
   
    @Override
    public void dispose()
    {
    	disposeListeners();
        super.dispose();
    }
    
    /**
     * Ripristina la visualizzazione iniziale della finestra:
     * <ul>
     *   <li>Svuota il campo di ricerca</li>
     *   <li>Imposta il focus sul campo di ricerca</li>

     * </ul>
     */
    public void resetView() 
    {
        header.resetView();
        // scrollPane.getVerticalScrollBar().setValue(0); // se hai scroll
        // refreshTableModel(); // se carichi dati
    }
   
  
}
