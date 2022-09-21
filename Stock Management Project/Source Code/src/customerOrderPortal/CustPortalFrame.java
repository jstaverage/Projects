//Student Name 	    : Shane Kennedy
//StudentId Number  : C00263504
//Date 	            : March 2022
//Purpose 	    : Customer Portal Frame for allowing Customer to place Sales Orders
package customerOrderPortal;

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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import customGuiComponents.AutoColumnWidthTable;
import customerViewOrderInvoice.CustInvFrame;
import customerViewOrderInvoice.InvoiceFrame;
import databaseConnection.ConnectDB;

/**
 * Customer facing GUI Frame allowing customers to create Sales Orders
 * @author Shane Kennedy
 */
public class CustPortalFrame extends JFrame
{	
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
    
    private JButton btnLogoff;
    private JButton btnExit;
    private JButton btnViewBasket;
    private JButton btnHistory;
    private JButton btnCheckout;
    private JButton btnAddBasket;
    
    private AutoColumnWidthTable jTable1;
    
    private JScrollPane tableScrollPane;
    
    private JTextField textFieldSearchName;
    
    //Create reference for JFrame window
  	CustPortalFrame frame = this;
  	
    //Instance Variabls
    private static int itemsBasket = 0;   //For number of items in basket for basket label
    private String globalUsername;       //Username for lable
    private Basket custBasketObj;      //Customer basket object
  	
