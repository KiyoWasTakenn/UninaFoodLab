package UninaFoodLab.Boundary;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.lgooddatepicker.components.DatePicker;

import UninaFoodLab.Controller.Controller;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final String FontAwesomeSolid = null;
	private JPanel contentPane;
	private JXPanel panel;
	private CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
				new LineBorder(Color.LIGHT_GRAY, 1), 
			  	new EmptyBorder(0, 6, 0, 0));
	private CompoundBorder errorBorder = BorderFactory.createCompoundBorder(
	        	new LineBorder(Color.RED, 1),
	        	new EmptyBorder(0, 6, 0, 0));
	private ImageIcon windowLogo;
	private ImageIcon paneLogo;
	private JXLabel logoLabel;
	private JXLabel userErrorLabel;
	private JXLabel passErrorLabel;
	private JXLabel userLabel;
	private JXTextField userField;
	private JXLabel passLabel;
	private JPasswordField passField;
	private JToggleButton showPassBtn;
	FontIcon eyeIcon = FontIcon.of(MaterialDesign.MDI_EYE, 18);
	FontIcon eyeOffIcon = FontIcon.of(MaterialDesign.MDI_EYE_OFF, 18);
	private JXButton loginBtn;
	private JXLabel orLabel;
	private JXButton registerBtn;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame()
	{
		setTitle("UninaFoodLab - Login");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
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
		
		panel = new JXPanel(new MigLayout("wrap 2, insets 15", "[right][grow, fill]", "[][3][][3][][10][]"));
		setContentPane(panel);
		
		paneLogo = new ImageIcon(getClass().getResource("/logo_schermata.png"));
		logoLabel = new JXLabel(new ImageIcon(paneLogo.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH)));
		panel.add(logoLabel, "span 2, align center, gapbottom 15");		
		
		userErrorLabel = new JXLabel(" ");
		userErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		userErrorLabel.setForeground(Color.RED);
		panel.add(userErrorLabel, "span 2, center, gapbottom 3");
		
		userLabel = new JXLabel("Username:");
		userLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		userField = new JXTextField();
		userField.setPreferredSize(new Dimension(250, 30));
		userField.setBorder(defaultBorder);
		panel.add(userLabel);
		panel.add(userField, "w 250!, h 25!, gapbottom 5"); 
		
		passErrorLabel = new JXLabel(" ");
		passErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		passErrorLabel.setForeground(Color.RED);
		panel.add(passErrorLabel, "span 2, center, gapbottom 3");
		
		passLabel = new JXLabel("Password:");
		passLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		passField = new JPasswordField();
		passField.setPreferredSize(new Dimension(250, 30));
		panel.add(passLabel); 
		panel.add(passField, "w 250!, h 25!, gapbottom 18, split2"); 
		
		showPassBtn = new JToggleButton();
		showPassBtn.setIcon(eyeOffIcon);
		showPassBtn.setPreferredSize(new Dimension(30, 30));
		showPassBtn.setFocusable(false);
		showPassBtn.setToolTipText("Mostra/Nascondi password");
		showPassBtn.setBorderPainted(false);
		showPassBtn.setContentAreaFilled(false);
		panel.add(showPassBtn, "w 30!, h 25!, gapleft 5, gapbottom 18");
	
		loginBtn = new JXButton("Login");
		loginBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		loginBtn.setPreferredSize(new Dimension(120, 30));
		loginBtn.setBackground(new Color(225, 126, 47, 220));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setOpaque(true);
		loginBtn.setFocusPainted(false);
		loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(loginBtn, "span 2, center, gaptop 10");
		
		orLabel = new JXLabel("Oppure");
		orLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(orLabel, "span 2, center");
		
		registerBtn = new JXButton("Registrati");
		registerBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
		registerBtn.setPreferredSize(new Dimension(120, 30));
		registerBtn.setBackground(new Color(225, 126, 47, 220));
		registerBtn.setForeground(Color.WHITE);
		registerBtn.setOpaque(true);
		registerBtn.setFocusPainted(false);
		registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(registerBtn, "span 2, center");
			
		// Action Listeners 
		userField.getDocument().addDocumentListener(new DocumentListener()
													{
														@Override
													    public void insertUpdate(DocumentEvent e) { checkUser(); }
													    @Override
													    public void removeUpdate(DocumentEvent e) { checkUser(); }
													    @Override
													    public void changedUpdate(DocumentEvent e) { checkUser(); }
													}
												   );
		
		passField.getDocument().addDocumentListener(new DocumentListener()
													{
														@Override
													    public void insertUpdate(DocumentEvent e) { checkPass(); }
													    @Override
													    public void removeUpdate(DocumentEvent e) { checkPass(); }
													    @Override
													    public void changedUpdate(DocumentEvent e) { checkPass(); }
													}
												   );
		
		userField.addFocusListener(new FocusAdapter()
								   {
		    							@Override
		    							public void focusLost(FocusEvent e)
		    							{
									        checkUser();
									    }
								   }
								  );

		passField.addFocusListener(new FocusAdapter()
								   {
										@Override
										public void focusLost(FocusEvent e)
										{
									        checkPass();
									    }
								   }
								  );
		
		showPassBtn.addActionListener(new ActionListener()
									  {
										@Override 
										public void actionPerformed(ActionEvent e)
										{
											if(showPassBtn.isSelected())
			    							{
										    	passField.setEchoChar('•');
										    	showPassBtn.setIcon(eyeOffIcon);
										    } 
										    else
										    {
										    	passField.setEchoChar((char)0);
										    	showPassBtn.setIcon(eyeIcon);
										    }
										}				
									  });
		
		loginBtn.addActionListener(new ActionListener()
									{
										@Override 
										public void actionPerformed(ActionEvent e)
										{
									        if(!checkUser())
									        	userField.requestFocus();
									        else if(!checkPass())
									        	passField.requestFocus();
									        else
									        {
									        	loginBtn.setEnabled(false);
									        	registerBtn.setEnabled(false);
									        	Controller ctrLogin = Controller.getController();
									        	ctrLogin.checkLogin(LoginFrame.this, userField.getText().trim(), passField.getPassword());
									        }
									    }
									}
								  );
		
	}
	
	public void enableButtons()
	{
	    loginBtn.setEnabled(true);
	    registerBtn.setEnabled(true);
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
		String text = new String(passField.getPassword()).trim();

	    if (text.length() < 8 || text.length() > 30)
	    {
	        passField.setBorder(errorBorder);
	        passErrorLabel.setText("La password deve essere tra 8 e 30 caratteri.");
	        check = false;
	    } 
	    else
	    {
	        passField.setBorder(defaultBorder);
	        passErrorLabel.setText(" ");
	    }
	    
	    return check; 
	}

	public void showError(String msg)
	{
		JOptionPane.showMessageDialog(this, msg, "Errore", JOptionPane.ERROR_MESSAGE);
	}	
}
