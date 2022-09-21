//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Customer frame class to display and add/edit Customer records
package customerManagement;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXImagePanel;

import inventoryManagement.InventoryFrame;
import customGuiComponents.AutoColumnWidthTable;
import databaseConnection.ConnectDB;
import loginAndUsers.LoginFrame;
import orderInvoiceManagement.StaffInvFrame;
import mainMenu.MenuFrame;

/**
 * Customer GUI frame allowing employees to view Customer records in database
 * @author Shane Kennedy
 */
public class CustomerFrame extends JFrame
{	
	//Components
	private JPanel panelTop;
	private	JPanel panelMain;
	private	JPanel panelLogo;
	private	JPanel panelTopRight;
	private JPanel panelTitle;
	private JPanel panelTopRightBottom;
	private JPanel panelUserTime;
	private JPanel panelSideMenu;
	private JPanel panelBottomRight;
	private JPanel panelTable;
	private JPanel panelBottomRightBot;
	
	private JXImagePanel imageJxPanel;
	
	private JLabel labelLogo;
	private JLabel labelTitle;
	private JLabel labelUser;
	private JLabel labelDateTime;
	private JLabel labelSearchName;
	
    private JPopupMenu dropdownMenu;
    private JMenuItem dropBacktoMenu;;
	private JMenuItem dropCust;
    private JMenuItem dropInv;
    private JMenuItem dropInvoices;
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator2;
    private JPopupMenu.Separator jSeparator3;
    
    private JButton btnMenu;
    private JButton btnLogoff;
    private JButton btnExit;
    private JButton btnAddCustomer;
    private JButton btnEditCustomer;
    
    private AutoColumnWidthTable jTableCustomers;
    
    private JScrollPane tableScrollPane;
    
    private JTextField textFieldSearchName;
    
    //Create reference for JFrame window
  	CustomerFrame frame = this;
  	
  	//Create objects to set up window listener to update table when these window close
    AddCustomerFrame addCustFrame;
    //EditCustomerFrame editCustFrame;
    WindowCloser winClose = new WindowCloser();
  	
