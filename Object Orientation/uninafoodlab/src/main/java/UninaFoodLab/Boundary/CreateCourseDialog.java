package UninaFoodLab.Boundary;

import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.DateVetoPolicyMinimumMaximumDate;

import UninaFoodLab.Controller.Controller;
import UninaFoodLab.DTO.Argomento;
import UninaFoodLab.DTO.FrequenzaSessioni;
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

    private JXLabel title, limitLabel, sessionTitle;
    private JXPanel mainPanel, leftPanel, sessionPanel, sessionsContainer;
    private JXTextField nameField;
    private JXTextArea descrizioneArea;
    private JXButton confirmBtn, goBackBtn, generateSessionsBtn;
    private JScrollPane scrollDescrizione, scrollSessions;
    private JFormattedTextField costField, limitField, numSessioniField;
    private JCheckBox praticoCheck;
    private JComboBox<FrequenzaSessioni> frequencyList;
    private java.util.List<JCheckBox> argumentsCheck;
    private DatePicker dataInizioField;

    public CreateCourseDialog(JXFrame parent)
    {
        super(parent, "Crea nuovo corso", true);
        initComponents();
        initListeners();
        pack();
        setLocationRelativeTo(parent);
        setResizable(true);
    }

    private void initComponents()
    {
        mainPanel = new JXPanel(new MigLayout("insets 20", "[grow][600]", "[]"));
        mainPanel.setBackground(BACKGROUND_COLOR);

        // === Sinistra: Titolo, info, dettagli ===
        leftPanel = new JXPanel(new MigLayout("wrap 1, gapy 20", "[grow,fill]"));
        leftPanel.setBackground(BACKGROUND_COLOR);

        title = new JXLabel("Inserisci i dettagli del nuovo corso");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        leftPanel.add(title, "align center");

        JXPanel infoPanel = new JXPanel(new MigLayout("wrap 2", "[right][grow,fill]"));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(mainBorder);

        nameField = new JXTextField();
        infoPanel.add(new JXLabel("Nome corso:"));
        infoPanel.add(nameField, "h 30!");

        descrizioneArea = new JXTextArea();
        descrizioneArea.setRows(4);
        descrizioneArea.setColumns(20);
        descrizioneArea.setLineWrap(true);
        descrizioneArea.setWrapStyleWord(true);
        scrollDescrizione = new JScrollPane(descrizioneArea);
        scrollDescrizione.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        infoPanel.add(new JXLabel("Descrizione:"));
        infoPanel.add(scrollDescrizione, "h 80!");

        frequencyList = new JComboBox<FrequenzaSessioni>(FrequenzaSessioni.values());
        infoPanel.add(new JXLabel("Frequenza:"));
        infoPanel.add(frequencyList, "h 30!");

        argumentsCheck = new ArrayList<>();
        JPanel argomentiPanel = new JPanel(new GridLayout(0, 1));
        argomentiPanel.setOpaque(false);
        argomentiPanel.setBackground(BACKGROUND_COLOR);

        for (Argomento a : Controller.getController().loadArgomenti())
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

        JXPanel detailPanel = new JXPanel(new MigLayout("wrap 3", "[right][grow,fill][]"));
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(mainBorder);

       
        DateVetoPolicy vetoPolicy = new DateVetoPolicyMinimumMaximumDate(null, LocalDate.now());
        DatePickerSettings ciao = new DatePickerSettings();
        dataInizioField = new DatePicker(ciao);
        ciao.setVetoPolicy(vetoPolicy);
        detailPanel.add(new JXLabel("Data Inizio:"));
        detailPanel.add(dataInizioField, "h 30!, span 2");
        
        costField = new JFormattedTextField(euroFormatter());
        costField.setValue(0.0);
        detailPanel.add(new JXLabel("Costo:"));
        detailPanel.add(costField, "h 30!");
        detailPanel.add(new JXLabel("€"));

        praticoCheck = new JCheckBox();
        praticoCheck.setBackground(Color.WHITE);
        detailPanel.add(new JXLabel("Pratico:"));
        detailPanel.add(praticoCheck, "span 2, left");

        limitLabel = new JXLabel("Limite partecipanti:");
        limitLabel.setVisible(false);
        limitField = new JFormattedTextField(integerFormatter());
        limitField.setVisible(false);
        limitField.setEnabled(false);

        detailPanel.add(limitLabel, "newline");
        detailPanel.add(limitField, "h 30!, span 2");

        leftPanel.add(detailPanel);

        // === Pulsanti finali ===
        JXPanel buttons = new JXPanel(new MigLayout("center", "[]20[]"));
        buttons.setBackground(mainPanel.getBackground());

        confirmBtn = new JXButton("Crea Corso");
        confirmBtn.setBackground(BUTTON_COLOR);
        confirmBtn.setForeground(Color.WHITE);

        goBackBtn = new JXButton("Annulla");

        buttons.add(confirmBtn, "w 120!, h 35!");
        buttons.add(goBackBtn, "w 120!, h 35!");

        leftPanel.add(buttons);

        // === Destra: pannello sessioni ===
        sessionPanel = new JXPanel(new MigLayout("wrap 1", "[grow,fill]", "[]10[]10[]"));
        sessionPanel.setBackground(Color.WHITE);
        sessionPanel.setBorder(mainBorder);

        sessionTitle = new JXLabel("Sessioni del Corso");
        sessionTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sessionPanel.add(sessionTitle);

        numSessioniField = new JFormattedTextField(integerFormatter());
        sessionPanel.add(new JXLabel("Numero Sessioni:"));
        sessionPanel.add(numSessioniField, "h 30!");

        generateSessionsBtn = new JXButton("Genera Sessioni");
        generateSessionsBtn.setBackground(BUTTON_COLOR);
        generateSessionsBtn.setForeground(Color.WHITE);
        sessionPanel.add(generateSessionsBtn, "right, h 30!, w 160!");

        sessionsContainer = new JXPanel(new MigLayout("wrap 1, gapy 10"));
        sessionsContainer.setOpaque(false);

        scrollSessions = new JScrollPane(
            sessionsContainer,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollSessions.setOpaque(false);
        scrollSessions.getViewport().setOpaque(false);
        scrollSessions.setBorder(BorderFactory.createEmptyBorder());
        scrollSessions.setPreferredSize(new Dimension(400, 400));
        scrollSessions.getVerticalScrollBar().setUnitIncrement(16);
        scrollSessions.getHorizontalScrollBar().setUnitIncrement(16);

        sessionPanel.add(scrollSessions, "grow");

        // === Aggiungi pannelli ===
        mainPanel.add(leftPanel, "growy");
        mainPanel.add(sessionPanel, "growy");

        setContentPane(mainPanel);
    }

    private void initListeners()
    {
        generateSessionsBtn.addActionListener(e ->
        {
            Object val = numSessioniField.getValue();
            if (val instanceof Number)
            {
                int n = ((Number) val).intValue();
                sessionsContainer.removeAll();

                for (int i = 1; i <= n; i++)
                {
                    JPanel p = creaSessionePanel(i, "Online", "da definire", 60);
                    sessionsContainer.add(p, "growx");
                }

                sessionsContainer.revalidate();
                sessionsContainer.repaint();
            }
        });

        numSessioniField.getDocument().addDocumentListener(new DocumentListener()
        {
            public void insertUpdate(DocumentEvent e) { toggleBtn(); }
            public void removeUpdate(DocumentEvent e) { toggleBtn(); }
            public void changedUpdate(DocumentEvent e) { toggleBtn(); }

            private void toggleBtn()
            {
                boolean enabled = !numSessioniField.getText().trim().isEmpty();
                generateSessionsBtn.setEnabled(enabled);
                generateSessionsBtn.setVisible(enabled);
            }
        });
    }

    private JPanel creaSessionePanel(int numero, String tipo, String data, int durata)
    {
        JPanel panel = new JPanel(new MigLayout("wrap 2", "[right][grow,fill]"));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_COLOR, 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        JLabel header = new JLabel("Sessione #" + numero + ": " + tipo);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setForeground(new Color(60, 60, 60));
        panel.add(header, "span 2");

        panel.add(new JLabel("Data:"));
        panel.add(new JLabel(data));

        panel.add(new JLabel("Durata (min):"));
        panel.add(new JLabel(String.valueOf(durata)));

        return panel;
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