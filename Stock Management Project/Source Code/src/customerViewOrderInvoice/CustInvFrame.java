//Student Name 	    : Shane Kennedy
//StudentId Number  : C00263504
//Date 	            : March 2022
//Purpose 	    : Customer Transaction History Frame for allow Customer to view their orders and invoices
package customerViewOrderInvoice;

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
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.RowFilter.Entry;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXImagePanel;

import customGuiComponents.AutoColumnWidthTable;
import customerOrderPortal.CustPortalFrame;
import databaseConnection.ConnectDB;
import orderInvoiceManagement.Order;
import orderInvoiceManagement.OrderItem;

/**
 * Customer Facing GUI Frame for viewing Sales Orders/Invoices
 * @author Shane Kennedy
 */
public class CustInvFrame extends JFrame
{	
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
    private JPanel panelRdBtnInv;
    private JPanel panelCards;
    private JPanel panelBottomRightInv;
    private JPanel panelTableInv;
    private JPanel panelBottomRightBotInv;
    private JPanel panelSideMenuInv;
    
    private JTabbedPane tabbedPnl;
    
	private JXImagePanel imageJxPanelOrders;
	private JXImagePanel imageJxPanelInvoices;
	
	private JLabel labelLogo;
	private JLabel labelTitle;
	private JLabel labelUser;
	private JLabel labelDateTime;
    private JLabel lblInvoiceSearchValue;
    private JLabel lblOrderSearchValue;
	
    private JTextField tFldInvoiceSearchValue;
    private JTextField tFldOrderSearchValue;
    
    private JButton btnLogoff;
    private JButton btnExit;
    private JButton btnMakeOrder;
    private JButton btnViewOrder;
	private JButton btnViewInv;
        
    private AutoColumnWidthTable jTableOrd;
    private AutoColumnWidthTable jTableInv;
    
    private JScrollPane tableScrollPaneOrd;
    private JScrollPane tableScrollPaneInv;

	private JRadioButton rBtnPaid;
    private JRadioButton rBtnUnPaid;
        
    private ButtonGroup btnGrpPaid;
    
    //Create reference for JFrame window
  	CustInvFrame frame = this;
  	
    //Instance variable for username
    private String globalUsername;
    //Global instance variable for setting customer ID in refreshTable2Data method
    private int globalCustID;
  	
