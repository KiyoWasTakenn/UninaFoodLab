package UninaFoodLab.Boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CourseCardFrame extends JFrame
{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CourseCardFrame frame = new CourseCardFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}


	
	
	public CourseCardFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
	}

}
