//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Login Frame for logging staff and customer in
package loginAndUsers;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import org.jdesktop.swingx.JXImagePanel;

import customerManagement.Customer;
import customerOrderPortal.CustPortalFrame;
import mainMenu.MenuFrame;
import validation.JTextFieldEmpty;
import validation.Validator;

/**
 * Login GUI Frame for allowing Staff and Customer to enter login details and validate credentials
 * @author Shane Kennedy
 */
public class LoginFrame extends JFrame 
{	
	private JButton btnClear;
    private JButton btnClearS;
    private JButton btnExt;
    private JButton btnExtS;
    private JButton btnLogin;
    private JButton btnLoginS;
    private JButton btnReg;
    private JXImagePanel imageJxPanel;
    private JLabel lblCustId;
    private JLabel lblCustReg;
    private JLabel lblCustUsername;
    private JPanel panelStaffLogCont;
    private JLabel lblStaffId;
    private JLabel labelText;
    private JLabel lblCustPass;
    private JLabel labelLogo;
    private JLabel lblStaffPass;
    private JLabel lblStfUsername;
    private JPanel panelCustLogin;
    private JPanel panelCustLogCont;
    private JPanel panelStaffLogin;
    private JPanel panelTop;
    private JPanel panelCustReg;
    private JPasswordField pfldCustPass;
    private JPasswordField pfldStaffPass;
    private JTabbedPane tabbedPnl;
    private JTextField tfdCustUser;
    private JTextField tfdStaffUser;
     
    //Create reference for JFrame window
   	JFrame frame = this;
    /**
     * Constructor for Login Frame
     */
    public LoginFrame() 
    {
    	//Method to build frame
        buildFrame();
    }
    /**
     * Method to initialize and format  GUI components
     */
    private void buildFrame() 
    {    	
    	
	    //Build title panel
	    panelTop = new JPanel(); 
	    panelTop.setPreferredSize(new Dimension(870,85));
	    panelTop.setBackground(new Color(68, 62, 90));
	    panelTop.setLayout(new BoxLayout(panelTop,BoxLayout.LINE_AXIS));
	    	//Build image and title labels
		    labelLogo = new JLabel();
		    labelLogo.setPreferredSize(new Dimension(85,85));
		    labelLogo.setMaximumSize(new Dimension(85,85));
		    labelLogo.setIcon(new ImageIcon(getClass().getResource("/Images/logosmall.png")));
	        labelLogo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

	        labelText = new JLabel();
	        labelText.setPreferredSize(new Dimension(275,35));
	        labelText.setMaximumSize(new Dimension(275,35));
	        labelText.setFont(new Font("Segoe UI", 1, 18));
	        labelText.setForeground(new Color(166, 76, 156));
	        labelText.setText("VENTAIR - LOGIN");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(25,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(260,0)));
	    panelTop.add(labelText);
	    
	    //Build staff login panel container(panel to contain textfields and buttons)
	    panelStaffLogCont = new JPanel();
	    panelStaffLogCont.setPreferredSize(new Dimension(475,180));
	    panelStaffLogCont.setMaximumSize(new Dimension(475,180));
	    panelStaffLogCont.setLayout(null);
	    panelStaffLogCont.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	    panelStaffLogCont.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	//Build textfields and labels
		    lblStfUsername = new JLabel();
		    lblStfUsername.setBackground(new Color(166, 76, 156));
		    lblStfUsername.setFont(new Font("Segoe UI", 0, 18));
		    lblStfUsername.setForeground(new Color(255, 255, 255));
		    lblStfUsername.setHorizontalAlignment(SwingConstants.CENTER);
		    lblStfUsername.setLabelFor(tfdStaffUser);
		    lblStfUsername.setText("Staff Username:");
		    lblStfUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		    lblStfUsername.setInheritsPopupMenu(false);
		    lblStfUsername.setOpaque(true);
		    lblStfUsername.setBounds(60,30,180,30);
		    
		    tfdStaffUser = new JTextField();
		    tfdStaffUser.setToolTipText("Staff Username Field");
		    tfdStaffUser.setBounds(250,30,180,30);
		    
			lblStaffPass = new JLabel();
		    lblStaffPass.setBackground(new Color(166, 76, 156));
		    lblStaffPass.setFont(new Font("Segoe UI", 0, 18)); //
		    lblStaffPass.setForeground(new Color(255, 255, 255));
		    lblStaffPass.setHorizontalAlignment(SwingConstants.CENTER);
		    lblStaffPass.setLabelFor(pfldStaffPass);
		    lblStaffPass.setText("Staff Password:");
		    lblStaffPass.setToolTipText("");
		    lblStaffPass.setHorizontalTextPosition(SwingConstants.CENTER);
		    lblStaffPass.setInheritsPopupMenu(false);
		    lblStaffPass.setOpaque(true);
		    lblStaffPass.setBounds(60,80,180,30);
		    