    /**
     * Constructor for StaffInvFrame object
     * @param username - Takes a String parameter for Username of Staff Member logged in
     */
	public CustInvFrame(String username)
	{
		//Method to build frame
		buildFrame();
		//Start timer for clock
        timerStart();
        //Set text of label and store username in global variable
        labelUser.setText("User: "+username);
        globalUsername = username;
        //Call methods to fill both tables with data
        refreshTableData();
        refreshTable2Data();
        //Call the row filter method for sales invoice tables
        setInvoiceRowFilter();
        //Create listener object and add to both Filter search text fields
        TextFieldChanged change = new TextFieldChanged();
        tFldInvoiceSearchValue.getDocument().addDocumentListener(change);
        tFldOrderSearchValue.getDocument().addDocumentListener(change);
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
    //Method to fill Sales Order table with data
    public void refreshTableData()
    {   
        //ArrayList to hold Order Items
        ArrayList<OrderItem> items = new  ArrayList<>();
        //Create connection objects
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        ResultSet res = null;
        
        try
        {   //Create table model and set its row count to 0, for when called a second time it will clear the table before refilling
            DefaultTableModel tblModel = (DefaultTableModel) jTableOrd.getModel();
            tblModel.setRowCount(0);
            //Create query to retrieve sales order and customer data
            String query = "Select Order_ID, Order_Date, SalesOrder.Customer_ID FROM SalesOrder Inner Join Customer On SalesOrder.Customer_ID =Customer.Customer_ID Where Customer.Customer_Username = ? and Complete=0";
            rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(globalUsername)));
            //Create an sql date for formatting date objects to sql formate
            java.sql.Date ordDate = new java.sql.Date(0);
            int custID = 0;
            while(rs.next())
            {  
                //Clear items
                items.clear();
                //Retrieve and format dates from database
                int orderID = rs.getInt("Order_ID");
                ordDate = rs.getDate("Order_Date");
                Date ord = new Date(ordDate.getTime());
                
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String date = df.format(ord);
                custID = rs.getInt("Customer_ID");
                //Set customer ID instance variable
                globalCustID = custID;
                //Get items on order
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
                //Calculate totals and fill data into table
                Order order = new Order(ordDate,custID, items);
                double grossTotal = order.calculateGrossTotal();
                double netTotal = order.calculateNetTotal();
                double VAT = netTotal - grossTotal;
                Object[] tbData = {orderID, date, grossTotal, VAT,netTotal};
                tblModel = (DefaultTableModel)jTableOrd.getModel();
                tblModel.addRow(tbData);

            }
        }
        catch(SQLException e)
        {   
           
            JOptionPane.showMessageDialog(null, "An Error retrieving Sales Order details, "
                                + "please contact your network administrator.", 
                                "Sales Order Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection objects, exceptions handled by ConnectDB
            con.closeResultSet(rs);
            con.closeResultSet(res);
            con.closeConnection();
        }
    }
    /**
     * Method to fill the Sales Invoice table with data
     */
    //Method to fill Sales Invoice table with data
    public void refreshTable2Data()
    {   
        //Create ArrayList to hold invoice items
        ArrayList<OrderItem> items = new  ArrayList<>();
        //Create connection objects
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        ResultSet res = null;
        
        try
        {   
            //Create a table model and set row count to 0
            DefaultTableModel tblModel = (DefaultTableModel) jTableInv.getModel();
            tblModel.setRowCount(0);
            //Prepare query inner joining Sales Order and Sales Invoice 
            String query = "Select Invoice_ID, SalesInvoice.Order_ID, Invoice_Date, Shipping_Date, Delivery_Date, Payment_Date, SalesOrder.Customer_ID FROM SalesInvoice Inner Join SalesOrder On SalesInvoice.Order_ID =SalesOrder.Order_ID Where SalesOrder.Customer_ID = ?";
            //Save result in result set rs, filter result for Customer ID of logged in Customer
            rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(globalCustID)));
           
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
                Order order = new Order(invDate,custID, items);
                double netTotal = order.calculateNetTotal();
                
