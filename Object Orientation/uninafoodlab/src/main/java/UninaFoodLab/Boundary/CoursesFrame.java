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

public class CoursesFrame extends JXFrame 
{

    private static final long serialVersionUID = 1L;

    // Icone e immagini utilizzate 
    private final FontIcon hamburgerIcon = FontIcon.of(MaterialDesign.MDI_MENU, 26, Color.DARK_GRAY);
    private final FontIcon filterIcon = FontIcon.of(MaterialDesign.MDI_FILTER, 18, Color.DARK_GRAY);
    private final FontIcon profileIcon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_CIRCLE, 26, Color.DARK_GRAY);
    private ImageIcon windowLogo, paneLogo;
    
    // Componenti Swing
    private JXLabel logoLabel;
    private JXPanel rootPanel, header, mainContentPanel;
    private JXButton hamburgerBtn, filterBtn, searchBtn, profileBtn;
    private JXTextField searchField;
    private ProfileDropdownPanel dropdownPanel;
    private SidebarPanel sidebar;
    
    // Listeners
    private AWTEventListener dropdownClickListener;
    private ActionListener profileBtnListener, hamburgerBtnListener;  
    private MouseListener logoLabelMouseListener;
    private ComponentAdapter frameComponentListener;
    

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                new CoursesFrame().setVisible(true);
    
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
    public CoursesFrame()
    {
        setTitle("UninaFoodLab - I miei corsi");
        setMinimumSize(new Dimension(700, 600));
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setExtendedState(JXFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
        setResizable(true);
        
        initComponents();
        getRootPane().setDefaultButton(searchBtn);
        initListeners();

        // Setto il focus iniziale sul bottone search (prima era sul menu hamburger)
        EventQueue.invokeLater(() -> searchField.requestFocusInWindow());
        
        setVisible(true);
    }
   
    /**
     * Inizializza e configura tutti i componenti grafici della finestra,
     * inclusi header, logo, barra di ricerca, pulsanti, sidebar e dropdown del profilo.
     * <p>
     * I componenti principali vengono disposti nel root panel usando MigLayout.
     */

    private void initComponents()
    {
    	rootPanel = new JXPanel(new MigLayout("fill, insets 0", "[grow]", "[][grow]"));
    	header = new JXPanel(new MigLayout(
                "insets 5 15 5 15",
                "[35!]10[10!]10[80!]10[20!]0[80!]0[grow, 50::1400]0[80!]10[grow, 10::89][35!][20!]",
                "[60!]"
        ));
    	hamburgerBtn = new JXButton();
    	filterBtn = new JXButton("Filtri");
    	searchField = new JXTextField();
    	searchBtn = new JXButton("Cerca");
    	profileBtn = new JXButton();
        sidebar = new SidebarPanel(this);
        dropdownPanel = new ProfileDropdownPanel(this);
        
    	windowLogo = new ImageIcon(getClass().getResource("/logo_finestra.png"));
        setIconImage(windowLogo.getImage());

        rootPanel.setBackground(new Color(0xFAFAFA));
        setContentPane(rootPanel);

        header.setBackground(Color.WHITE);
        header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0xDDDDDD)));
        rootPanel.add(header, "dock north");

        hamburgerBtn.setIcon(hamburgerIcon);
        hamburgerBtn.setContentAreaFilled(false);
        hamburgerBtn.setBorder(BorderFactory.createEmptyBorder());
        hamburgerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(hamburgerBtn, "cell 0 0, w 35!, h 35!, shrink 0");

        paneLogo = new ImageIcon(getClass().getResource("/logo_schermata.png"));
       	logoLabel = new JXLabel(new ImageIcon(paneLogo.getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));;
        logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoLabel.setToolTipText("Torna alla homepage");
        header.add(logoLabel, "cell 2 0, w 80!, h 60!, gapleft 0, shrink 0");
        
        filterBtn.setIcon(filterIcon);
        filterBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        filterBtn.setFocusPainted(false);
        filterBtn.setContentAreaFilled(true);
        filterBtn.setBackground(new Color(245, 245, 245));
        filterBtn.setBorder(BorderFactory.createCompoundBorder(
                 new LineBorder(new Color(200, 200, 200), 1, true),
                 BorderFactory.createEmptyBorder(5, 10, 5, 10)
         ));
        filterBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(filterBtn, "cell 4 0, h 35!, w 80!, shrink 0");
       
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPrompt("Cerca per nome corso...");
        searchField.setBorder(BorderFactory.createCompoundBorder(
                 new LineBorder(new Color(200, 200, 200), 1, true),
                 BorderFactory.createEmptyBorder(5, 10, 5, 10)
         ));
        searchField.setBackground(Color.WHITE);
        searchField.setOpaque(true);
        header.add(searchField, "cell 5 0, h 35!, growx, wmin 100, wmax 1400");

        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchBtn.setFocusPainted(false);
        searchBtn.setBackground(new Color(225, 126, 47));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setOpaque(true);
        searchBtn.setBorder(BorderFactory.createCompoundBorder(
                 new LineBorder(new Color(225, 126, 47), 1, true),
                 BorderFactory.createEmptyBorder(5, 15, 5, 15)
         ));
        searchBtn.setPreferredSize(new Dimension(80, 35));
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(searchBtn, "cell 6 0, h 35!, w 80!, shrink 0");

        profileBtn.setIcon(profileIcon);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setBorder(BorderFactory.createEmptyBorder());
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(profileBtn, "cell 8 0, w 35!, h 35!, align right");
    
         
       
        dropdownPanel.setVisible(false);
        // Aggiungo il dropdown come popup (quindi che viene messo su un layer superiore a tutti) 
        getLayeredPane().add(dropdownPanel, JLayeredPane.POPUP_LAYER);
         
        sidebar.setVisible(false);
        // Lo mettiamo su un layer superiore, come il dropdown
        getLayeredPane().add(sidebar, JLayeredPane.POPUP_LAYER);
 
        mainContentPanel = new JXPanel(new MigLayout("fill", "[grow]", "[grow]"));
        rootPanel.add(mainContentPanel, "grow, wrap");
    }
 
    /**
     * Registra tutti i listener necessari per l'interazione utente.
     * <ul>
     *   <li>Click su logo per tornare alla homepage</li>
     *   <li>Gestione toggle dropdown del profilo</li>
     *   <li>Chiusura automatica del dropdown cliccando fuori</li>
     *   <li>Gestione toggle della sidebar</li>
     *   <li>Listener su ridimensionamento/spostamento finestra per aggiornare posizione componenti</li>
     * </ul>
     */
    private void initListeners()
    {
    	 /*
         * Listeners di navigazione
         */
    	logoLabelMouseListener = new MouseAdapter()
		   {
			 @Override
			 public void mouseClicked(MouseEvent e)
			 {
				 Controller.getController().goToHomepage(CoursesFrame.this);
			 }
		   };
		   logoLabel.addMouseListener(logoLabelMouseListener);	

    	/**
         * Gestisce il click sul pulsante del profilo.
         * Mostra o nasconde il pannello dropdown del profilo posizionandolo correttamente
         * sotto l'header e allineato al bordo destro della finestra.
         * Garantisce che il dropdown appaia sempre nella posizione visibile e corretta.
         */
    	profileBtnListener = new ActionListener()
					         {
					        	 @Override 
								 public void actionPerformed(ActionEvent e)
								 {
						   		     dropdownPanel.setSize(dropdownPanel.getPreferredSize());
						       		 Point headerBottomLeft = SwingUtilities.convertPoint(header, 0, header.getHeight(), getLayeredPane());
						             int x = getLayeredPane().getWidth() - dropdownPanel.getWidth() - 1;
						             int y = headerBottomLeft.y;
						             dropdownPanel.setLocation(x, y);
						             dropdownPanel.setVisible(!dropdownPanel.isVisible());
								 }
					         };
		profileBtn.addActionListener(profileBtnListener);	
		
		/**
         * Listener globale per intercettare tutti i click del mouse nella finestra.
         * Se il click avviene fuori dal pannello dropdown del profilo e dal pulsante profilo,
         * nasconde automaticamente il dropdown.
         * Serve a chiudere il menu dropdown cliccando ovunque fuori da esso,
         * migliorando l'usabilità e la gestione del focus.
         */
		dropdownClickListener = new AWTEventListener() 
						        {
						            @Override
						            public void eventDispatched(AWTEvent event) 
						            {
						            	if(!dropdownPanel.isVisible()) return;
						                // Verifica che l'evento sia un click del mouse
						                if(event instanceof MouseEvent) 
						                {
						                    MouseEvent me = (MouseEvent) event;
						                    if(me.getID() == MouseEvent.MOUSE_PRESSED) 
						                    {
						                        // Ottiene la posizione assoluta del mouse al momento del click (sul monitor)
						                        Point pt = me.getLocationOnScreen();
						
						                        // Ottiene la posizione assoluta dell'intera finestra (top-left corner)
						                        Point winLoc = getLocationOnScreen();
						
						                        // Calcola la posizione del click dentro la finestra
						                        Point compPt = new Point(pt.x - winLoc.x, pt.y - winLoc.y);
						
						                        // Se il click NON è dentro il dropdown E NON è sul pulsante del profilo...
						                        if(!dropdownPanel.getBounds().contains(compPt) && !profileBtn.getBounds().contains(compPt))
						                            // ...nascondi il dropdown
						                            dropdownPanel.setVisible(false);
						                    }
						                }
						            }
						        };     
        Toolkit.getDefaultToolkit().addAWTEventListener(dropdownClickListener, AWTEvent.MOUSE_EVENT_MASK);
        
        /**
         * Gestisce il click sul pulsante hamburger del menu.
         * Alterna la visibilità della sidebar laterale.
         * Quando la sidebar viene mostrata, viene posizionata sotto l'header
         * e adattata all'altezza della finestra corrente.
         * Quando la sidebar viene nascosta, viene semplicemente disabilitata.
         */
        hamburgerBtnListener = new ActionListener() 
						        {
						            @Override
						            public void actionPerformed(ActionEvent e)
						            {
						                if(!sidebar.isVisible())
						                {
						                    // Posizione e dimensioni
						                    // Calcola la posizione Y del pannello header all'interno del layered pane
						                    Point headerBottomLeft = SwingUtilities.convertPoint(header, 0, header.getHeight(), getLayeredPane());
						
						                    sidebar.setBounds(
						                        0,
						                        headerBottomLeft.y,
						                        190,
						                        getHeight() - headerBottomLeft.y
						                    );
						
						                    // Porta in primo piano
						                    getLayeredPane().setLayer(sidebar, JLayeredPane.POPUP_LAYER);
						                }
						                sidebar.setVisible(!sidebar.isVisible());
						            }
						        };
        hamburgerBtn.addActionListener(hamburgerBtnListener);
					
		
	    /**
         * Listener per eventi di ridimensionamento e spostamento della finestra.
         * Aggiorna la posizione e dimensione della sidebar e del dropdown del profilo
         * per mantenere un corretto posizionamento rispetto all'header e alla finestra.
         * Garantisce che i componenti popup rimangano ben allineati e visibili anche al resize o spostamento.
         */
        frameComponentListener = new ComponentAdapter()
	        					 {
	        						@Override
	        						public void componentResized(ComponentEvent e)
	        						{
	        							updateDropDownPosition();
	        							updateSidebarPosition();
	        						}
	        						@Override
	        						public void componentMoved(ComponentEvent e)
	        						{
	        							updateDropDownPosition();
	        							updateSidebarPosition();
	        						}
	        					 };
		addComponentListener(frameComponentListener);
    }
    
    /**
     * Rimuove in sicurezza tutti i listener registrati (AWT, azioni, mouse, component).
     * Va chiamato prima di chiudere la finestra per evitare memory leak o comportamenti anomali.
     */
    private void disposeListeners() 
    {
        if(dropdownClickListener != null)
            Toolkit.getDefaultToolkit().removeAWTEventListener(dropdownClickListener);
        
        sidebar.disposeListeners();
        dropdownPanel.disposeListeners();
        
        if(profileBtn != null && profileBtnListener != null)
            profileBtn.removeActionListener(profileBtnListener);
        
        if(hamburgerBtn != null && hamburgerBtnListener != null)
            hamburgerBtn.removeActionListener(hamburgerBtnListener);

        if(logoLabel != null && logoLabelMouseListener != null)
            logoLabel.removeMouseListener(logoLabelMouseListener);

        if(frameComponentListener != null)
            removeComponentListener(frameComponentListener);
    }
 
    /**
     * Esegue una pulizia completa dei listener e delle risorse
     * prima di chiudere la finestra. Override del metodo {@code JXFrame.dispose()}.
     */
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
        searchField.setText("");
        searchField.requestFocusInWindow();
        // scrollPane.getVerticalScrollBar().setValue(0); // se hai scroll
        // refreshTableModel(); // se carichi dati
    }
   
    /**
     * Ricalcola e aggiorna dinamicamente la posizione del pannello dropdown del profilo
     * in base alla larghezza del layered pane e all'altezza dell'header.
     */
    private void updateDropDownPosition()
    {
        dropdownPanel.updatePosition(header, getLayeredPane());
    }

    /**
     * Ricalcola e aggiorna dinamicamente la posizione e l'altezza della sidebar (hamburger menu)
     * in modo che sia sempre allineata sotto l'header e adatta alla dimensione corrente della finestra.
     */
    private void updateSidebarPosition()
    {
        sidebar.updatePosition(header, getLayeredPane(), getHeight());
    }
}