			pfldStaffPass = new JPasswordField();
		    pfldStaffPass.setToolTipText("Staff Password Field");
		    pfldStaffPass.setBounds(250,80,180,30);
		    pfldStaffPass.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling pfldStaffPass enter pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Enter button pressed in Password Field, trigger method
			                btnLoginSActionEvent(evt);
			            }
					}
					);//End Action Listener
		    //Build Buttons
		    //Staff Login Button
			btnLoginS = new JButton();
		    btnLoginS.setText("Login");
		    btnLoginS.setBounds(140,135,100,30);
		    btnLoginS.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnLoginS enter pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Login button pressed, trigger method
			                btnLoginSActionEvent(evt);
			            }
					}
					);//End Action Listener
		    //Staff Clear Button
			btnClearS = new JButton();
			btnClearS.setText("Clear");
			btnClearS.setBounds(245,135,100,30);
			btnClearS.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnClearS pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Clear button event
			                //Set both text boxes clear
			                tfdStaffUser.setText("");
			                pfldStaffPass.setText("");
			            }
					}
					);//End Action Listener
			//Staff Exit Button
			btnExtS = new JButton();
			btnExtS.setText("Exit");
			btnExtS.setBounds(350,135,100,30);
			btnExtS.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnExtS pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	//Exit btn Event                                 
			                //confirm will be 0 if yes selected
			                int confirm = 0;
			                confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?");
			                if(confirm == 0)
			                {   //Trigger Window Closing Event
			                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));;
			                }
			            }
					}
					);//End Action Listener
		//Add components
		panelStaffLogCont.add(lblStfUsername);	
		panelStaffLogCont.add(lblStaffPass);
		panelStaffLogCont.add(tfdStaffUser);
		panelStaffLogCont.add(pfldStaffPass);
		panelStaffLogCont.add(btnLoginS);
		panelStaffLogCont.add(btnClearS);
		panelStaffLogCont.add(btnExtS);
		
		//Build staff login panel
		panelStaffLogin = new JPanel();
		panelStaffLogin.setLayout(new BoxLayout(panelStaffLogin, BoxLayout.PAGE_AXIS));
		panelStaffLogin.setBackground(new Color(102, 120, 139));
	    	//Build label text
			lblStaffId = new JLabel();
		    lblStaffId.setBackground(new Color(166, 76, 156));
		    lblStaffId.setFont(new Font("Segoe UI", 0, 18));
		    lblStaffId.setForeground(new Color(255, 255, 255));
		    lblStaffId.setHorizontalAlignment(SwingConstants.CENTER);
		    lblStaffId.setText("Please Enter your Staff Login Details:");
		    lblStaffId.setHorizontalTextPosition(SwingConstants.CENTER);
		    lblStaffId.setInheritsPopupMenu(false);
		    lblStaffId.setOpaque(true);
		    lblStaffId.setPreferredSize(new Dimension(340,30));
		    lblStaffId.setMaximumSize(new Dimension(340,30));
		    lblStaffId.setAlignmentX(Component.CENTER_ALIGNMENT);
	    //Add components
		panelStaffLogin.add(Box.createRigidArea(new Dimension(0,35)));
		panelStaffLogin.add(lblStaffId);
		panelStaffLogin.add(Box.createRigidArea(new Dimension(0,35)));
		panelStaffLogin.add(panelStaffLogCont);
		
		//Build customer registration panel
		panelCustReg = new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
		panelCustReg.setPreferredSize(new Dimension(405,25));
		panelCustReg.setBackground(new Color(102, 120, 139));
			//Build text label		
			lblCustReg = new JLabel();
		    lblCustReg.setBackground(new Color(166, 76, 156));
		    lblCustReg.setFont(new Font("Segoe UI", 0, 14));
		    lblCustReg.setForeground(new Color(255, 255, 255));
		    lblCustReg.setHorizontalAlignment(SwingConstants.CENTER);
		    lblCustReg.setText("If not a registered Customer please click Register to set up an account:");
		    lblCustReg.setHorizontalTextPosition(SwingConstants.CENTER);
		    lblCustReg.setInheritsPopupMenu(false);
		    lblCustReg.setOpaque(true);
		    lblCustReg.setPreferredSize(new Dimension(500,25));
		    //Build button
			btnReg = new JButton();
		    btnReg.setText("Register");
		    btnReg.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnReg enter pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Register Btn Pressed Event
			                RegCustomerFrame regFrame = new RegCustomerFrame();
			                regFrame.setVisible(true);
			            }
					}
					);//End Action Listener
		//Add components
		panelCustReg.add(lblCustReg);
		panelCustReg.add(btnReg);		
		    
	    //Build customer login panel container(panel to contain textfields and buttons)
	    panelCustLogCont = new JPanel();
	    panelCustLogCont.setPreferredSize(new Dimension(475,180));
	    panelCustLogCont.setMaximumSize(new Dimension(475,180));
	    panelCustLogCont.setLayout(null);
	    panelCustLogCont.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	    panelCustLogCont.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	//Build textfields and labels
	    	lblCustUsername = new JLabel();
	    	lblCustUsername.setBackground(new Color(166, 76, 156));
	    	lblCustUsername.setFont(new Font("Segoe UI", 0, 18));
	    	lblCustUsername.setForeground(new Color(255, 255, 255));
	    	lblCustUsername.setHorizontalAlignment(SwingConstants.CENTER);
	    	lblCustUsername.setLabelFor(tfdCustUser);
	    	lblCustUsername.setText("Customer Username:");
		    lblCustUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		    lblCustUsername.setInheritsPopupMenu(false);
		    lblCustUsername.setOpaque(true);
		    lblCustUsername.setBounds(40,30,200,30);
		    
		    tfdCustUser = new JTextField();
		    tfdCustUser.setToolTipText("Customer Username Field");
		    tfdCustUser.setBounds(260,30,180,30);
		    
			lblCustPass = new JLabel();
			lblCustPass.setBackground(new Color(166, 76, 156));
			lblCustPass.setFont(new Font("Segoe UI", 0, 18)); //
			lblCustPass.setForeground(new Color(255, 255, 255));
			lblCustPass.setHorizontalAlignment(SwingConstants.CENTER);
			lblCustPass.setLabelFor(pfldStaffPass);
			lblCustPass.setText("Customer Password:");
			lblCustPass.setToolTipText("");
			lblCustPass.setHorizontalTextPosition(SwingConstants.CENTER);
			lblCustPass.setInheritsPopupMenu(false);
			lblCustPass.setOpaque(true);
			lblCustPass.setBounds(40,80,200,30);
		    
			pfldCustPass = new JPasswordField();
			pfldCustPass.setToolTipText("Customer Password Field");
			pfldCustPass.setBounds(260,80,180,30);
			pfldCustPass.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling pfldCustPass enter pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Enter button pressed in Password Field, trigger method
			                btnLoginActionEvent(evt);
			            }
					}
					);//End Action Listener
		    //Build Buttons
		    //Customer Login Button
			btnLogin = new JButton();
			btnLogin.setText("Login");
			btnLogin.setBounds(140,135,100,30);
			btnLogin.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnLogin pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	//Login button pressed, trigger method
			                btnLoginActionEvent(evt);
			            }
					}
					);//End Action Listener
		    //Customer Clear Button
			btnClear = new JButton();
			btnClear.setText("Clear");
			btnClear.setBounds(245,135,100,30);
			btnClear.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnClear pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Clear button event
			                //Set both text boxes clear
			                pfldCustPass.setText("");
			                tfdCustUser.setText("");
			            }
					}
					);//End Action Listener
			//Staff Exit Button
			btnExt = new JButton();
			btnExt.setText("Exit");
			btnExt.setBounds(350,135,100,30);
			btnExt.addActionListener(
		    		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnExt pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	//Exit btn Event                                 
			                //confirm will be 0 if yes selected
			                int confirm = 0;
			                confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?");
			                if(confirm == 0)
			                {   //Trigger Window Closing Event
			                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));;
			                }
			            }
					}
					);//End Action Listener
		//Add components
		panelCustLogCont.add(lblCustUsername);	
		panelCustLogCont.add(lblCustPass);
		panelCustLogCont.add(tfdCustUser);
		panelCustLogCont.add(pfldCustPass);
		panelCustLogCont.add(btnLogin);
		panelCustLogCont.add(btnClear);
		panelCustLogCont.add(btnExt);
		
	    //Build Customer login panel
		panelCustLogin = new JPanel();
		panelCustLogin.setLayout(new BoxLayout(panelCustLogin, BoxLayout.PAGE_AXIS));
		panelCustLogin.setBackground(new Color(102, 120, 139));
	    	//Build label text
			lblCustId = new JLabel();
			lblCustId.setBackground(new Color(166, 76, 156));
			lblCustId.setFont(new Font("Segoe UI", 0, 18));
			lblCustId.setForeground(new Color(255, 255, 255));
			lblCustId.setHorizontalAlignment(SwingConstants.CENTER);
			lblCustId.setText("Please Enter your Customer Login Details:");
			lblCustId.setHorizontalTextPosition(SwingConstants.CENTER);
			lblCustId.setInheritsPopupMenu(false);
			lblCustId.setOpaque(true);
			lblCustId.setPreferredSize(new Dimension(380,30));
			lblCustId.setMaximumSize(new Dimension(380,30));
			lblCustId.setAlignmentX(Component.CENTER_ALIGNMENT);
	    //Add components
		panelCustLogin.add(Box.createRigidArea(new Dimension(0,35)));
		panelCustLogin.add(lblCustId);
		panelCustLogin.add(Box.createRigidArea(new Dimension(0,35)));
		panelCustLogin.add(panelCustLogCont);
		panelCustLogin.add(Box.createRigidArea(new Dimension(0,15)));
		panelCustLogin.add(panelCustReg);
		
	    //Build tabbed panel(holds staff/customer login details panels)
		tabbedPnl = new JTabbedPane();
	    tabbedPnl.setBackground(new Color(255, 255, 255));
	    tabbedPnl.setFont(new Font("Segoe UI", 0, 16));
	    tabbedPnl.setPreferredSize(new Dimension(680,365));
	    //Add tabs
	    tabbedPnl.addTab("Staff Login", panelStaffLogin);
	    tabbedPnl.addTab("Customer Login", panelCustLogin);
	    
	    //Build Image Panel
	    imageJxPanel = new JXImagePanel();
	    imageJxPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,85));
	    //Set Image icon to be the background for Jxpanel background
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aircon.jpg"));
        Image img = icon.getImage();
        imageJxPanel.setImage(img) ;  
        imageJxPanel.setStyle(JXImagePanel.Style.SCALED);
        
        //Add Login Detail Panel
        imageJxPanel.add(tabbedPnl);
           
	    //Build frame
  		getContentPane().setLayout(new BorderLayout());
  		getContentPane().add(panelTop, BorderLayout.NORTH);
  		getContentPane().add(imageJxPanel, BorderLayout.CENTER);
  		
  		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  		setTitle("Ventair - Login");
        setIconImage(new ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(795, 615);
  		setResizable(false);
  		pack();
    }//end buildFrame method
 
    /**
     * Method for Button/Enter Pressed event for Login Button of Customer tab, checks if password and username are correct and opens Menu Frame if valid
     * @param evt -Takes an Action event object as a parameter
     */
    private void btnLoginActionEvent(ActionEvent evt) 
    {
        // Customer Login Btn Event
        // Login Btn Event
        //Check for valid input
        Validator val = new Validator();
        try
        {   //Will check components on panel, if JTextfield will check that they are not empty
            for(Component c : panelCustLogCont.getComponents())
            {
                if(c instanceof JTextField)
                {
                    val.JTextFieldEmpty((JTextField)c);
                }
            }
            //Retrieve password from Password Field
            String pass = String.valueOf(pfldCustPass.getPassword());
            //Create staff object
            Customer cust = new Customer(tfdCustUser.getText(),pass);
            String username = cust.getUserUsername();
            //Validate login details
            //If validate returns true open Menu
            if(cust.validateLogin())
            {
                CustPortalFrame frame = new CustPortalFrame(username);
                frame.setVisible(true);
                this.dispose();
            }
            //If validate false, give popup window warning
            else
            {
                JOptionPane.showMessageDialog(this, "Username or Password incorrect, check your details and try again", "Validate Details Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(JTextFieldEmpty e)
        {
            //If exception occurs JTextFieldEmpty will handle it
        }
    }
    /**
     * Method for Button/Enter Pressed event for Login Button of Staff tab, checks if password and username are correct and opens Menu Frame if valid
     * @param evt -Takes an Action event object as a parameter
     */
    private void btnLoginSActionEvent(ActionEvent evt) 
    {  
    	// Staff Login Btn Event
        //Check for valid input
        Validator val = new Validator();
        try
        {   //Will check components on panel, if JTextfield will check that they are not empty
            for(Component c : panelStaffLogCont.getComponents())
            {
                if(c instanceof JTextField)
                {
                    val.JTextFieldEmpty((JTextField)c);
                }
            }
        
            //Retrieve password from Password Field
            String pass = String.valueOf(pfldStaffPass.getPassword());
            //Create staff object
            Staff stf = new Staff(tfdStaffUser.getText(),pass);
            String username = stf.getUserUsername();
            //Validate login details
            //If validate returns true open Menu
            if(stf.validateLogin())
            {
                MenuFrame frame = new MenuFrame(username);
                frame.hide();
                frame.setVisible(true);
                frame.dispose();
            }
            //If validate false, give popup window warning
            else
            {
                JOptionPane.showMessageDialog(frame, "Username or Password incorrect, check your details and try again", "Validate Details Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(JTextFieldEmpty e)
        {
            //If exception occurs JTextFieldEmpty will handle it
        }
    }
}// end class
