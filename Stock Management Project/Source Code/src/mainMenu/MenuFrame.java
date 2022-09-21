//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Menu Frame for central navigation page for Staff
package mainMenu;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import org.jdesktop.swingx.JXImagePanel;

import customerManagement.CustomerFrame;
import inventoryManagement.InventoryFrame;
import loginAndUsers.LoginFrame;
import orderInvoiceManagement.StaffInvFrame;

/**
 * Main Menu GUI frame, central navigation page for employees
 * @author Shane Kennedy
 */
public class MenuFrame extends JFrame
{	
	private JPanel panelTop;
	private	JPanel panelLogo;
	private	JPanel panelTopRight;
	private JPanel panelTitle;
	private JPanel panelTopRightBottom;
	private JPanel panelUserTime;

	private JPanel panelBtnMenu;
	private JPanel panelBtnCont;
	
	private JLayeredPane layeredPaneMain;
	
	private JXImagePanel imageJxPanel;
	
	private JLabel labelLogo;
	private JLabel labelTitle;
	private JLabel labelUser;
	private JLabel labelDateTime;
	
    private JPopupMenu dropdownMenu;
	private JMenuItem dropCust;
    private JMenuItem dropInv;
    private JMenuItem dropInvoices;
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator2;
    
    private JButton btnMenu;
    private JButton btnLogoff;
    private JButton btnExit;
    private JButton btnInv;
    private JButton btnCustomer;
    private JButton btnInvoice;
    
