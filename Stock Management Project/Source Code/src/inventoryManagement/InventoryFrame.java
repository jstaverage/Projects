//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Inventory Frame for displaying and control of Stock Items
package inventoryManagement;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
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
import customerManagement.CustomerFrame;
import databaseConnection.ConnectDB;
import loginAndUsers.LoginFrame;
import mainMenu.MenuFrame;
import orderInvoiceManagement.StaffInvFrame;

/**
 * Inventory GUI frame to allow employees to view Inventory items in database
 * @author Shane Kennedy
 */
public class InventoryFrame extends JFrame
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
    private JButton btnAddProduct;
    private JButton btnEditProduct;
    
    private AutoColumnWidthTable jTableInventory;
    
    private JScrollPane tableScrollPane;
    
    private JTextField textFieldSearchName;
    
    //Create reference for JFrame window
  	InventoryFrame frame = this;
  	
  	/**
     * Constructor for Inventory Frame
     * @param username - String representing Staff Username of staff member logged in
     */
	public InventoryFrame(String username)
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
     * Method to fill the Inventory table with records from the database
     */
    //Method to fill table with data from database
    public void refreshTableData()
    {   
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        
        try
        {   DefaultTableModel tblModel = (DefaultTableModel) jTableInventory.getModel();
            tblModel.setRowCount(0);
            String query = "Select Inventory.Product_ID, Inventory.Product_Name, Inventory.Product_Description, "
                    + "Inventory.Qty_in_Stock, Inventory.Reorder_Level, Inventory.Max_Stock_Level,Inventory.Cost_Price,"
                    + "Inventory.Retail_Price,Inventory.Qty_on_Order,Inventory.Supplier_ID, Supplier.Supplier_Name FROM "
                    + "Inventory INNER JOIN Supplier ON Inventory.Supplier_ID = Supplier.Supplier_ID";
           
            rs = con.returnSet(query);
           
            while(rs.next())
            {  
                String productID = String.valueOf(rs.getInt("Product_ID"));
                String productName = rs.getString("Product_Name");
                String productDesc = rs.getString("Product_Description");
                String qty = String.valueOf(rs.getInt("Qty_in_Stock"));
                String reorder = String.valueOf(rs.getInt("Reorder_Level"));
                String max = String.valueOf(rs.getInt("Max_Stock_Level"));
                double cost = rs.getDouble("Cost_Price");
                String retail = String.valueOf(rs.getDouble("Retail_Price"));
                String ordered = String.valueOf(rs.getInt("Qty_on_Order"));
                String suppID = String.valueOf(rs.getInt("Supplier_ID"));
                String suppName = rs.getString("Supplier_Name");
                
                Object[] tbData = {new Integer(productID), productName, productDesc,new Integer(qty),new Integer(reorder),new Integer(max),
                    currencyFormat.format(cost),new Double(retail),new Integer(ordered),new Integer(suppID),suppName};
                tblModel = (DefaultTableModel)jTableInventory.getModel();
                
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
			labelTitle.setText("Ventair - Inventory");
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
		
		//Build side menu panel
        panelSideMenu = new JPanel();
        panelSideMenu.setLayout(new BoxLayout(panelSideMenu,BoxLayout.PAGE_AXIS));
        panelSideMenu.setPreferredSize(new Dimension(200,150));
        panelSideMenu.setBackground(new Color(68, 62, 90));
        panelSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        	//Build buttons
			//Add Product button link
	        btnAddProduct = new javax.swing.JButton();
	        btnAddProduct.setBackground(new Color(166, 76, 156));
	        btnAddProduct.setFont(new Font("Segoe UI", 0, 14));
	        btnAddProduct.setForeground(new Color(255, 255, 255));
	        btnAddProduct.setPreferredSize(new Dimension(180,40));
	        btnAddProduct.setMaximumSize(new Dimension(180,40));
	        btnAddProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnAddProduct.setText("Add Product");
	        btnAddProduct.setBorderPainted(false);
	        btnAddProduct.setFocusable(false);
	        btnAddProduct.addActionListener(
	                		//Anonymous Inner Class
	        				new ActionListener() 
	        				{
	        					//Handling btnAddProduct menu item pressed action event
	        		            public void actionPerformed(ActionEvent evt) 
	        		            {
	        		            	// Add Button opens Add Product Window
	        		                //Add reference to parent frame for calling refresh table method
	        		                InventoryFrame parent = frame;
	        		                //addProdFrame = new AddProductFrame(parent);
	        		                // addProdFrame.setVisible(true);
	        		            }
	        				}
	        				);//End Action Listener
	
	        //Edit Product button link
	        btnEditProduct = new JButton();
	        btnEditProduct.setBackground(new Color(166, 76, 156));
	        btnEditProduct.setFont(new Font("Segoe UI", 0, 14));
	        btnEditProduct.setForeground(new Color(255, 255, 255));
	        btnEditProduct.setPreferredSize(new Dimension(180,40));
	        btnEditProduct.setMaximumSize(new Dimension(180,40));
	        btnEditProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnEditProduct.setText("Edit/Delete Product");
	        btnEditProduct.setBorderPainted(false);
	        btnEditProduct.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnEditProduct menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Edit Button opens Edit Product Window
			                //Check if row selected, if not pop dialog box
			                if(jTableInventory.getSelectedRow() == -1)
			                {
			                    JOptionPane.showMessageDialog(btnEditProduct, "Please select a product from table");
			                }
			                else
			                {
			                    int prodID = Integer.parseInt(String.valueOf(jTableInventory.getValueAt(jTableInventory.getSelectedRow(), 0)));
			                    InventoryFrame parent = frame;
			                    //EditProductFrame editProdframe = new EditProductFrame(prodID, parent);
			                    //editProdframe.setVisible(true);
			                }
			            }
					}
					);//End Action Listener
        //Add buttons to button container panel
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,30)));
        panelSideMenu.add(btnAddProduct);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnEditProduct);
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
        	jTableInventory = new AutoColumnWidthTable();
        	jTableInventory.setFont(new Font("Segoe UI", 0, 14));
            jTableInventory.setModel(
            	//Default table model
            	new DefaultTableModel
            	(
	                new Object [][] {                },
	                //Column Names
	                new String [] {
	                    "Product ID", "Product Name", "Product Description", "Qty in Stock", "Reorder Level", "Max Stock Level", "Cost Price", "Retail Price", "Qty on Order", "Supplier ID", "Supplier Name"
	                }
	            ) 
            	{	
            		//Column classes
	                Class[] types = new Class [] {
	                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
	                };
	                boolean[] canEdit = new boolean [] {
	                    false, false, false, false, false, false, false, false, false, false, false
	                };
	
	                public Class getColumnClass(int columnIndex) {
	                    return types [columnIndex];
	                }
	
	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            });
            jTableInventory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
            jTableInventory.setRowHeight(26);//Row height
            jTableInventory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
            tableScrollPane = new JScrollPane();
            tableScrollPane.setViewportView(jTableInventory);

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
	        labelSearchName.setText("Search by Item Name:");
	        labelSearchName.setOpaque(true);
	        labelSearchName.setPreferredSize(new Dimension(160, 30));
	        labelSearchName.setMaximumSize(new Dimension(160, 30));
	        labelSearchName.setAlignmentY(Component.CENTER_ALIGNMENT);
	
	        textFieldSearchName = new JTextField();
	        textFieldSearchName.setPreferredSize(new Dimension(250, 30));
	        textFieldSearchName.setMaximumSize(new Dimension(250, 30));
	        textFieldSearchName.setAlignmentY(Component.CENTER_ALIGNMENT);
	        textFieldSearchName.addPropertyChangeListener(
			        //Anonymous Inner Class
					new PropertyChangeListener() 
					{
						//Handling textFieldSearchName textfield property change event
			            public void propertyChange(PropertyChangeEvent evt) 
			            {
			            	// Change Listener for textfield search
			                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTableInventory.getModel());
			                String searchValue = textFieldSearchName.getText();
			                sorter.setRowFilter(new InventoryRowFilter(searchValue));
			                jTableInventory.setRowSorter(sorter);
			            }
					}
					);//End Action Listener
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
        setTitle("Ventair - Inventory");
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
     * Text Field Changed class to listen for changes in search text field and set row filter
     */
    //Inner class for TextField Change Listener
    private class TextFieldChanged implements DocumentListener
    {   
        //Create table Sorter and set jTable1s model as model
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(jTableInventory.getModel()); 
        String searchValue = "";
                        

        @Override
        //Method called when textfield is typed in
        public void insertUpdate(DocumentEvent e) 
        {
            //Get search text and convert to lower case and then use value to call row filter        
            searchValue = textFieldSearchName.getText();
            sorter.setRowFilter(new InventoryRowFilter(searchValue.toLowerCase()));
            jTableInventory.setRowSorter(sorter); 
        }

        @Override
        //Method called when textfield is cleared
        public void removeUpdate(DocumentEvent e) 
        {
            //Get search text and convert to lower case and then use value to call row filter  
            searchValue = textFieldSearchName.getText();
            sorter.setRowFilter(new InventoryRowFilter(searchValue.toLowerCase()));
            jTableInventory.setRowSorter(sorter); 
        }

        @Override
        public void changedUpdate(DocumentEvent e) 
        {
          
        }
        
    }
     /**
     * Inventory filter object for filtering Inventory table, extends Row Filter
     */
    //Row Filter Class for Inventory Table
    private class InventoryRowFilter extends RowFilter
    {
        //Instance variable
        private String searchValue;
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
