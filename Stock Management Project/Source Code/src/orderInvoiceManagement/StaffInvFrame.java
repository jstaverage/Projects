//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Staff version of Customer Orders/Invoices Frame for displaying and control of Customer Sales orders and invoices
package orderInvoiceManagement;

//Imports
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.RowFilter.Entry;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXImagePanel;

import customGuiComponents.AutoColumnWidthTable;
import customerManagement.CustomerFrame;
import databaseConnection.ConnectDB;
import inventoryManagement.InventoryFrame;
import loginAndUsers.LoginFrame;
import mainMenu.MenuFrame;

/**
 * Staff Sales Order/Invoice GUI Frame to allow employees to manipulate Sales Orders and Invoices
 * @author Shane Kennedy
 */
public class StaffInvFrame extends JFrame
{	
	//Components
	private JPanel panelTop;
	private	JPanel panelMain;
	private	JPanel panelLogo;
	private	JPanel panelTopRight;
	private JPanel panelTitle;
	private JPanel panelTopRightBottom;
	private JPanel panelUserTime;
	private JPanel panelSideMenuOrd;
	private JPanel panelBottomRightOrd;
	private JPanel panelTableOrd;
	private JPanel panelBottomRightBotOrd;
    private JPanel panelOrdSideMenu;
    private JPanel panelRdBtnOrders;
    private JPanel panelRdBtnInv;
    private JPanel panelCards;
    private JPanel panelBottomRightInv;
    private JPanel panelTableInv;
    private JPanel panelBottomRightBotInv;
    private JPanel panelInvSideMenu;
    private JPanel panelSideMenuInv;
    
    private JTabbedPane tabbedPnl;
    
	private JXImagePanel imageJxPanelOrders;
	private JXImagePanel imageJxPanelInvoices;
	
	private JLabel labelLogo;
	private JLabel labelTitle;
	private JLabel labelUser;
	private JLabel labelDateTime;
	private JLabel labelFilterOrd;
	private JLabel labelFilterInv;
	private JLabel lblShipDate;
    private JLabel lblExDeliv;
    private JLabel lblPayDate;
	
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
    private JButton btnCreateOrd;
    private JButton btnViewOrd;
    private JButton btnMarkComplete;
	private JButton btnCreateInv;
	private JButton btnSetPaid;
	private JButton btnViewInv;
	private JButton btnMarkPaid;
        
    private AutoColumnWidthTable jTableOrd;
    private AutoColumnWidthTable jTableInv;
    
    private JScrollPane tableScrollPaneOrd;
    private JScrollPane tableScrollPaneInv;
    
	private JXDatePicker jXDPExpDeliv;
    private JXDatePicker jXDPShipping;
    private JXDatePicker jXDPPayDate;
    
    private JComboBox<String> comboBoxCustOrd;
    private JComboBox<String> comboBoxCustInv;
    
   	private JRadioButton rBtnComplete;
    private JRadioButton rBtnUncomplete;
	private JRadioButton rBtnPaid;
    private JRadioButton rBtnUnPaid;
        
    private ButtonGroup btnGrpComplete;
    private ButtonGroup btnGrpPaid;
    
    //Create reference for JFrame window
  	StaffInvFrame frame = this;
  	
  	//Instance variables
  	private String globalUsername;
    private int globalOrderID;
    private int globalInvoiceID;
  	
