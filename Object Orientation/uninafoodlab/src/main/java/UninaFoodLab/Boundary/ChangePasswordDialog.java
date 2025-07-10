package UninaFoodLab.Boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import UninaFoodLab.Controller.Controller;
import net.miginfocom.swing.MigLayout;

public class ChangePasswordDialog extends JDialog {
	
	FontIcon eyeIconOld = FontIcon.of(MaterialDesign.MDI_EYE, 18);
	FontIcon eyeOffIconOld = FontIcon.of(MaterialDesign.MDI_EYE_OFF, 18);
	
	FontIcon eyeIconNew = FontIcon.of(MaterialDesign.MDI_EYE, 18);
	FontIcon eyeOffIconNew = FontIcon.of(MaterialDesign.MDI_EYE_OFF, 18);
	
	private JXLabel oldPasswordErrorLabel;
	private JXLabel oldPasswordLabel;
	private JToggleButton showOldPassBtn;
	private JPasswordField oldPasswordField;
	
	private JXLabel newPasswordErrorLabel;
	private JXLabel newPasswordLabel;
	private JToggleButton showNewPassBtn;
	private JPasswordField newPasswordField;
	
	private CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
			new LineBorder(Color.LIGHT_GRAY, 1), 
		  	new EmptyBorder(0, 6, 0, 0));
	private CompoundBorder errorBorder = BorderFactory.createCompoundBorder(
        	new LineBorder(Color.RED, 1),
        	new EmptyBorder(0, 6, 0, 0));
	
	private JXButton conferma;
	
	ActionListener showOldPassBtnActionListener;
	ActionListener showNewPassBtnActionListener;
	ActionListener ConfermaBtnActionListener;
	DocumentListener oldPassFieldDocumentListener;
	DocumentListener newPassFieldDocumentListener;
	
	private JXPanel panel;
	
	public ChangePasswordDialog(JXFrame parent)
	{
		
		super(parent, "Cambia Password", true);
        initComponents();
        initListeners();
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
	}
	
	private void initComponents()
	{
		setTitle("Cambia Password");
		setSize(450, 350);
		panel = new JXPanel(new MigLayout("", "20[]5[]20", "[]10[]10[]10[]10[]10[]30[]"));
        panel.setBackground(Color.WHITE);
        setContentPane(panel);
        
        oldPasswordErrorLabel = new JXLabel(" ");
        oldPasswordErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        oldPasswordErrorLabel.setForeground(Color.RED);
		panel.add(oldPasswordErrorLabel, "span 2, cell 0 0, center");
		
		oldPasswordLabel = new JXLabel("Inserisci la vecchia password: ");
		oldPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(oldPasswordLabel, "span 2, cell 0 1, left");	
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setPreferredSize(new Dimension(200, 30));
		panel.add(oldPasswordField, "cell 0 2, center");;
		
    	showOldPassBtn= new JToggleButton();
    	showOldPassBtn.setIcon(eyeOffIconOld);
    	showOldPassBtn.setPreferredSize(new Dimension(30, 30));
    	showOldPassBtn.setFocusable(false);
    	showOldPassBtn.setToolTipText("Mostra/Nascondi password");
    	showOldPassBtn.setBorderPainted(false);
    	showOldPassBtn.setContentAreaFilled(false);
		panel.add(showOldPassBtn, "w 30!, h 25!, gapleft 5");
    	
		
		newPasswordErrorLabel = new JXLabel(" ");
		newPasswordErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		newPasswordErrorLabel.setForeground(Color.RED);
		panel.add(newPasswordErrorLabel, "span 2, cell 0 3, center");
		
		newPasswordLabel = new JXLabel("Inserisci la nuova password: ");
		newPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(newPasswordLabel, "span 2, cell 0 4, left");	
		
		newPasswordField = new JPasswordField();
		newPasswordField.setPreferredSize(new Dimension(200, 30));
		panel.add(newPasswordField, "cell 0 5, center");;
		
		showNewPassBtn= new JToggleButton();
		showNewPassBtn.setIcon(eyeOffIconOld);
		showNewPassBtn.setPreferredSize(new Dimension(30, 30));
		showNewPassBtn.setFocusable(false);
		showNewPassBtn.setToolTipText("Mostra/Nascondi password");
		showNewPassBtn.setBorderPainted(false);
		showNewPassBtn.setContentAreaFilled(false);
		panel.add(showNewPassBtn, "w 30!, h 25!, gapleft 5");
    	
    	
    	conferma= new JXButton("Conferma");
    	conferma.setFont(new Font("SansSerif", Font.BOLD, 18));
    	conferma.setPreferredSize(new Dimension(120, 30));
		conferma.setBackground(new Color(225, 126, 47, 220));
		conferma.setForeground(Color.WHITE);
		conferma.setOpaque(true);
		conferma.setFocusPainted(false);
		conferma.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(conferma, "span 2, cell 0 6, center");
        
	}
	
	private void initListeners()
	{
		showOldPassBtnActionListener = new ActionListener()
		  {
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				if(showOldPassBtn.isSelected())
				{
			    	oldPasswordField.setEchoChar('•');
			    	showOldPassBtn.setIcon(eyeOffIconNew);
			    } 
			    else
			    {
			    	oldPasswordField.setEchoChar((char)0);
			    	showOldPassBtn.setIcon(eyeIconNew);
			    }
			}				
		  };
		  showOldPassBtn.addActionListener(showOldPassBtnActionListener);
		
		  oldPassFieldDocumentListener = new DocumentListener()
			{
				@Override
			    public void insertUpdate(DocumentEvent e) { checkOldPass(); }
			    @Override
			    public void removeUpdate(DocumentEvent e) { checkOldPass(); }
			    @Override
			    public void changedUpdate(DocumentEvent e) { checkOldPass(); }
			};
		   
		oldPasswordField.getDocument().addDocumentListener(oldPassFieldDocumentListener);
			
		  showNewPassBtnActionListener = new ActionListener()
		  {
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				if(showNewPassBtn.isSelected())
				{
			    	newPasswordField.setEchoChar('•');
			    	showNewPassBtn.setIcon(eyeOffIconNew);
			    } 
			    else
			    {
			    	newPasswordField.setEchoChar((char)0);
			    	showNewPassBtn.setIcon(eyeIconNew);
			    }
			}				
		  };
		showNewPassBtn.addActionListener(showNewPassBtnActionListener);
		
		ConfermaBtnActionListener = new ActionListener()
		{
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				/*if(!checkOldPass())
					oldPasswordField.requestFocus();
				else */if(!checkNewPass())
					newPasswordField.requestFocus();
		        else
		        {
		        	conferma.setEnabled(false);
		        	
		        	Controller.getController().checkNewPassword(ChangePasswordDialog.this, newPasswordField.getPassword(), oldPasswordField.getPassword());
		        }
		    }
		};
		conferma.addActionListener(ConfermaBtnActionListener);
	}
	
	private boolean checkNewPass()
	{
		boolean check = true;
		String text = new String(newPasswordField.getPassword()).trim();

	    if (text.length() < 8 || text.length() > 30)
	    {
	    	newPasswordField.setBorder(errorBorder);
	    	newPasswordErrorLabel.setText("La password deve essere tra 8 e 30 caratteri.");
	        check = false;
	    } 
	    else
	    {
	    	newPasswordField.setBorder(defaultBorder);
	    	newPasswordErrorLabel.setText(" ");
	    }
	    
	    return check; 
	}
	
	/*private boolean checkOldPass()
	{
		boolean check = true;
		String text = new String(oldPasswordField.getPassword()).trim();

	    if (text.length() < 8 || text.length() > 30)
	    {
	    	oldPasswordField.setBorder(errorBorder);
	    	oldPasswordErrorLabel.setText("La password deve essere tra 8 e 30 caratteri.");
	        check = false;
	    } 
	    else
	    {
	    	oldPasswordField.setBorder(defaultBorder);
	    	oldPasswordErrorLabel.setText(" ");
	    }
	    
	    return check; 
	}*/
}
