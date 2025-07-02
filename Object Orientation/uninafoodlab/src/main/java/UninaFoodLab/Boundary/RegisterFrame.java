package UninaFoodLab.Boundary;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import org.jdesktop.swingx.JXLabel.TextAlignment;
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
	private JPasswordField passwordField;
	private JXLabel userErrorLabel;
	private JXLabel userLabel;
	private JXTextField userField;
	private JXButton registerBtn;


	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RegisterFrame frame = new RegisterFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

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
		
		panel = new JXPanel(new MigLayout("", "[grow]3[grow]20[grow]3[grow]", "[]30[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]30[fill]"));
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
		panel.add(passwordField, "cell 3 10, left");
		
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
		panel.add(registerBtn, "cell 0 13, span 4, center, gaptop 100");
	

		
		}
}

