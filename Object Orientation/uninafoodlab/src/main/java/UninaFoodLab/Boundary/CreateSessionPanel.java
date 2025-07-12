package UninaFoodLab.Boundary;

import javax.swing.*;
import org.jdesktop.swingx.*;
import com.github.lgooddatepicker.components.*;

import UninaFoodLab.Controller.Controller;
import UninaFoodLab.DTO.Ricetta;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.*;
import java.util.List;

public class CreateSessionPanel extends JXPanel
{
	private static final long serialVersionUID = 1L;

	private int numero;
	private boolean pratica;
	private CreateCourseDialog parent;

	private JXLabel numeroLabel;
	private DatePicker datePicker;
	private TimePicker timePicker;
	private JSpinner durataSpinner;
	private JComboBox<Ricetta> ricettaCombo;
	private JXTextField addressField, linkField;
	private JXButton removeBtn;

	public CreateSessionPanel(int numero, boolean pratica, CreateCourseDialog parent)
	{
		this.numero = numero;
		this.pratica = pratica;
		this.parent = parent;

		initComponents();
		initListeners();
	}

	private void initComponents()
	{
		setLayout(new MigLayout("wrap 2, insets 15, gapy 10", "[right][grow,fill]"));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
			BorderFactory.createEmptyBorder(10, 10, 10, 10)
		));

		numeroLabel = new JXLabel("Sessione #" + numero);
		numeroLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		add(numeroLabel, "span 2, center");

		add(new JXLabel("Tipo:"));
		add(new JXLabel(pratica ? "Pratica" : "Online"));

		add(new JXLabel("Data:"));
		datePicker = new DatePicker();
		add(datePicker);

		add(new JXLabel("Ora:"));
		timePicker = new TimePicker();
		add(timePicker);

		add(new JXLabel("Durata (minuti):"));
		durataSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 480, 1));
		add(durataSpinner);

		if(pratica)
		{
			add(new JXLabel("Indirizzo: "));
			addressField = new JXTextField();	
			add(addressField, "h 30!");
			
			List<Ricetta> ricette = Controller.getController().loadRicette();
			ricettaCombo = new JComboBox<>(ricette.toArray(new Ricetta[0]));
			add(ricettaCombo, "h 30!");
			ricettaCombo.setEditable(true);
		}
		else
		{
			add(new JXLabel("Link riunione:"));
			linkField = new JXTextField();
			add(linkField, "h 30!");
		}

		removeBtn = new JXButton("Rimuovi sessione");
		removeBtn.setBackground(new Color(220, 53, 69));
		removeBtn.setForeground(Color.WHITE);
		removeBtn.setFocusPainted(false);
		removeBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		add(removeBtn, "span 2, center, gaptop 10");
	}

	private void initListeners()
	{
		removeBtn.addActionListener(e -> parent.removeSessionCard(this));
	}

	public void disposeListeners()
	{
		for(ActionListener al : removeBtn.getActionListeners())
			removeBtn.removeActionListener(al);
	}

	public void aggiornaNumero(int nuovoNumero)
	{
		this.numero = nuovoNumero;
		numeroLabel.setText("Sessione #" + nuovoNumero);
	}

	public boolean isValidSession()
	{
		if(datePicker.getDate() == null || timePicker.getTime() == null)
			return false;

		if(pratica)
			return ricettaCombo.getSelectedItem() != null && !ricettaCombo.getSelectedItem().toString().trim().isEmpty();
		else
			return linkField.getText().trim().length() > 0;
	}

	public String getTipo() 
	{ 
		return pratica ? "Pratica" : "Online"; 
	}
	
	public LocalDate getData() 
	{ 
		return datePicker.getDate(); 
	}
	
	public LocalTime getOra() 
	{ 
		return timePicker.getTime(); 
	}
	
	public int getDurata() 
	{ 
		return (int) durataSpinner.getValue(); 
	}
	
	public String getRicetta() 
	{ 
		return pratica ? ricettaCombo.getSelectedItem().toString().trim() : null; 
	}
	
	public String getLinkRiunione() 
	{ 
		return pratica ? null : linkField.getText().trim(); 
	}
}