package UninaFoodLab.Boundary;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.swingx.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import net.miginfocom.swing.MigLayout;

public class ReportFrame extends JXFrame {

    private static final long serialVersionUID = 1L;

    private final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 26);
    private final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private final Color PRIMARY_COLOR = new Color(225, 126, 47);
    private final Color SECONDARY_COLOR = new Color(60, 130, 200);

    private JXLabel lblDescrizioneCorsi;
    private JXLabel lblDescrizioneRicette;
    private ChartPanel chartPanelCorsi;
    private ChartPanel chartPanelRicette;

    public ReportFrame() {
        setTitle("UninaFoodLab - Report Mensile");
        setMinimumSize(new Dimension(800, 600));
        setSize(900, 700); // finestra iniziale non fullscreen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JXPanel content = new JXPanel(new MigLayout(
            "wrap 1, insets 20 40 20 40, gapy 15",
            "[grow]",
            "[][][grow][][grow][]"
        ));
        content.setBackground(new Color(240, 240, 240));
        setContentPane(content);

        String meseAnno = java.time.LocalDate.now()
                .getMonth()
                .getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ITALIAN)
                + " " + java.time.LocalDate.now().getYear();

        content.add(createLabel("📊 Report Mensile - " + meseAnno, TITLE_FONT), "growx");

        chartPanelCorsi = createChartPanel();
        chartPanelRicette = createChartPanel();
        lblDescrizioneCorsi = createLabel("", SUBTITLE_FONT);
        lblDescrizioneRicette = createLabel("", SUBTITLE_FONT);

        content.add(chartPanelCorsi, "grow, push, hmin 250");
        content.add(lblDescrizioneCorsi, "growx");
        content.add(chartPanelRicette, "grow, push, hmin 250");
        content.add(lblDescrizioneRicette, "growx");
    }

    private ChartPanel createChartPanel() {
        ChartPanel panel = new ChartPanel(null);
        panel.setMouseWheelEnabled(true); // zoom con rotellina
        panel.setPopupMenu(null);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(800, 350)); // dimensione comoda ma ridimensionabile
        return panel;
    }

    private JXLabel createLabel(String text, Font font) {
        JXLabel label = new JXLabel(text);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(40, 40, 40));
        return label;
    }

    public void setReportData(int numCorsi, int numSessioniOnline, int numSessioniPresenza,
                               int ricetteMin, int ricetteMax, double ricetteMedia) {
        resetView();

        boolean noData = (numCorsi + numSessioniOnline + numSessioniPresenza == 0);
        boolean noSessioniPratiche = (numSessioniPresenza == 0);

        if (noData) {
            lblDescrizioneCorsi.setText("⚠ Nessun dato disponibile per il mese corrente.");
            return;
        }

        DefaultCategoryDataset corsiDataset = new DefaultCategoryDataset();
        corsiDataset.addValue(numCorsi, "Totale", "Corsi");
        corsiDataset.addValue(numSessioniOnline, "Totale", "Online");
        corsiDataset.addValue(numSessioniPresenza, "Totale", "In Presenza");

        chartPanelCorsi.setChart(createBarChart("Corsi e Sessioni", corsiDataset, PRIMARY_COLOR));
        lblDescrizioneCorsi.setText("• Corsi totali: " + numCorsi +
            " | Sessioni (Online / In presenza): " + numSessioniOnline + " / " + numSessioniPresenza);

        if (noSessioniPratiche) {
            lblDescrizioneRicette.setText("⚠ Nessuna sessione pratica disponibile.");
            return;
        }

        DefaultCategoryDataset ricetteDataset = new DefaultCategoryDataset();
        ricetteDataset.addValue(ricetteMin, "Ricette", "Min");
        ricetteDataset.addValue(ricetteMedia, "Ricette", "Media");
        ricetteDataset.addValue(ricetteMax, "Ricette", "Max");

        chartPanelRicette.setChart(createBarChart("Statistiche Ricette", ricetteDataset, SECONDARY_COLOR));
        lblDescrizioneRicette.setText("• Ricette per sessione (Min / Max / Media): " +
            ricetteMin + " / " + ricetteMax + " / " + String.format("%.2f", ricetteMedia));
    }

    private JFreeChart createBarChart(String title, DefaultCategoryDataset dataset, Color color) {
        JFreeChart chart = ChartFactory.createBarChart(title, null, null, dataset);
        chart.setBackgroundPaint(new Color(250, 250, 250));
        chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 18));
        chart.getTitle().setPaint(new Color(30, 30, 30));
        chart.removeLegend();

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(220, 220, 220));
        plot.setInsets(new RectangleInsets(10, 20, 10, 20)); // padding extra per evitare tagli

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)); // 22.5°
        domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 13));
        domainAxis.setTickLabelPaint(new Color(60, 60, 60));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 13));
        rangeAxis.setTickLabelPaint(new Color(60, 60, 60));

        double maxValue = 0;
        for (int row = 0; row < dataset.getRowCount(); row++) {
            for (int col = 0; col < dataset.getColumnCount(); col++) {
                Number val = dataset.getValue(row, col);
                if (val != null) maxValue = Math.max(maxValue, val.doubleValue());
            }
        }
        double upper = Math.max(10, maxValue * 1.25);
        rangeAxis.setRange(0, upper);
        rangeAxis.setAutoRange(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, color);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setItemMargin(0.1);
        renderer.setShadowVisible(false);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 13));
        renderer.setDefaultItemLabelPaint(Color.BLACK);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        return chart;
    }

    public void resetView() {
        lblDescrizioneCorsi.setText("");
        lblDescrizioneRicette.setText("");
        chartPanelCorsi.setChart(null);
        chartPanelRicette.setChart(null);
    }
}
