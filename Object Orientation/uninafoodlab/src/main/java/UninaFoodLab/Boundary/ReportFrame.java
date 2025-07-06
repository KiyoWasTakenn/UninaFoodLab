package UninaFoodLab.Boundary;

import javax.swing.*;

import org.jdesktop.swingx.*;


public class ReportFrame extends JXFrame 
{

	private static final long serialVersionUID = 1L;
	
    public ReportFrame() 
    {
       initComponents();
       initListeners();
       
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReportFrame().setVisible(true);
        });
    }
    
    
    
    private void initComponents()
    {
    	
    }
    
    private void initListeners()
    {
    	
    }
    
    
    public void resetView()
    {
    	
    }
}