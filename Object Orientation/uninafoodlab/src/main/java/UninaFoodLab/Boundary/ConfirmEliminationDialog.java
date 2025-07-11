package UninaFoodLab.Boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import UninaFoodLab.Controller.Controller;
import net.miginfocom.swing.MigLayout;

public class ConfirmEliminationDialog extends JDialog {

		FontIcon eyeIcon = FontIcon.of(MaterialDesign.MDI_EYE, 18);
		FontIcon eyeOffIcon = FontIcon.of(MaterialDesign.MDI_EYE_OFF, 18);
		private JXLabel passwordErrorLabel;
	    private JPasswordField passwordField;
	    private JButton confirmButton;
	    private JToggleButton showPassBtn;
	    private JXPanel panel;
	    private ActionListener confirmButtonListener;
	    ActionListener showPassBtnActionListener;
	    private JXLabel passwordLabel;
		private CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
				new LineBorder(Color.LIGHT_GRAY, 1), 
			  	new EmptyBorder(0, 6, 0, 0));
		private CompoundBorder errorBorder = BorderFactory.createCompoundBorder(
	        	new LineBorder(Color.RED, 1),
	        	new EmptyBorder(0, 6, 0, 0));
	    
	    public ConfirmEliminationDialog(JXFrame parent)
		{
			
			super(parent, "Conferma Eliminazione Profilo", true);
			initComponents();
			initListeners();
	        pack();
	        setLocationRelativeTo(parent);
	        setResizable(false);
		}
		

	    private void initComponents() {
	        // Pannello principale con layout a griglia (1 riga, 2 colonne)
	        panel = new JXPanel(new MigLayout("", "[][][]30[]", "[][]"));
	        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margine interno

	        passwordErrorLabel = new JXLabel(" ");
	        passwordErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
	        passwordErrorLabel.setForeground(Color.RED);
			panel.add(passwordErrorLabel, "span 2, cell 0 0, center");	
       
	        passwordLabel = new JXLabel("Inserisci password per eliminare il profilo:");
	        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		    panel.add(passwordLabel, "cell 0 1, span 2");        
	        
	        passwordField = new JPasswordField(20); // 20 colonne di larghezza preferita
	        passwordField.setPreferredSize(new Dimension(200, 30));
	        panel.add(passwordField, "cell 0 2");

	        showPassBtn= new JToggleButton();
	    	showPassBtn.setIcon(eyeOffIcon);
	    	showPassBtn.setPreferredSize(new Dimension(30, 30));
	    	showPassBtn.setFocusable(false);
	    	showPassBtn.setToolTipText("Mostra/Nascondi password");
	    	showPassBtn.setBorderPainted(false);
	    	showPassBtn.setContentAreaFilled(false);
			panel.add(showPassBtn, "w 30!, h 25!, gapleft 5");
			        
	        confirmButton = new JButton("Conferma");
	        panel.add(confirmButton, "cell 0 3, span 2, right");

	        // Aggiungi i pannelli al dialog
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(panel, BorderLayout.CENTER);
	    }
	    
	    private void initListeners() {
			  showPassBtnActionListener = new ActionListener()
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
			  };
			showPassBtn.addActionListener(showPassBtnActionListener);
			
			confirmButtonListener = new ActionListener()
			{
				@Override 
				public void actionPerformed(ActionEvent e)
				{
					if(!checkNewPass())
						passwordField.requestFocus();
			        else
			        {
			        	confirmButton.setEnabled(false);
			        	
			        	Controller.getController().checkDelete(ConfirmEliminationDialog.this, passwordField.getPassword());
			        }
			    }
			};
			confirmButton.addActionListener(confirmButtonListener);
		}
		
		private boolean checkNewPass()
		{
			boolean check = true;
			String text = new String(passwordField.getPassword()).trim();

		    if (text.length() < 8 || text.length() > 30)
		    {
		    	passwordField.setBorder(errorBorder);
		    	passwordErrorLabel.setText("La password deve essere tra 8 e 30 caratteri.");
		        check = false;
		    } 
		    else
		    {
		    	passwordField.setBorder(defaultBorder);
		    	passwordErrorLabel.setText(" ");
		    }
		    
		    return check; 
		}
		
		public void showError(String msg)
		{
			JOptionPane.showMessageDialog(this, msg, "Errore", JOptionPane.ERROR_MESSAGE);
		}	
		
}