    /**
     * Constructor for Customer Frame
     * @param username - String representing Staff Username of staff member logged in
     */
	public CustPortalFrame(String username)
	{
		super("Menu");
		//Method to build frame
		buildFrame();
		//Start timer
        timerStart();
		//Call method to fill table
        refreshTableData();
		//Set user label text and save user name parameter to globalUsername
	    labelUser.setText("User: "+username);
	    globalUsername = username;
	    //Create basket object to store customer order
        custBasketObj = new Basket(username);
        //Window closing listener to update table and delete basket from databse
        WindowCloser winClose = new WindowCloser();
        this.addWindowListener(winClose);
        //Call method to create basket table in database       
        //createBasket();
        //Maximise window on opening
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
	}//end constructor
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
     * Method to fill the Inventory Table with data from database
     */
    //Method to fill Inventory table with data   
    public void refreshTableData()
    {   
        //Connection objects for querying database
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        
        try
        {   //Create a table model and set row count to 0
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.setRowCount(0);
            //Create query and execute
            String query = "Select Product_ID, Product_Name, Product_Description, "
                          + "Retail_Price FROM Inventory";
           
            rs = con.returnSet(query);
           
            //Get data from resultset
            while(rs.next())
            {  
                String productID = String.valueOf(rs.getInt("Product_ID"));
                String productName = rs.getString("Product_Name");
                String productDesc = rs.getString("Product_Description");
                String retail = String.valueOf(rs.getDouble("Retail_Price"));
                Object[] tbData = {productID, productName, productDesc,retail};
                tblModel = (DefaultTableModel)jTable1.getModel();
                
                tblModel.addRow(tbData);
            }
           
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "An error has occured querying Inventory database, "
                            + "please contact your network administrator.", 
                            "Error querying database", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
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
			labelTitle.setText("Ventair - New Orders");
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
			//Build View Basket button link
	        btnViewBasket = new JButton();
	        btnViewBasket.setBackground(new Color(166, 76, 156));
	        btnViewBasket.setFont(new Font("Segoe UI", 0, 14));
	        btnViewBasket.setForeground(new Color(255, 255, 255));
	        btnViewBasket.setPreferredSize(new Dimension(180,40));
	        btnViewBasket.setMaximumSize(new Dimension(180,40));
	        btnViewBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnViewBasket.setText("View Basket");
	        btnViewBasket.setBorderPainted(false);
	        btnViewBasket.setFocusable(false);
	        btnViewBasket.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnViewBasket menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	// View Basket Button opens Basket Window
	        		                //Check if row selected, if not pop dialog box
	        		                if(custBasketObj.noOfItems() == 0)
	        		                {
	        		                   JOptionPane.showMessageDialog(btnViewBasket, "Please add some items to Basket");
	        		                }
	        		                else
	        		                {
	        		                    //Add reference to parent frame for calling methods
	        		                    CustPortalFrame parent = frame;
	        		                    BasketFrame frm = new BasketFrame(custBasketObj, parent);
	        		                    frm.setVisible(true);
	        		                }
	        		            }
	        				}
	        				);//End Action Listener
	
	        //Build View Transaction History button link
	        btnHistory = new JButton();
	        btnHistory.setBackground(new Color(166, 76, 156));
	        btnHistory.setFont(new Font("Segoe UI", 0, 14));
	        btnHistory.setForeground(new Color(255, 255, 255));
	        btnHistory.setPreferredSize(new Dimension(180,40));
	        btnHistory.setMaximumSize(new Dimension(180,40));
	        btnHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnHistory.setText("View Transactions");
	        btnHistory.setBorderPainted(false);
	        btnHistory.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnHistory menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// View Transaction history button
			                //Check if there are items in basket, if there is warn user basket will be deleted
			                if(custBasketObj.noOfItems() > 0)
			                {
			                    int confirm = 0;
			                    confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to view transaction history, items in basket will be deleted?");
			                    if(confirm == 0)
			                    {   
			                        CustInvFrame frm = new CustInvFrame(globalUsername);
			                        frm.setVisible(true);
			                        frame.dispose();
			                    }
			                }
			                //else just close window and open customer transaction history
			                else
			                {
			                    CustInvFrame frm = new CustInvFrame(globalUsername);
			                    frm.setVisible(true);
			                    frame.dispose();
			                }
			            }
					}
					);//End Action Listener
        //Add buttons to button container panel
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,30)));
        panelSideMenu.add(btnViewBasket);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnHistory);
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
        	jTable1 = new AutoColumnWidthTable();
        	jTable1.setFont(new Font("Segoe UI", 0, 14));
        	jTable1.setModel(
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
            jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
            jTable1.setRowHeight(26);//Row height
            jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
            tableScrollPane = new JScrollPane();
            tableScrollPane.setViewportView(jTable1);
        //Add Component
        panelTable.add(tableScrollPane);
        	
        //Build bottom right bottom panel(label and search box)
        panelBottomRightBot = new JPanel();
        panelBottomRightBot.setBackground(new Color(153,180,209));
        panelBottomRightBot.setLayout(new BoxLayout(panelBottomRightBot,BoxLayout.LINE_AXIS));
        panelBottomRightBot.setPreferredSize(new Dimension(980,50));
        	//Build label,search box and buttons
	    	labelSearchName = new JLabel();
	        labelSearchName.setBackground(new Color(166, 76, 156));
	        labelSearchName.setFont(new Font("Segoe UI", 0, 14));
	        labelSearchName.setForeground(new Color(255, 255, 255));
	        labelSearchName.setHorizontalAlignment(SwingConstants.CENTER);
	        labelSearchName.setText("Search by Product Name:");
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
	        
	        //Build add to basket button
	        btnAddBasket = new javax.swing.JButton();
	        btnAddBasket.setBackground(new Color(166, 76, 156));
	        btnAddBasket.setFont(new Font("Segoe UI", 0, 14));
	        btnAddBasket.setForeground(new Color(255, 255, 255));
	        btnAddBasket.setPreferredSize(new Dimension(140,30));
	        btnAddBasket.setMaximumSize(new Dimension(140,30));
	        btnAddBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnAddBasket.setText("Add to Basket");
	        btnAddBasket.setBorderPainted(false);
	        btnAddBasket.setFocusable(false);
	        btnAddBasket.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnAddBasket menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	// Add to basket button event
	        		                //Check if row selected, if not pop dialog box
	        		               if(jTable1.getSelectedRow() == -1)
	        		               {
	        		                  JOptionPane.showMessageDialog(btnAddBasket, "Please select a product from table");
	        		               }
	        		               else
	        		               {   
	        		                   //Ask user to input quantity
	        		                   String input = JOptionPane.showInputDialog("Please select a quantity to add to basket");
	        		                   //If user does not press cancel
	        		                   if(input!=null)
	        		                   {
	        		                       //Try block to catch non integer values
	        		                       try
	        		                       {
	        		                           //Parse quantity to int
	        		                           int quantity = Integer.parseInt(input);
	        		                           //Get item id from table
	        		                           int itemID = Integer.parseInt(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0))); 
	        		                           //If basket contains item add quantity
	        		                           if(custBasketObj.getItemArray().contains(itemID))
	        		                           {
	        		                               int index = custBasketObj.getItemArray().indexOf(itemID);
	        		                               int oldQuantity = custBasketObj.getQuantityArray().get(index);
	        		                               custBasketObj.getQuantityArray().set(index, (oldQuantity+quantity));
	        		                               custBasketObj.changeItemQuantity(itemID, (oldQuantity+quantity));
	        		                           }
	        		                           //otherwise add item
	        		                           else
	        		                           {
	        		                              custBasketObj.addItem(itemID, quantity);
	        		                           }
	        		                           try
	        		                           {   
	        		                               //If i = 1, adding succeeded
	        		                               int i = custBasketObj.addItemBasketTable(itemID);
	        		                               if(i == 1)
	        		                               {
	        		                                   JOptionPane.showMessageDialog(frame, "Item added to Basket");
	        		                               }
	        		                               //Set the text on the basket button to show item added
	        		                               btnViewBasket.setText("View Basket ("+custBasketObj.noOfItems() + " Items)");

	        		                           }
	        		                           catch(Exception e)
	        		                           {
	        		                               JOptionPane.showMessageDialog(frame, "An unknown error occured adding item to basket, please try again");
	        		                           }
	        		                           
	        		                       }
	        		                       //Exception if user didn't enter integer quantity
	        		                       catch(Exception NumberFormatException)
	        		                       {   
	        		                           JOptionPane.showMessageDialog(btnAddBasket, "Please enter an integer quantity");
	        		                       }
	        		                   }
	        		               }
	        		            }
	        				}
	        				);//End Action Listener
	
	        //Build checkout button link
	        btnCheckout = new JButton();
	        btnCheckout.setBackground(new Color(166, 76, 156));
	        btnCheckout.setFont(new Font("Segoe UI", 0, 14));
	        btnCheckout.setForeground(new Color(255, 255, 255));
	        btnCheckout.setPreferredSize(new Dimension(140,30));
	        btnCheckout.setMaximumSize(new Dimension(140,30));
	        btnCheckout.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnCheckout.setText("Checkout");
	        btnCheckout.setBorderPainted(false);
	        btnCheckout.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnCheckout pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	 //Check if there are items in basket before going to checkout
			                if(custBasketObj.noOfItems()!=0)
			                {
			                    // Checkout Btn
			                    InvoiceFrame frm = new InvoiceFrame(custBasketObj);
			                    frm.setVisible(true);
			                }
			                else
			                {
			                    JOptionPane.showMessageDialog(frame, "Please add some items to basket before going to Checkout");
			                }
			            }
					}
					);//End Action Listener
	    //Add components
	    panelBottomRightBot.add(Box.createRigidArea(new Dimension(10,0)));
	    panelBottomRightBot.add(labelSearchName);
	    panelBottomRightBot.add(Box.createRigidArea(new Dimension(10,0)));
	    panelBottomRightBot.add(textFieldSearchName);
	    panelBottomRightBot.add(Box.createRigidArea(new Dimension(250,0)));
	    panelBottomRightBot.add(Box.createHorizontalGlue());
	    panelBottomRightBot.add(btnAddBasket);
	    panelBottomRightBot.add(Box.createRigidArea(new Dimension(10,0)));	
	    panelBottomRightBot.add(btnCheckout);
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
        setTitle("Ventair - Customer New Orders");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
		setSize(1200, 680);
		setMinimumSize(new Dimension(1200, 680));
		pack();
	}//end buildFrame method
	
	/**
     * Method to start the timer
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
     * Method to update label of Basket button, set to public to be accessed from outside frame
     */
    //Public Method to update Basket label when item no changed
    public void updateLabel()
    {   
        //if no of items 0, just set text to view basket
        if(custBasketObj.noOfItems()==0)
        {
            btnViewBasket.setText("View Basket");
        }
        //Otherwise add number of items afterwards
        else
        {
            btnViewBasket.setText("View Basket ("+custBasketObj.noOfItems() + " Items)");
        }
    }
    /**
     * Method which creates a Basket table for user in database
     */  
    //Method to create basket for user in database
    private void createBasket()
    {
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        String query = "";
             
        try
        {   
            //Check if basket already exists, if it does delete it
            DatabaseMetaData dbm = con.getConnection().getMetaData();
            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, "Basket"+labelUser.getText().substring(6), null);
            //If table exits
            if (tables.next()) 
            {
                deleteBasket();
            }
            //Create new basket table in database
            query = "CREATE TABLE Basket"+labelUser.getText().substring(6) +" (Item_ID int(10), Quantity int(10))";
            con.prepareStatement(query, new ArrayList<Object>());
        }
        catch(Exception e)
        {   
            JOptionPane.showMessageDialog(this, "An error occured creating customer basket, please try again or contact your administrator");
        }
        finally 
        {
                 try 
                 {    con.closeResultSet(rs);
                      con.closeConnection();
                 }
                 catch(Exception e)
                 {
                     //Exception handled by ConnectDB class
                 }
         }
         
        
    }
    /**
     * Method which deletes basket table for Customer from database
     */
    //Method to delete basket table in database
    private void deleteBasket()
    {
        
        ConnectDB con = new ConnectDB();
        try
        {  
            String query = "DROP TABLE Basket"+labelUser.getText().substring(6);
            con.prepareStatement(query, new ArrayList<Object>());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "An error occured deleting customer basket, please try again or contact your administrator");
        }
        finally 
        {
                 try 
                 {
                      con.closeConnection();
                 }
                 catch(Exception e)
                 {
                     //Exception handled by ConnectDB
                    
                 }
         }
    }
    /**
     * Window Closer listener to listen for window closing events to delete basket table when frame closes
     */
    // Listener for window closing events to delete basket table from database when frame is closed
	class WindowCloser implements WindowListener 
        {
        @Override
        public void windowClosing(WindowEvent e) 
        {     
            
            deleteBasket();
        }
        @Override
        public void windowClosed(WindowEvent e) 
        {
            deleteBasket();
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
     * Text Field Changed Listener to set row filter based on Text types in search field
     */
    //Inner class for TextField Change Listener
    private class TextFieldChanged implements DocumentListener
    {   
        //Create table Sorter and set jTable1s model as model
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTable1.getModel()); 
        String searchValue = "";
                        

        @Override
        //Method called when textfield is typed in
        public void insertUpdate(DocumentEvent e) 
        {
            //Get search text and convert to lower case and then use value to call row filter        
            searchValue = textFieldSearchName.getText();
            sorter.setRowFilter(new InventoryRowFilter(searchValue.toLowerCase()));
            jTable1.setRowSorter(sorter); 
        }

        @Override
        //Method called when textfield is cleared
        public void removeUpdate(DocumentEvent e) 
        {
            //Get search text and convert to lower case and then use value to call row filter  
            searchValue = textFieldSearchName.getText();
            sorter.setRowFilter(new InventoryRowFilter(searchValue.toLowerCase()));
            jTable1.setRowSorter(sorter); 
        }

        @Override
        public void changedUpdate(DocumentEvent e) 
        {
          
        }
        
    }
    /**
     * Inventory Row Filter object to filter the Inventory table based on search value
     */
    //Row Filter Class for Inventory Table
    private class InventoryRowFilter extends RowFilter
    {
        //Instance variable
        private String searchValue;
        /**
        * Constructor for inventory row filter object
        * @param -search value for filter
        */
        //Constructor
        InventoryRowFilter(String searchValue)
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
