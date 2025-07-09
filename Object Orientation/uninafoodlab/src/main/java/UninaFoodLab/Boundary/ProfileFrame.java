package UninaFoodLab.Boundary;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
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
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
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
import UninaFoodLab.DTO.Utente;
import net.miginfocom.swing.MigLayout;

public class ProfileFrame extends JXFrame
{

	private static final long serialVersionUID = 1L;
	
	private JXLabel logoLabel;
	
    private ImageIcon windowLogo;
    private ImageIcon paneLogo;
    
	private JPanel contentPane;
	
	private Utente loggedUser;
	
	private JXButton hamburgerBtn;
	private JXButton profileBtn;
	private JXButton modifyBtn;
	private SidebarPanel sidebar;
	private JXPanel rootPanel;
	private JXPanel header;
	private JXPanel mainContentPanel;
	
	private ProfileDropdownPanel dropdownPanel;
	private JXLabel BenvenutoLabel;
	private JXLabel DatiLabel;
	private JXLabel NomeLabel;
	private JXTextField NomeField;
	private JXLabel CognomeLabel;
	private JXTextField CognomeField;
	private JXLabel CodFiscaleLabel;
	private JXTextField CodFiscaleField;
	private JXLabel EmailLabel;
	private JXTextField EmailField;
	private JXLabel CurriculumLabel;
	private JXButton VisualizzaCurriculumBtn;
	private JXLabel UsernameLabel;
	private JXTextField UsernameField;
	private JXButton EliminaProfiloBtn;
	private JXButton AnnullaBtn;
	private JXButton ConfermaBtn;
	private JXButton ScegliCurriculumBtn;
	private JXButton CambiaPasswordBtn;
	private JFileChooser fileChooser;
	private JXLabel fileLabel;
	private File selectedFile;
    // Listeners
	ActionListener scegliBtnActionListener;		
	private AWTEventListener dropdownClickListener;
	private ActionListener CurriculumBtnListener;
	private ActionListener modifyBtnListener;
	private ActionListener profileBtnListener;
    private ActionListener hamburgerBtnListener;  
    private ActionListener EliminaBtnListener;
    private ActionListener AnnullaBtnListener;
    private ActionListener ConfermaBtnListener;
    private MouseListener logoLabelMouseListener;
    private ComponentAdapter frameComponentListener;
	
