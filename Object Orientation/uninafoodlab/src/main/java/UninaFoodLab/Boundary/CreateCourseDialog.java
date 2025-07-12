package UninaFoodLab.Boundary;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.*;

import com.github.lgooddatepicker.components.*;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.DateVetoPolicyMinimumMaximumDate;

import UninaFoodLab.Controller.Controller;
import UninaFoodLab.DTO.Argomento;
import UninaFoodLab.DTO.FrequenzaSessioni;
import UninaFoodLab.DTO.Ricetta;
import net.miginfocom.swing.MigLayout;

public class CreateCourseDialog extends JDialog
{
	private static final long serialVersionUID = 1L;

	private static final Color BACKGROUND_COLOR = new Color(245, 248, 250);
	private static final Color BORDER_COLOR = new Color(220, 225, 230);
	private static final Color BUTTON_COLOR = new Color(225, 126, 47, 220);
	private static final CompoundBorder mainBorder = new CompoundBorder(
		new LineBorder(BORDER_COLOR, 1, true),
		BorderFactory.createEmptyBorder(12, 12, 12, 12)
	);

	private JXTextField nameField;
	private JXTextArea descrizioneArea;
	private JFormattedTextField costField, limitField;
	private JComboBox<FrequenzaSessioni> frequencyList;
	private JCheckBox praticoCheck;
	private DatePicker dataInizioField;
	private List<JCheckBox> argumentsCheck;
	private List<CreateSessionPanel> sessionCards = new ArrayList<>();
	private JPanel sessionsContainer;
	private JSpinner praticheSpinner;
	private JLabel limitLabel;
	private JXButton confirmBtn, goBackBtn;
	private JLabel aggiungiSessioneLabel;

	public CreateCourseDialog(JXFrame parent)
	{
		super(parent, "Crea nuovo corso", true);
		initComponents();
		initListeners();
		pack();
		setMinimumSize(new Dimension(1150, 700));
		setPreferredSize(new Dimension(1200, 700));
		setLocationRelativeTo(parent);
		setResizable(true);
		setIconImage(parent.getIconImage());
	}

