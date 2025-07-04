package UninaFoodLab.Boundary;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXFrame;

public class ProfileFrame extends JXFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ProfileFrame()
	{
		setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
