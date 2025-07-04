package UninaFoodLab.Boundary;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXFrame;

public class CoursesFrame extends JXFrame
{
	private static final long serialVersionUID = 1L;
	private CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
			new LineBorder(Color.LIGHT_GRAY, 1), 
		  	new EmptyBorder(0, 6, 0, 0));
	private CompoundBorder errorBorder = BorderFactory.createCompoundBorder(
        	new LineBorder(Color.RED, 1),
        	new EmptyBorder(0, 6, 0, 0));
	private ImageIcon windowLogo;
	private ImageIcon paneLogo;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CoursesFrame frame = new CoursesFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public CoursesFrame()
	{
		setTitle("UninaFoodLab - I miei corsi");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setExtendedState(JXFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
		setResizable(true);
	}

}