    //Create reference for JFrame window
  	JFrame frame = this;
    /**
     * Constructor for Menu Frame
     * @param username - Takes a String as parameter representing the Username of Staff member logged in
     */
	public MenuFrame(String username)
	{
		super("Menu");
		//Method to build frame
		buildFrame();
        //Start timer
        timerStart();
	}
	 //Methods
    /**
    * Method to start the timer
    */
    //Starts the timer    
    public void timerStart()
    {
        Timer t = new Timer(1000, updateClockAction);
        t.start();
    }
	/**
    * Method to initialize and format  GUI components
    */
	private void buildFrame()
	{
		//Build Logo Panel(Logo)
		panelLogo = new JPanel();
		panelLogo.setBackground(new Color(68, 62, 90));
		panelLogo.setPreferredSize(new Dimension(220,170));
		panelLogo.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
			//Add Logo
			labelLogo = new JLabel();
			labelLogo.setBackground(new Color(68, 62, 90));
			labelLogo.setIcon(new ImageIcon(getClass().getResource("/Images/Logo.png")));
			labelLogo.setOpaque(true);
			panelLogo.add(labelLogo);
		
		//Build Title Panel(Title)
		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(68, 62, 90));
		panelTitle.setPreferredSize(new Dimension(760, 305));
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 35));
			//Add Title
			labelTitle = new JLabel();
			labelTitle.setFont(new Font("Segoe UI", 1, 36));
			labelTitle.setForeground(new Color(166, 76, 156));
			labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitle.setText("Ventair - Main Menu");
			panelTitle.add(labelTitle);
		
		//Build UserLabel and Time Panel(UserLabel and Time Labels), contained in panelUserTimeCont
		panelUserTime = new JPanel(new GridLayout(2,1));
		panelUserTime.setPreferredSize(new Dimension(300, 58));
		  //Add User Label
		  labelUser = new JLabel();
		  labelUser.setBackground(new Color(185, 209, 234));
		  labelUser.setFont(new Font("Segoe UI", 1, 14));
		  labelUser.setForeground(new Color(51, 51, 51));
		  labelUser.setHorizontalAlignment(SwingConstants.CENTER);
		  labelUser.setText("User:");
		  labelUser.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		  labelUser.setOpaque(true);
		  labelUser.setPreferredSize(new Dimension(300, 34));
		  panelUserTime.add(labelUser);
		  //Add Time Label
		  labelDateTime = new JLabel();
		  labelDateTime.setBackground(new Color(185, 209, 234));
		  labelDateTime.setFont(new Font("Segoe UI", 1, 14));
		  labelDateTime.setForeground(new Color(51, 51, 51));
		  labelDateTime.setHorizontalAlignment(SwingConstants.CENTER);
		  labelDateTime.setText("DateTime:");
		  labelDateTime.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		  labelDateTime.setOpaque(true);
		  labelDateTime.setPreferredSize(new Dimension(300, 34));
		  panelUserTime.add(labelDateTime);
		 
		//Build Top Right Bottom Panel(UserLabel, Time and Menu)
		panelTopRightBottom = new JPanel();
		panelTopRightBottom.setLayout(new BoxLayout(panelTopRightBottom, BoxLayout.LINE_AXIS));
		panelTopRightBottom.setBackground(new Color(215,228,242));
		panelTopRightBottom.setPreferredSize(new Dimension(760,58));
		
		//--Build Menu Buttons--
		//Create dropdownmenu object
		dropdownMenu = new JPopupMenu();
		//Logoff Button
		btnLogoff = new JButton();
		btnLogoff.setBackground(new Color(225, 225, 225));
		btnLogoff.setFont(new Font("Segoe UI", 0, 14));
		btnLogoff.setText("Logoff");
		btnLogoff.setPreferredSize(new Dimension(200, 58));
		btnLogoff.setMaximumSize(new Dimension(200, 58));
		btnLogoff.addActionListener(
			//Anonymous Inner Class
			new ActionListener() 
			{
				//Handling btnLogoff pressed action event
	            public void actionPerformed(ActionEvent evt) 
	            {
	            	 //confirm will be 0 if yes selected
	    	        int confirm = 0;
	    	        confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to log off?");
	    	        if(confirm == 0)
	    	        {   //Hide Current frame
	    	            frame.setVisible(false);
	    	            //Open and set Login window to visible
	    	            LoginFrame loginFrame = new LoginFrame();
	    	            loginFrame.setVisible(true);
	    	            //Close current frame
	    	            frame.dispose();
	    	        }
	            }
			}
			);//End Action Listener
		//Exit Button
		btnExit = new JButton();
		btnExit.setBackground(new Color(225, 225, 225));
		btnExit.setFont(new Font("Segoe UI", 0, 14));
		btnExit.setText("Exit");
		btnExit.setPreferredSize(new Dimension(200, 58));
		btnExit.setMaximumSize(new Dimension(200, 58));
		btnExit.addActionListener(
				//Anonymous Inner Class
				new ActionListener() 
				{
					//Handling btnExit pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
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
		//Menu Button
		btnMenu = new JButton();
		btnMenu.setBackground(new Color(225, 225, 225));
		btnMenu.setFont(new Font("Segoe UI", 0, 14));
		btnMenu.setText("Menu");
		btnMenu.setPreferredSize(new Dimension(200, 58));
		btnMenu.setMaximumSize(new Dimension(200, 58));
		btnMenu.addActionListener(
				//Anonymous Inner Class
				new ActionListener() 
				{
					//Handling btnMenu pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	//Menu Pressed show dropdown menu
		    	        dropdownMenu.show(btnMenu,0,55);
		            }
				}
				);//End Action Listener
		
		//Build Dropdown Menu
		dropInv = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        dropCust = new JMenuItem();
        jSeparator2 = new JPopupMenu.Separator();
        dropInvoices = new JMenuItem();
        
        dropdownMenu.setComponentPopupMenu(dropdownMenu);
        dropdownMenu.setInvoker(btnMenu);
        dropdownMenu.setPreferredSize(new Dimension(200, 98));
        
        dropInv.setText("Inventory");
        dropInv.addActionListener(
        		//Anonymous Inner Class
				new ActionListener() 
				{	
					//Handling dropInv menu item pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	//Inventory Link from Dropdown
		    	        String username = labelUser.getText().substring(6);
		    	        InventoryFrame frm = new InventoryFrame(username);
		    	        frm.setVisible(true);
		    	        frame.dispose();
		            }
				}
				);//End Action Listener
     
        dropdownMenu.add(dropInv);
        dropdownMenu.add(jSeparator1);

        dropCust.setText("Customers");
        dropCust.addActionListener(
        		//Anonymous Inner Class
				new ActionListener() 
				{
					//Handling dropCust menu item pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	// Main Customer Link
		    	        String username = labelUser.getText().substring(6);
		    	        CustomerFrame frm = new CustomerFrame(username);
		    	        frm.setVisible(true);
		    	        frame.dispose();
		            }
				}
				);//End Action Listener
        
        
        dropdownMenu.add(dropCust);
        dropdownMenu.add(jSeparator2);

        dropInvoices.setText("Sales Orders/Invoices");
        dropInvoices.addActionListener(
        		//Anonymous Inner Class
				new ActionListener() 
				{
					//Handling dropInvoices menu item pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	// Main Sales Invoices Link
		    	        String username = labelUser.getText().substring(6);
		    	        CustomerFrame frm = new CustomerFrame(username);
		    	        frm.setVisible(true);
		    	        frame.dispose();
		            }
				}
				);//End Action Listener
        dropdownMenu.add(dropInvoices);
 
		//Add Labels and buttons
        panelTopRightBottom.add(Box.createRigidArea(new Dimension(10,0)));
		panelTopRightBottom.add(panelUserTime);
		panelTopRightBottom.add(Box.createHorizontalGlue());
		panelTopRightBottom.add(btnMenu);
		panelTopRightBottom.add(Box.createRigidArea(new Dimension(10,0)));
		panelTopRightBottom.add(btnLogoff);
		panelTopRightBottom.add(Box.createRigidArea(new Dimension(10,0)));
		panelTopRightBottom.add(btnExit);
		panelTopRightBottom.add(Box.createRigidArea(new Dimension(10,0)));

		//Build PanelTopRight (Title and Dropdown Menu)
		panelTopRight = new JPanel();
		panelTopRight.setBackground(new Color(68, 62, 90));
		panelTopRight.setPreferredSize(new Dimension(760,170));
		panelTopRight.setLayout(new BorderLayout());
		panelTopRight.add(panelTitle, BorderLayout.CENTER);
		panelTopRight.add(panelTopRightBottom, BorderLayout.SOUTH);
		
		
		//Build Top Panel (Logo, Title and Menu)
		panelTop = new JPanel();
		panelTop.setBackground(new Color(68, 62, 90));
		panelTop.setPreferredSize(new Dimension(980,170));
		panelTop.setLayout(new BorderLayout());
		//Add Component Panels
		panelTop.add(panelLogo, BorderLayout.WEST);
		panelTop.add(panelTopRight, BorderLayout.CENTER);
		
		//Build button container(container for buttons)
        panelBtnCont = new JPanel(new GridLayout(3,1,0,45));
        panelBtnCont.setPreferredSize(new Dimension(350,300));
        panelBtnCont.setBackground(new Color(102, 120, 139));
	        //Build buttons
			//Inventory button link
	        btnInv = new JButton();
	        btnInv.setPreferredSize(new Dimension(350,55));
	        btnInv.setFont(new Font("Segoe UI", 0, 14));
	        btnInv.setIcon(new ImageIcon(getClass().getResource("/Images/InvIcon.png")));
	        btnInv.setText("Inventory");
	        btnInv.setHorizontalAlignment(SwingConstants.LEFT);
	        btnInv.setIconTextGap(50);
	        btnInv.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnInv menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Main Inventory Link
			                String username = labelUser.getText().substring(6);
			                InventoryFrame frm = new InventoryFrame(username);
			                frm.setVisible(true);
			                frame.dispose();
			            }
					}
					);//End Action Listener
	        //Sales Invoice button link
	        btnInvoice = new JButton();
	        btnInvoice.setPreferredSize(new Dimension(350,55));        
	        btnInvoice.setFont(new Font("Segoe UI", 0, 14));
	        btnInvoice.setIcon(new ImageIcon(getClass().getResource("/Images/InvoiceIcon.png")));
	        btnInvoice.setText("Customer Orders/Invoices");
	        btnInvoice.setHorizontalAlignment(SwingConstants.LEFT);
	        btnInvoice.setIconTextGap(50);
	        btnInvoice.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnInvoice menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	 // Staff Invoice managemenr menu link
			                String username = labelUser.getText().substring(6);
			                StaffInvFrame frm = new StaffInvFrame(username);
			                frm.setVisible(true);
			                frame.dispose();
			            }
					}
					);//End Action Listener
	        //Customers button link
	        btnCustomer = new JButton();
	        btnCustomer.setPreferredSize(new Dimension(350,55));    
	        btnCustomer.setFont(new Font("Segoe UI", 0, 14));
	        btnCustomer.setIcon(new ImageIcon(getClass().getResource("/Images/CustIcon.png")));
	        btnCustomer.setText("Customers");
	        btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
	        btnCustomer.setIconTextGap(50);
	        btnCustomer.addActionListener(
	            		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnCustomer menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Main Customer Link
	    		                String username = labelUser.getText().substring(6);
	    		                CustomerFrame frm = new CustomerFrame(username);
	    		                frm.setVisible(true);
	    		                frame.dispose();
	    		            }
	    				}
	    				);//End Action Listener
        //Add buttons to button container panel
        panelBtnCont.add(btnInv);
        panelBtnCont.add(btnInvoice);
        panelBtnCont.add(btnCustomer);
        
		//Build button panel
		panelBtnMenu = new JPanel(new FlowLayout(FlowLayout.CENTER,0,40));
		panelBtnMenu.setBackground(new Color(102, 120, 139));
		panelBtnMenu.setPreferredSize(new Dimension(475,375));
		panelBtnMenu.setMaximumSize(new Dimension(475,375));
		panelBtnMenu.add(panelBtnCont);
		
		//--Build Image panel--
		//Set image for Jximage panel
		imageJxPanel = new JXImagePanel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aircon.jpg"));
        Image img = icon.getImage();
        imageJxPanel.setImage(img) ;  
        imageJxPanel.setStyle(JXImagePanel.Style.SCALED);
        imageJxPanel.setLayout(new BoxLayout(imageJxPanel,BoxLayout.LINE_AXIS));//(new FlowLayout(FlowLayout.TRAILING, 160,65))
        imageJxPanel.setPreferredSize(new Dimension(1200,510));
        imageJxPanel.add(Box.createHorizontalGlue());
        imageJxPanel.add(panelBtnMenu);
        imageJxPanel.add(Box.createRigidArea(new Dimension(160,0)));
				
		//Build main layered panel(Hold main panel)
		layeredPaneMain = new JLayeredPane();
		layeredPaneMain.setLayout(new BorderLayout());
		layeredPaneMain.setPreferredSize(new Dimension(1200,510));
		layeredPaneMain.add(imageJxPanel, BorderLayout.CENTER);
		//Set layers
		layeredPaneMain.setLayer(imageJxPanel, 2);
		layeredPaneMain.setLayer(panelBtnMenu, 1);
		layeredPaneMain.setLayer(panelBtnCont, 0);
		
		//Build frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelTop, BorderLayout.NORTH);
		getContentPane().add(layeredPaneMain, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventair - Main Menu");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
		setSize(1200, 680);
		setMinimumSize(new Dimension(1200, 680));
		pack();
	}
	//Listeners and Events
    /**
     * Action Listener to update timer
     */
    //Action Listener to update clock, fires once a second and updates Label
    ActionListener updateClockAction = new ActionListener()
    {
    public void actionPerformed(ActionEvent e) 
        {
          // Assumes clock is a JLabel
            labelDateTime.setText("DateTime: "+new Date().toString()); 
        } 
    }; 
}//end class
