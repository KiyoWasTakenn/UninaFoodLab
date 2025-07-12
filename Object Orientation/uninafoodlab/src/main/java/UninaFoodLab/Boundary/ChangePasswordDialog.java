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
import javax.swing.JOptionPane;
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
	private JXFrame parent;

	public ChangePasswordDialog(JXFrame parent)
	{
		super(parent, "Cambia Password", true);
		this.parent=parent;
        initComponents();
        initListeners();
        setSize(380,300);
        setLocationRelativeTo(parent);
        setResizable(false);
	}

	private void initComponents()
	{
		setTitle("Cambia Password");
		// Le dimensioni verranno calcolate da pack(), ma una dimensione iniziale può aiutare MigLayout
		// setSize(450, 350);
		panel = new JXPanel(new MigLayout("wrap 2", "[grow, fill]5[]", "[]10[]10[]10[]10[]10[]30[]"));
        panel.setBackground(Color.WHITE);
        setContentPane(panel);

        oldPasswordErrorLabel = new JXLabel(" ");
        oldPasswordErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        oldPasswordErrorLabel.setForeground(Color.RED);
		oldPasswordErrorLabel.setPreferredSize(new Dimension(300, 20)); // Aggiunto per visibilità
		panel.add(oldPasswordErrorLabel, "span 2, wrap, align center, h 20!"); // Aggiornato per visibilità

		oldPasswordLabel = new JXLabel("Inserisci la vecchia password: ");
		oldPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(oldPasswordLabel, "span 2, wrap, left");

		oldPasswordField = new JPasswordField();
		oldPasswordField.setPreferredSize(new Dimension(200, 30));
		oldPasswordField.setBorder(defaultBorder); // Imposta il bordo predefinito
		panel.add(oldPasswordField, "growx"); // Modificato in growx per espansione orizzontale

    	showOldPassBtn= new JToggleButton();
    	showOldPassBtn.setIcon(eyeOffIconOld);
    	showOldPassBtn.setPreferredSize(new Dimension(30, 30));
    	showOldPassBtn.setFocusable(false);
    	showOldPassBtn.setToolTipText("Mostra/Nascondi password");
    	showOldPassBtn.setBorderPainted(false);
    	showOldPassBtn.setContentAreaFilled(false);
		panel.add(showOldPassBtn, "w 30!, h 25!, wrap, gapleft 5"); // Aggiunto wrap qui

		newPasswordErrorLabel = new JXLabel(" ");
		newPasswordErrorLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		newPasswordErrorLabel.setForeground(Color.RED);
		newPasswordErrorLabel.setPreferredSize(new Dimension(300, 20)); // Aggiunto per visibilità
		panel.add(newPasswordErrorLabel, "span 2, wrap, align center, h 20!"); // Aggiornato per visibilità

		newPasswordLabel = new JXLabel("Inserisci la nuova password: ");
		newPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		panel.add(newPasswordLabel, "span 2, wrap, left");

		newPasswordField = new JPasswordField();
		newPasswordField.setPreferredSize(new Dimension(200, 30));
		newPasswordField.setBorder(defaultBorder); // Imposta il bordo predefinito
		panel.add(newPasswordField, "growx"); // Modificato in growx per espansione orizzontale

		showNewPassBtn= new JToggleButton();
		showNewPassBtn.setIcon(eyeOffIconNew); // Corretto a eyeOffIconNew
		showNewPassBtn.setPreferredSize(new Dimension(30, 30));
		showNewPassBtn.setFocusable(false);
		showNewPassBtn.setToolTipText("Mostra/Nascondi password");
		showNewPassBtn.setBorderPainted(false);
		showNewPassBtn.setContentAreaFilled(false);
		panel.add(showNewPassBtn, "w 30!, h 25!, wrap, gapleft 5"); // Aggiunto wrap qui


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
			    	oldPasswordField.setEchoChar((char)0); // Mostra la password
			    	showOldPassBtn.setIcon(eyeIconOld); // Corretto a eyeIconOld
			    }
			    else
			    {
			    	oldPasswordField.setEchoChar('•'); // Nasconde la password
			    	showOldPassBtn.setIcon(eyeOffIconOld); // Corretto a eyeOffIconOld
			    }
			}
		  };
		  showOldPassBtn.addActionListener(showOldPassBtnActionListener);

		  showNewPassBtnActionListener = new ActionListener()
		  {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(showNewPassBtn.isSelected())
				{
			    	newPasswordField.setEchoChar((char)0); // Mostra la password
			    	showNewPassBtn.setIcon(eyeIconNew);
			    }
			    else
			    {
			    	newPasswordField.setEchoChar('•'); // Nasconde la password
			    	showNewPassBtn.setIcon(eyeOffIconNew);
			    }
			}
		  };
		showNewPassBtn.addActionListener(showNewPassBtnActionListener);

		// Listener per la validazione in tempo reale della vecchia password
		oldPassFieldDocumentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkOldPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkOldPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Non usato per PlainDocument
            }
        };
        oldPasswordField.getDocument().addDocumentListener(oldPassFieldDocumentListener);

        // Listener per la validazione in tempo reale della nuova password
        newPassFieldDocumentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkNewPass();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkNewPass();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Non usato per PlainDocument
            }
        };
        newPasswordField.getDocument().addDocumentListener(newPassFieldDocumentListener);

		ConfermaBtnActionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Esegui entrambi i controlli e sposta il focus se uno fallisce
				boolean oldPassValid = checkOldPass();
				boolean newPassValid = checkNewPass();

				if (!oldPassValid) {
					oldPasswordField.requestFocus();
				} else if (!newPassValid) {
					newPasswordField.requestFocus();
				} else {
		        	conferma.setEnabled(false); // Disabilita il pulsante durante l'operazione
		        	Controller.getController().checkNewPassword(ChangePasswordDialog.this, parent, newPasswordField.getPassword(), oldPasswordField.getPassword());
		        	// La riabilitazione del pulsante e la chiusura della dialog (o gestione errori) dovrebbe avvenire dopo la risposta del controller
		        }
		    }
		};
		conferma.addActionListener(ConfermaBtnActionListener);
	}

	private boolean checkOldPass() {
	    boolean check = true;
	    String text = new String(oldPasswordField.getPassword()).trim();

	    if (text.isEmpty()) {
	    	oldPasswordField.setBorder(errorBorder);
	    	oldPasswordErrorLabel.setText("La vecchia password non può essere vuota.");
	        check = false;
	    } else if (text.length() < 8 || text.length() > 30) {
	    	oldPasswordField.setBorder(errorBorder);
	    	oldPasswordErrorLabel.setText("La vecchia password deve essere tra 8 e 30 caratteri.");
	        check = false;
	    } else {
	    	oldPasswordField.setBorder(defaultBorder);
	    	oldPasswordErrorLabel.setText(" "); // Pulisce il messaggio di errore
	    }
	    return check;
	}


	private boolean checkNewPass()
	{
		boolean check = true;
		String text = new String(newPasswordField.getPassword()).trim();

	    if (text.isEmpty()) {
	    	newPasswordField.setBorder(errorBorder);
	    	newPasswordErrorLabel.setText("La nuova password non può essere vuota.");
	        check = false;
	    } else if (text.length() < 8 || text.length() > 30)
	    {
	    	newPasswordField.setBorder(errorBorder);
	    	newPasswordErrorLabel.setText("La password deve essere tra 8 e 30 caratteri.");
	        check = false;
	    }
	    else
	    {
	    	newPasswordField.setBorder(defaultBorder);
	    	newPasswordErrorLabel.setText(" "); // Pulisce il messaggio di errore
	    }

	    return check;
	}

	public void showError(String msg)
	{
		JOptionPane.showMessageDialog(this, msg, "Errore", JOptionPane.ERROR_MESSAGE);
		conferma.setEnabled(true); // Riabilita il pulsante in caso di errore dal controller
	}

	public void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Successo", JOptionPane.INFORMATION_MESSAGE);
        conferma.setEnabled(true); // Riabilita il pulsante in caso di successo
        dispose(); // Chiude la finestra di dialogo al successo
    }

}