    /**
     * Constructor for StaffInvFrame object
     * @param username - Takes a String parameter for Username of Staff Member logged in
     */
	public StaffInvFrame(String username)
	{
		super("Menu");
		//Method to build frame
		buildFrame();
		//Start timer
        timerStart();
        //Call method to fill table
        refreshTableData();
        refreshTable2Data();
        //Fill Customer comboBox with values
        fillComboBoxes();
        //Set text of label and store username in global variable
        labelUser.setText("User: "+username);
        globalUsername = username;
        //Call the row filter method for sales order table
        setOrderRowFilter();
        //Call the row filter method for sales invoice tables
        setInvoiceRowFilter();
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
     * Method to fill the Sales Order Table with data from database
     */
    //Method to fill and refresh the Sales Order Table
    public void refreshTableData()
    {   
        //Create array list for order ietms
        ArrayList<OrderItem> items = new  ArrayList<OrderItem>();
        //Create connection and resultset objects
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        ResultSet res = null;
        //Create number formatter for currency
        NumberFormat form =  NumberFormat.getCurrencyInstance();
        
        try
        {   //Create a default table model and set Sales order table to that model
            DefaultTableModel tblModel = (DefaultTableModel) jTableOrd.getModel();
            //Delete all rows if table loaded already
            tblModel.setRowCount(0);
            //Query the database and get details for Sales Order table
            String query = "Select Order_ID, Order_Date, SalesOrder.Customer_ID, Complete FROM SalesOrder Inner Join Customer On SalesOrder.Customer_ID =Customer.Customer_ID";
            rs = con.returnSet(query);
            java.sql.Date ordDate = new java.sql.Date(0);
            int custID = 0;
            while(rs.next())
            {  
                //Clear items
                items.clear();
                //Create variables for totals
                double grossTotal = 0;
                double netTotal = 0;
                double VAT = 0;
                //Clear item list
                items.clear();
                int orderID = rs.getInt("Order_ID");
                //Format dates from sql to java date
                ordDate = rs.getDate("Order_Date");
                Date ord = new Date(ordDate.getTime());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String date = df.format(ord);
                custID = rs.getInt("Customer_ID");
                int complete = rs.getInt("Complete");
                //Set if sales order is complete
                boolean comp = false;
                if(complete == 1)
                {
                    comp = true;
                }
                //Now query the OrderItem table in database to get items on order
                query = "Select Product_ID, Quantity, Current_Price From Order_Item Where Order_ID = ?" ;
                res = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(orderID)));
                while(res.next())
                {
                    int prodID = res.getInt("Product_ID");
                    int quantity = res.getInt("Quantity");
                    double price = res.getDouble("Current_Price");
                   
                    OrderItem item = new OrderItem(prodID, quantity, price);
                    items.add(item);
                }
                //Create order object to calculate gross and net totals
                Order order = new Order(ordDate,custID, items);
                grossTotal = order.calculateGrossTotal();
                netTotal = order.calculateNetTotal();
                VAT = netTotal - grossTotal;
                Object[] tbData = {orderID, custID,date, form.format(grossTotal), form.format(VAT),form.format(netTotal),comp};
                tblModel = (DefaultTableModel)jTableOrd.getModel();
                //Add row to table model and fire data changed event
                tblModel.addRow(tbData);
                tblModel.fireTableDataChanged();
            }
       
        }
        catch(SQLException e)
        {   
            JOptionPane.showMessageDialog(this, "A problem occured loading Sales Order table data, please contact your network administrator");
        }
        finally 
        {
            con.closeResultSet(rs);
            con.closeConnection();
        }
    }
    /**
     * Method to fill the Sales Invoice table with data
     */
    //Method to fill and refresh the Sales Invoice Table
    public void refreshTable2Data()
    {   
        ArrayList<OrderItem> items = new  ArrayList<OrderItem>();
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        ResultSet res = null;
        
        try
        {   
            //Create a table model and set row count to 0
            DefaultTableModel tblModel = (DefaultTableModel) jTableInv.getModel();
            tblModel.setRowCount(0);
            //Prepare query inner joining Sales Order and Sales Invoice 
            String query = "Select Invoice_ID, SalesInvoice.Order_ID, Invoice_Date, Shipping_Date, Delivery_Date, Payment_Date, SalesOrder.Customer_ID FROM SalesInvoice Inner Join SalesOrder On SalesInvoice.Order_ID =SalesOrder.Order_ID";
            //Save result in result set rs
            rs = con.returnSet(query);
           
            //Parse Results and add rows to table model
            while(rs.next())
            {  
                //Clear items
                items.clear();
                int invoiceID = rs.getInt("Invoice_ID");
                //Create date formatter to output dates in format needed
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                //Get sql date for Invoice and convert to Date in format needed
                java.sql.Date invDate = rs.getDate("Invoice_Date");
                Date invD = new Date(invDate.getTime());
                String invDString = df.format(invD);
                int orderID = rs.getInt("Order_ID");
                //Get sql date for shipping and convert to Date in format needed
                java.sql.Date shipDate = rs.getDate("Shipping_Date");
                String shipDString = df.format(shipDate);
                //Get sql date for delivery and convert to Date in format needed
                java.sql.Date delivDate = rs.getDate("Delivery_Date");
                String delivDString = df.format(delivDate);
                //Get sql date for payment and convert to Date in format needed
                String payDString = "";
                //Check if null first
                if(rs.getDate("Payment_Date") != null)
                {
                    java.sql.Date payDate = rs.getDate("Payment_Date");
                    payDString = df.format(payDate);
                }
                else
                {
                   payDString = "None";
                }
                             
                //Get customer id
                int custID = rs.getInt("Customer_ID");
                
                //Create query to find items on Order to calculate net price for table
                query = "Select Product_ID, Quantity, Current_Price From Invoice_Order_Item Where Invoice_ID = ?" ;
                res = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(invoiceID)));
                while(res.next())
                {
                    int prodID = res.getInt("Product_ID");
                    int quantity = res.getInt("Quantity");
                    double price = res.getDouble("Current_Price");
                   
                    //Create order item and add to arraylist of order items
                    OrderItem item = new OrderItem(prodID, quantity, price);
                    items.add(item);
                }
                //Create order to gain access to calculatenetprice method
                SalesInvoice invoice = new  SalesInvoice(invoiceID,custID, items);
                double netTotal = invoice.calculateNetTotal();
                
                //Add row tdData and add to table model
                Object[] tbData = {invoiceID,orderID,custID,invDString,shipDString,delivDString,netTotal,payDString};
                tblModel = (DefaultTableModel)jTableInv.getModel();
                tblModel.addRow(tbData);
                //Call fireTableDataChanged method to update table if changes are made
                tblModel.fireTableDataChanged();
            }
                  
           
        }
        catch(SQLException e)
        {   
            JOptionPane.showMessageDialog(this, "A problem occured loading Sales Invoice table data, please contact your network administrator");
        }
        finally 
        {
            con.closeResultSet(rs);
            con.closeResultSet(res);
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
			labelTitle.setText("Ventair - Customer Sales Orders/Invoices");
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
	      labelUser.setText("User: ");
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
		
		//Build order shipping/delivery date panel for Sales Orders(appears on complete order button press)
		panelOrdSideMenu = new JPanel();
		panelOrdSideMenu.setLayout(new BoxLayout(panelOrdSideMenu,BoxLayout.PAGE_AXIS));
		panelOrdSideMenu.setPreferredSize(new Dimension(190,175));
		panelOrdSideMenu.setMaximumSize(new Dimension(190,175));
		panelOrdSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			//Build labels and date pickers and button
		    lblShipDate = new javax.swing.JLabel();
		    lblShipDate.setBackground(new Color(185, 209, 234));
		    lblShipDate.setHorizontalAlignment(SwingConstants.CENTER);
		    lblShipDate.setText("Set Shipping Date:");
		    lblShipDate.setOpaque(true);
		    lblShipDate.setPreferredSize(new Dimension(165,30));
		    lblShipDate.setMaximumSize(new Dimension(165,30));
		    lblShipDate.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		    lblExDeliv = new JLabel();
		    lblExDeliv.setBackground(new Color(185, 209, 234));
		    lblExDeliv.setHorizontalAlignment(SwingConstants.CENTER);
		    lblExDeliv.setText("Set Expected Delivery:");
		    lblExDeliv.setOpaque(true);
		    lblExDeliv.setPreferredSize(new Dimension(165,30));
		    lblExDeliv.setMaximumSize(new Dimension(165,30));
		    lblExDeliv.setAlignmentX(Component.CENTER_ALIGNMENT);

		    jXDPShipping = new JXDatePicker();
		    jXDPShipping.setPreferredSize(new Dimension(165,35));
		    jXDPShipping.setMaximumSize(new Dimension(165,35));
		    jXDPShipping.setAlignmentX(Component.CENTER_ALIGNMENT);

		    jXDPExpDeliv = new JXDatePicker();
		    jXDPExpDeliv.setPreferredSize(new Dimension(165,35));
		    jXDPExpDeliv.setMaximumSize(new Dimension(165,35));
		    jXDPExpDeliv.setAlignmentX(Component.CENTER_ALIGNMENT);
		    
		    btnCreateInv = new JButton();
		    btnCreateInv.setBackground(new Color(166, 76, 156));
		    btnCreateInv.setFont(new Font("Segoe UI", 0, 14));
		    btnCreateInv.setForeground(new Color(255, 255, 255));
		    btnCreateInv.setText("Create Sales Invoice");
		    btnCreateInv.setBorderPainted(false);
		    btnCreateInv.setAlignmentX(Component.CENTER_ALIGNMENT);
		    btnCreateInv.addActionListener(
			    		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnCreateInv pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Create Sales invoice btn
	    		                //Check if both dates have been picked, if not warn user, if so pop confirm dialog box
	    		                if(jXDPShipping.getDate() == null)
	    		                {
	    		                    JOptionPane.showMessageDialog(jXDPShipping, "Please select a shipping date for order");
	    		                }
	    		                else if(jXDPExpDeliv.getDate() == null)
	    		                {
	    		                    JOptionPane.showMessageDialog(jXDPExpDeliv, "Please select an expected delivery date for order");
	    		                } 
	    		                else
	    		                {
	    		                    //Pop confirm check using JOPtionPane
	    		                    int confirm = 0;
	    		                    confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to create Sales Invoice from Order?");
	    		                    //If user selected yes, use global order id to create a Sales Invoice Object and create entry in Database
	    		                    if(confirm == 0)
	    		                    {   
	    		                        //ArrayList for order Items
	    		                        ArrayList<OrderItem> items = new  ArrayList<OrderItem>();
	    		                        //Create Connection and ResultSet objects
	    		                        ConnectDB con = new ConnectDB();
	    		                        ResultSet res = null;

	    		                        try
	    		                        {   
	    		                            //Query to return Order items for ArrayList to create Sales Invoice Object
	    		                            String query = "Select Product_ID, Quantity, Current_Price From Order_Item Where Order_ID = ?" ;
	    		                            res = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(globalOrderID)));


	    		                            while(res.next())
	    		                            {  
	    		                                int prodID = res.getInt("Product_ID");
	    		                                int quantity = res.getInt("Quantity");
	    		                                double price = res.getDouble("Current_Price");
	    		                                OrderItem item = new OrderItem(prodID, quantity, price);
	    		                                items.add(item);
	    		                            }
	    		                            //Create todays date for Invoice Date and convert to mysql Date formate
	    		                            Date date = new Date();
	    		                            date.getTime();
	    		                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    		                            df.format(date);
	    		                            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    		                            //Get shipping date from Date Picker and convert to mysql Date format
	    		                            Date shippingDate = jXDPShipping.getDate();
	    		                            df.format(shippingDate);
	    		                            java.sql.Date sqlShippingDate = new java.sql.Date(shippingDate.getTime());
	    		                            //Get expected delivery date from Date Picker and convert to mysql Date format
	    		                            Date deliveryDate = jXDPShipping.getDate();
	    		                            df.format(deliveryDate);
	    		                            java.sql.Date sqlDeliveryDate = new java.sql.Date(deliveryDate.getTime());

	    		                            //Create Sales Invoice Object
	    		                            SalesInvoice inv = new SalesInvoice(globalOrderID,sqlDate,sqlShippingDate,sqlDeliveryDate,items);

	    		                            //Add Invoice to Database
	    		                            inv.createInvoiceEntry();

	    		                            //Create empty Order object and call method to mark order as complete
	    		                            Order ord = new Order();
	    		                            ord.markOrderComplete(globalOrderID);

	    		                            //Set Panel for picking dates back to invisible
	    		                            panelOrdSideMenu.setVisible(false);

	    		                            //RefreshTableData for Sales Invoice and Sales Order
	    		                            refreshTableData();
	    		                            refreshTable2Data();
	    		                        }
	    		                        catch(SQLException e)
	    		                        {   
	    		                            JOptionPane.showMessageDialog(frame, "A problem occured creating Sales Invoice, please contact your network administrator");
	    		                        }
	    		                        finally 
	    		                        {
	    		                            con.closeResultSet(res);
	    		                            con.closeConnection();
	    		                             
	    		                        }
	    		                    }
	    		                }
	    		            }
	    				}
	    				);//End Action Listener
		//Add components
		panelOrdSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
		panelOrdSideMenu.add(lblShipDate);
		panelOrdSideMenu.add(Box.createRigidArea(new Dimension(0,5)));
		panelOrdSideMenu.add(jXDPShipping);
		panelOrdSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelOrdSideMenu.add(lblExDeliv);
        panelOrdSideMenu.add(Box.createRigidArea(new Dimension(0,5)));
        panelOrdSideMenu.add(jXDPExpDeliv);
        panelOrdSideMenu.add(Box.createRigidArea(new Dimension(0,5)));
        panelOrdSideMenu.add(btnCreateInv);
        panelOrdSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelOrdSideMenu.setVisible(false);//Invisible until Mark Order complete button pressed
        
    	//Build invoice pay date panel(appears on Set Invoice as Paid button press)
		panelInvSideMenu = new JPanel();
		panelInvSideMenu.setLayout(new BoxLayout(panelInvSideMenu,BoxLayout.PAGE_AXIS));
		panelInvSideMenu.setPreferredSize(new Dimension(190,100));
		panelInvSideMenu.setMaximumSize(new Dimension(190,100));
		panelInvSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			//Build labels and date pickers and button
		    lblPayDate = new javax.swing.JLabel();
		    lblPayDate.setBackground(new Color(185, 209, 234));
		    lblPayDate.setHorizontalAlignment(SwingConstants.CENTER);
		    lblPayDate.setText("Set Payment Date:");
		    lblPayDate.setOpaque(true);
		    lblPayDate.setPreferredSize(new Dimension(165,30));
		    lblPayDate.setMaximumSize(new Dimension(165,30));
		    lblPayDate.setAlignmentX(Component.CENTER_ALIGNMENT);

		    jXDPPayDate = new JXDatePicker();
		    jXDPPayDate.setPreferredSize(new Dimension(165,35));
		    jXDPPayDate.setMaximumSize(new Dimension(165,35));
		    jXDPPayDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		    
		    btnSetPaid = new JButton();
		    btnSetPaid.setBackground(new Color(166, 76, 156));
		    btnSetPaid.setFont(new Font("Segoe UI", 0, 14));
		    btnSetPaid.setForeground(new Color(255, 255, 255));
		    btnSetPaid.setText("Create Sales Invoice");
		    btnSetPaid.setBorderPainted(false);
		    btnSetPaid.setAlignmentX(Component.CENTER_ALIGNMENT);
		    btnSetPaid.addActionListener(
			    		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnSetPaid menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Set Payment date for invoice btn
	    		                //Check if a date has been selected if not warn user, else mark invoice as paid with that date
	    		                if(jXDPPayDate.getDate() == null)
	    		                {
	    		                     JOptionPane.showMessageDialog(jXDPPayDate, "Please select a payment date for Invoice");
	    		                }
	    		                else
	    		                {
	    		                    //Pop confirm check using JOPtionPane
	    		                    int confirm = 0;
	    		                    confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to set Invoice as Paid with selected Date?");
	    		                    //If user selected yes, use global invoice id to create a Sales Invoice Object and update current invoice with selected Payment Date
	    		                    if(confirm == 0)
	    		                    {   
	    		                        //Create Date Formatter object
	    		                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    		                        //Get payment date from Date Picker and convert to mysql Date format
	    		                        Date payDate = jXDPPayDate.getDate();
	    		                        df.format(payDate);
	    		                        //Create Invoice Object and call method to set Payment Date
	    		                        SalesInvoice inv = new SalesInvoice(payDate);
	    		                        inv.setInvoicePayDate(globalInvoiceID);

	    		                        //Set panel back to invisible
	    		                        panelInvSideMenu.setVisible(false);
	    		                        //Refresh table data
	    		                        refreshTable2Data();

	    		                    }
	    		                }
	    		            }
	    				}
	    				);//End Action Listener
		//Add components
		panelInvSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
		panelInvSideMenu.add(lblPayDate);
		panelInvSideMenu.add(Box.createRigidArea(new Dimension(0,5)));
		panelInvSideMenu.add(jXDPPayDate);
		panelInvSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
		panelInvSideMenu.add(btnSetPaid);
		panelInvSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
		panelInvSideMenu.setVisible(false);//Invisible until Set Invoice as Paid button pressed
        
		//Build side menu panel for Sales Order tab
        panelSideMenuOrd = new JPanel();
        panelSideMenuOrd.setLayout(new BoxLayout(panelSideMenuOrd,BoxLayout.PAGE_AXIS));
        panelSideMenuOrd.setMinimumSize(new Dimension(200,190));
        panelSideMenuOrd.setMaximumSize(new Dimension(200,365));
        panelSideMenuOrd.setBackground(new Color(68, 62, 90));
        panelSideMenuOrd.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSideMenuOrd.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        //Build buttons
			//Create Order button link
	        btnCreateOrd = new javax.swing.JButton();
	        btnCreateOrd.setBackground(new Color(166, 76, 156));
	        btnCreateOrd.setFont(new Font("Segoe UI", 0, 14));
	        btnCreateOrd.setForeground(new Color(255, 255, 255));
	        btnCreateOrd.setPreferredSize(new Dimension(180,40));
	        btnCreateOrd.setMaximumSize(new Dimension(180,40));
	        btnCreateOrd.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnCreateOrd.setText("Create Order");
	        btnCreateOrd.setBorderPainted(false);
	        btnCreateOrd.setFocusable(false);
	        btnCreateOrd.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnCreateOrd menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	// Create Order Button
	        		                StaffInvFrame parent = frame;
	        		                StaffCreateSOrderFrame frm = new StaffCreateSOrderFrame(parent);
	        		                frm.setVisible(true);
	        		            }
	        				}
	        				);//End Action Listener
	        //View/Delete Order button link
	        btnViewOrd = new JButton();
	        btnViewOrd.setBackground(new Color(166, 76, 156));
	        btnViewOrd.setFont(new Font("Segoe UI", 0, 14));
	        btnViewOrd.setForeground(new Color(255, 255, 255));
	        btnViewOrd.setPreferredSize(new Dimension(180,40));
	        btnViewOrd.setMaximumSize(new Dimension(180,40));
	        btnViewOrd.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnViewOrd.setText("View/Delete Order");
	        btnViewOrd.setBorderPainted(false);
	        btnViewOrd.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnEditProduct menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	 // View Order Btn
			                //Check that a row is selected, if not alert user
			                if(jTableOrd.getSelectedRow() == -1)
			                {
			                    JOptionPane.showMessageDialog(btnViewOrd, "Please select an order from table");
			                }
			                else
			                {
			                    int orderID = Integer.parseInt(String.valueOf(jTableOrd.getValueAt(jTableOrd.getSelectedRow(), 0)));
			                    //Create reference to parent
			                    StaffInvFrame parent = frame;
			                    StaffEditOrdFrame frm = new StaffEditOrdFrame(orderID, parent);
			                    frm.setVisible(true);
			                }
			            }
					}
					);//End Action Listener
	        //View/Delete Order button link
	        btnMarkComplete = new JButton();
	        btnMarkComplete.setBackground(new Color(166, 76, 156));
	        btnMarkComplete.setFont(new Font("Segoe UI", 0, 14));
	        btnMarkComplete.setForeground(new Color(255, 255, 255));
	        btnMarkComplete.setPreferredSize(new Dimension(180,40));
	        btnMarkComplete.setMaximumSize(new Dimension(180,40));
	        btnMarkComplete.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnMarkComplete.setText("Complete Order");
	        btnMarkComplete.setBorderPainted(false);
	        btnMarkComplete.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnEditProduct menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	 // Mark as completed button event
			                //Check if row selected, if not pop dialog box
			                if(jTableOrd.getSelectedRow() == -1)
			                {
			                    JOptionPane.showMessageDialog(btnMarkComplete, "Please select an order from table");
			                }
			                else
			                {
			                    //Set Panel to be Visible if invisible and vice versa
			                    if(panelOrdSideMenu.isVisible())
			                    {
			                       panelOrdSideMenu.setVisible(false);
			                    }
			                    else
			                    {
			                        panelOrdSideMenu.setVisible(true);
			                    }
			                    //Sets global OrderID so can be accessed by Create Invoice Button Action event
			                    globalOrderID = Integer.parseInt(String.valueOf(jTableOrd.getValueAt(jTableOrd.getSelectedRow(), 0)));
			                }
			            }
					}
					);//End Action Listener
        //Add buttons to button container panel
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,25)));
        panelSideMenuOrd.add(btnCreateOrd);
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenuOrd.add(btnViewOrd);
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenuOrd.add(btnMarkComplete);
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,15)));
        panelSideMenuOrd.add(Box.createVerticalGlue());
        panelSideMenuOrd.add(panelOrdSideMenu);
        panelSideMenuOrd.add(Box.createVerticalGlue());
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,10)));
        
		//Build side menu panel for Sales Invoice
        panelSideMenuInv = new JPanel();
        panelSideMenuInv.setLayout(new BoxLayout(panelSideMenuInv,BoxLayout.PAGE_AXIS));
        panelSideMenuInv.setMinimumSize(new Dimension(200,140));
        panelSideMenuInv.setMaximumSize(new Dimension(200,240));
        panelSideMenuInv.setBackground(new Color(68, 62, 90));
        panelSideMenuInv.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSideMenuInv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	        //Build buttons
			//Create view delete Invoice button
	        btnViewInv = new javax.swing.JButton();
	        btnViewInv.setBackground(new Color(166, 76, 156));
	        btnViewInv.setFont(new Font("Segoe UI", 0, 14));
	        btnViewInv.setForeground(new Color(255, 255, 255));
	        btnViewInv.setPreferredSize(new Dimension(180,40));
	        btnViewInv.setMaximumSize(new Dimension(180,40));
	        btnViewInv.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnViewInv.setText("View/Delete Invoice");
	        btnViewInv.setBorderPainted(false);
	        btnViewInv.setFocusable(false);
	        btnViewInv.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnViewInv menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	//View Invoice Btn
	        		               //Check if row selected, if not pop dialog box
	        		               if(jTableInv.getSelectedRow() == -1)
	        		               {
	        		                  JOptionPane.showMessageDialog(btnViewInv, "Please select an invoice from table");
	        		               }
	        		               else
	        		               {
	        		                   int invoiceID = Integer.parseInt(String.valueOf(jTableInv.getValueAt(jTableInv.getSelectedRow(), 0)));
	        		                   StaffInvFrame parent = frame;
	        		                   StaffViewInvFrame frm = new StaffViewInvFrame(invoiceID, parent);
	        		                   frm.setVisible(true);
	        		               }
	        		            }
	        				}
	        				);//End Action Listener
	        //Mark invoice as Paid
	        btnMarkPaid = new JButton();
	        btnMarkPaid.setBackground(new Color(166, 76, 156));
	        btnMarkPaid.setFont(new Font("Segoe UI", 0, 14));
	        btnMarkPaid.setForeground(new Color(255, 255, 255));
	        btnMarkPaid.setPreferredSize(new Dimension(180,40));
	        btnMarkPaid.setMaximumSize(new Dimension(180,40));
	        btnMarkPaid.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnMarkPaid.setText("Mark Invoice Paid");
	        btnMarkPaid.setBorderPainted(false);
	        btnMarkPaid.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnMarkPaid menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			                // Mark Invoice Paid button event
			                //Check if row selected, if not pop dialog box
			                if(jTableInv.getSelectedRow() == -1)
			                {
			                   JOptionPane.showMessageDialog(btnMarkPaid, "Please select an invoice from table");
			                }
			                else
			                {
			                    //Set Panel to be Visible if invisible and vice versa
			                    if(panelInvSideMenu.isVisible())
			                    {
			                       panelInvSideMenu.setVisible(false);
			                    }
			                    else
			                    {
			                        panelInvSideMenu.setVisible(true);
			                    }
			                    //Sets global InvoiceID so can be accessed by Set Payment Date Action event
			                    globalInvoiceID = Integer.parseInt(String.valueOf(jTableInv.getValueAt(jTableInv.getSelectedRow(), 0)));
			                }
			            }
					}
					);//End Action Listener
        //Add buttons to button container panel
        panelSideMenuInv.add(Box.createRigidArea(new Dimension(0,25)));
        panelSideMenuInv.add(btnViewInv);
        panelSideMenuInv.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenuInv.add(btnMarkPaid);
        panelSideMenuInv.add(Box.createRigidArea(new Dimension(0,15)));
        panelSideMenuInv.add(Box.createVerticalGlue());
        panelSideMenuInv.add(panelInvSideMenu);
        panelSideMenuInv.add(Box.createVerticalGlue());
        panelSideMenuInv.add(Box.createRigidArea(new Dimension(0,10)));
        
		//--Build Image panel for Sales Orders Side Menu--
		//Set image for Jximage panel
		imageJxPanelOrders = new JXImagePanel();
		ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aircon.jpg"));
        Image img = icon.getImage();
        imageJxPanelOrders.setImage(img) ;  
        imageJxPanelOrders.setStyle(JXImagePanel.Style.SCALED);
        imageJxPanelOrders.setLayout(new FlowLayout(FlowLayout.CENTER, 0,70));
        imageJxPanelOrders.setPreferredSize(new Dimension(220,510));
        //Add components
        imageJxPanelOrders.add(panelSideMenuOrd);
        
    	//--Build Image panel--
		//Set image for Jximage panel
		imageJxPanelInvoices = new JXImagePanel();
        img = icon.getImage();
        imageJxPanelInvoices.setImage(img) ;  
        imageJxPanelInvoices.setStyle(JXImagePanel.Style.SCALED);
        imageJxPanelInvoices.setLayout(new FlowLayout(FlowLayout.CENTER, 0,70));
        imageJxPanelInvoices.setPreferredSize(new Dimension(220,510));
        //Add components
        imageJxPanelInvoices.add(panelSideMenuInv);
		
        //Build card layers for side menu(switch between order side menu and invoice side menu when tabbed panel changed)
        panelCards = new JPanel();
        panelCards.setLayout(new CardLayout());
        panelCards.add(imageJxPanelOrders, "orderSideMenu");
        panelCards.add(imageJxPanelInvoices, "invoiceSideMenu");
        
        //Build jtable panel for Sales Orders tab
        panelTableOrd = new JPanel(new GridLayout(1,1));
        panelTableOrd.setBackground(new Color(153,180,209));
        panelTableOrd.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        	//Build JTable
        	jTableOrd = new AutoColumnWidthTable();
        	jTableOrd.setFont(new Font("Segoe UI", 0, 14));
            jTableOrd.setModel(
            	//Default table model
            	new DefaultTableModel
            	(
	                new Object [][] {                },
	                //Column Names
	                new String [] {
	                		"Order ID", "Customer ID", "Order Date", "Price Excluding VAT", "Total VAT", "Net Price", "Complete"
	                }
	            ) 
            	{	
            		//Column classes
	                Class[] types = new Class [] {
	                		java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
	                };
	                boolean[] canEdit = new boolean [] {
	                	false, false, false, false, false, false, false
	                };
	
	                public Class getColumnClass(int columnIndex) {
	                    return types [columnIndex];
	                }
	
	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            });
            jTableOrd.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
            jTableOrd.setRowHeight(26);//Row height
            jTableOrd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
            tableScrollPaneOrd = new JScrollPane();
            tableScrollPaneOrd.setViewportView(jTableOrd);
        //Add Component
        panelTableOrd.add(tableScrollPaneOrd);
        
        //Build jtable panel for Sales Orders tab
        panelTableInv = new JPanel(new GridLayout(1,1));
        panelTableInv.setBackground(new Color(153,180,209));
        panelTableInv.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        	//Build JTable
        	jTableInv = new AutoColumnWidthTable();
        	jTableInv.setFont(new Font("Segoe UI", 0, 14));
        	jTableInv.setModel(
            	//Default table model
            	new DefaultTableModel
            	(
	                new Object [][] {                },
	                //Column Names
	                new String [] {
	                		"Invoice ID", "Order ID", "Customer ID", "Invoice Date", "Shipping Date", "Deliivery Date", "Net Total", "Payment Date"
	                }
	            ) 
            	{	
            		//Column classes
	                Class[] types = new Class [] {
	                		java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
	                };
	                boolean[] canEdit = new boolean [] {
	                	false, false, false, false, false, false, false, false
	                };
	
	                public Class getColumnClass(int columnIndex) {
	                    return types [columnIndex];
	                }
	
	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            });
        	jTableInv.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
        	jTableInv.setRowHeight(26);//Row height
        	jTableInv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
            tableScrollPaneInv = new JScrollPane();
            tableScrollPaneInv.setViewportView(jTableInv);
        //Add Component
        panelTableInv.add(tableScrollPaneInv);
        
        //Build radio button panel(contains uncomplete/complete filter radiobuttons for Sales Orders)
        panelRdBtnOrders = new JPanel(new GridLayout(2,1));
        panelRdBtnOrders.setBackground(new Color(153,180,209));
        panelRdBtnOrders.setPreferredSize(new Dimension(200,60));
        panelRdBtnOrders.setMaximumSize(new Dimension(200,60));
        	//Build radioButtons
        	rBtnComplete = new JRadioButton();
        	rBtnComplete.setFont(new Font("Segoe UI", 0, 14));
    	    rBtnComplete.setText("Show Only Complete");
    	    rBtnComplete.setPreferredSize(new Dimension(200,30));
    	    rBtnComplete.setBackground(new Color(153,180,209));
    	    rBtnComplete.addActionListener(
    	    		//Anonymous Inner Class
    				new ActionListener() 
    				{
    					//Handling rBtnComplete pressed action event
    		            public void actionPerformed(ActionEvent evt) 
    		            {
    		            	 //Btn complete
    		                setOrderRowFilter(); 
    		            }
    				}
    				);//End Action Listener
 
		    rBtnUncomplete = new JRadioButton();
		    rBtnUncomplete.setFont(new Font("Segoe UI", 0, 14));
		    rBtnUncomplete.setText("Show Only Uncomplete");
		    rBtnUncomplete.setBackground(new Color(153,180,209));
		    rBtnUncomplete.setPreferredSize(new Dimension(200,30));
		    //Set default value for rbutton complete
	        rBtnUncomplete.setSelected(true);
		    rBtnUncomplete.addActionListener(
		    		//Anonymous Inner Class
    				new ActionListener() 
    				{
    					//Handling rBtnComplete pressed action event
    		            public void actionPerformed(ActionEvent evt) 
    		            {
    		            	// Btn Uncomplete, call setRowFilter
    		                setOrderRowFilter();  
    		            }
    				}
    				);//End Action Listener
		    //Add buttons to button group
		    btnGrpComplete = new ButtonGroup();
		    btnGrpComplete.add(rBtnUncomplete);
		    btnGrpComplete.add(rBtnComplete);
		//Add components
		panelRdBtnOrders.add(rBtnComplete);
		panelRdBtnOrders.add(rBtnUncomplete);
		
	    //Build radio button panel(contains Paid/Unpaid filter radiobuttons for Sales Invoices)
        panelRdBtnInv = new JPanel(new GridLayout(2,1));
        panelRdBtnInv.setBackground(new Color(153,180,209));
        panelRdBtnInv.setPreferredSize(new Dimension(200,60));
        panelRdBtnInv.setMaximumSize(new Dimension(200,60));
        	//Build radioButtons
        	rBtnPaid = new JRadioButton();
        	rBtnPaid.setFont(new Font("Segoe UI", 0, 14));
        	rBtnPaid.setText("Show Only Paid");
        	rBtnPaid.setPreferredSize(new Dimension(200,30));
        	rBtnPaid.setBackground(new Color(153,180,209));
        	rBtnPaid.addActionListener(
    	    		//Anonymous Inner Class
    				new ActionListener() 
    				{
    					//Handling rBtnPaid pressed action event
    		            public void actionPerformed(ActionEvent evt) 
    		            {
    		            	// Unpaid radio btn event
    		                setInvoiceRowFilter();
    		            }
    				}
    				);//End Action Listener
 
		    rBtnUnPaid = new JRadioButton();
		    rBtnUnPaid.setFont(new Font("Segoe UI", 0, 14));
		    rBtnUnPaid.setText("Show Only Unpaid");
		    rBtnUnPaid.setBackground(new Color(153,180,209));
		    rBtnUnPaid.setPreferredSize(new Dimension(200,30));
		    //Set default value for rbutton unpaid
		    rBtnUnPaid.setSelected(true);
		    rBtnUnPaid.addActionListener(
		    		//Anonymous Inner Class
    				new ActionListener() 
    				{
    					//Handling rBtnUnPaid pressed action event
    		            public void actionPerformed(ActionEvent evt) 
    		            {
    		            	// Paid radio btn event
    		                setInvoiceRowFilter();
    		            }
    				}
    				);//End Action Listener
		    //Add buttons to button group
		    btnGrpPaid = new ButtonGroup();
		    btnGrpPaid.add(rBtnUnPaid);
		    btnGrpPaid.add(rBtnPaid);
		//Add components
		panelRdBtnInv.add(rBtnPaid);
		panelRdBtnInv.add(rBtnUnPaid);   
		
        //Build bottom right bottom panel for Sales Orders(label, search box and radiobuttons)
        panelBottomRightBotOrd = new JPanel();
        panelBottomRightBotOrd.setBackground(new Color(153,180,209));
        panelBottomRightBotOrd.setLayout(new BoxLayout(panelBottomRightBotOrd,BoxLayout.LINE_AXIS));
        panelBottomRightBotOrd.setPreferredSize(new Dimension(980,50));
        	//Build label and search box
	    	labelFilterOrd = new JLabel();
	        labelFilterOrd.setBackground(new Color(166, 76, 156));
	        labelFilterOrd.setFont(new Font("Segoe UI", 0, 14));
	        labelFilterOrd.setForeground(new Color(255, 255, 255));
	        labelFilterOrd.setHorizontalAlignment(SwingConstants.CENTER);
	        labelFilterOrd.setText("Filter Customer:");
	        labelFilterOrd.setOpaque(true);
	        labelFilterOrd.setPreferredSize(new Dimension(160, 30));
	        labelFilterOrd.setMaximumSize(new Dimension(160, 30));
	        labelFilterOrd.setAlignmentY(Component.CENTER_ALIGNMENT);

	    	comboBoxCustOrd = new JComboBox<>();
	    	comboBoxCustOrd.setPreferredSize(new Dimension(160, 30));
	    	comboBoxCustOrd.setMaximumSize(new Dimension(160, 30));
	    	comboBoxCustOrd.addItemListener(
	        		//Anonymous Inner Class
	        		new ItemListener() 
	        		{
	        			//Handling comboBoxCustOrd item changed Item event
	        			public void itemStateChanged(ItemEvent evt) 
	        			{
	        				// ComboBox Customer ID changed from sales order tab, call row filter method
	        		        setOrderRowFilter();
	        			}
	        		});//End Item Listener
	    //Add components
		panelBottomRightBotOrd.add(Box.createRigidArea(new Dimension(10,0)));
	    panelBottomRightBotOrd.add(labelFilterOrd);
	    panelBottomRightBotOrd.add(Box.createRigidArea(new Dimension(5,0)));
	    panelBottomRightBotOrd.add(comboBoxCustOrd);
	    panelBottomRightBotOrd.add(Box.createHorizontalGlue());
	    panelBottomRightBotOrd.add(panelRdBtnOrders);
	    panelBottomRightBotOrd.add(Box.createRigidArea(new Dimension(10,0)));	    
        
        //Build bottom right bottom panel for Sales Invoices(label, search box and radiobuttons)
        panelBottomRightBotInv = new JPanel();
        panelBottomRightBotInv.setBackground(new Color(153,180,209));
        panelBottomRightBotInv.setLayout(new BoxLayout(panelBottomRightBotInv,BoxLayout.LINE_AXIS));
        panelBottomRightBotInv.setPreferredSize(new Dimension(980,50));
        	//Build label and search box
	    	labelFilterInv = new JLabel();
	    	labelFilterInv.setBackground(new Color(166, 76, 156));
	    	labelFilterInv.setFont(new Font("Segoe UI", 0, 14));
	    	labelFilterInv.setForeground(new Color(255, 255, 255));
	        labelFilterInv.setHorizontalAlignment(SwingConstants.CENTER);
	        labelFilterInv.setText("Filter Customer:");
	        labelFilterInv.setOpaque(true);
	        labelFilterInv.setPreferredSize(new Dimension(160, 30));
	        labelFilterInv.setMaximumSize(new Dimension(160, 30));
	        labelFilterInv.setAlignmentY(Component.CENTER_ALIGNMENT);

	    	comboBoxCustInv = new JComboBox<>();
	    	comboBoxCustInv.setPreferredSize(new Dimension(160, 30));
	    	comboBoxCustInv.setMaximumSize(new Dimension(160, 30));
	    	comboBoxCustInv.addItemListener(
	        		//Anonymous Inner Class
	        		new ItemListener() 
	        		{
	        			//Handling comboBoxCustInv item changed Item event
	        			public void itemStateChanged(ItemEvent evt) 
	        			{
	        				// ComboBox Customer ID changed from sales invoice tab, call row filter method
	        		        setInvoiceRowFilter();
	        			}
	        		});//End Item Listener
	    //Add components
		panelBottomRightBotInv.add(Box.createRigidArea(new Dimension(10,0)));
		panelBottomRightBotInv.add(labelFilterInv);
		panelBottomRightBotInv.add(Box.createRigidArea(new Dimension(5,0)));
		panelBottomRightBotInv.add(comboBoxCustInv);
		panelBottomRightBotInv.add(Box.createHorizontalGlue());
		panelBottomRightBotInv.add(panelRdBtnInv);
		panelBottomRightBotInv.add(Box.createRigidArea(new Dimension(10,0)));	
	    
        //Build bottom right panel for Sales Orders
        panelBottomRightOrd = new JPanel(new BorderLayout());
        panelBottomRightOrd.add(panelTableOrd, BorderLayout.CENTER);
        panelBottomRightOrd.add(panelBottomRightBotOrd, BorderLayout.SOUTH);
        
        //Build bottom right panel for Sales Invoices
        panelBottomRightInv = new JPanel(new BorderLayout());
        panelBottomRightInv.add(panelTableInv, BorderLayout.CENTER);
        panelBottomRightInv.add(panelBottomRightBotInv, BorderLayout.SOUTH);
        
        //Build tabbed panel to hold Sales orders and Sales Invoices tabs
      	tabbedPnl = new JTabbedPane();
	    tabbedPnl.setBackground(new Color(255, 255, 255));
	    tabbedPnl.setFont(new Font("Segoe UI", 0, 18));
	    tabbedPnl.addChangeListener(
	    		//Anonymous Inner Class
	    		new ChangeListener() 
	    		{	
	    			//Handling tabbed panel state changed event
	    			public void stateChanged(ChangeEvent evt) 
	            	{
	    			    // When tabbed Panel state changed, get index and set which side menu is shown accordingly
	    		        int panel = tabbedPnl.getSelectedIndex();
	    		        if(panel == 1)
	    		        {   
	    		            //Card label for invoiceSideMenu
	    		            CardLayout card = (CardLayout) panelCards.getLayout();
	    		            card.show(panelCards, "orderSideMenu");
	    		        }
	    		        else
	    		        {
	    		             //Card label for orderSideMenu
	    		            CardLayout card = (CardLayout) panelCards.getLayout();
	    		            card.show(panelCards, "invoiceSideMenu");
	    		        }
	            	}
	    		});//End Change Listener
	    //Add tabs
	    tabbedPnl.addTab("Sales Orders", panelBottomRightOrd);
	    tabbedPnl.addTab("Sales Invoices", panelBottomRightInv);
	    
        //Build main panel(side menu and jtable, main bottom partition)
        panelMain = new JPanel(new BorderLayout());
        panelMain.add(panelCards, BorderLayout.WEST);
        panelMain.add(tabbedPnl, BorderLayout.CENTER);
        
        //Build frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelTop, BorderLayout.NORTH);
		getContentPane().add(panelMain, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventair - Sales Orders/Invoices");
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
     * Method to set OrderRowFilter to filter Sales Order table
     */
    //Method call for Row Filter for Sales Order
    private void setOrderRowFilter()
    {   
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTableOrd.getModel()); 
        boolean booleanvalue = false;
        int custID = 0;
        //If radio button complete is selected set boolean value to false
        if(rBtnUncomplete.isSelected())
        {
            booleanvalue = false;
        }
        //else its true
        else
        {
            booleanvalue = true;
          
        }
        //if comboCust is to all set custID to 0
        if(comboBoxCustOrd.getSelectedIndex() == 0)
        {
            custID = 0;
        }
        else
        {
            //Else cut the customer number off the string value, parse to int and set value to cust id
            custID = Integer.parseInt(comboBoxCustOrd.getSelectedItem().toString().substring(0,comboBoxCustOrd.getSelectedItem().toString().indexOf(':')));
        }
        sorter.setRowFilter(new SalesTableRowFilter(booleanvalue,custID));
        jTableOrd.setRowSorter(sorter);   
        
    }
    /**
     * Method to set InvoiceRowFilter to filter Sales Invoice table
     */
    //Method to set row filter value
    private void setInvoiceRowFilter()
    {   
        //New Table sorter object, set model from JTable2
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTableInv.getModel()); 
        String payDate = "";
        int custID = 0;
        //If radioUnpaid is selccted, set payDate to blank(don't filter)
        if(rBtnUnPaid.isSelected())
        {
            payDate = "Paid";
        }
        //Else filter
        else
        {
            payDate = "None";
        }
        //if comboCustInv(on sales invoice page) is to all set custID to 0
        if(comboBoxCustInv.getSelectedIndex() == 0)
        {
            custID = 0;
        }
        else
        {
            //Else cut the customer number off the string value, parse to int and set value to cust id
            custID = Integer.parseInt(comboBoxCustInv.getSelectedItem().toString().substring(0,1));
        }
        sorter.setRowFilter(new InvoiceTableRowFilter(payDate, custID));
        jTableInv.setRowSorter(sorter);   
        
    }
    /**
     * Method to fill Customer Combo Box with data from database for filter
     */
    //Method to fill combobox Customer filter
    private void fillComboBoxes()
    {   
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        //Create ArrayList for combobox items
        ArrayList<String> comboBoxItems = new ArrayList<String>();
        comboBoxItems.add("All");
        try
        {
            String query = "Select Customer_ID, Customer_Name From Customer";
            rs = con.returnSet(query);
            
            //Fill array list from result set
            while(rs.next())
            {
                int custID = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String fillValue = String.valueOf(custID)+": "+ custName;
                comboBoxItems.add(fillValue);
            }
            //Set Model of combobox and set selected index to 0
            comboBoxCustOrd.setModel(new DefaultComboBoxModel(comboBoxItems.toArray()));
            comboBoxCustOrd.setSelectedIndex(0);
            comboBoxCustInv.setModel(new DefaultComboBoxModel(comboBoxItems.toArray()));
            comboBoxCustInv.setSelectedIndex(0);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "A problem occured filling Customer combobox from database, please contact your network administrator");
        }
        //Finally block to close connection
        finally
        {
            con.closeResultSet(rs);
            con.closeConnection();
        }
    }
    /**
     * SalesTableRowFilter object to filter Sales Order table
     */
    //Row Filter Class for SalesOrder Table
    private class SalesTableRowFilter extends RowFilter
    {   
        //Instance variables
        private boolean booleanvalue;
        private int customer;
        
        //Constructor, two parameters boolean for radiobutton value and int for selected customer from combobox
        SalesTableRowFilter(boolean booleanvalue, int customer)
        {
            this.booleanvalue = booleanvalue;
            this.customer = customer;
        }
        @Override
        public boolean include(Entry entry) 
        {
            //If combobox set to All, just filter on complete
            if(customer == 0)
            {
                return (entry.getValue(6).equals(booleanvalue));
            }
            //Else filter on complete and customer ID
            else
            {
                return (entry.getValue(6).equals(booleanvalue) && (entry.getValue(1).equals(customer)));
            }
            
        
        }
        
    }
    /**
     * InvoiceRowFilter object to filter Sales Invoice table
     */
    //Row Filter Class for SalesInvoice Table
    private class InvoiceTableRowFilter extends RowFilter
    {
        //Instance Variables
        private String payDate;
        private int customer;
        
        //Constructor two parameters String for payment date value and int for selecte customer from combobox
        InvoiceTableRowFilter(String payDate, int customer)
        {
            this.payDate = payDate;
            this.customer = customer;
        }
        @Override
        public boolean include(RowFilter.Entry entry) 
        {	
        	//If combobox set to All, check if paid is filtered
            if(customer == 0)
            {   
                if(payDate.equals("None"))
                {   
                     return !(entry.getValue(7).toString().contains("None"));
                }
                else
                {   
                    return (entry.getValue(7).toString().contains("None"));
                }
            }
            else
            {
                if(payDate == "None")
                {
                     return (!(entry.getValue(7).toString().contains("None")) && (entry.getValue(1).equals(customer)));
                }
                else
                {
                    return ((entry.getValue(7).toString().contains("None")) && (entry.getValue(1).equals(customer)));
                }
               
            }
               
        }
        
    }

}//end class
