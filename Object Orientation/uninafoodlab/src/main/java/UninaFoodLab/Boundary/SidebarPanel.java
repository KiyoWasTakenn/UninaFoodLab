package UninaFoodLab.Boundary;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;

import UninaFoodLab.Controller.Controller;
import net.miginfocom.swing.MigLayout;

/**
 * Componente grafico riutilizzabile che rappresenta la sidebar del menu di navigazione laterale.
 * 
 * Questa classe non è da intendersi come un Boundary autonomo, ma come un componente
 * UI di supporto utilizzato all'interno dei Boundary principali.
 *
 */

public class SidebarPanel extends JXPanel
{

	private static final long serialVersionUID = 1L;

	private JXButton homeBtn, coursesBtn, recipesBtn, reportBtn;
	private MouseAdapter hoverListener;

	public SidebarPanel()
	{
        setLayout(new MigLayout("insets 20, wrap 1", "[grow, fill]"));
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));
        setPreferredSize(new Dimension(190, 800));

        initComponents();
        styleComponents();
        initListeners();
	}

	private void initComponents()
	{
		homeBtn = new JXButton("  Homepage");
    	coursesBtn = new JXButton("  I miei corsi");
    	
    	add(homeBtn);
        add(coursesBtn);
        
        if(Controller.getController().isChefLogged())
        {
        	recipesBtn = new JXButton(" Le mie ricette");
        	reportBtn = new JXButton(" Visualizza Report");
        	add(recipesBtn);
            add(reportBtn);
        }
	}
	
	private void styleComponents()
	{
		 styleSidebarButton(homeBtn);
	     styleSidebarButton(coursesBtn);
	     
	     if(Controller.getController().isChefLogged())
	     {
	    	 styleSidebarButton(recipesBtn);
		     styleSidebarButton(reportBtn);	    	 
	     }
	}
	
	private void initListeners()
	{
		hoverListener = new MouseAdapter() 
		{
		    @Override
		    public void mouseEntered(MouseEvent e) 
		    {
		        if(e.getSource() instanceof JXButton) 
		        {
		            JXButton btn = (JXButton) e.getSource();
		            btn.setBackground(new Color(230, 230, 230));
		            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        }
		    }
		    @Override
		    public void mouseExited(MouseEvent e) 
		    {
		        if(e.getSource() instanceof JXButton) 
		        {
		            JXButton btn = (JXButton) e.getSource();
		            btn.setBackground(new Color(245, 245, 245));
		        }
		    }
		};
		
		homeBtn.addMouseListener(hoverListener);
        coursesBtn.addMouseListener(hoverListener);
        
        if(Controller.getController().isChefLogged())
        {
        	recipesBtn.addMouseListener(hoverListener);
            reportBtn.addMouseListener(hoverListener);
        } 
	}
	
	 /**
     * Applica uno stile coerente a un pulsante della sidebar.
     * Imposta font, cursore, colori e bordi.
     *
     * @param button Il pulsante JXButton da stilizzare.
     */
    private void styleSidebarButton(JXButton button)
    {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(new Color(245, 245, 245));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));  
    }
    
    public JXButton getHomeButton()
    {
        return homeBtn;
    }

    public JXButton getCoursesButton()
    {
        return coursesBtn;
    }

    public JXButton getRecipesButton()
    {
        return recipesBtn;
    }

    public JXButton getReportButton()
    {
        return reportBtn;
    }
}