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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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

public class CoursesFrame extends JXFrame 
{

    private static final long serialVersionUID = 1L;

    // Icone e immagini utilizzate 
    private final FontIcon userIcon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_CIRCLE, 20, new Color(80, 80, 80));
    private final FontIcon hamburgerIcon = FontIcon.of(MaterialDesign.MDI_MENU, 26, Color.DARK_GRAY);
    private final FontIcon filterIcon = FontIcon.of(MaterialDesign.MDI_FILTER, 18, Color.DARK_GRAY);
    private final FontIcon profileIcon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_CIRCLE, 26, Color.DARK_GRAY);
    private final FontIcon profileIconMenu = FontIcon.of(MaterialDesign.MDI_ACCOUNT, 18, new Color(60, 60, 60));
    private final FontIcon logoutIcon = FontIcon.of(MaterialDesign.MDI_LOGOUT, 18, new Color(60, 60, 60));
    private ImageIcon windowLogo;
    private ImageIcon paneLogo;
    
    // Componenti Swing e Bordi
    private JXLabel logoLabel;
    private final CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
									             new LineBorder(new Color(220, 220, 220), 1, true),
									             new EmptyBorder(10, 12, 10, 12));
    private JXPanel rootPanel;
    private JXPanel header;
    private JXButton hamburgerBtn;
    private JXButton filterBtn;
    private JXTextField searchField;
    private JXButton searchBtn;
    private JXButton profileBtn;
    private JXPanel dropdownPanel;
    private JXLabel userLabel;
    private JXPanel separator;
    private JXButton profileItemBtn;
    private JXButton logoutItemBtn;
    private JXPanel sidebarPanel;
    private JXButton homeBtn;
    private JXButton coursesBtn;
    private JXButton recipesBtn;
    private JXButton reportBtn;
    private JXPanel mainContentPanel;

    // Listeners
    private AWTEventListener dropdownClickListener;
    private ActionListener profileBtnListener;  
    private ActionListener hamburgerBtnListener;
    private MouseListener logoLabelMouseListener;
    private ActionListener homeBtnListener;
    private ActionListener coursesBtnListener;
    private ActionListener recipesBtnListener;
    private ActionListener reportBtnListener;
    private ActionListener profileItemBtnListener;
    private ActionListener logoutItemBtnListener;
    private MouseListener hoverListener;
    private ComponentAdapter frameComponentListener;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                new CoursesFrame().setVisible(true);
    
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

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
        styleComponents();
        initListeners();

        // Setto il focus iniziale sul bottone search (prima era sul menu hamburger)
        EventQueue.invokeLater(() -> searchField.requestFocusInWindow());
    }
   
    /**
     * Serve per inizializzare i componenti UI, creare e aggiungere tutti i pannelli e pulsanti.
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
    	dropdownPanel = new JXPanel(new MigLayout("insets 10, wrap 1", "[fill, grow]"));
    	userLabel = new JXLabel("ciao", userIcon, JXLabel.LEFT); /*Controller.getController().getLoggedUser().getUsername()*/
    	separator = new JXPanel();
    	profileItemBtn = new JXButton("  Profilo", profileIconMenu);
    	logoutItemBtn = new JXButton("  Logout", logoutIcon);
    	sidebarPanel = new JXPanel();
    	homeBtn = new JXButton("  Homepage");
    	coursesBtn = new JXButton("  I miei corsi");
    	recipesBtn = new JXButton(" Le mie ricette");
    	reportBtn = new JXButton(" Visualizza Report");
    	
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
    
         
        dropdownPanel.setBackground(Color.WHITE);
        dropdownPanel.setBorder(defaultBorder);
        dropdownPanel.setOpaque(true);
        dropdownPanel.setVisible(false);

        userLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        userLabel.setForeground(new Color(40, 40, 40));
        userLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        dropdownPanel.add(userLabel, "gaptop 0");

        separator.setBackground(new Color(200, 200, 200));
        separator.setPreferredSize(new Dimension(1, 2));
        separator.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        dropdownPanel.add(separator, "h 2!, gapbottom 8");
         
        dropdownPanel.add(profileItemBtn);
        dropdownPanel.add(logoutItemBtn);
         
        // Aggiungo il dropdown come popup (quindi che viene messo su un layer superiore a tutti) 
        getLayeredPane().add(dropdownPanel, JLayeredPane.POPUP_LAYER);
         
        // Menu hamburger 
        sidebarPanel.setLayout(new MigLayout("insets 20, wrap 1", "[grow, fill]"));
        sidebarPanel.setBackground(Color.WHITE);
        sidebarPanel.setBorder(new MatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));
        sidebarPanel.setPreferredSize(new Dimension(190, 800));
        sidebarPanel.setVisible(false); 

        // Lo mettiamo su un layer superiore, come il dropdown
        getLayeredPane().add(sidebarPanel, JLayeredPane.POPUP_LAYER);
         
        sidebarPanel.add(homeBtn);
        sidebarPanel.add(coursesBtn);
        sidebarPanel.add(recipesBtn);
        sidebarPanel.add(reportBtn);  
        
        mainContentPanel = new JXPanel(new MigLayout("fill", "[grow]", "[grow]"));
        rootPanel.add(mainContentPanel, "grow, wrap");




    }
 
    /**
     * Registra tutti i listener per pulsanti, eventi di mouse e
     * gestione dell'interfaccia.
     */
    private void initListeners()
    {
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
						                if(!sidebarPanel.isVisible())
						                {
						                    // Posizione e dimensioni
						                    // Calcola la posizione Y del pannello header all'interno del layered pane
						                    Point headerBottomLeft = SwingUtilities.convertPoint(header, 0, header.getHeight(), getLayeredPane());
						
						                    sidebarPanel.setBounds(
						                        0,
						                        headerBottomLeft.y,
						                        190,
						                        getHeight() - headerBottomLeft.y
						                    );
						
						                    // Porta in primo piano
						                    getLayeredPane().setLayer(sidebarPanel, JLayeredPane.POPUP_LAYER);
						
						                    sidebarPanel.setVisible(true);
						                }
						                else
						                	sidebarPanel.setVisible(false);
						            }
						        };
        hamburgerBtn.addActionListener(hamburgerBtnListener);
        
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
		
		homeBtnListener = new ActionListener()
						  {
							 @Override
							 public void actionPerformed(ActionEvent e)
							 {
								 Controller.getController().goToHomepage(CoursesFrame.this);
							 }
						  };
		homeBtn.addActionListener(homeBtnListener);
		
		coursesBtnListener = new ActionListener()
							  {
								 @Override
								 public void actionPerformed(ActionEvent e)
								 {
									 Controller.getController().goToCourses(CoursesFrame.this);
								 }
							  };
		coursesBtn.addActionListener(coursesBtnListener);
		
		recipesBtnListener = new ActionListener()
							 {
								 @Override
								 public void actionPerformed(ActionEvent e)
								 {
									 Controller.getController().goToRecipes(CoursesFrame.this);
								 }
							 };
		recipesBtn.addActionListener(recipesBtnListener);
		

		reportBtnListener = new ActionListener()
							 {
								 @Override
								 public void actionPerformed(ActionEvent e)
								 {
									 Controller.getController().goToReport(CoursesFrame.this);
								 }
							 };
		reportBtn.addActionListener(reportBtnListener);		
		
		profileItemBtnListener = new ActionListener()
								 {
									 @Override
									 public void actionPerformed(ActionEvent e)
									 {
										 Controller.getController().goToProfile(CoursesFrame.this);
									 }
								 };
		profileItemBtn.addActionListener(profileItemBtnListener);
		
		
		logoutItemBtnListener = new ActionListener()
								 {
									 @Override
									 public void actionPerformed(ActionEvent e)
									 {
										 Controller.getController().logout(CoursesFrame.this);
									 }
								 };
		logoutItemBtn.addActionListener(logoutItemBtnListener);
		
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
		
		hoverListener = new MouseAdapter() 
						{
						    @Override
						    public void mouseEntered(MouseEvent e) 
						    {
						        if (e.getSource() instanceof JXButton) 
						        {
						            JXButton btn = (JXButton) e.getSource();
						            btn.setBackground(new Color(230, 230, 230));
						            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						        }
						    }
						    @Override
						    public void mouseExited(MouseEvent e) 
						    {
						        if (e.getSource() instanceof JXButton) 
						        {
						            JXButton btn = (JXButton) e.getSource();
						            btn.setBackground(new Color(245, 245, 245));
						        }
						    }
						};

		homeBtn.addMouseListener(hoverListener);
		coursesBtn.addMouseListener(hoverListener);
		recipesBtn.addMouseListener(hoverListener);
		reportBtn.addMouseListener(hoverListener);
		profileItemBtn.addMouseListener(hoverListener);
		logoutItemBtn.addMouseListener(hoverListener);
    }
    
    /**
     * Rimuove tutti i listener registrati per evitare
     * memory leak o comportamenti indesiderati alla chiusura.
     */
    private void disposeListeners() 
    {

        if(dropdownClickListener != null)
            Toolkit.getDefaultToolkit().removeAWTEventListener(dropdownClickListener);


        if(homeBtn != null && homeBtnListener != null)
            homeBtn.removeActionListener(homeBtnListener);
        
        if(coursesBtn != null && coursesBtnListener != null)
            coursesBtn.removeActionListener(coursesBtnListener);
        
        if(recipesBtn != null && recipesBtnListener != null)
            recipesBtn.removeActionListener(recipesBtnListener);
        
        if(reportBtn != null && reportBtnListener != null)
            reportBtn.removeActionListener(reportBtnListener);
        
        if(profileItemBtn != null && profileItemBtnListener != null)
            profileItemBtn.removeActionListener(profileItemBtnListener);
        
        if(logoutItemBtn != null && logoutItemBtnListener != null)
            logoutItemBtn.removeActionListener(logoutItemBtnListener);
        
        if(profileBtn != null && profileBtnListener != null)
            profileBtn.removeActionListener(profileBtnListener);
        
        if(hamburgerBtn != null && hamburgerBtnListener != null)
            hamburgerBtn.removeActionListener(hamburgerBtnListener);

        if(hoverListener != null) 
        {
            if (homeBtn != null) homeBtn.removeMouseListener(hoverListener);
            if (coursesBtn != null) coursesBtn.removeMouseListener(hoverListener);
            if (recipesBtn != null) recipesBtn.removeMouseListener(hoverListener);
            if (reportBtn != null) reportBtn.removeMouseListener(hoverListener);
            if (profileItemBtn != null) profileItemBtn.removeMouseListener(hoverListener);
            if (logoutItemBtn != null) logoutItemBtn.removeMouseListener(hoverListener);
        }

        if(logoLabel != null && logoLabelMouseListener != null)
            logoLabel.removeMouseListener(logoLabelMouseListener);

        if(frameComponentListener != null)
            removeComponentListener(frameComponentListener);
    }
 
    /**
     * Rimuove tutti i listener registrati e libera risorse
     * prima di chiudere la finestra, per evitare memory leak.
     */
    @Override
    public void dispose()
    {
    	disposeListeners();
        super.dispose();
    }
    
    /**
     * Resetta la vista della finestra pulendo il campo di ricerca
     * e ripristinando il focus su di esso.
     * Può essere usato per aggiornare la vista ad uno stato iniziale.
     */
    public void resetView() 
    {
        searchField.setText("");
        searchField.requestFocusInWindow();
        // scrollPane.getVerticalScrollBar().setValue(0); // se hai scroll
        // refreshTableModel(); // se carichi dati
    }
   
    /**
     * Applica uno stile coerente a un pulsante della sidebar.
     * Imposta font, cursore, colori e bordi.
     *
     * @param button Il pulsante JXButton da stilizzare.
     */
    private void styleSidebarButton(JXButton button)
    {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setOpaque(true);
        button.setBackground(new Color(245, 245, 245));
    }
    
    /**
     * Applica uno stile coerente a un pulsante all'interno del menu dropdown del profilo.
     * Imposta allineamento, font, colori e bordi.
     *
     * @param button Il pulsante JXButton da stilizzare.
     */
    private void styleDropdownButton(JXButton button)
    {
        button.setHorizontalAlignment(JXButton.LEFT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setContentAreaFilled(true);
        button.setBackground(new Color(245, 245, 245));
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setForeground(new Color(60, 60, 60));
    }
    
    /**
     * Applica uno stile coerente ai componenti grafici,
     * inclusi pulsanti della sidebar e del menu dropdown.
     */
    private void styleComponents()
    {
    	styleDropdownButton(profileItemBtn);
        styleDropdownButton(logoutItemBtn);
        
        styleSidebarButton(homeBtn);
        styleSidebarButton(coursesBtn);
        styleSidebarButton(recipesBtn);
        styleSidebarButton(reportBtn);
    }

    /**
     * Aggiorna la posizione del pannello dropdown del profilo in base alla dimensione
     * e posizione corrente della finestra, assicurandone la corretta visualizzazione
     * sotto l'header e allineato a destra.
     */
    private void updateDropDownPosition()
    {
        if (dropdownPanel.isVisible())
        {
            // Posizione Y sotto l'header (come prima)
            Point headerBottomLeft = SwingUtilities.convertPoint(header, 0, header.getHeight(), getLayeredPane());
            
            // Calcola X in modo che sia attaccato al bordo destro della finestra
            int x = getLayeredPane().getWidth() - dropdownPanel.getWidth() - 1;
            int y = headerBottomLeft.y;

            dropdownPanel.setLocation(x, y);
        }
    }
    
    /**
     * Aggiorna la posizione e dimensione del pannello sidebar (menu hamburger)
     * per adattarsi alla finestra e posizionarsi sotto l'header.
     */
    private void updateSidebarPosition() 
    {
        if(sidebarPanel != null && sidebarPanel.isVisible())
        {
            Point headerBottomLeft = SwingUtilities.convertPoint(header, 0, header.getHeight(), getLayeredPane());
            sidebarPanel.setBounds(0, headerBottomLeft.y, 200, getHeight() - headerBottomLeft.y);
        }
    }
}
