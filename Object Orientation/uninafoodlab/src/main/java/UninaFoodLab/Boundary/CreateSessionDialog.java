package UninaFoodLab.Boundary;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class CreateSessionDialog extends JDialog 
{

	private static final long serialVersionUID = 1L;
	private JComboBox<String> tipoBox;
    private JXTextField dataField;
    private JFormattedTextField durataField;

    private boolean confermato = false;

    public CreateSessionDialog(Window owner) 
    {
        super(owner, "Crea Nuova Sessione", ModalityType.APPLICATION_MODAL);
        initComponents();
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() 
    {
        JXPanel content = new JXPanel(new MigLayout("wrap 2", "[][grow,fill]", "[]20[]20[]20[]"));
        content.setBackground(Color.WHITE);

        JXLabel titolo = new JXLabel("Inserisci i dettagli della sessione");
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        content.add(titolo, "span 2, align center");

        content.add(new JXLabel("Tipo:"));
        tipoBox = new JComboBox<>(new String[]{"Pratica", "Online"});
        content.add(tipoBox);

        content.add(new JXLabel("Data (es. 2025-07-08):"));
        dataField = new JXTextField();
        content.add(dataField);

        content.add(new JXLabel("Durata (minuti):"));
        durataField = new JFormattedTextField(integerFormatter());
        durataField.setValue(60);
        content.add(durataField);

        JXPanel buttons = new JXPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton confermaBtn = new JButton("Conferma");
        JButton annullaBtn = new JButton("Annulla");

        buttons.add(confermaBtn);
        buttons.add(annullaBtn);

        content.add(buttons, "span 2, align center");

        setContentPane(content);
        getRootPane().setDefaultButton(confermaBtn);
    }

    private boolean validateInput() 
    {
        String data = getData();
        if (data.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Inserisci una data valida.", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int durata = getDurata();
        if (durata <= 0) 
        {
            JOptionPane.showMessageDialog(this, "La durata deve essere positiva.", "Errore", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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

    public boolean isConfermato() 
    {
        return confermato;
    }

    public String getTipo() 
    {
        return (String) tipoBox.getSelectedItem();
    }

    public String getData() 
    {
        return dataField.getText().trim();
    }

    public int getDurata() 
    {
        Object val = durataField.getValue();
        return (val instanceof Number) ? ((Number) val).intValue() : 0;
    }
}