	private void initComponents()
	{
		JScrollPane rootScroll = new JScrollPane();
		rootScroll.getVerticalScrollBar().setUnitIncrement(16);
		rootScroll.setBorder(null);
		JXPanel container = new JXPanel(new BorderLayout());
		rootScroll.setViewportView(container);
		setContentPane(rootScroll);

		JXPanel mainPanel = new JXPanel(new MigLayout("insets 20, wrap 2", "[grow 65]0[grow 35]", "[grow]"));
		mainPanel.setBackground(BACKGROUND_COLOR);
		container.add(mainPanel, BorderLayout.CENTER);

		// LEFT SIDE
		JXPanel leftPanel = new JXPanel(new MigLayout("wrap 1, gapy 20", "[grow,fill]"));
		leftPanel.setBackground(BACKGROUND_COLOR);

		JXLabel title = new JXLabel("Inserisci i dettagli del nuovo corso");
		title.setFont(new Font("Segoe UI", Font.BOLD, 20));
		leftPanel.add(title, "align center");

		// INFO PANEL
		JXPanel infoPanel = new JXPanel(new MigLayout("wrap 2", "[right][grow,fill]"));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(mainBorder);

		nameField = new JXTextField();
		infoPanel.add(new JLabel("Nome corso:"));
		infoPanel.add(nameField, "h 30!");

		descrizioneArea = new JXTextArea();
		descrizioneArea.setRows(4);
		descrizioneArea.setColumns(20);
		descrizioneArea.setLineWrap(true);
		descrizioneArea.setWrapStyleWord(true);
		JScrollPane scrollDescrizione = new JScrollPane(descrizioneArea);
		scrollDescrizione.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
		infoPanel.add(new JLabel("Descrizione:"));
		infoPanel.add(scrollDescrizione, "h 80!");

		frequencyList = new JComboBox<>(FrequenzaSessioni.values());
		infoPanel.add(new JLabel("Frequenza:"));
		infoPanel.add(frequencyList, "h 30!");

		// ARGOMENTI
		argumentsCheck = new ArrayList<>();
		JPanel argomentiPanel = new JPanel(new GridLayout(0, 1));
		argomentiPanel.setOpaque(false);

		for(Argomento a : Controller.getController().loadArgomenti())
		{
			JCheckBox cb = new JCheckBox(a.getNome());
			argumentsCheck.add(cb);
			argomentiPanel.add(cb);
		}

		JScrollPane scrollArgomenti = new JScrollPane(argomentiPanel);
		scrollArgomenti.setPreferredSize(new Dimension(200, 100));
		scrollArgomenti.setOpaque(false);
		scrollArgomenti.getViewport().setOpaque(false);
		scrollArgomenti.getVerticalScrollBar().setUnitIncrement(14);
		infoPanel.add(new JLabel("Argomenti:"));
		infoPanel.add(scrollArgomenti, "span 2");

		leftPanel.add(infoPanel);

		// DETTAGLI
		JXPanel detailPanel = new JXPanel(new MigLayout("wrap 3", "[right][grow,fill][]"));
		detailPanel.setBackground(Color.WHITE);
		detailPanel.setBorder(mainBorder);

		DateVetoPolicy vetoPolicy = new DateVetoPolicyMinimumMaximumDate(LocalDate.now().plusDays(1), null);
		DatePickerSettings settings = new DatePickerSettings();
		dataInizioField = new DatePicker(settings);
		settings.setVetoPolicy(vetoPolicy);
		detailPanel.add(new JLabel("Data Inizio:"));
		detailPanel.add(dataInizioField, "h 30!, span 2");

		costField = new JFormattedTextField(euroFormatter());
		costField.setValue(0.0);
		detailPanel.add(new JLabel("Costo:"));
		detailPanel.add(costField, "h 30!");
		detailPanel.add(new JLabel("€"));

		praticoCheck = new JCheckBox();
		praticoCheck.setBackground(Color.WHITE);
		detailPanel.add(new JLabel("Pratico:"));
		detailPanel.add(praticoCheck, "span 2, left");

		limitLabel = new JLabel("Limite partecipanti:");
		limitLabel.setVisible(false);
		limitField = new JFormattedTextField(integerFormatter());
		limitField.setVisible(false);
		detailPanel.add(limitLabel, "newline");
		detailPanel.add(limitField, "h 30!, span 2");

		leftPanel.add(detailPanel);

		// BOTTONI
		JXPanel buttons = new JXPanel(new MigLayout("center", "[]20[]"));
		buttons.setBackground(BACKGROUND_COLOR);
		confirmBtn = new JXButton("Crea Corso");
		confirmBtn.setBackground(BUTTON_COLOR);
		confirmBtn.setForeground(Color.WHITE);
		goBackBtn = new JXButton("Annulla");
		buttons.add(confirmBtn, "w 120!, h 35!");
		buttons.add(goBackBtn, "w 120!, h 35!");
		leftPanel.add(buttons);

		mainPanel.add(leftPanel, "growy");

		// RIGHT SIDE – SESSIONI
		JXPanel sessionPanel = new JXPanel(new MigLayout("wrap", "[grow][grow][grow]", "[grow]"));
		sessionPanel.setBackground(Color.WHITE);
		sessionPanel.setBorder(mainBorder);

		JXLabel sessionTitle = new JXLabel("Sessioni del Corso");
		sessionTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		sessionPanel.add(sessionTitle);

		aggiungiSessioneLabel = new JLabel("<html><u>Aggiungi sessioni</u></html>");
		aggiungiSessioneLabel.setForeground(Color.ORANGE.darker());
		aggiungiSessioneLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sessionPanel.add(aggiungiSessioneLabel, "left, span 2, wrap");

		sessionsContainer = new JXPanel(new MigLayout("wrap, fillx, insets 10", "[]", "[]"));
		sessionsContainer.setBackground(Color.WHITE);
		JScrollPane scrollSessions = new JScrollPane(sessionsContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSessions.setPreferredSize(new Dimension(880, 500));
		scrollSessions.setBorder(BorderFactory.createEmptyBorder());
		scrollSessions.getViewport().setBackground(Color.WHITE);
		scrollSessions.setBackground(Color.WHITE);

		sessionPanel.add(scrollSessions, "grow, push");
		mainPanel.add(sessionPanel, "grow");
	}

	private void initListeners()
	{
		aggiungiSessioneLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showAddSessionsDialog();
			}
		});

		confirmBtn.addActionListener(e ->
		{
			if(sessionCards.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Devi aggiungere almeno una sessione.", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}

			for(CreateSessionPanel card : sessionCards)
				if(!card.isValidSession())
				{
					JOptionPane.showMessageDialog(this, "Errore nei dati di una sessione. Controlla data, ora e ricetta/link.", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}

			// TODO: invia dati al Controller
			dispose();
		});

		praticoCheck.addItemListener(e ->
		{
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				limitField.setVisible(true);
				limitLabel.setVisible(true);
			}
			else
			{
				boolean hasPratiche = sessionCards.stream().anyMatch(card -> "Pratica".equals(card.getTipo()));
				if(hasPratiche)
				{
					int result = JOptionPane.showConfirmDialog(this, "Hai già aggiunto sessioni pratiche. Vuoi rimuoverle automaticamente?", "Conferma", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION)
					{
						List<CreateSessionPanel> toRemove = new ArrayList<>();
						for(CreateSessionPanel card : sessionCards)
							if("Pratica".equals(card.getTipo()))
								toRemove.add(card);

						for(CreateSessionPanel card : toRemove)
							removeSessionCard(card);

						praticoCheck.setSelected(false);
						limitLabel.setVisible(false);
						limitField.setVisible(false);
					}
					else praticoCheck.setSelected(true);
				}
				else
				{
					limitLabel.setVisible(false);
					limitField.setVisible(false);
				}
			}
		});
	}

	private void showAddSessionsDialog()
	{
		JDialog dialog = new JDialog(this, "Seleziona numero sessioni", true);
		dialog.setLayout(new MigLayout("wrap 2", "[right][grow,fill]"));

		JSpinner onlineSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 20, 1));
		dialog.add(new JLabel("Sessioni Online:"));
		dialog.add(onlineSpinner);

		if(praticoCheck.isSelected())
		{
			praticheSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 20, 1));
			dialog.add(new JLabel("Sessioni Pratiche:"));
			dialog.add(praticheSpinner);
		}

		JButton addBtn = new JButton("Aggiungi");
		JButton cancelBtn = new JButton("Annulla");
		dialog.add(addBtn, "span 1, split 2, right");
		dialog.add(cancelBtn);

		addBtn.addActionListener(e ->
		{
			int onlineCount = (int) onlineSpinner.getValue();
			int praticheCount = praticheSpinner != null ? (int) praticheSpinner.getValue() : 0;

			for(int i = 0; i < onlineCount; i++)
				addNewSessionCard(false);

			for(int i = 0; i < praticheCount; i++)
				addNewSessionCard(true);

			dialog.dispose();
		});

		cancelBtn.addActionListener(e -> dialog.dispose());
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	private void addNewSessionCard(boolean pratica)
	{
		CreateSessionPanel card = new CreateSessionPanel(sessionCards.size() + 1, pratica, this);
		sessionCards.add(card);
		sessionsContainer.add(card, "w pref!, h pref!");
		sessionsContainer.revalidate();
		sessionsContainer.repaint();
	}

	public void removeSessionCard(CreateSessionPanel panel)
	{
		panel.disposeListeners();
		sessionCards.remove(panel);
		sessionsContainer.remove(panel);

		for(int i = 0; i < sessionCards.size(); i++)
			sessionCards.get(i).aggiornaNumero(i + 1);

		sessionsContainer.revalidate();
		sessionsContainer.repaint();
	}

	private NumberFormatter euroFormatter()
	{
		NumberFormatter formatter = new NumberFormatter(NumberFormat.getNumberInstance(Locale.ITALY));
		formatter.setValueClass(Double.class);
		formatter.setMinimum(0.0);
		formatter.setAllowsInvalid(false);
		return formatter;
	}

	private NumberFormatter integerFormatter()
	{
		NumberFormat format = NumberFormat.getIntegerInstance(Locale.ITALY);
		format.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setAllowsInvalid(false);
		return formatter;
	}
}