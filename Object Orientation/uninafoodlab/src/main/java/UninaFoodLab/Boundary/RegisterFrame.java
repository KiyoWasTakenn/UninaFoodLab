package UninaFoodLab.Boundary;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.lgooddatepicker.components.DatePicker;

import UninaFoodLab.Controller.Controller;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import org.jdesktop.swingx.JXLabel.TextAlignment;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class RegisterFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup buttonGroup= new ButtonGroup();
	private JXPanel panel;
	private ImageIcon windowLogo;
	private ImageIcon paneLogo;
	private JXLabel logoLabel;
	private JXLabel tipoLabel;
	private JRadioButton chefButton;
	private JRadioButton partecipanteButton;
	private JXLabel nomeErrorLabel;
	private CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
			new LineBorder(Color.LIGHT_GRAY, 1), 
		  	new EmptyBorder(0, 6, 0, 0));
	private CompoundBorder errorBorder = BorderFactory.createCompoundBorder(
        	new LineBorder(Color.RED, 1),
        	new EmptyBorder(0, 6, 0, 0));
	private JXLabel nomeLabel;
	private JXTextField nomeField;
	private JXLabel cognomeErrorLabel;
	private JXLabel cognomeLabel;
	private JXTextField cognomeField;
	private JXLabel dataErrorLabel;
	private JXLabel dataLabel;
	private DatePicker dataPicker;
	private JXLabel luogoErrorLabel;
	private JXLabel luogoLabel;
	private JXTextField luogoField;
	private JXLabel codFiscErrorLabel;
	private JXLabel codFiscLabel;
	private JXTextField codFiscField;
	private JXLabel emailErrorLabel;
	private JXLabel emailLabel;
	private JXTextField emailField;
	private JXLabel passwordErrorLabel;
	private JXLabel passwordLabel;
	private JToggleButton showPassBtn;
	FontIcon eyeIcon = FontIcon.of(MaterialDesign.MDI_EYE, 18);
	FontIcon eyeOffIcon = FontIcon.of(MaterialDesign.MDI_EYE_OFF, 18);
	private JPasswordField passwordField;
	private JXLabel userErrorLabel;
	private JXLabel userLabel;
	private JXTextField userField;
	private JXButton registerBtn;
	private JXLabel accediLabel;
	private JXButton accediBtn;

	public RegisterFrame()
	{
		setTitle("UninaFoodLab - Registrazione");
		setSize(850, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		windowLogo = new ImageIcon(getClass().getResource("/logo_finestra.png"));
		setIconImage(windowLogo.getImage());

		try
		{
			UIManager.setLookAndFeel(new FlatLightLaf());
		} 
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		
		panel = new JXPanel(new MigLayout("", "20[grow]3[grow]20[grow]3[grow]", "[]30[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]30[]10[]10[fill]"));
		setContentPane(panel);
		
		paneLogo = new ImageIcon(getClass().getResource("/logo_schermata.png"));
		logoLabel = new JXLabel(new ImageIcon(paneLogo.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH)));
		panel.add(logoLabel, "cell 0 0, span 4, center, gapbottom30");	
		
		
		tipoLabel = new JXLabel("Come vuoi registrarti? ");
		tipoLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(tipoLabel, "cell 0 2, span2, right");	
				
		chefButton = new JRadioButton("Chef");
		chefButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(chefButton, "cell 2 2, left, split 2");
		partecipanteButton = new JRadioButton("Partecipante");
		partecipanteButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(partecipanteButton, "gapleft 30");
		partecipanteButton.setSelected(true);
		buttonGroup.add (partecipanteButton);
		buttonGroup.add (chefButton);
		
		nomeErrorLabel = new JXLabel(" ");
		nomeErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		nomeErrorLabel.setForeground(Color.RED);
		panel.add(nomeErrorLabel, "span 2, cell 0 3, center");
		
		nomeLabel = new JXLabel("Nome: ");
		nomeLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(nomeLabel, "cell 0 4, right");	
		
		nomeField = new JXTextField();
		nomeField.setPreferredSize(new Dimension(200, 30));
		panel.add(nomeField, "cell 1 4, left");

		cognomeErrorLabel = new JXLabel(" ");
		cognomeErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		cognomeErrorLabel.setForeground(Color.RED);
		panel.add(cognomeErrorLabel, "span 2, cell 2 3, center");
		
		cognomeLabel = new JXLabel("Cognome: ");
		cognomeLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(cognomeLabel, "cell 2 4, right");	
		
		cognomeField = new JXTextField();
		cognomeField.setPreferredSize(new Dimension(200, 30));
		panel.add(cognomeField, "cell 3 4, left");
		
		
		dataErrorLabel = new JXLabel(" ");
		dataErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		dataErrorLabel.setForeground(Color.RED);
		panel.add(dataErrorLabel, "span 2, cell 0 5, center");
		
		dataLabel = new JXLabel("Data di nascita: ");
		dataLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(dataLabel, "cell 0 6, right");	
		
		dataPicker = new DatePicker();
		dataPicker.setPreferredSize(new Dimension(230, 30));
		panel.add(dataPicker, "cell 1 6, left");	
		
		luogoErrorLabel = new JXLabel(" ");
		luogoErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		luogoErrorLabel.setForeground(Color.RED);
		panel.add(luogoErrorLabel, "span 2, cell 2 5, center");
		
		luogoLabel = new JXLabel("Luogo di nascita: ");
		luogoLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(luogoLabel, "cell 2 6, right");	
		
		luogoField = new JXTextField();
		luogoField.setPreferredSize(new Dimension(200, 30));
		panel.add(luogoField, "cell 3 6, left");
		
		codFiscErrorLabel = new JXLabel(" ");
		codFiscErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		codFiscErrorLabel.setForeground(Color.RED);
		panel.add(codFiscErrorLabel, "span 2, cell 0 7, center");
		
		codFiscLabel = new JXLabel("Codice Fiscale: ");
		codFiscLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(codFiscLabel, "cell 0 8, right");	
		
		codFiscField = new JXTextField();
		codFiscField.setPreferredSize(new Dimension(200, 30));
		panel.add(codFiscField, "cell 1 8, left");
		
		emailErrorLabel = new JXLabel(" ");
		emailErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		emailErrorLabel.setForeground(Color.RED);
		panel.add(emailErrorLabel, "span 2, cell 2 7, center");
		
		emailLabel = new JXLabel("Email: ");
		emailLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(emailLabel, "cell 2 8, right");	
		
		emailField = new JXTextField();
		emailField.setPreferredSize(new Dimension(200, 30));
		panel.add(emailField, "cell 3 8, left");
		
		passwordErrorLabel = new JXLabel(" ");
		passwordErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		passwordErrorLabel.setForeground(Color.RED);
		panel.add(passwordErrorLabel, "span 2, cell 2 9, center");
		
		passwordLabel = new JXLabel("Password: ");
		passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(passwordLabel, "cell 2 10, right");	
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200, 30));
		panel.add(passwordField, "cell 3 10, left, split 2");
		
		showPassBtn = new JToggleButton();
		showPassBtn.setIcon(eyeOffIcon);
		showPassBtn.setPreferredSize(new Dimension(30, 30));
		showPassBtn.setFocusable(false);
		showPassBtn.setToolTipText("Mostra/Nascondi password");
		showPassBtn.setBorderPainted(false);
		showPassBtn.setContentAreaFilled(false);
		panel.add(showPassBtn, "w 30!, h 25!, gapleft 5");
		
		userErrorLabel = new JXLabel(" ");
		userErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		userErrorLabel.setForeground(Color.RED);
		panel.add(userErrorLabel, "span 2, cell 0 9, center");
		
		userLabel = new JXLabel("Username: ");
		userLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(userLabel, "cell 0 10, right");	
		
		userField = new JXTextField();
		userField.setPreferredSize(new Dimension(200, 30));
		panel.add(userField, "cell 1 10, left");		
		
		registerBtn = new JXButton("Registrati");
		registerBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		registerBtn.setPreferredSize(new Dimension(120, 30));
		registerBtn.setBackground(new Color(225, 126, 47, 220));
		registerBtn.setForeground(Color.WHITE);
		registerBtn.setOpaque(true);
		registerBtn.setFocusPainted(false);
		registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(registerBtn, "cell 0 13, span 4, center, gaptop 50");

		accediLabel = new JXLabel("Oppure, se sei già registrato");
		accediLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(accediLabel, "cell 0 14, span 4, center");
		
		accediBtn = new JXButton("Accedi");
		accediBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		accediBtn.setPreferredSize(new Dimension(120, 30));
		accediBtn.setBackground(new Color(225, 126, 47, 220));
		accediBtn.setForeground(Color.WHITE);
		accediBtn.setOpaque(true);
		accediBtn.setFocusPainted(false);
		accediBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(accediBtn, "cell 0 15, span 4, center");
	

		showPassBtn.addActionListener(new ActionListener()
		  {
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				if(showPassBtn.isSelected())
				{
			    	passwordField.setEchoChar('•');
			    	showPassBtn.setIcon(eyeOffIcon);
			    } 
			    else
			    {
			    	passwordField.setEchoChar((char)0);
			    	showPassBtn.setIcon(eyeIcon);
			    }
			}				
		  });
		
		registerBtn.addActionListener(new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				if(!checkNome())
					nomeField.requestFocus();
				else if(!checkCognome())
					cognomeField.requestFocus();
				else if(!checkData())
					dataPicker.requestFocus();
				else if(!checkLuogo())
					luogoField.requestFocus();
				else if(!checkCod())
					codFiscField.requestFocus();
				else if(!checkEmail())
					emailField.requestFocus();
				else if(!checkUser())
		        	userField.requestFocus();
		        else if(!checkPass())
		        	passwordField.requestFocus();
		        else
		        {
		        	registerBtn.setEnabled(false);
		        	//Controller.getController().checkRegister(RegisterFrame.this, userField.getText().trim(), passwordField.getPassword());
		        }
		    }
		}
	  );

		accediBtn.addActionListener(new ActionListener()
		  {
			@Override 
			public void actionPerformed(ActionEvent e)
			{										
				//Controller.getController().goToRegister(LoginFrame.this);
			}
		  }
		);
		
		}
	
	private boolean checkNome()
	{
		boolean check = true;
	    String text = nomeField.getText().trim();
	      
	    if(text.isEmpty())
	    {
	    	nomeField.setBorder(errorBorder);
	    	nomeErrorLabel.setText("Bisogna inserire un nome!");
	    	check = false;
	    }
	    else
	    {
	        nomeField.setBorder(defaultBorder);
	        nomeErrorLabel.setText(" ");
	    }
	    
	    return check;
	}
	
	private boolean checkCognome()
	{
		boolean check = true;
	    String text = cognomeField.getText().trim();
	      
	    if(text.isEmpty())
	    {
	    	cognomeField.setBorder(errorBorder);
	    	cognomeErrorLabel.setText("Bisogna inserire un cognome!");
	    	check = false;
	    }
	    else
	    {
	        cognomeField.setBorder(defaultBorder);
	        cognomeErrorLabel.setText(" ");
	    }
	    
	    return check;
	}
	
	private boolean checkCod()
	{
		boolean check = true;
	    String text = codFiscField.getText().trim();
	      
	    if(text.isEmpty())
	    {
	    	codFiscField.setBorder(errorBorder);
	    	codFiscErrorLabel.setText("Bisogna inserire un codice fiscale!");
	    	check = false;
	    }
	    else if(text.length()!=16)
	    {
	    	codFiscField.setBorder(errorBorder);
	    	codFiscErrorLabel.setText("Il codice fiscale deve avere 16 caratteri!");
	    	check = false;	    	
	    }
	    else
	    {
	    	codFiscField.setBorder(defaultBorder);
	    	codFiscErrorLabel.setText(" ");
	    }
	    
	    return check;
	}	
	
	private boolean checkLuogo()
	{
		boolean check = true;
	    String text = luogoField.getText().trim();
	      
	    if(text.isEmpty())
	    {
	    	luogoField.setBorder(errorBorder);
	    	luogoErrorLabel.setText("Bisogna inserire il luogo di nascita!");
	    	check = false;
	    }
	    else
	    {
	    	luogoField.setBorder(defaultBorder);
	    	luogoErrorLabel.setText(" ");
	    }
	    
	    return check;
	}	
	
	private boolean checkData()
	{
		boolean check = true;

	    if(!dataPicker.isTextFieldValid()||dataPicker.getText().isEmpty())
	    {
	    	dataPicker.setBorder(errorBorder);
	    	dataErrorLabel.setText("Bisogna inserire la data di nascita!");
	    	check = false;
	    }
	    else
	    {
	    	dataPicker.setBorder(defaultBorder);
	    	dataErrorLabel.setText(" ");
	    }
	    
	    return check;
	}	
	
	private boolean checkEmail()
	{
		boolean check = true;
	    String text = emailField.getText().trim();
	      
	    if(text.isEmpty())
	    {
	    	emailField.setBorder(errorBorder);
	    	emailErrorLabel.setText("Bisogna inserire un' email!");
	    	check = false;
	    }
	    else
	    {
	    	emailField.setBorder(defaultBorder);
	    	emailErrorLabel.setText(" ");
	    }
	    
	    return check;
	}	
	
	private boolean checkUser() 
	{
		boolean check = true;
	    String text = userField.getText().trim();
	      
	    if(text.contains(" ") || text.contains("\t") || text.contains("\n"))
	    {
	    	userField.setBorder(errorBorder);
	    	userErrorLabel.setText("L'username non può contenere spazi!");
	    	check = false;
	    }
	    else if(text.isEmpty())
	    {
	    	userField.setBorder(errorBorder);
	    	userErrorLabel.setText("Bisogna inserire un username!");
	    	check = false;
	    }
	    else if(text.length() < 4 || text.length() > 20) 
	 	{
	 	    userField.setBorder(errorBorder);
	 	    userErrorLabel.setText("L'username deve essere tra 4 e 20 caratteri!");
	 	    check = false;
	 	} 
	    else
	    {
	        userField.setBorder(defaultBorder);
	        userErrorLabel.setText(" ");
	    }
	    
	    return check;
	}

	private boolean checkPass()
	{
		boolean check = true;
		String text = new String(passwordField.getPassword()).trim();

	    if (text.length() < 8 || text.length() > 30)
	    {
	        passwordField.setBorder(errorBorder);
	        passwordErrorLabel.setText("La password deve essere tra 8 e 30 caratteri.");
	        check = false;
	    } 
	    else if(text.isEmpty())
	    {
	    	userField.setBorder(errorBorder);
	    	userErrorLabel.setText("Bisogna inserire una password!");
	    	check = false;
	    }
	    else
	    {
	        passwordField.setBorder(defaultBorder);
	        passwordErrorLabel.setText(" ");
	    }
	    
	    return check; 
	}
}