                //Add row tdData and add to table model
                Object[] tbData = {invoiceID,orderID,invDString,shipDString,delivDString,netTotal,payDString};
                tblModel = (DefaultTableModel)jTableInv.getModel();
                tblModel.addRow(tbData);
                //Call fireTableDataChanged method to update table if changes are made
                tblModel.fireTableDataChanged();
            }
                  
           
        }
        catch(SQLException e)
        {   
           JOptionPane.showMessageDialog(null, "An error has occured filling Sales Invoice Table, "
                                + "please contact your network administrator.", 
                                "Sales Invoice Table Fill error", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection objects
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
			labelTitle.setText("Ventair - Customer Transaction History");
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
	    	            //LoginFrame loginFrame = new LoginFrame();
	    	            //loginFrame.setVisible(true);
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
		//Add Labels and buttons
        panelTopRightBottom.add(Box.createRigidArea(new Dimension(10,0)));
		panelTopRightBottom.add(panelUserTime);
		panelTopRightBottom.add(Box.createHorizontalGlue());
		panelTopRightBottom.add(Box.createRigidArea(new Dimension(210,0)));
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
        
		//Build side menu panel for Sales Order tab
        panelSideMenuOrd = new JPanel();
        panelSideMenuOrd.setLayout(new BoxLayout(panelSideMenuOrd,BoxLayout.PAGE_AXIS));
        panelSideMenuOrd.setPreferredSize(new Dimension(200,120));
        panelSideMenuOrd.setBackground(new Color(68, 62, 90));
        panelSideMenuOrd.setAlignmentX(Component.CENTER_ALIGNMENT);
        	//Build buttons
			//View Order button link
	        btnViewOrder = new javax.swing.JButton();
	        btnViewOrder.setBackground(new Color(166, 76, 156));
	        btnViewOrder.setFont(new Font("Segoe UI", 0, 14));
	        btnViewOrder.setForeground(new Color(255, 255, 255));
	        btnViewOrder.setPreferredSize(new Dimension(180,40));
	        btnViewOrder.setMaximumSize(new Dimension(180,40));
	        btnViewOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnViewOrder.setText("View Order");
	        btnViewOrder.setBorderPainted(false);
	        btnViewOrder.setFocusable(false);
	        btnViewOrder.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnViewOrd menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	// View Order Btn
	        		                //Check if row selected, if not pop dialog box
	        		                if(jTableOrd.getSelectedRow() == -1)
	        		                {
	        		                   JOptionPane.showMessageDialog(btnViewInv, "Please select an order from table");
	        		                }
	        		                else
	        		                {
	        		                    int orderID = Integer.parseInt(String.valueOf(jTableOrd.getValueAt(jTableOrd.getSelectedRow(), 0)));
	        		                    CustViewOrdFrame frm = new CustViewOrdFrame(orderID);
	        		                    frm.setVisible(true);
	        		                }
	        		            }
	        				}
	        				);//End Action Listener
	        //Make new order button link
	        btnMakeOrder = new JButton();
	        btnMakeOrder.setBackground(new Color(166, 76, 156));
	        btnMakeOrder.setFont(new Font("Segoe UI", 0, 14));
	        btnMakeOrder.setForeground(new Color(255, 255, 255));
	        btnMakeOrder.setPreferredSize(new Dimension(180,40));
	        btnMakeOrder.setMaximumSize(new Dimension(180,40));
	        btnMakeOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnMakeOrder.setText("Make New Order");
	        btnMakeOrder.setBorderPainted(false);
	        btnMakeOrder.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnMakeOrder menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Back to CustPortFrame
			                CustPortalFrame frm = new CustPortalFrame(globalUsername);
			                frm.setVisible(true);
			                frame.dispose();
			            }
					}
					);//End Action Listener
        //Add buttons to button container panel
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,15)));
        panelSideMenuOrd.add(btnViewOrder);
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenuOrd.add(btnMakeOrder);
        panelSideMenuOrd.add(Box.createRigidArea(new Dimension(0,15)));
         
		//Build side menu panel for Sales Invoice
        panelSideMenuInv = new JPanel();
        panelSideMenuInv.setLayout(new BoxLayout(panelSideMenuInv,BoxLayout.PAGE_AXIS));
        panelSideMenuInv.setPreferredSize(new Dimension(200,70));
        panelSideMenuInv.setBackground(new Color(68, 62, 90));
        panelSideMenuInv.setAlignmentX(Component.CENTER_ALIGNMENT);
	        //Build buttons
			//Create view Invoice button
	        btnViewInv = new javax.swing.JButton();
	        btnViewInv.setBackground(new Color(166, 76, 156));
	        btnViewInv.setFont(new Font("Segoe UI", 0, 14));
	        btnViewInv.setForeground(new Color(255, 255, 255));
	        btnViewInv.setPreferredSize(new Dimension(180,40));
	        btnViewInv.setMaximumSize(new Dimension(180,40));
	        btnViewInv.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnViewInv.setText("View Invoice");
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
	        		                   CustViewInvFrame frame = new CustViewInvFrame(invoiceID);
	        		                   frame.setVisible(true);
	        		               }
	        		            }
	        				}
	        				);//End Action Listener
        //Add buttons to button container panel
        panelSideMenuInv.add(Box.createRigidArea(new Dimension(0,15)));
        panelSideMenuInv.add(btnViewInv);
        panelSideMenuInv.add(Box.createRigidArea(new Dimension(0,15)));
        
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
	                		"Order ID", "Order Date", "Price Excluding VAT", "Total VAT", "Net Price"
	                }
	            ) 
            	{	
            		//Column classes
	                Class[] types = new Class [] {
	                		java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
	                };
	                boolean[] canEdit = new boolean [] {
	                	false, false, false, false, false
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
        
        //Build jtable panel for Sales Invoices tab
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
	                		"Invoice ID", "Order ID", "Invoice Date", "Shipping Date", "Expected Delivery", "Net Price", "Payment Date"
	                }
	            ) 
            	{	
            		//Column classes
	                Class[] types = new Class [] {
	                		java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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
        	jTableInv.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
        	jTableInv.setRowHeight(26);//Row height
        	jTableInv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
            tableScrollPaneInv = new JScrollPane();
            tableScrollPaneInv.setViewportView(jTableInv);
        //Add Component
        panelTableInv.add(tableScrollPaneInv);

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
	    	lblOrderSearchValue = new JLabel();
	    	lblOrderSearchValue.setBackground(new Color(166, 76, 156));
	    	lblOrderSearchValue.setFont(new Font("Segoe UI", 0, 14));
	    	lblOrderSearchValue.setForeground(new Color(255, 255, 255));
	    	lblOrderSearchValue.setHorizontalAlignment(SwingConstants.CENTER);
	    	lblOrderSearchValue.setText("Search by Order ID:");
	    	lblOrderSearchValue.setOpaque(true);
	    	lblOrderSearchValue.setPreferredSize(new Dimension(200, 30));
	    	lblOrderSearchValue.setMaximumSize(new Dimension(200, 30));
	    	lblOrderSearchValue.setAlignmentY(Component.CENTER_ALIGNMENT);
	
	    	tFldOrderSearchValue = new JTextField();
	    	tFldOrderSearchValue.setPreferredSize(new Dimension(250, 30));
	    	tFldOrderSearchValue.setMaximumSize(new Dimension(250, 30));
	    	tFldOrderSearchValue.setAlignmentY(Component.CENTER_ALIGNMENT);
	        //Create listener object and add to Filter search text field
	        TextFieldChanged change = new TextFieldChanged();
	        tFldOrderSearchValue.getDocument().addDocumentListener(change);
	    //Add components
		panelBottomRightBotOrd.add(Box.createRigidArea(new Dimension(10,0)));
	    panelBottomRightBotOrd.add(lblOrderSearchValue);
	    panelBottomRightBotOrd.add(Box.createRigidArea(new Dimension(5,0)));
	    panelBottomRightBotOrd.add(tFldOrderSearchValue);
        
        //Build bottom right bottom panel for Sales Invoices(label, search box and radiobuttons)
        panelBottomRightBotInv = new JPanel();
        panelBottomRightBotInv.setBackground(new Color(153,180,209));
        panelBottomRightBotInv.setLayout(new BoxLayout(panelBottomRightBotInv,BoxLayout.LINE_AXIS));
        panelBottomRightBotInv.setPreferredSize(new Dimension(980,50));
      		//Build label,search box and radiobuttons
	    	lblInvoiceSearchValue = new JLabel();
	    	lblInvoiceSearchValue.setBackground(new Color(166, 76, 156));
	    	lblInvoiceSearchValue.setFont(new Font("Segoe UI", 0, 14));
	    	lblInvoiceSearchValue.setForeground(new Color(255, 255, 255));
	    	lblInvoiceSearchValue.setHorizontalAlignment(SwingConstants.CENTER);
	    	lblInvoiceSearchValue.setText("Search by Invoice ID:");
	    	lblInvoiceSearchValue.setOpaque(true);
	    	lblInvoiceSearchValue.setPreferredSize(new Dimension(200, 30));
	    	lblInvoiceSearchValue.setMaximumSize(new Dimension(200, 30));
	    	lblInvoiceSearchValue.setAlignmentY(Component.CENTER_ALIGNMENT);
	
	        tFldInvoiceSearchValue = new JTextField();
	        tFldInvoiceSearchValue.setPreferredSize(new Dimension(250, 30));
	        tFldInvoiceSearchValue.setMaximumSize(new Dimension(250, 30));
	        tFldInvoiceSearchValue.setAlignmentY(Component.CENTER_ALIGNMENT);
	        //Create listener object and add to Filter search text field
	        tFldInvoiceSearchValue.getDocument().addDocumentListener(change);
	    //Add components
		panelBottomRightBotInv.add(Box.createRigidArea(new Dimension(10,0)));
		panelBottomRightBotInv.add(lblInvoiceSearchValue);
		panelBottomRightBotInv.add(Box.createRigidArea(new Dimension(5,0)));
		panelBottomRightBotInv.add(tFldInvoiceSearchValue);
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
        setTitle("Ventair - Customer Transaction History");
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
     * Text Field Changed Listener to set row filter based on Text types in search field
     */
    //Inner class for TextField Change Listener
    private class TextFieldChanged implements DocumentListener
    {   
        //Create table Sorter and set jTableOrds model as model
        TableRowSorter<DefaultTableModel> orderSorter = new TableRowSorter(jTableOrd.getModel()); 
        String orderSearchValue = "";
        TableRowSorter<DefaultTableModel> invoiceSorter = new TableRowSorter(jTableInv.getModel());                
        String invoiceSearchValue = "";
        String showPaid = "";
        
        @Override
        //Method called when textfield is typed in
        public void insertUpdate(DocumentEvent e) 
        {   
            //Get value of radiobuttons paid/unpaid
            //If radioUnpaid is selccted, set payDate to blank(don't filter)
            if(rBtnUnPaid.isSelected())
            {
                showPaid = "";
            }
            //Else filter
            else
            {
                showPaid = "None";
            }            
            //Get search text and then use value to call row filter        
            orderSearchValue = tFldOrderSearchValue.getText();
            invoiceSearchValue = tFldInvoiceSearchValue.getText();
            orderSorter.setRowFilter(new OrderRowFilter(orderSearchValue));
            jTableOrd.setRowSorter(orderSorter); 
            invoiceSorter.setRowFilter(new InvoiceRowFilter(showPaid,invoiceSearchValue));
            jTableInv.setRowSorter(invoiceSorter); 
        }

        @Override
        //Method called when textfield is cleared
        public void removeUpdate(DocumentEvent e) 
        {   
            //Get value of radiobuttons paid/unpaid
            //If radioUnpaid is selccted, set payDate to blank(don't filter)
            if(rBtnUnPaid.isSelected())
            {
                showPaid = "";
            }
            //Else filter
            else
            {
                showPaid = "None";
            }  
            //Get search text and then use value to call row filter  
            orderSearchValue = tFldOrderSearchValue.getText();
            invoiceSearchValue = tFldInvoiceSearchValue.getText();
            orderSorter.setRowFilter(new OrderRowFilter(orderSearchValue));
            jTableOrd.setRowSorter(orderSorter); 
            invoiceSorter.setRowFilter(new InvoiceRowFilter(showPaid,invoiceSearchValue));
            jTableInv.setRowSorter(invoiceSorter);
        }

        @Override
        public void changedUpdate(DocumentEvent e) 
        {
          
        }
        
    }
    /**
     * Method which sets Invoice Row Filter based on status of paid radiobuttons and text in search field
     */
    //Method to set row filter value
    private void setInvoiceRowFilter()
    {   
        //New Table sorter object, set model from jTableInv
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTableInv.getModel()); 
        String payDate = "";
        String invoiceSearch = "";
        //If radioUnpaid is selccted, set payDate to blank(don't filter)
        if(rBtnUnPaid.isSelected())
        {
            payDate = "None";
        }
        //Else filter
        else
        {
            payDate = "";
        }
        invoiceSearch = tFldInvoiceSearchValue.getText();
        sorter.setRowFilter(new InvoiceRowFilter(payDate, invoiceSearch));
        jTableInv.setRowSorter(sorter);   
        
    }
    /**
     * OrderRowFilter object to filter Sales Order table
     */
    //Row Filter Class for Inventory Table
    private class OrderRowFilter extends RowFilter
    {
        //Instance variable
        private String searchValue;
        //Constructor
        OrderRowFilter(String searchValue)
        {
            this.searchValue = searchValue;
        }
        @Override
        //Include these values if returns true
        public boolean include(RowFilter.Entry entry) 
        {
            //Convert entry to string and compare to search value, indexOf returns greater than 0 if text is contained in entry
            return (entry.getStringValue(0).indexOf(searchValue) >= 0);
        
        }
        
    }
    /**
     * InvoiceRowFilter object to filter Sales Invoice table
     */
    //Row Filter Class for Invoice Table
    private class InvoiceRowFilter extends RowFilter
    {   
        //Instance variables
        private String payDate;
        private String searchValue;
        
        //Constructor, two parameters boolean for radiobutton value and String for searched invoice id from searchbox
        InvoiceRowFilter(String payDate, String searchValue)
        {
            this.payDate = payDate;
            this.searchValue = searchValue;
        }
        @Override
        public boolean include(Entry entry) 
        {   
            //If unpaid selected paydate will be none
            if(payDate.equals("None"))
            {
                return (entry.getValue(6).equals("None") && (entry.getStringValue(0).indexOf(searchValue) >= 0));
            }
            else
            {
                return (!(entry.getValue(6).equals("None")) && (entry.getStringValue(0).indexOf(searchValue) >= 0));
            }
        }
    }

}//end class