    /**
     * Constructor for Customer Frame
     * @param username - String representing Staff Username of staff member logged in
     */
	public CustomerFrame(String username)
	{
		//Method to build frame
		buildFrame();
		//Set user label text
	    labelUser.setText("User: "+username);
        //Start timer
        timerStart();
        //Call method to fill table
        refreshTableData();
                
        //Maximise window on opening
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
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
     * Method to fill the Customer table with records from the database
     */
    //Method to fill and refresh JTable with Customer records 
    public void refreshTableData()
    {   
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        
        try
        {   DefaultTableModel tblModel = (DefaultTableModel) jTableCustomers.getModel();
            tblModel.setRowCount(0);
            String query = "SELECT * FROM Customer" ;
           
            rs = con.returnSet(query);
           
            while(rs.next())
            {  
                int custID = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String custContact = rs.getString("Customer_Contact_Name");
                String custAddLine1 = rs.getString("Address_Line_1");
                String custStreet = rs.getString("Address_Street");
                String custTown = rs.getString("Address_Town");
                String custCounty = rs.getString("Address_County");
                String custCountry = rs.getString("Address_Country");
                String custPhone = rs.getString("Customer_Phonenumber");
                String custEmail = rs.getString("Customer_Email");
                String custVAT = rs.getString("Customer_VatNo");
                String custUsername = rs.getString("Customer_Username");
                String custPass = rs.getString("Customer_Password");
                Object[] tbData = {custID, custName, custContact, custAddLine1, custStreet, custTown, custCounty, custCountry, custPhone, custEmail, custVAT, custUsername, custPass};
                tblModel = (DefaultTableModel)jTableCustomers.getModel();
                //Add row and call Data changed methods
                tblModel.addRow(tbData);
                tblModel.fireTableDataChanged();
                     
            }
            
        }
        catch(SQLException e)
        {   
            JOptionPane.showMessageDialog(this, "A problem occured loading table data, please contact your network administrator");
        }
        finally 
        {
            //Close connection objects, exceptions handled by databaseConnection.ConnectDB     
            con.closeResultSet(rs);
            con.closeConnection();
                
        }
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
			//Build Logo
			labelLogo = new JLabel();
			labelLogo.setBackground(new Color(68, 62, 90));
			labelLogo.setIcon(new ImageIcon(getClass().getResource("/Images/Logo.png")));
			labelLogo.setOpaque(true);
		//Add components
		panelLogo.add(labelLogo);
		
		//Build Title Panel(Title)
		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(68, 62, 90));
		panelTitle.setPreferredSize(new Dimension(760, 305));
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 35));
			//Build Title
			labelTitle = new JLabel();
			labelTitle.setFont(new Font("Segoe UI", 1, 36));
			labelTitle.setForeground(new Color(166, 76, 156));
			labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitle.setText("Ventair - Customers");
		//Add components
		panelTitle.add(labelTitle);
		
		//Build UserLabel and Time Panel(UserLabel and Time Labels), contained in panelUserTimeCont
		panelUserTime = new JPanel(new GridLayout(2,1));
		panelUserTime.setPreferredSize(new Dimension(300, 58));
			  //Build User Label
			  labelUser = new JLabel();
			  labelUser.setBackground(new Color(185, 209, 234));
			  labelUser.setFont(new Font("Segoe UI", 1, 14));
			  labelUser.setForeground(new Color(51, 51, 51));
			  labelUser.setHorizontalAlignment(SwingConstants.CENTER);
		      labelUser.setText("User: ");
			  labelUser.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			  labelUser.setOpaque(true);
			  labelUser.setPreferredSize(new Dimension(300, 34));
			  //Build Time Label
			  labelDateTime = new JLabel();
			  labelDateTime.setBackground(new Color(185, 209, 234));
			  labelDateTime.setFont(new Font("Segoe UI", 1, 14));
			  labelDateTime.setForeground(new Color(51, 51, 51));
			  labelDateTime.setHorizontalAlignment(SwingConstants.CENTER);
			  labelDateTime.setText("DateTime:");
			  labelDateTime.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			  labelDateTime.setOpaque(true);
			  labelDateTime.setPreferredSize(new Dimension(300, 34));
	    //Add components
	    panelUserTime.add(labelUser);
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
        jSeparator3 = new JPopupMenu.Separator();
        dropBacktoMenu = new JMenuItem();
        
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
		    	        StaffInvFrame frm = new StaffInvFrame(username);
		    	        frm.setVisible(true);
		    	        frame.dispose();
		            }
				}
				);//End Action Listener
        dropdownMenu.add(dropInvoices);
        dropdownMenu.add(jSeparator3);
        
        dropBacktoMenu.setText("Back to Menu");
        dropBacktoMenu.addActionListener(
        		//Anonymous Inner Class
				new ActionListener() 
				{
					//Handling dropBacktoMenu menu item pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	 // Back to Menu Dropdown Link
		                String username = labelUser.getText().substring(6);
		                MenuFrame frm = new MenuFrame(username);
		                frm.setVisible(true);
		                frame.dispose();
		            }
				}
				);//End Action Listener
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
		
		//Build side menu panel
        panelSideMenu = new JPanel();
        panelSideMenu.setLayout(new BoxLayout(panelSideMenu,BoxLayout.PAGE_AXIS));
        panelSideMenu.setPreferredSize(new Dimension(200,150));
        panelSideMenu.setBackground(new Color(68, 62, 90));
        panelSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
	        //Build buttons
			//Product button link
	        btnAddCustomer = new javax.swing.JButton();
	        btnAddCustomer.setBackground(new Color(166, 76, 156));
	        btnAddCustomer.setFont(new Font("Segoe UI", 0, 14));
	        btnAddCustomer.setForeground(new Color(255, 255, 255));
	        btnAddCustomer.setPreferredSize(new Dimension(180,40));
	        btnAddCustomer.setMaximumSize(new Dimension(180,40));
	        btnAddCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnAddCustomer.setText("Add Customer");
	        btnAddCustomer.setBorderPainted(false);
	        btnAddCustomer.setFocusable(false);
	        btnAddCustomer.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnAddProduct menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	// Add Button opens Add Product Window
	        		                addCustFrame = new AddCustomerFrame();
	        		                addCustFrame.addWindowListener(winClose);
	        		                addCustFrame.setVisible(true);
	        		            }
	        				}
	        				);//End Action Listener
	
	        //Edit Product button link
	        btnEditCustomer = new JButton();
	        btnEditCustomer.setBackground(new Color(166, 76, 156));
	        btnEditCustomer.setFont(new Font("Segoe UI", 0, 14));
	        btnEditCustomer.setForeground(new Color(255, 255, 255));
	        btnEditCustomer.setPreferredSize(new Dimension(180,40));
	        btnEditCustomer.setMaximumSize(new Dimension(180,40));
	        btnEditCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnEditCustomer.setText("Edit/Delete Customer");
	        btnEditCustomer.setBorderPainted(false);
	        btnEditCustomer.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnEditProduct menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Edit Button opens Edit Product Window
			                //Check if row selected, if not pop dialog box
			                if(jTableCustomers.getSelectedRow() == -1)
			                {
			                    JOptionPane.showMessageDialog(btnEditCustomer, "Please select a customer from table");
			                }
			                else
			                {
			                    int custID = Integer.parseInt(String.valueOf(jTableCustomers.getValueAt(jTableCustomers.getSelectedRow(), 0)));
			                    EditCustomerFrame editCustFrame = new EditCustomerFrame(custID);
			                    //Add window listener
			                    editCustFrame.addWindowListener(winClose);
			                    editCustFrame.setVisible(true);
			                }
			            }
					}
					);//End Action Listener
        //Add buttons to button container panel
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,30)));
        panelSideMenu.add(btnAddCustomer);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnEditCustomer);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,30)));
        
		//--Build Image panel--
		//Set image for Jximage panel
		imageJxPanel = new JXImagePanel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aircon.jpg"));
        Image img = icon.getImage();
        imageJxPanel.setImage(img) ;  
        imageJxPanel.setStyle(JXImagePanel.Style.SCALED);
        imageJxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,70));
        imageJxPanel.setPreferredSize(new Dimension(220,510));
        //Add components
        imageJxPanel.add(panelSideMenu);
		
        //Build jtable panel
        panelTable = new JPanel(new GridLayout(1,1));
        panelTable.setBackground(new Color(153,180,209));
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        	//Build JTable
        	jTableCustomers = new AutoColumnWidthTable();
        	jTableCustomers.setFont(new Font("Segoe UI", 0, 14));
        	jTableCustomers.setModel(
        			//Default Table Model
        			new DefaultTableModel(
                    new Object [][] {

                    },
                    //Column names
                    new String [] {
                        "Customer ID", "Customer Name", "Customer Contact Name", "Address Line 1", "Sreet", "Town", "County", "Country", "Phone Number", "Email", "Customer VAT No", "Customer Username", "Customer Password"
                    }
        			) 
        			{	
        			//Column classes
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false, false, false, false, false, false, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
            jTableCustomers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
            jTableCustomers.setRowHeight(26);//Row height
            jTableCustomers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
            tableScrollPane = new JScrollPane();
            tableScrollPane.setViewportView(jTableCustomers);
        //Add Component
        panelTable.add(tableScrollPane);
        	
        //Build bottom right bottom panel(label and search box)
        panelBottomRightBot = new JPanel();
        panelBottomRightBot.setBackground(new Color(153,180,209));
        panelBottomRightBot.setLayout(new BoxLayout(panelBottomRightBot,BoxLayout.LINE_AXIS));
        panelBottomRightBot.setPreferredSize(new Dimension(980,50));
        	//Build label and search box
	    	labelSearchName = new JLabel();
	        labelSearchName.setBackground(new Color(166, 76, 156));
	        labelSearchName.setFont(new Font("Segoe UI", 0, 14));
	        labelSearchName.setForeground(new Color(255, 255, 255));
	        labelSearchName.setHorizontalAlignment(SwingConstants.CENTER);
	        labelSearchName.setText("Search by Customer Name:");
	        labelSearchName.setOpaque(true);
	        labelSearchName.setPreferredSize(new Dimension(200, 30));
	        labelSearchName.setMaximumSize(new Dimension(200, 30));
	        labelSearchName.setAlignmentY(Component.CENTER_ALIGNMENT);
	
	        textFieldSearchName = new JTextField();
	        textFieldSearchName.setPreferredSize(new Dimension(250, 30));
	        textFieldSearchName.setMaximumSize(new Dimension(250, 30));
	        textFieldSearchName.setAlignmentY(Component.CENTER_ALIGNMENT);
	        //Create listener object and add to Filter search text field
	        TextFieldChanged change = new TextFieldChanged();
	        textFieldSearchName.getDocument().addDocumentListener(change);
	    //Add components
	    panelBottomRightBot.add(Box.createHorizontalGlue());
	    panelBottomRightBot.add(labelSearchName);
	    panelBottomRightBot.add(Box.createRigidArea(new Dimension(10,0)));
	    panelBottomRightBot.add(textFieldSearchName);
	    panelBottomRightBot.add(Box.createRigidArea(new Dimension(10,0)));	    
        
        //Build bottom right panel
        panelBottomRight = new JPanel(new BorderLayout());
        panelBottomRight.add(panelTable, BorderLayout.CENTER);
        panelBottomRight.add(panelBottomRightBot, BorderLayout.SOUTH);
                
        //Build main panel(side menu and jtable, main bottom partition)
        panelMain = new JPanel(new BorderLayout());
        panelMain.add(imageJxPanel, BorderLayout.WEST);
        panelMain.add(panelBottomRight, BorderLayout.CENTER);
        
        //Build frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelTop, BorderLayout.NORTH);
		getContentPane().add(panelMain, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventair - Customers");
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
    /**
     * Window Closing listener, listens for window closing and refreshs table if detected
     */
    // Listener for window, if window closing event detected refresh table
	class WindowCloser implements WindowListener 
        {
        @Override
        public void windowClosing(WindowEvent e) 
        {     
            refreshTableData();
        }
        @Override
        public void windowClosed(WindowEvent e) 
        {
            refreshTableData();
        }

        @Override
        public void windowOpened(WindowEvent e){}
        
        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}
        

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {}
       
	}
	/**
     * Text Field Changed class to listen for changes in search text field and set row filter
     */
    //Inner class for TextField Change Listener
    private class TextFieldChanged implements DocumentListener
    {   
        //Create table Sorter and set jTable1s model as model
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTableCustomers.getModel()); 
        String searchValue = "";
                        

        @Override
        //Method called when textfield is typed in
        public void insertUpdate(DocumentEvent e) 
        {
            //Get search text and convert to lower case and then use value to call row filter        
            searchValue = textFieldSearchName.getText();
            sorter.setRowFilter(new CustomerRowFilter(searchValue.toLowerCase()));
            jTableCustomers.setRowSorter(sorter); 
        }

        @Override
        //Method called when textfield is cleared
        public void removeUpdate(DocumentEvent e) 
        {
            //Get search text and convert to lower case and then use value to call row filter  
            searchValue = textFieldSearchName.getText();
            sorter.setRowFilter(new CustomerRowFilter(searchValue.toLowerCase()));
            jTableCustomers.setRowSorter(sorter); 
        }

        @Override
        public void changedUpdate(DocumentEvent e) 
        {
          
        }
        
    }
    /**
     * Customer Row Filter object for filtering Customer table, extends Row Filter
     */
    //Row Filter Class for Customer Table
    private class CustomerRowFilter extends RowFilter
    {
        //Instance variable
        private String searchValue;
        //Constructor
        CustomerRowFilter(String searchValue)
        {
            this.searchValue = searchValue;
        }
        @Override
        //Include these values if returns true
        public boolean include(RowFilter.Entry entry) 
        {
            //convert entry to lowercase and compare to search value, indexOf returns greater than 0 if text is contained in entry
            return (entry.getStringValue(1).toLowerCase().indexOf(searchValue) >= 0);
        
        }
        
    }
}//end class
