package UninaFoodLab.Boundary;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTextField;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import com.formdev.flatlaf.FlatLightLaf;

import UninaFoodLab.Controller.Controller;
import net.miginfocom.swing.MigLayout;

public class CoursesFrame extends JXFrame {

    private static final long serialVersionUID = 1L;

    private final FontIcon userIcon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_CIRCLE, 20, new Color(80, 80, 80));
    private final FontIcon hamburgerIcon = FontIcon.of(MaterialDesign.MDI_MENU, 26, Color.DARK_GRAY);
    private final FontIcon filterIcon = FontIcon.of(MaterialDesign.MDI_FILTER, 18, Color.DARK_GRAY);
    private final FontIcon profileIcon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_CIRCLE, 26, Color.DARK_GRAY);
    private final FontIcon profileIconMenu = FontIcon.of(MaterialDesign.MDI_ACCOUNT, 18, new Color(60, 60, 60));
    private final FontIcon logoutIcon = FontIcon.of(MaterialDesign.MDI_LOGOUT, 18, new Color(60, 60, 60));
    private ImageIcon windowLogo;
    private ImageIcon paneLogo;
    private JXLabel logoLabel;
    private final CompoundBorder defaultBorder = BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(10, 12, 10, 12)
    );
    private JXPanel rootPanel = new JXPanel(new MigLayout("fill, insets 0", "[grow]", "[60!][grow]"));
    private JXPanel header = new JXPanel(new MigLayout(
            "insets 5 15 5 15",
            "[35!]10[10!]10[80!]10[20!]0[80!]0[grow, 50::1400]0[80!]10[grow, 10::89][35!][20!]",
            "[60!]"
    ));
    private JXButton hamburgerBtn = new JXButton();
    private JXButton filterBtn = new JXButton("Filtri");
    private JXTextField searchField = new JXTextField();
    private JXButton searchBtn = new JXButton("Cerca");
    private JXButton profileBtn = new JXButton();
    private JXPanel dropdownPanel = new JXPanel(new MigLayout("insets 10, wrap 1", "[fill, grow]"));
    private JXLabel userLabel = new JXLabel("sample", userIcon, JXLabel.LEFT);
    private JXPanel separator = new JXPanel();
    private JXButton profileItemBtn = new JXButton("  Profilo", profileIconMenu);
    private JXButton logoutItemBtn = new JXButton("  Logout", logoutIcon);
    
    


   
    
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                CoursesFrame frame = new CoursesFrame();
                frame.setVisible(true);
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

    public CoursesFrame()
    {
        setTitle("UninaFoodLab - I miei corsi");
        setMinimumSize(new Dimension(700, 600));
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setExtendedState(JXFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
        setResizable(true);

        windowLogo = new ImageIcon(getClass().getResource("/logo_finestra.png"));
        setIconImage(windowLogo.getImage());

        rootPanel.setBackground(new Color(0xFAFAFA));
        setContentPane(rootPanel);

        header.setBackground(Color.WHITE);
        header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0xDDDDDD)));
        rootPanel.add(header, "dock north");

        hamburgerBtn.setIcon(hamburgerIcon);
        hamburgerBtn.setContentAreaFilled(false);
        hamburgerBtn.setBorder(BorderFactory.createEmptyBorder());
        hamburgerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(hamburgerBtn, "cell 0 0, w 35!, h 35!, shrink 0");

        paneLogo = new ImageIcon(getClass().getResource("/logo_schermata.png"));
        logoLabel = new JXLabel(new ImageIcon(paneLogo.getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));
        logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoLabel.setToolTipText("Torna alla homepage");
        header.add(logoLabel, "cell 2 0, w 80!, h 60!, gapleft 0, shrink 0");

        filterBtn.setIcon(filterIcon);
        filterBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        filterBtn.setFocusPainted(false);
        filterBtn.setContentAreaFilled(true);
        filterBtn.setBackground(new Color(245, 245, 245));
        filterBtn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        filterBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(filterBtn, "cell 4 0, h 35!, w 80!, shrink 0");
      
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setPrompt("Cerca per nome corso...");
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setBackground(Color.WHITE);
        searchField.setOpaque(true);
        header.add(searchField, "cell 5 0, h 35!, growx, wmin 100, wmax 1400");

        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchBtn.setFocusPainted(false);
        searchBtn.setBackground(new Color(225, 126, 47));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setOpaque(true);
        searchBtn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(225, 126, 47), 1, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        searchBtn.setPreferredSize(new Dimension(80, 35));
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(searchBtn, "cell 6 0, h 35!, w 80!, shrink 0");

        profileBtn.setIcon(profileIcon);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setBorder(BorderFactory.createEmptyBorder());
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        header.add(profileBtn, "cell 8 0, w 35!, h 35!, align right");
   
        
        
        dropdownPanel.setBackground(Color.WHITE);
        dropdownPanel.setBorder(defaultBorder);
        dropdownPanel.setOpaque(true);
        dropdownPanel.setVisible(false);

        userLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        userLabel.setForeground(new Color(40, 40, 40));
        userLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        dropdownPanel.add(userLabel);

        separator.setBackground(new Color(200, 200, 200));
        separator.setPreferredSize(new Dimension(1, 2));
        separator.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        dropdownPanel.add(separator, "h 2!, gapbottom 8");

        styleDropdownButton(profileItemBtn);
        dropdownPanel.add(profileItemBtn);

        styleDropdownButton(logoutItemBtn);
        dropdownPanel.add(logoutItemBtn);

        getLayeredPane().add(dropdownPanel, JLayeredPane.POPUP_LAYER);


        profileBtn.addActionListener(new ActionListener()
							         {
							        	@Override 
										public void actionPerformed(ActionEvent e)
										{	
							        		dropdownPanel.setSize(dropdownPanel.getPreferredSize());
							                int x = profileBtn.getLocationOnScreen().x - getLocationOnScreen().x + profileBtn.getWidth() - dropdownPanel.getWidth();
							                int y = profileBtn.getLocationOnScreen().y - getLocationOnScreen().y + profileBtn.getHeight();
							                dropdownPanel.setLocation(x + 33, y + 10);
							                dropdownPanel.setVisible(!dropdownPanel.isVisible());
										}
							         }
        							);
        
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof MouseEvent me && me.getID() == MouseEvent.MOUSE_PRESSED) 
            {
                if (!dropdownPanel.isVisible()) return;
                
                Point pt = me.getLocationOnScreen();
                Point ptInDropdown = new Point(pt);
                
                SwingUtilities.convertPointFromScreen(ptInDropdown, dropdownPanel);
                Point ptInProfileBtn = new Point(pt);
                
                SwingUtilities.convertPointFromScreen(ptInProfileBtn, profileBtn);
                
                if (!dropdownPanel.contains(ptInDropdown) && !profileBtn.contains(ptInProfileBtn))
                    dropdownPanel.setVisible(false);
            }
        }, AWTEvent.MOUSE_EVENT_MASK);

        addComponentListener(new ComponentAdapter()
        					 {
        						@Override
        						public void componentResized(ComponentEvent e)
        						{
        							updateDropDownPosition();
        						}
        						@Override
        						public void componentMoved(ComponentEvent e)
        						{
        							updateDropDownPosition();
        						}
        					 });
        

    }

    private void updateDropDownPosition()
    {
    	if(dropdownPanel.isVisible())
    	{
    		int x = profileBtn.getLocationOnScreen().x - getLocationOnScreen().x + profileBtn.getWidth() - dropdownPanel.getWidth();
            int y = profileBtn.getLocationOnScreen().y - getLocationOnScreen().y + profileBtn.getHeight();
            dropdownPanel.setLocation(x + 33, y + 10);
    	}
    }
    
    private void styleDropdownButton(JXButton button) 
    {
        button.setHorizontalAlignment(JXButton.LEFT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setContentAreaFilled(true);
        button.setBackground(new Color(245, 245, 245));
        button.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setForeground(new Color(60, 60, 60));

        button.addMouseListener(new MouseAdapter() 
        						{
						            public void mouseEntered(MouseEvent e) 
						            {
						                button.setBackground(new Color(230, 230, 230));
						            }
						
						            public void mouseExited(MouseEvent e)
						            {
						                button.setBackground(new Color(245, 245, 245));
						            }
						        });
    }

}