    /*public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                new ProfileFrame().setVisible(true);
    
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }*/
	public ProfileFrame()
	{
		setTitle("UninaFoodLab - Profilo");
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
    	rootPanel = new JXPanel(new MigLayout("fill, insets 0", "[grow]", "[][grow]"));
    	header = new JXPanel(new MigLayout(
                "insets 5 15 5 15",
                "[35!]10[10!]10[80!]10[20!]0[80!]0[grow, 50::1400]0[80!]10[grow, 10::89][35!][20!]",
                "[60!]"
        ));
    	
    	hamburgerBtn = new JXButton();
    	profileBtn = new JXButton();
    	modifyBtn = new JXButton();
        sidebar = new SidebarPanel(this);
        dropdownPanel = new ProfileDropdownPanel(this);
        
    	windowLogo = new ImageIcon(getClass().getResource("/logo_finestra.png"));
        setIconImage(windowLogo.getImage());

        rootPanel.setBackground(new Color(0xFAFAFA));
        setContentPane(rootPanel);

        header.setBackground(Color.WHITE);
        header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0xDDDDDD)));
        rootPanel.add(header, "dock north");

        hamburgerBtn.setIcon(FontIcon.of(MaterialDesign.MDI_MENU, 26, Color.DARK_GRAY));
        hamburgerBtn.setContentAreaFilled(false);
        hamburgerBtn.setBorder(BorderFactory.createEmptyBorder());
        hamburgerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(hamburgerBtn, "cell 0 0, w 35!, h 35!, shrink 0");

        paneLogo = new ImageIcon(getClass().getResource("/logo_schermata.png"));
       	logoLabel = new JXLabel(new ImageIcon(paneLogo.getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));;
        logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoLabel.setToolTipText("Torna alla homepage");
        header.add(logoLabel, "cell 2 0, w 80!, h 60!, gapleft 0, shrink 0");

        profileBtn.setIcon(FontIcon.of(MaterialDesign.MDI_ACCOUNT_CIRCLE, 26, Color.DARK_GRAY));
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
        
        mainContentPanel = new JXPanel(new MigLayout("fill","300[grow]3[grow]30[grow]3[grow]3[grow]300","[]100[]50[]50[]50[]50[]100[fill]"));
        rootPanel.add(mainContentPanel, "grow, wrap");
        
        loggedUser = Controller.getController().getLoggedUser();
    	BenvenutoLabel = new JXLabel("BENVENUTO "+ loggedUser.getNome().toUpperCase() +" !");
    	BenvenutoLabel.setFont(new Font("Roboto", Font.BOLD, 40));
    	BenvenutoLabel.setOpaque(true);
    	BenvenutoLabel.setBackground(Color.ORANGE);
    	BenvenutoLabel.repaint();
    	//BenvenutoLabel.setForeground(Color.WHITE);
    	mainContentPanel.add(BenvenutoLabel, "cell 0 0, span 5, center");	
    	
 
    	
    	DatiLabel = new JXLabel("I tuoi dati:");
    	DatiLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
    	mainContentPanel.add(DatiLabel, "cell 0 1, right");	
    	
    	modifyBtn.setIcon(FontIcon.of(MaterialDesign.MDI_PENCIL, 26, Color.DARK_GRAY));
        modifyBtn.setContentAreaFilled(false);
        modifyBtn.setBorder(BorderFactory.createEmptyBorder());
        modifyBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainContentPanel.add(modifyBtn, "cell 4 1, w 35!, h 35!, align left");
        
        
    	NomeLabel = new JXLabel("Nome: ");
    	NomeLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    	NomeLabel.setForeground(new Color(225, 126, 47, 220));
    	mainContentPanel.add(NomeLabel, "cell 0 2, right");	
    	
    	NomeField = new JXTextField();
    	NomeField.setFont(new Font("SansSerif", Font.BOLD, 15));
    	mainContentPanel.add(NomeField, "cell 1 2, left");	
    	NomeField.setText(loggedUser.getNome());
    	NomeField.setEditable(false);
    	NomeField.setFocusable(false);
    	NomeField.setBorder(new EmptyBorder(0,0,0,0));
    	//NomeField.setVisible(false);
    	
    	CognomeLabel = new JXLabel("Cognome: ");
    	CognomeLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    	CognomeLabel.setForeground(new Color(225, 126, 47, 220));
    	mainContentPanel.add(CognomeLabel, "cell 2 2, right");	
    	
    	CognomeField = new JXTextField();
    	CognomeField.setFont(new Font("SansSerif", Font.BOLD, 15));
    	mainContentPanel.add(CognomeField, "cell 3 2, left");	
    	CognomeField.setText(loggedUser.getCognome());
    	CognomeField.setEditable(false);
    	CognomeField.setFocusable(false);
    	CognomeField.setBorder(new EmptyBorder(0,0,0,0));
    	
    	CodFiscaleLabel = new JXLabel("Codice Fiscale: ");
    	CodFiscaleLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    	CodFiscaleLabel.setForeground(new Color(225, 126, 47, 220));
    	mainContentPanel.add(CodFiscaleLabel, "cell 0 3, right");	
 
    	CodFiscaleField = new JXTextField();
    	CodFiscaleField.setFont(new Font("SansSerif", Font.BOLD, 15));
    	mainContentPanel.add(CodFiscaleField, "cell 1 3, left");	
    	CodFiscaleField.setText(loggedUser.getCodiceFiscale());
    	CodFiscaleField.setEditable(false);
    	CodFiscaleField.setFocusable(false);
    	CodFiscaleField.setBorder(new EmptyBorder(0,0,0,0));
    	
    	EmailLabel = new JXLabel("Email: ");
    	EmailLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    	EmailLabel.setForeground(new Color(225, 126, 47, 220));
    	mainContentPanel.add(EmailLabel, "cell 2 3, right");	

    	EmailField = new JXTextField();
    	EmailField.setFont(new Font("SansSerif", Font.BOLD, 15));
    	mainContentPanel.add(EmailField, "cell 3 3, left");	
    	EmailField.setText(loggedUser.getEmail());
    	EmailField.setEditable(false);
    	EmailField.setFocusable(false);
    	EmailField.setBorder(new EmptyBorder(0,0,0,0));
    	
    	UsernameLabel = new JXLabel("Username: ") ;
    	UsernameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    	UsernameLabel.setForeground(new Color(225, 126, 47, 220));
    	mainContentPanel.add(UsernameLabel, "cell 0 4, right");	
  
    	UsernameField = new JXTextField();
    	UsernameField.setFont(new Font("SansSerif", Font.BOLD, 15));
    	mainContentPanel.add(UsernameField, "cell 1 4, left");	
    	UsernameField.setText(loggedUser.getUsername());
    	UsernameField.setEditable(false);
    	UsernameField.setFocusable(false);
    	UsernameField.setBorder(new EmptyBorder(0,0,0,0));
    	
    	CambiaPasswordBtn = new JXButton("Cambia password");
    	CambiaPasswordBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
    	CambiaPasswordBtn.setPreferredSize(new Dimension(120, 30));
    	CambiaPasswordBtn.setBackground(new Color(200, 200, 200));
    	CambiaPasswordBtn.setForeground(Color.BLACK);
    	CambiaPasswordBtn.setOpaque(true);
    	CambiaPasswordBtn.setFocusPainted(false);
    	CambiaPasswordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	mainContentPanel.add(CambiaPasswordBtn, "cell 3 4, span 2, left");
    	CurriculumLabel = new JXLabel ("Curriculum :");
    	CurriculumLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
    	CurriculumLabel.setForeground(new Color(225, 126, 47, 220));
    	mainContentPanel.add(CurriculumLabel, "cell 0 5, right");
    	
    	VisualizzaCurriculumBtn = new JXButton ("Vedi curriculum");
    	VisualizzaCurriculumBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
    	VisualizzaCurriculumBtn.setPreferredSize(new Dimension(120, 30));
    	VisualizzaCurriculumBtn.setBackground(new Color(200, 200, 200));
    	VisualizzaCurriculumBtn.setForeground(Color.BLACK);
    	VisualizzaCurriculumBtn.setOpaque(true);
    	VisualizzaCurriculumBtn.setFocusPainted(false);
    	VisualizzaCurriculumBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	mainContentPanel.add(VisualizzaCurriculumBtn, "cell 1 5, span 4, left");
    	
    	ScegliCurriculumBtn = new JXButton("Scegli curriculum");
    	ScegliCurriculumBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
    	ScegliCurriculumBtn.setPreferredSize(new Dimension(120, 30));
    	ScegliCurriculumBtn.setBackground(new Color(200, 200, 200));
    	ScegliCurriculumBtn.setForeground(Color.BLACK);
    	ScegliCurriculumBtn.setOpaque(true);
    	ScegliCurriculumBtn.setFocusPainted(false);
    	ScegliCurriculumBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	mainContentPanel.add(ScegliCurriculumBtn, "cell 1 5, span 4, left");
    	
    	fileLabel = new JXLabel(" ");
		fileLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		fileLabel.setForeground(Color.BLACK);
		mainContentPanel.add(fileLabel,  "cell 1 5, span 4, left");
		
    	EliminaProfiloBtn = new JXButton ("Elimina Profilo");
    	EliminaProfiloBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
    	EliminaProfiloBtn.setPreferredSize(new Dimension(120, 30));
    	EliminaProfiloBtn.setBackground(new Color(199, 0, 0));
    	EliminaProfiloBtn.setForeground(Color.WHITE);
		EliminaProfiloBtn.setOpaque(true);
		EliminaProfiloBtn.setFocusPainted(false);
		EliminaProfiloBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainContentPanel.add(EliminaProfiloBtn, "cell 3 6, right, gapright 30");
		
		ConfermaBtn = new JXButton ("Conferma");
		ConfermaBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		ConfermaBtn.setPreferredSize(new Dimension(120, 30));
		ConfermaBtn.setBackground(new Color(73,202,62,255));
		ConfermaBtn.setForeground(Color.WHITE);
		ConfermaBtn.setOpaque(true);
		ConfermaBtn.setFocusPainted(false);
		ConfermaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainContentPanel.add(ConfermaBtn, "cell 2 6, left, gapright 30");
		
		AnnullaBtn = new JXButton ("Annulla");
		AnnullaBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		AnnullaBtn.setPreferredSize(new Dimension(120, 30));
		AnnullaBtn.setBackground(Color.GRAY);
		AnnullaBtn.setForeground(Color.BLACK);
		AnnullaBtn.setOpaque(true);
		AnnullaBtn.setFocusPainted(false);
		AnnullaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainContentPanel.add(AnnullaBtn, "cell 1 6, left, gapright 30");
		AnnullaBtn.setVisible(false);
		AnnullaBtn.setEnabled(false);
		ConfermaBtn.setVisible(false);
		ConfermaBtn.setEnabled(false);
		EliminaProfiloBtn.setVisible(false);
		EliminaProfiloBtn.setEnabled(false);
		CambiaPasswordBtn.setVisible(false);
		CambiaPasswordBtn.setEnabled(false);
		ScegliCurriculumBtn.setVisible(false);
		ScegliCurriculumBtn.setEnabled(false);
		fileLabel.setVisible(false);
		fileLabel.setEnabled(false);
    }
    
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
				 Controller.getController().goToHomepage(ProfileFrame.this);
			 }
		   };
		   logoLabel.addMouseListener(logoLabelMouseListener);	

		   modifyBtnListener = new ActionListener()
		   {
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
					   NomeField.setEditable(true);
					   NomeField.setFocusable(true);
					   NomeField.setBorder(new LineBorder(Color.BLACK));
				   
					   CognomeField.setEditable(true);
					   CognomeField.setFocusable(true);
					   CognomeField.setBorder(new LineBorder(Color.BLACK));
					   
					   CodFiscaleField.setEditable(true);
					   CodFiscaleField.setFocusable(true);
					   CodFiscaleField.setBorder(new LineBorder(Color.BLACK));
					   
					   EmailField.setEditable(true);
					   EmailField.setFocusable(true);
					   EmailField.setBorder(new LineBorder(Color.BLACK));
					   
					   UsernameField.setEditable(true);
					   UsernameField.setFocusable(true);
					   UsernameField.setBorder(new LineBorder(Color.BLACK));
					   
					   modifyBtn.setVisible(false);
					   modifyBtn.setEnabled(false);
					   AnnullaBtn.setVisible(true);
					   AnnullaBtn.setEnabled(true);
					   ConfermaBtn.setVisible(true);
					   ConfermaBtn.setEnabled(true);
					   EliminaProfiloBtn.setVisible(true);
					   EliminaProfiloBtn.setEnabled(true);
					   CambiaPasswordBtn.setVisible(true);
					   CambiaPasswordBtn.setEnabled(true);
					   ScegliCurriculumBtn.setVisible(true);
					   ScegliCurriculumBtn.setEnabled(true);
					   fileLabel.setVisible(true);
					   fileLabel.setEnabled(true);
					   
			   }
		   };
		   modifyBtn.addActionListener(modifyBtnListener);
		   
		   CurriculumBtnListener = new ActionListener()
			{
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
				 if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "[Esposito]_[VirginiaAntonia]_[N86004987]_ER1.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				} 
			   }
			};
		   VisualizzaCurriculumBtn.addActionListener(CurriculumBtnListener);

		   AnnullaBtnListener = new ActionListener()
			{
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
			    	NomeField.setText(loggedUser.getNome());
			    	NomeField.setEditable(false);
			    	NomeField.setFocusable(false);
			    	NomeField.setBorder(new EmptyBorder(0,0,0,0));
			    	
			    	CognomeField.setText(loggedUser.getCognome());
			    	CognomeField.setEditable(false);
			    	CognomeField.setFocusable(false);
			    	CognomeField.setBorder(new EmptyBorder(0,0,0,0));
			    	
			    	CodFiscaleField.setText(loggedUser.getCodiceFiscale());
			    	CodFiscaleField.setEditable(false);
			    	CodFiscaleField.setFocusable(false);
			    	CodFiscaleField.setBorder(new EmptyBorder(0,0,0,0));
			    	
					EmailField.setText(loggedUser.getEmail());
					EmailField.setEditable(false);
					EmailField.setFocusable(false);
					EmailField.setBorder(new EmptyBorder(0,0,0,0));
					
					UsernameField.setText(loggedUser.getUsername());
					UsernameField.setEditable(false);
					UsernameField.setFocusable(false);
					UsernameField.setBorder(new EmptyBorder(0,0,0,0));
					
					AnnullaBtn.setEnabled(false);
					AnnullaBtn.setVisible(false);
					modifyBtn.setEnabled(true);
					modifyBtn.setVisible(true);
					ConfermaBtn.setEnabled(false);
					ConfermaBtn.setVisible(false);
					EliminaProfiloBtn.setEnabled(false);
					EliminaProfiloBtn.setVisible(false);
					CambiaPasswordBtn.setVisible(false);
					CambiaPasswordBtn.setEnabled(false);
					ScegliCurriculumBtn.setVisible(false);
					ScegliCurriculumBtn.setEnabled(false);
					fileLabel.setVisible(false);
					fileLabel.setEnabled(false);
			   }
			};
		   AnnullaBtn.addActionListener(AnnullaBtnListener);
		   
		   ConfermaBtnListener = new ActionListener()
			{
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
					
					AnnullaBtn.setEnabled(false);
					AnnullaBtn.setVisible(false);
					modifyBtn.setEnabled(true);
					modifyBtn.setVisible(true);
					ConfermaBtn.setEnabled(false);
					ConfermaBtn.setVisible(false);
					EliminaProfiloBtn.setEnabled(false);
					EliminaProfiloBtn.setVisible(false);
					CambiaPasswordBtn.setVisible(false);
					CambiaPasswordBtn.setEnabled(false);
					ScegliCurriculumBtn.setVisible(false);
					ScegliCurriculumBtn.setEnabled(false);
					fileLabel.setVisible(false);
					fileLabel.setEnabled(false);
			   }
			};
		   ConfermaBtn.addActionListener(ConfermaBtnListener);
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
    
    public void dispose()
    {
    	disposeListeners();
        super.dispose();
    }
    
    private void updateDropDownPosition()
    {
        dropdownPanel.updatePosition(header, getLayeredPane());
    }
    
    private void updateSidebarPosition()
    {
        sidebar.updatePosition(header, getLayeredPane(), getHeight());
    }
    
    public void resetView()
    {
    	dropdownPanel.setVisible(false);
    }
}
