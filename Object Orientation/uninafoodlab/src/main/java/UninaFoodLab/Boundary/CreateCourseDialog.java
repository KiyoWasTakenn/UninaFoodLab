package UninaFoodLab.Boundary;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.*;

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
    private JXLabel title, limitLabel, sessionTitle, addSessionLabel;
    private JXPanel mainPanel, infoPanel, detailPanel, sessionPanel, sessionsContainer;
    private JXTextField nameField;
    private JXTextArea descrizioneArea;
    private JXButton confirmBtn, goBackBtn;
    private JScrollPane scrollDescrizione, scrollSessions;
    private JFormattedTextField costField, limitField;
    private JCheckBox praticoCheck;
    private JComboBox<> frequencyList;
    
    public CreateCourseDialog(JXFrame parent)
    {
        super(parent, "Crea nuovo corso", true);
        initComponents();
        initListeners();
        pack();
        setLocationRelativeTo(parent);
    }
    
    
    private void initComponents()
    {
    	mainPanel = new JXPanel(new MigLayout("wrap 1, insets 20", "[grow,fill]", "[]20[]20[]20[]"));
    	mainPanel.setBackground(BACKGROUND_COLOR);
        
        // === Titolo ===
        title = new JXLabel("Inserisci i dettagli del nuovo corso");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mainPanel.add(title, "align center");
        
        // === Sezione dati generali ===
        infoPanel = new JXPanel(new MigLayout("wrap 2", "[right][grow,fill]"));
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

        frequencyList = new JComboBox<>();
        infoPanel.add(new JXLabel("Frequenza:"));
        infoPanel.add(frequencyList, "h 30!");
        
        mainPanel.add(infoPanel);
        
        detailPanel = new JXPanel(new MigLayout("wrap 3", "[right][grow,fill][]"));
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(mainBorder);
        
        detailPanel.add(new JXLabel("Costo:"));
        costField = new JFormattedTextField(euroFormatter());
        costField.setValue(0.0);
        detailPanel.add(costField, "h 30!");
        detailPanel.add(new JXLabel("€"));
        
        praticoCheck = new JCheckBox();
        praticoCheck.setBackground(Color.WHITE);
        detailPanel.add(new JXLabel("Pratico:"));
        detailPanel.add(praticoCheck, "span 2, left");
        
        limitLabel = new JXLabel("Limite partecipanti:");
        limitLabel.setVisible(false);
        detailPanel.add(limitLabel, "newline");
        
        limitField = new JFormattedTextField(integerFormatter());
        limitField.setVisible(false);
        limitField.setEnabled(false);
        detailPanel.add(limitField, "h 30!, span 2");
        
        mainPanel.add(detailPanel);
        
        // === Sessioni ===
        sessionPanel = new JXPanel(new MigLayout("wrap 1", "[grow,fill]", "[]10[]10[]"));
        sessionPanel.setBackground(Color.WHITE);
        sessionPanel.setBorder(mainBorder);
        
        sessionTitle = new JXLabel("Sessioni del Corso");
        sessionTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sessionPanel.add(sessionTitle);
        
        addSessionLabel = new JXLabel("<html><u>+ Aggiungi una sessione</u></html>");
        addSessionLabel.setForeground(BUTTON_COLOR);
        addSessionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addSessionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sessionPanel.add(addSessionLabel, "right");
        
        sessionsContainer = new JXPanel(new MigLayout("wrap 1, insets 0, gapy 10", "[grow,fill]"));
        sessionsContainer.setOpaque(false);
        
        scrollSessions = new JScrollPane(sessionsContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSessions.setOpaque(false);
        scrollSessions.getViewport().setOpaque(false);
        scrollSessions.setBorder(BorderFactory.createEmptyBorder());
        scrollSessions.getVerticalScrollBar().setUnitIncrement(14);
        scrollSessions.setPreferredSize(new Dimension(100, 200));
        sessionPanel.add(scrollSessions, "grow");
        
        mainPanel.add(sessionPanel);
        
        // === Pulsanti finali ===
        JXPanel buttons = new JXPanel(new MigLayout("center", "[]20[]"));
        buttons.setBackground(mainPanel.getBackground());

        confirmBtn = new JXButton("Crea Corso");
        confirmBtn.setBackground(BUTTON_COLOR);
        confirmBtn.setForeground(Color.WHITE);

        goBackBtn = new JXButton("Annulla");

        buttons.add(confirmBtn, "w 120!, h 35!");
        buttons.add(goBackBtn, "w 120!, h 35!");

        mainPanel.add(buttons);
        setContentPane(mainPanel); 
    }
    
    private void initListeners()
    {
    	
    }
    
    private JPanel creaSessionePanel(int numero, String tipo, String data, int durata)
    {
        JPanel panel = new JPanel(new MigLayout("wrap 2", "[right][grow,fill]", "[]"));
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
